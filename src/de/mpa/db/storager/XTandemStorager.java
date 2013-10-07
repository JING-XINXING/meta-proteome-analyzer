package de.mpa.db.storager;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.xml.sax.SAXException;

import com.compomics.util.protein.Header;
import com.compomics.util.protein.Protein;

import de.mpa.client.model.dbsearch.SearchEngineType;
import de.mpa.db.MapContainer;
import de.mpa.db.accessor.Pep2prot;
import de.mpa.db.accessor.PeptideAccessor;
import de.mpa.db.accessor.ProteinAccessor;
import de.mpa.db.accessor.XtandemhitTableAccessor;
import de.proteinms.xtandemparser.xtandem.Domain;
import de.proteinms.xtandemparser.xtandem.Peptide;
import de.proteinms.xtandemparser.xtandem.PeptideMap;
import de.proteinms.xtandemparser.xtandem.ProteinMap;
import de.proteinms.xtandemparser.xtandem.Spectrum;
import de.proteinms.xtandemparser.xtandem.XTandemFile;

/**
 * This class stores X!Tandem results to the DB.
 * @author T.Muth
 */
public class XTandemStorager extends BasicStorager {

    /**
     * Variable holding an xTandemFile.
     */
    private XTandemFile xTandemFile;
    
    /**
     * The q-value file.
     */
    private File qValueFile = null;
    
    private Map<Double, List<Double>> scoreQValueMap;

	private Map<String, Long> domainMap;
	
    /**
     * Constructor for storing results from a target-only search with X!Tandem.
     */
    public XTandemStorager(final Connection conn, final File file){
    	this.conn = conn;
    	this.file = file;
    	this.searchEngineType = SearchEngineType.XTANDEM;
    }
    
    /**
     * Constructor for storing results from a target-decoy search with X!Tandem.
     */
	public XTandemStorager(final Connection conn, final File file, File qValueFile) {
		this.conn = conn;
		this.file = file;
		this.qValueFile = qValueFile;
		this.searchEngineType = SearchEngineType.XTANDEM;
	}

    /**
     * Parses and loads the X!Tandem results file(s).
     */
    public void load() {
        try {
            xTandemFile = new XTandemFile(file.getAbsolutePath());
        } catch (SAXException ex) {
            log.error("Error while parsing X!Tandem file: " + ex.getMessage());
            ex.printStackTrace();
        }
        if(qValueFile != null) this.processQValues();
    }
    
    /**
     * Stores X!Tandem results file contents to the database.
     * @throws IOException
     * @throws SQLException
     */
    public void store() throws IOException, SQLException {
               
        // Iterate over all the spectra
        @SuppressWarnings("unchecked")
		Iterator<de.proteinms.xtandemparser.xtandem.Spectrum> iter = xTandemFile.getSpectraIterator();

        // Prepare everything for the peptides.
        PeptideMap pepMap = xTandemFile.getPeptideMap();
        
        // ProteinMap protMap 
        ProteinMap protMap = xTandemFile.getProteinMap();
        
        // DomainID as key, xtandemID as value.
        domainMap = new HashMap<String, Long>();
        
        // List for the q-values.
        List<Double> qvalues;
        
        while (iter.hasNext()) {

            // Get the next spectrum.
            Spectrum spectrum = iter.next();
            int spectrumNumber = spectrum.getSpectrumNumber();
            
            String spectrumTitle = xTandemFile.getSupportData(spectrumNumber).getFragIonSpectrumDescription();
           
            // Get all identifications from the spectrum
            ArrayList<Peptide> pepList = pepMap.getAllPeptides(spectrumNumber);
            List<String> peptides = new ArrayList<String>();
            
            // Iterate over all peptide identifications aka. domains
            for (Peptide peptide : pepList) {
            	
            	List<Domain> domains = peptide.getDomains();
            	for (Domain domain : domains) {
                   	String sequence = domain.getDomainSequence();
                	if(!peptides.contains(sequence)){
                	      HashMap<Object, Object> hitdata = new HashMap<Object, Object>(17);
                	      
                	      // Only store if the search spectrum id is referenced.
                	      if(MapContainer.SpectrumTitle2IdMap.containsKey(spectrumTitle)) {
                	    	  long searchspectrumid = MapContainer.SpectrumTitle2IdMap.get(spectrumTitle);
                	    	  hitdata.put(XtandemhitTableAccessor.FK_SEARCHSPECTRUMID, searchspectrumid);  
                  	    	  
                              // Set the domain id  
                              String domainID = domain.getDomainID();
                              hitdata.put(XtandemhitTableAccessor.DOMAINID, domainID);
                              
                              // parse the FASTA header
                              Header header = Header.parseFromFASTA(protMap.getProteinWithPeptideID(domainID).getLabel());
                              String accession = header.getAccession();
                              
                              Protein protein = MapContainer.FastaLoader.getProteinFromFasta(accession);
                              String description = protein.getHeader().getDescription();
                              
                              hitdata.put(XtandemhitTableAccessor.START, Long.valueOf(domain.getDomainStart()));
                              hitdata.put(XtandemhitTableAccessor.END, Long.valueOf(domain.getDomainEnd()));
                              hitdata.put(XtandemhitTableAccessor.EVALUE, domain.getDomainExpect());
                              hitdata.put(XtandemhitTableAccessor.DELTA, domain.getDomainDeltaMh());
                              hitdata.put(XtandemhitTableAccessor.HYPERSCORE, domain.getDomainHyperScore());                
                              hitdata.put(XtandemhitTableAccessor.PRE, domain.getUpFlankSequence());
                              hitdata.put(XtandemhitTableAccessor.POST, domain.getDownFlankSequence());                
                              hitdata.put(XtandemhitTableAccessor.MISSCLEAVAGES, Long.valueOf(domain.getMissedCleavages()));
                              qvalues = scoreQValueMap.get(domain.getDomainHyperScore());
                        	
                              // Check if q-value is provided.
                              if(qvalues == null){
                                	hitdata.put(XtandemhitTableAccessor.PEP, 1.0);
                                    hitdata.put(XtandemhitTableAccessor.QVALUE, 1.0);                	
                                } else {
                                	hitdata.put(XtandemhitTableAccessor.PEP, qvalues.get(0));
                                    hitdata.put(XtandemhitTableAccessor.QVALUE, qvalues.get(1));
                                }

                              // Create the database object.
                              if((Double)hitdata.get(XtandemhitTableAccessor.QVALUE) < 0.1){
                                  // Get and store the peptide.
                                  long peptideID = PeptideAccessor.findPeptideIDfromSequence(sequence, conn);
                      	    	  hitdata.put(XtandemhitTableAccessor.FK_PEPTIDEID, peptideID);
                      	    	  
                      	    	  // Get the protein(s).
                                  Long proteinID;
                                  ProteinAccessor proteinDAO = ProteinAccessor.findFromAttributes(accession, conn);
                                  if (proteinDAO == null) {	// protein not yet in database
            							// Add new protein to the database
                                	  proteinDAO = ProteinAccessor.addProteinWithPeptideID(peptideID, accession, description, protein.getSequence().getSequence(), conn);
                                	  MapContainer.ProteinMap.put(accession, proteinDAO.getProteinid());
            						} else {
            							proteinID = proteinDAO.getProteinid();
            							// check whether pep2prot link already exists, otherwise create new one
            							Pep2prot pep2prot = Pep2prot.findLink(peptideID, proteinID, conn);
            							if (pep2prot == null) {	// link doesn't exist yet
            								// Link peptide to protein.
            								pep2prot = Pep2prot.linkPeptideToProtein(peptideID, proteinID, conn);
            							}
            						}
                                  hitdata.put(XtandemhitTableAccessor.FK_PROTEINID, proteinDAO.getProteinid());
                                  
                            	  XtandemhitTableAccessor xtandemhit = new XtandemhitTableAccessor(hitdata);     
                                  xtandemhit.persist(conn);
                                  
                                  // Get the xtandemhitid
                                  Long xtandemhitid = (Long) xtandemhit.getGeneratedKeys()[0];
                                  domainMap.put(domainID, xtandemhitid);   
                                  peptides.add(sequence);
                                  conn.commit();
                              }
                	      }
                	}
				}
            }      
        }
    }
    
    /**
     * Stores the q-values (obtained from the hyperscore distribution) to the database.
     */
	private void processQValues() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(qValueFile));
			scoreQValueMap = new HashMap<Double, List<Double>>();
			String nextLine;
			// Skip the first line
			reader.readLine();
			List<Double> qvalityList;
			// Iterate over all the lines of the file.
			while ((nextLine = reader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(nextLine, "\t");
				List<String> tokenList = new ArrayList<String>();
				qvalityList = new ArrayList<Double>();
				
				// Iterate over all the tokens
				while (tokenizer.hasMoreTokens()) {
					tokenList.add(tokenizer.nextToken());
				}
				double score = Double.valueOf(tokenList.get(0));				
				qvalityList.add(Double.valueOf(tokenList.get(1)));
				qvalityList.add(Double.valueOf(tokenList.get(2)));
				scoreQValueMap.put(score, qvalityList);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} 
	}
}
