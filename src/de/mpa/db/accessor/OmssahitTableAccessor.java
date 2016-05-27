/*
 * Created by the DBAccessor generator.
 * Programmer: Lennart Martens
 * Date: 02/04/2012
 * Time: 15:25:47
 */
package de.mpa.db.accessor;

import java.io.Serializable;
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
 * This class is a generated accessor for the Omssahit table.
 *
 * @author DBAccessor generator class (Lennart Martens).
 */
@SuppressWarnings("serial")
public class OmssahitTableAccessor implements Deleteable, Retrievable, Updateable, Persistable, Serializable {

	/**
	 * This variable tracks changes to the object.
	 */
	protected boolean iUpdated = false;

	/**
	 * This variable can hold generated primary key columns.
	 */
	protected Object[] iKeys = null;

	/**
	 * This variable represents the contents for the 'omssahitid' column.
	 */
	protected long iOmssahitid = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'fk_searchspectrumid' column.
	 */
	protected long iFk_searchspectrumid = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'fk_peptideid' column.
	 */
	protected long iFk_peptideid = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'fk_proteinid' column.
	 */
	protected long iFk_proteinid = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'hitsetnumber' column.
	 */
	protected long iHitsetnumber = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'evalue' column.
	 */
	protected Number iEvalue = null;


	/**
	 * This variable represents the contents for the 'pvalue' column.
	 */
	protected Number iPvalue = null;


	/**
	 * This variable represents the contents for the 'charge' column.
	 */
	protected long iCharge = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'mass' column.
	 */
	protected Number iMass = null;


	/**
	 * This variable represents the contents for the 'theomass' column.
	 */
	protected Number iTheomass = null;


	/**
	 * This variable represents the contents for the 'start' column.
	 */
	protected String iStart = null;


	/**
	 * This variable represents the contents for the 'end' column.
	 */
	protected String iEnd = null;


	/**
	 * This variable represents the contents for the 'qvalue' column.
	 */
	protected Number iQvalue = null;


	/**
	 * This variable represents the contents for the 'pep' column.
	 */
	protected Number iPep = null;


	/**
	 * This variable represents the contents for the 'creationdate' column.
	 */
	protected java.sql.Timestamp iCreationdate = null;


	/**
	 * This variable represents the contents for the 'modificationdate' column.
	 */
	protected String iModificationdate = null;


	/**
	 * This variable represents the key for the 'omssahitid' column.
	 */
	public static final String OMSSAHITID = "OMSSAHITID";

	/**
	 * This variable represents the key for the 'fk_searchspectrumid' column.
	 */
	public static final String FK_SEARCHSPECTRUMID = "FK_SEARCHSPECTRUMID";

	/**
	 * This variable represents the key for the 'fk_peptideid' column.
	 */
	public static final String FK_PEPTIDEID = "FK_PEPTIDEID";

	/**
	 * This variable represents the key for the 'fk_proteinid' column.
	 */
	public static final String FK_PROTEINID = "FK_PROTEINID";

	/**
	 * This variable represents the key for the 'hitsetnumber' column.
	 */
	public static final String HITSETNUMBER = "HITSETNUMBER";

	/**
	 * This variable represents the key for the 'evalue' column.
	 */
	public static final String EVALUE = "EVALUE";

	/**
	 * This variable represents the key for the 'pvalue' column.
	 */
	public static final String PVALUE = "PVALUE";

	/**
	 * This variable represents the key for the 'charge' column.
	 */
	public static final String CHARGE = "CHARGE";

	/**
	 * This variable represents the key for the 'mass' column.
	 */
	public static final String MASS = "MASS";

	/**
	 * This variable represents the key for the 'theomass' column.
	 */
	public static final String THEOMASS = "THEOMASS";

	/**
	 * This variable represents the key for the 'start' column.
	 */
	public static final String START = "START";

	/**
	 * This variable represents the key for the 'end' column.
	 */
	public static final String END = "END";

	/**
	 * This variable represents the key for the 'qvalue' column.
	 */
	public static final String QVALUE = "QVALUE";

	/**
	 * This variable represents the key for the 'pep' column.
	 */
	public static final String PEP = "PEP";

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
	public OmssahitTableAccessor() {
	}

	/**
	 * This constructor allows the creation of the 'OmssahitTableAccessor' object based on a set of values in the HashMap.
	 *
	 * @param	aParams	HashMap with the parameters to initialize this object with.
	 *		<i>Please use only constants defined on this class as keys in the HashMap!</i>
	 */
	public OmssahitTableAccessor(@SuppressWarnings("rawtypes") HashMap aParams) {
		if(aParams.containsKey(OMSSAHITID)) {
			this.iOmssahitid = ((Long)aParams.get(OMSSAHITID)).longValue();
		}
		if(aParams.containsKey(FK_SEARCHSPECTRUMID)) {
			this.iFk_searchspectrumid = ((Long)aParams.get(FK_SEARCHSPECTRUMID)).longValue();
		}
		if(aParams.containsKey(FK_PEPTIDEID)) {
			this.iFk_peptideid = ((Long)aParams.get(FK_PEPTIDEID)).longValue();
		}
		if(aParams.containsKey(FK_PROTEINID)) {
			this.iFk_proteinid = ((Long)aParams.get(FK_PROTEINID)).longValue();
		}
		if(aParams.containsKey(HITSETNUMBER)) {
			this.iHitsetnumber = ((Long)aParams.get(HITSETNUMBER)).longValue();
		}
		if(aParams.containsKey(EVALUE)) {
			this.iEvalue = (Number)aParams.get(EVALUE);
		}
		if(aParams.containsKey(PVALUE)) {
			this.iPvalue = (Number)aParams.get(PVALUE);
		}
		if(aParams.containsKey(CHARGE)) {
			this.iCharge = ((Long)aParams.get(CHARGE)).longValue();
		}
		if(aParams.containsKey(MASS)) {
			this.iMass = (Number)aParams.get(MASS);
		}
		if(aParams.containsKey(THEOMASS)) {
			this.iTheomass = (Number)aParams.get(THEOMASS);
		}
		if(aParams.containsKey(START)) {
			this.iStart = (String)aParams.get(START);
		}
		if(aParams.containsKey(END)) {
			this.iEnd = (String)aParams.get(END);
		}
		if(aParams.containsKey(QVALUE)) {
			this.iQvalue = (Number)aParams.get(QVALUE);
		}
		if(aParams.containsKey(PEP)) {
			this.iPep = (Number)aParams.get(PEP);
		}
		if(aParams.containsKey(CREATIONDATE)) {
			this.iCreationdate = (java.sql.Timestamp)aParams.get(CREATIONDATE);
		}
		if(aParams.containsKey(MODIFICATIONDATE)) {
			this.iModificationdate = (String)aParams.get(MODIFICATIONDATE);
		}
		this.iUpdated = true;
	}


	/**
	 * This constructor allows the creation of the 'OmssahitTableAccessor' object based on a resultset
	 * obtained by a 'select * from Omssahit' query.
	 *
	 * @param	aResultSet	ResultSet with the required columns to initialize this object with.
	 * @exception	SQLException	when the ResultSet could not be read.
	 */
	public OmssahitTableAccessor(ResultSet aResultSet) throws SQLException {
		this.iOmssahitid = aResultSet.getLong("omssahitid");
		this.iFk_searchspectrumid = aResultSet.getLong("fk_searchspectrumid");
		this.iFk_peptideid = aResultSet.getLong("fk_peptideid");
		this.iFk_proteinid = aResultSet.getLong("fk_proteinid");
		this.iHitsetnumber = aResultSet.getLong("hitsetnumber");
		this.iEvalue = (Number)aResultSet.getObject("evalue");
		this.iPvalue = (Number)aResultSet.getObject("pvalue");
		this.iCharge = aResultSet.getLong("charge");
		this.iMass = (Number)aResultSet.getObject("mass");
		this.iTheomass = (Number)aResultSet.getObject("theomass");
		this.iStart = (String)aResultSet.getObject("start");
		this.iEnd = (String)aResultSet.getObject("end");
		this.iQvalue = (Number)aResultSet.getObject("qvalue");
		this.iPep = (Number)aResultSet.getObject("pep");
		this.iCreationdate = (java.sql.Timestamp)aResultSet.getObject("creationdate");
		this.iModificationdate = (String)aResultSet.getObject("modificationdate");

		this.iUpdated = true;
	}


	/**
	 * This method returns the value for the 'Omssahitid' column
	 * 
	 * @return	long	with the value for the Omssahitid column.
	 */
	public long getOmssahitid() {
		return this.iOmssahitid;
	}

	/**
	 * This method returns the value for the 'Fk_searchspectrumid' column
	 * 
	 * @return	long	with the value for the Fk_searchspectrumid column.
	 */
	public long getFk_searchspectrumid() {
		return this.iFk_searchspectrumid;
	}

	/**
	 * This method returns the value for the 'Fk_peptideid' column
	 * 
	 * @return	long	with the value for the Fk_peptideid column.
	 */
	public long getFk_peptideid() {
		return this.iFk_peptideid;
	}

	/**
	 * This method returns the value for the 'Fk_proteinid' column
	 * 
	 * @return	long	with the value for the Fk_proteinid column.
	 */
	public long getFk_proteinid() {
		return this.iFk_proteinid;
	}

	/**
	 * This method returns the value for the 'Hitsetnumber' column
	 * 
	 * @return	long	with the value for the Hitsetnumber column.
	 */
	public long getHitsetnumber() {
		return this.iHitsetnumber;
	}

	/**
	 * This method returns the value for the 'Evalue' column
	 * 
	 * @return	Number	with the value for the Evalue column.
	 */
	public Number getEvalue() {
		return this.iEvalue;
	}

	/**
	 * This method returns the value for the 'Pvalue' column
	 * 
	 * @return	Number	with the value for the Pvalue column.
	 */
	public Number getPvalue() {
		return this.iPvalue;
	}

	/**
	 * This method returns the value for the 'Charge' column
	 * 
	 * @return	long	with the value for the Charge column.
	 */
	public long getCharge() {
		return this.iCharge;
	}

	/**
	 * This method returns the value for the 'Mass' column
	 * 
	 * @return	Number	with the value for the Mass column.
	 */
	public Number getMass() {
		return this.iMass;
	}

	/**
	 * This method returns the value for the 'Theomass' column
	 * 
	 * @return	Number	with the value for the Theomass column.
	 */
	public Number getTheomass() {
		return this.iTheomass;
	}

	/**
	 * This method returns the value for the 'Start' column
	 * 
	 * @return	String	with the value for the Start column.
	 */
	public String getStart() {
		return this.iStart;
	}

	/**
	 * This method returns the value for the 'End' column
	 * 
	 * @return	String	with the value for the End column.
	 */
	public String getEnd() {
		return this.iEnd;
	}

	/**
	 * This method returns the value for the 'Qvalue' column
	 * 
	 * @return	Number	with the value for the Qvalue column.
	 */
	public Number getQvalue() {
		return this.iQvalue;
	}

	/**
	 * This method returns the value for the 'Pep' column
	 * 
	 * @return	Number	with the value for the Pep column.
	 */
	public Number getPep() {
		return this.iPep;
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
	 * @return	String	with the value for the Modificationdate column.
	 */
	public String getModificationdate() {
		return this.iModificationdate;
	}

	/**
	 * This method sets the value for the 'Omssahitid' column
	 * 
	 * @param	aOmssahitid	long with the value for the Omssahitid column.
	 */
	public void setOmssahitid(long aOmssahitid) {
		this.iOmssahitid = aOmssahitid;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Fk_searchspectrumid' column
	 * 
	 * @param	aFk_searchspectrumid	long with the value for the Fk_searchspectrumid column.
	 */
	public void setFk_searchspectrumid(long aFk_searchspectrumid) {
		this.iFk_searchspectrumid = aFk_searchspectrumid;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Fk_peptideid' column
	 * 
	 * @param	aFk_peptideid	long with the value for the Fk_peptideid column.
	 */
	public void setFk_peptideid(long aFk_peptideid) {
		this.iFk_peptideid = aFk_peptideid;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Fk_proteinid' column
	 * 
	 * @param	aFk_proteinid	long with the value for the Fk_proteinid column.
	 */
	public void setFk_proteinid(long aFk_proteinid) {
		this.iFk_proteinid = aFk_proteinid;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Hitsetnumber' column
	 * 
	 * @param	aHitsetnumber	long with the value for the Hitsetnumber column.
	 */
	public void setHitsetnumber(long aHitsetnumber) {
		this.iHitsetnumber = aHitsetnumber;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Evalue' column
	 * 
	 * @param	aEvalue	Number with the value for the Evalue column.
	 */
	public void setEvalue(Number aEvalue) {
		this.iEvalue = aEvalue;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Pvalue' column
	 * 
	 * @param	aPvalue	Number with the value for the Pvalue column.
	 */
	public void setPvalue(Number aPvalue) {
		this.iPvalue = aPvalue;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Charge' column
	 * 
	 * @param	aCharge	long with the value for the Charge column.
	 */
	public void setCharge(long aCharge) {
		this.iCharge = aCharge;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Mass' column
	 * 
	 * @param	aMass	Number with the value for the Mass column.
	 */
	public void setMass(Number aMass) {
		this.iMass = aMass;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Theomass' column
	 * 
	 * @param	aTheomass	Number with the value for the Theomass column.
	 */
	public void setTheomass(Number aTheomass) {
		this.iTheomass = aTheomass;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Start' column
	 * 
	 * @param	aStart	String with the value for the Start column.
	 */
	public void setStart(String aStart) {
		this.iStart = aStart;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'End' column
	 * 
	 * @param	aEnd	String with the value for the End column.
	 */
	public void setEnd(String aEnd) {
		this.iEnd = aEnd;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Qvalue' column
	 * 
	 * @param	aQvalue	Number with the value for the Qvalue column.
	 */
	public void setQvalue(Number aQvalue) {
		this.iQvalue = aQvalue;
		this.iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Pep' column
	 * 
	 * @param	aPep	Number with the value for the Pep column.
	 */
	public void setPep(Number aPep) {
		this.iPep = aPep;
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
	 * @param	aModificationdate	String with the value for the Modificationdate column.
	 */
	public void setModificationdate(String aModificationdate) {
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
		PreparedStatement lStat = aConn.prepareStatement("DELETE FROM omssahit WHERE omssahitid = ?");
		lStat.setLong(1, iOmssahitid);
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
		if(!aKeys.containsKey(OMSSAHITID)) {
			throw new IllegalArgumentException("Primary key field 'OMSSAHITID' is missing in HashMap!");
		} else {
			iOmssahitid = ((Long)aKeys.get(OMSSAHITID)).longValue();
		}
		// In getting here, we probably have all we need to continue. So let's...
		PreparedStatement lStat = aConn.prepareStatement("SELECT * FROM omssahit WHERE omssahitid = ?");
		lStat.setLong(1, iOmssahitid);
		ResultSet lRS = lStat.executeQuery();
		int hits = 0;
		while(lRS.next()) {
			hits++;
			iOmssahitid = lRS.getLong("omssahitid");
			iFk_searchspectrumid = lRS.getLong("fk_searchspectrumid");
			iFk_peptideid = lRS.getLong("fk_peptideid");
			iFk_proteinid = lRS.getLong("fk_proteinid");
			iHitsetnumber = lRS.getLong("hitsetnumber");
			iEvalue = (Number)lRS.getObject("evalue");
			iPvalue = (Number)lRS.getObject("pvalue");
			iCharge = lRS.getLong("charge");
			iMass = (Number)lRS.getObject("mass");
			iTheomass = (Number)lRS.getObject("theomass");
			iStart = (String)lRS.getObject("start");
			iEnd = (String)lRS.getObject("end");
			iQvalue = (Number)lRS.getObject("qvalue");
			iPep = (Number)lRS.getObject("pep");
			iCreationdate = (java.sql.Timestamp)lRS.getObject("creationdate");
			iModificationdate = (String)lRS.getObject("modificationdate");
		}
		lRS.close();
		lStat.close();
		if(hits>1) {
			throw new SQLException("More than one hit found for the specified primary keys in the 'omssahit' table! Object is initialized to last row returned.");
		} else if(hits == 0) {
			throw new SQLException("No hits found for the specified primary keys in the 'omssahit' table! Object is not initialized correctly!");
		}
	}
	/**
	 * This method allows the caller to obtain a basic select for this table.
	 *
	 * @return   String with the basic select statement for this table.
	 */
	public static String getBasicSelect(){
		return "select * from omssahit";
	}

	/**
	 * This method allows the caller to obtain all rows for this
	 * table from a persistent store.
	 *
	 * @param   aConn Connection to the persitent store.
	 * @return   ArrayList<OmssahitTableAccessor>   with all entries for this table.
	 */
	public static ArrayList<OmssahitTableAccessor> retrieveAllEntries(Connection aConn) throws SQLException {
		ArrayList<OmssahitTableAccessor>  entities = new ArrayList<OmssahitTableAccessor>();
		Statement stat = aConn.createStatement();
		ResultSet rs = stat.executeQuery(getBasicSelect());
		while(rs.next()) {
			entities.add(new OmssahitTableAccessor(rs));
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
		PreparedStatement lStat = aConn.prepareStatement("UPDATE omssahit SET omssahitid = ?, fk_searchspectrumid = ?, fk_peptideid = ?, fk_proteinid = ?, hitsetnumber = ?, evalue = ?, pvalue = ?, charge = ?, mass = ?, theomass = ?, start = ?, end = ?, qvalue = ?, pep = ?, creationdate = ?, modificationdate = CURRENT_TIMESTAMP WHERE omssahitid = ?");
		lStat.setLong(1, iOmssahitid);
		lStat.setLong(2, iFk_searchspectrumid);
		lStat.setLong(3, iFk_peptideid);
		lStat.setLong(4, iFk_proteinid);
		lStat.setLong(5, iHitsetnumber);
		lStat.setObject(6, iEvalue);
		lStat.setObject(7, iPvalue);
		lStat.setLong(8, iCharge);
		lStat.setObject(9, iMass);
		lStat.setObject(10, iTheomass);
		lStat.setObject(11, iStart);
		lStat.setObject(12, iEnd);
		lStat.setObject(13, iQvalue);
		lStat.setObject(14, iPep);
		lStat.setObject(15, iCreationdate);
		lStat.setLong(16, iOmssahitid);
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
		PreparedStatement lStat = aConn.prepareStatement("INSERT INTO omssahit (omssahitid, fk_searchspectrumid, fk_peptideid, fk_proteinid, hitsetnumber, evalue, pvalue, charge, mass, theomass, start, end, qvalue, pep, creationdate, modificationdate) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);
		if(iOmssahitid == Long.MIN_VALUE) {
			lStat.setNull(1, 4);
		} else {
			lStat.setLong(1, iOmssahitid);
		}
		if(iFk_searchspectrumid == Long.MIN_VALUE) {
			lStat.setNull(2, 4);
		} else {
			lStat.setLong(2, iFk_searchspectrumid);
		}
		if(iFk_peptideid == Long.MIN_VALUE) {
			lStat.setNull(3, 4);
		} else {
			lStat.setLong(3, iFk_peptideid);
		}
		if(iFk_proteinid == Long.MIN_VALUE) {
			lStat.setNull(4, 4);
		} else {
			lStat.setLong(4, iFk_proteinid);
		}
		if(iHitsetnumber == Long.MIN_VALUE) {
			lStat.setNull(5, 4);
		} else {
			lStat.setLong(5, iHitsetnumber);
		}
		if(iEvalue == null) {
			lStat.setNull(6, 3);
		} else {
			lStat.setObject(6, iEvalue);
		}
		if(iPvalue == null) {
			lStat.setNull(7, 3);
		} else {
			lStat.setObject(7, iPvalue);
		}
		if(iCharge == Long.MIN_VALUE) {
			lStat.setNull(8, 4);
		} else {
			lStat.setLong(8, iCharge);
		}
		if(iMass == null) {
			lStat.setNull(9, 3);
		} else {
			lStat.setObject(9, iMass);
		}
		if(iTheomass == null) {
			lStat.setNull(10, 3);
		} else {
			lStat.setObject(10, iTheomass);
		}
		if(iStart == null) {
			lStat.setNull(11, 12);
		} else {
			lStat.setObject(11, iStart);
		}
		if(iEnd == null) {
			lStat.setNull(12, 12);
		} else {
			lStat.setObject(12, iEnd);
		}
		if(iQvalue == null) {
			lStat.setNull(13, 3);
		} else {
			lStat.setObject(13, iQvalue);
		}
		if(iPep == null) {
			lStat.setNull(14, 3);
		} else {
			lStat.setObject(14, iPep);
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
			iOmssahitid = ((Number) iKeys[0]).longValue();
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