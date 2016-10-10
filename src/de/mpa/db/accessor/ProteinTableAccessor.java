/*
 * Created by the DBAccessor generator.
 * Programmer: Lennart Martens
 * Date: 02/04/2012
 * Time: 15:26:01
 */
package de.mpa.db.accessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.compomics.util.db.interfaces.Deleteable;
import com.compomics.util.db.interfaces.Persistable;
import com.compomics.util.db.interfaces.Retrievable;
import com.compomics.util.db.interfaces.Updateable;

/*
 * CVS information:
 *
 * $Revision: 1.4 $
 * $Date: 2007/07/06 09:41:53 $
 */

/**
 * This class is a generated accessor for the Protein table.
 *
 * @author DBAccessor generator class (Lennart Martens).
 */
public class ProteinTableAccessor implements Deleteable, Retrievable, Updateable, Persistable {

	/**
	 * This variable tracks changes to the object.
	 */
	protected boolean iUpdated = false;

	/**
	 * This variable can hold generated primary key columns.
	 */
	protected Object[] iKeys = null;

	/**
	 * This variable represents the contents for the 'proteinid' column.
	 */
	protected long iProteinid = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'accession' column.
	 */
	protected String iAccession = null;


	/**
	 * This variable represents the contents for the 'description' column.
	 */
	protected String iDescription = null;


	/**
	 * This variable represents the contents for the 'sequence' column.
	 */
	protected String iSequence = null;
	
	
	/**
	 * This variable represents the contents for the 'sequence' column.
	 */
	protected long ifk_UniProtID = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'source' column.
	 */
	protected String iSource = null;


	/**
	 * This variable represents the contents for the 'creationdate' column.
	 */
	protected java.sql.Timestamp iCreationdate = null;


	/**
	 * This variable represents the contents for the 'modificationdate' column.
	 */
	protected java.sql.Timestamp iModificationdate = null;


	/**
	 * This variable represents the key for the 'proteinid' column.
	 */
	public static final String PROTEINID = "PROTEINID";

	/**
	 * This variable represents the key for the 'accession' column.
	 */
	public static final String ACCESSION = "ACCESSION";

	/**
	 * This variable represents the key for the 'description' column.
	 */
	public static final String DESCRIPTION = "DESCRIPTION";

	/**
	 * This variable represents the key for the 'sequence' column.
	 */
	public static final String SEQUENCE = "SEQUENCE";
	
	
	/**
	 * This variable represents the key for the 'sequence' column.
	 */
	public static final String FK_UNIPROTID = "UNIPROT_ID";

	/**
	 * This variable represents the key for the 'source' column.
	 */
	public static final String SOURCE = "SOURCE";

	/**
	 * This variable represents the key for the 'creationdate' column.
	 */
	public static final String CREATIONDATE = "CREATIONDATE";

	/**
	 * This variable represents the key for the 'modificationdate' column.
	 */
	public static final String MODIFICATIONDATE = "MODIFICATIONDATE";




	/**
	 * Default constructor.
	 */
	public ProteinTableAccessor() {
	}

	/**
	 * This constructor allows the creation of the 'ProteinTableAccessor' object based on a set of values in the HashMap.
	 *
	 * @param	aParams	HashMap with the parameters to initialize this object with.
	 *		<i>Please use only constants defined on this class as keys in the HashMap!</i>
	 */
	public ProteinTableAccessor(@SuppressWarnings("rawtypes") HashMap aParams) {
		if(aParams.containsKey(PROTEINID)) {
			this.iProteinid = ((Long)aParams.get(PROTEINID)).longValue();
		}
		if(aParams.containsKey(ACCESSION)) {
			this.iAccession = (String)aParams.get(ACCESSION);
		}
		if(aParams.containsKey(DESCRIPTION)) {
			this.iDescription = (String)aParams.get(DESCRIPTION);
		}
		if(aParams.containsKey(SEQUENCE)) {
			this.iSequence = (String)aParams.get(SEQUENCE);
		}
		if(aParams.containsKey(FK_UNIPROTID)) {
			this.ifk_UniProtID = (Long)aParams.get(FK_UNIPROTID);
		}
		if(aParams.containsKey(SOURCE)) {
			this.iSource = (String)aParams.get(SOURCE);
		}
		if(aParams.containsKey(CREATIONDATE)) {
			this.iCreationdate = (java.sql.Timestamp)aParams.get(CREATIONDATE);
		}
		if(aParams.containsKey(MODIFICATIONDATE)) {
			this.iModificationdate = (java.sql.Timestamp)aParams.get(MODIFICATIONDATE);
		}
		this.iUpdated = true;
	}


	/**
	 * This constructor allows the creation of the 'ProteinTableAccessor' object based on a resultset
	 * obtained by a 'select * from Protein' query.
	 *
	 * @param	aResultSet	ResultSet with the required columns to initialize this object with.
	 * @exception	SQLException	when the ResultSet could not be read.
	 */
	public ProteinTableAccessor(ResultSet aResultSet) throws SQLException {
		this.iProteinid = aResultSet.getLong("proteinid");
		this.iAccession = (String)aResultSet.getObject("accession");
		this.iDescription = (String)aResultSet.getObject("description");
		this.iSequence = (String)aResultSet.getObject("sequence");
		this.ifk_UniProtID = (Long)aResultSet.getLong("fk_uniprotentryid");
		this.iSource = (String)aResultSet.getObject("source");
		this.iCreationdate = (java.sql.Timestamp)aResultSet.getObject("creationdate");
		this.iModificationdate = (java.sql.Timestamp)aResultSet.getObject("modificationdate");

		this.iUpdated = true;
	}


	/**
	 * This method returns the value for the 'Proteinid' column
	 * 
	 * @return	long	with the value for the Proteinid column.
	 */
	public long getProteinid() {
		return this.iProteinid;
	}

	/**
	 * This method returns the value for the 'Accession' column
	 * 
	 * @return	String	with the value for the Accession column.
	 */
	public String getAccession() {
		return this.iAccession;
	}

	/**
	 * This method returns the value for the 'Description' column
	 * 
	 * @return	String	with the value for the Description column.
	 */
	public String getDescription() {
		return this.iDescription;
	}

	/**
	 * This method returns the value for the 'Sequence' column
	 * 
	 * @return	String	with the value for the Sequence column.
	 */
	public String getSequence() {
		return this.iSequence;
	}
	
	
	/**
	 * This method returns the value for the 'fk_UniProtID' column
	 * 
	 * @return	Long with the value for the Fk_UniProtID column.
	 */
	public Long getFK_UniProtID() {
		return this.ifk_UniProtID;
	}
	

	/**
	 * This method returns the value for the 'Source' column
	 * 
	 * @return	String	with the value for the Source column.
	 */
	public String getSource() {
		return this.iSource;
	}

	/**
	 * This method returns the value for the 'Creationdate' column
	 * 
	 * @return	java.sql.Timestamp	with the value for the Creationdate column.
	 */
	public java.sql.Timestamp getCreationdate() {
		return this.iCreationdate;
	}

	/**
	 * This method returns the value for the 'Modificationdate' column
	 * 
	 * @return	java.sql.Timestamp	with the value for the Modificationdate column.
	 */
	public java.sql.Timestamp getModificationdate() {
		return this.iModificationdate;
	}

	/**
	 * This method sets the value for the 'Proteinid' column
	 * 
	 * @param	aProteinid	long with the value for the Proteinid column.
	 */
	public void setProteinid(long aProteinid) {
		this.iProteinid = aProteinid;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Accession' column
	 * 
	 * @param	aAccession	String with the value for the Accession column.
	 */
	public void setAccession(String aAccession) {
		this.iAccession = aAccession;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Description' column
	 * 
	 * @param	aDescription	String with the value for the Description column.
	 */
	public void setDescription(String aDescription) {
		this.iDescription = aDescription;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Sequence' column
	 * 
	 * @param	aSequence	String with the value for the Sequence column.
	 */
	public void setSequence(String aSequence) {
		this.iSequence = aSequence;
		this.iUpdated = true;
	}
	
	/**
	 * This method sets the value for the 'aFK_uniProtID' column
	 * 
	 * @param	aSequence	Long with the value for the aFK_uniProtID column.
	 */
	public void setFK_uniProtID(Long aFK_uniProtID) {
		this.ifk_UniProtID = aFK_uniProtID;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Source' column
	 * 
	 * @param	aSource	String with the value for the Source column.
	 */
	public void setSource(String aSource) {
		this.iSource = aSource;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Creationdate' column
	 * 
	 * @param	aCreationdate	java.sql.Timestamp with the value for the Creationdate column.
	 */
	public void setCreationdate(java.sql.Timestamp aCreationdate) {
		this.iCreationdate = aCreationdate;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Modificationdate' column
	 * 
	 * @param	aModificationdate	java.sql.Timestamp with the value for the Modificationdate column.
	 */
	public void setModificationdate(java.sql.Timestamp aModificationdate) {
		this.iModificationdate = aModificationdate;
		this.iUpdated = true;
	}



	/**
	 * This method allows the caller to delete the data represented by this
	 * object in a persistent store.
	 *
	 * @param   aConn Connection to the persitent store.
	 */
	public int delete(Connection aConn) throws SQLException {
		PreparedStatement lStat = aConn.prepareStatement("DELETE FROM protein WHERE proteinid = ?");
		lStat.setLong(1, iProteinid);
		int result = lStat.executeUpdate();
		lStat.close();
		return result;
	}


	/**
	 * This method allows the caller to read data for this
	 * object from a persistent store based on the specified keys.
	 *
	 * @param   aConn Connection to the persitent store.
	 */
	public void retrieve(Connection aConn, @SuppressWarnings("rawtypes") HashMap aKeys) throws SQLException {
		// First check to see whether all PK fields are present.
		if(!aKeys.containsKey(PROTEINID)) {
			throw new IllegalArgumentException("Primary key field 'PROTEINID' is missing in HashMap!");
		} else {
			iProteinid = ((Long)aKeys.get(PROTEINID)).longValue();
		}
		// In getting here, we probably have all we need to continue. So let's...
		PreparedStatement lStat = aConn.prepareStatement("SELECT * FROM protein WHERE proteinid = ?");
		lStat.setLong(1, iProteinid);
		ResultSet lRS = lStat.executeQuery();
		int hits = 0;
		while(lRS.next()) {
			hits++;
			iProteinid = lRS.getLong("proteinid");
			iAccession = (String)lRS.getObject("accession");
			iDescription = (String)lRS.getObject("description");
			iSequence = (String)lRS.getObject("sequence");
			ifk_UniProtID = (Long)lRS.getLong("fk_uniprotentryid");
			iSource = (String)lRS.getObject("source");
			iCreationdate = (java.sql.Timestamp)lRS.getObject("creationdate");
			iModificationdate = (java.sql.Timestamp)lRS.getObject("modificationdate");
		}
		lRS.close();
		lStat.close();
		if(hits>1) {
			throw new SQLException("More than one hit found for the specified primary keys in the 'protein' table! Object is initialized to last row returned.");
		} else if(hits == 0) {
			throw new SQLException("No hits found for the specified primary keys in the 'protein' table! Object is not initialized correctly!");
		}
	}
	/**
	 * This method allows the caller to obtain a basic select for this table.
	 *
	 * @return   String with the basic select statement for this table.
	 */
	public static String getBasicSelect(){
		return "select * from protein";
	}

	/**
	 * This method allows the caller to obtain all rows for this
	 * table from a persistent store.
	 *
	 * @param   aConn Connection to the persitent store.
	 * @return   ArrayList<ProteinTableAccessor>   with all entries for this table.
	 */
	public static ArrayList<ProteinTableAccessor> retrieveAllEntries(Connection aConn) throws SQLException {
		ArrayList<ProteinTableAccessor>  entities = new ArrayList<ProteinTableAccessor>();
		Statement stat = aConn.createStatement();
		ResultSet rs = stat.executeQuery(getBasicSelect());
		while(rs.next()) {
			entities.add(new ProteinTableAccessor(rs));
		}
		rs.close();
		stat.close();
		return entities;
	}



	/**
	 * This method allows the caller to update the data represented by this
	 * object in a persistent store.
	 *
	 * @param   aConn Connection to the persitent store.
	 */
	public int update(Connection aConn) throws SQLException {
		if(!this.iUpdated) {
			return 0;
		}
		PreparedStatement lStat = aConn.prepareStatement("UPDATE protein SET proteinid = ?, accession = ?, description = ?, sequence = ?, fk_uniprotentryid = ?, source = ?, creationdate = ?, modificationdate = CURRENT_TIMESTAMP WHERE proteinid = ?");
		lStat.setLong(1, iProteinid);
		lStat.setObject(2, iAccession);
		lStat.setObject(3, iDescription);
		lStat.setObject(4, iSequence);
		lStat.setLong(5, ifk_UniProtID);
		lStat.setObject(6, iSource);
		lStat.setObject(7, iCreationdate);
		lStat.setLong(8, iProteinid);
		int result = lStat.executeUpdate();
		lStat.close();
		this.iUpdated = false;
		return result;
	}


	/**
	 * This method allows the caller to insert the data represented by this
	 * object in a persistent store.
	 *
	 * @param   aConn Connection to the persitent store.
	 */
	public int persist(Connection aConn) throws SQLException {
		PreparedStatement lStat = aConn.prepareStatement("INSERT INTO protein (proteinid, accession, description, sequence, fk_uniprotentryid, source, creationdate, modificationdate) values(?, ?, ?, ?, ?,?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);
		if(iProteinid == Long.MIN_VALUE) {
			lStat.setNull(1, 4);
		} else {
			lStat.setLong(1, iProteinid);
		}
		if(iAccession == null) {
			lStat.setNull(2, 12);
		} else {
			lStat.setObject(2, iAccession);
		}
		if(iDescription == null) {
			lStat.setNull(3, -1);
		} else {
			lStat.setObject(3, iDescription);
		}
		if(iSequence == null) {
			lStat.setNull(4, -1);
		} else {
			lStat.setObject(4, iSequence);
		}
		if(ifk_UniProtID == Long.MIN_VALUE) {
			lStat.setNull(5, -1);
		} else {
			lStat.setLong(5, ifk_UniProtID);
		}
		if(iSource == null) {
			lStat.setNull(6, 12);
		} else {
			lStat.setObject(6, iSource);
		}
		int result = lStat.executeUpdate();

		// Retrieving the generated keys (if any).
		ResultSet lrsKeys = lStat.getGeneratedKeys();
		ResultSetMetaData lrsmKeys = lrsKeys.getMetaData();
		int colCount = lrsmKeys.getColumnCount();
		iKeys = new Object[colCount];
		while(lrsKeys.next()) {
			for(int i=0;i<iKeys.length;i++) {
				iKeys[i] = lrsKeys.getObject(i+1);
			}
		}
		lrsKeys.close();
		lStat.close();
		// Verify that we have a single, generated key.
		if(iKeys != null && iKeys.length == 1 && iKeys[0] != null) {
			// Since we have exactly one key specified, and only
			// one Primary Key column, we can infer that this was the
			// generated column, and we can therefore initialize it here.
			iProteinid = ((Number) iKeys[0]).longValue();
		}
		this.iUpdated = false;
		return result;
	}
	
	/**
	 * This method will return the automatically generated key for the insert if 
	 * one was triggered, or 'null' otherwise.
	 *
	 * @return	Object[]	with the generated keys.
	 */
	public Object[] getGeneratedKeys() {
		return this.iKeys;
	}

}