/*
 * Created by the DBAccessor generator.
 * Programmer: Lennart Martens
 * Date: 02/04/2012
 * Time: 15:54:20
 */
package de.mpa.db.mysql.accessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
 * This class is a generated accessor for the Experiment table.
 *
 * @author DBAccessor generator class (Lennart Martens).
 */
public class ExperimentTableAccessor implements Deleteable, Retrievable, Updateable, Persistable {

	/**
	 * This variable tracks changes to the object.
	 */
	protected boolean iUpdated;

	/**
	 * This variable can hold generated primary key columns.
	 */
	protected Object[] iKeys;

	/**
	 * This variable represents the contents for the 'experimentid' column.
	 */
	protected long iExperimentid = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'fk_projectid' column.
	 */
	protected long iFk_projectid = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'fk_settingsid' column.
	 */
	protected long iFk_settingsid = Long.MIN_VALUE;


	/**
	 * This variable represents the contents for the 'title' column.
	 */
	protected String iTitle;


	/**
	 * This variable represents the contents for the 'creationdate' column.
	 */
	protected Timestamp iCreationdate;


	/**
	 * This variable represents the contents for the 'modificationdate' column.
	 */
	protected Timestamp iModificationdate;


	/**
	 * This variable represents the key for the 'experimentid' column.
	 */
	public static final String EXPERIMENTID = "EXPERIMENTID";

	/**
	 * This variable represents the key for the 'fk_projectid' column.
	 */
	public static final String FK_PROJECTID = "FK_PROJECTID";

	/**
	 * This variable represents the key for the 'fk_settingsid' column.
	 */
	public static final String FK_SETTINGSID = "FK_SETTINGSID";

	/**
	 * This variable represents the key for the 'title' column.
	 */
	public static final String TITLE = "TITLE";

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
	public ExperimentTableAccessor() {
	}

	/**
	 * This constructor allows the creation of the 'ExperimentTableAccessor' object based on a set of values in the HashMap.
	 *
	 * @param	aParams	HashMap with the parameters to initialize this object with.
	 *		<i>Please use only constants defined on this class as keys in the HashMap!</i>
	 */
	public ExperimentTableAccessor(HashMap aParams) {
		if(aParams.containsKey(ExperimentTableAccessor.EXPERIMENTID)) {
            iExperimentid = ((Long)aParams.get(ExperimentTableAccessor.EXPERIMENTID)).longValue();
		}
		if(aParams.containsKey(ExperimentTableAccessor.FK_PROJECTID)) {
            iFk_projectid = ((Long)aParams.get(ExperimentTableAccessor.FK_PROJECTID)).longValue();
		}
		if(aParams.containsKey(ExperimentTableAccessor.FK_SETTINGSID)) {
            iFk_settingsid = ((Long)aParams.get(ExperimentTableAccessor.FK_SETTINGSID)).longValue();
		}
		if(aParams.containsKey(ExperimentTableAccessor.TITLE)) {
            iTitle = (String)aParams.get(ExperimentTableAccessor.TITLE);
		}
		if(aParams.containsKey(ExperimentTableAccessor.CREATIONDATE)) {
            iCreationdate = (Timestamp)aParams.get(ExperimentTableAccessor.CREATIONDATE);
		}
		if(aParams.containsKey(ExperimentTableAccessor.MODIFICATIONDATE)) {
            iModificationdate = (Timestamp)aParams.get(ExperimentTableAccessor.MODIFICATIONDATE);
		}
        iUpdated = true;
	}


	/**
	 * This constructor allows the creation of the 'ExperimentTableAccessor' object based on a resultset
	 * obtained by a 'select * from Experiment' query.
	 *
	 * @param	aResultSet	ResultSet with the required columns to initialize this object with.
	 * @exception	SQLException	when the ResultSet could not be read.
	 */
	public ExperimentTableAccessor(ResultSet aResultSet) throws SQLException {
        iExperimentid = aResultSet.getLong("experimentid");
        iFk_projectid = aResultSet.getLong("fk_projectid");
        iFk_settingsid = aResultSet.getLong("fk_settingsid");
        iTitle = (String)aResultSet.getObject("title");
        iCreationdate = (Timestamp)aResultSet.getObject("creationdate");
        iModificationdate = (Timestamp)aResultSet.getObject("modificationdate");

        iUpdated = true;
	}


	/**
	 * This method returns the value for the 'Experimentid' column
	 * 
	 * @return	long	with the value for the Experimentid column.
	 */
	public long getExperimentid() {
		return iExperimentid;
	}

	/**
	 * This method returns the value for the 'Fk_projectid' column
	 * 
	 * @return	long	with the value for the Fk_projectid column.
	 */
	public long getFk_projectid() {
		return iFk_projectid;
	}

	/**
	 * This method returns the value for the 'Fk_settingsid' column
	 * 
	 * @return	long	with the value for the Fk_settingsid column.
	 */
	public long getFk_settingsid() {
		return iFk_settingsid;
	}

	/**
	 * This method returns the value for the 'Title' column
	 * 
	 * @return	String	with the value for the Title column.
	 */
	public String getTitle() {
		return iTitle;
	}

	/**
	 * This method returns the value for the 'Creationdate' column
	 * 
	 * @return	java.sql.Timestamp	with the value for the Creationdate column.
	 */
	public Timestamp getCreationdate() {
		return iCreationdate;
	}

	/**
	 * This method returns the value for the 'Modificationdate' column
	 * 
	 * @return	java.sql.Timestamp	with the value for the Modificationdate column.
	 */
	public Timestamp getModificationdate() {
		return iModificationdate;
	}

	/**
	 * This method sets the value for the 'Experimentid' column
	 * 
	 * @param	aExperimentid	long with the value for the Experimentid column.
	 */
	public void setExperimentid(long aExperimentid) {
        iExperimentid = aExperimentid;
        iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Fk_projectid' column
	 * 
	 * @param	aFk_projectid	long with the value for the Fk_projectid column.
	 */
	public void setFk_projectid(long aFk_projectid) {
        iFk_projectid = aFk_projectid;
        iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Fk_settingsid' column
	 * 
	 * @param	aFk_settingsid	long with the value for the Fk_settingsid column.
	 */
	public void setFk_settingsid(long aFk_settingsid) {
        iFk_settingsid = aFk_settingsid;
        iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Title' column
	 * 
	 * @param	aTitle	String with the value for the Title column.
	 */
	public void setTitle(String aTitle) {
        iTitle = aTitle;
        iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Creationdate' column
	 * 
	 * @param	aCreationdate	java.sql.Timestamp with the value for the Creationdate column.
	 */
	public void setCreationdate(Timestamp aCreationdate) {
        iCreationdate = aCreationdate;
        iUpdated = true;
	}

	/**
	 * This method sets the value for the 'Modificationdate' column
	 * 
	 * @param	aModificationdate	java.sql.Timestamp with the value for the Modificationdate column.
	 */
	public void setModificationdate(Timestamp aModificationdate) {
        iModificationdate = aModificationdate;
        iUpdated = true;
	}



	/**
	 * This method allows the caller to delete the data represented by this
	 * object in a persistent store.
	 *
	 * @param   aConn Connection to the persitent store.
	 */
	public int delete(Connection aConn) throws SQLException {
		PreparedStatement lStat = aConn.prepareStatement("DELETE FROM experiment WHERE experimentid = ?");
		lStat.setLong(1, this.iExperimentid);
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
	public void retrieve(Connection aConn, HashMap aKeys) throws SQLException {
		// First check to see whether all PK fields are present.
		if(!aKeys.containsKey(ExperimentTableAccessor.EXPERIMENTID)) {
			throw new IllegalArgumentException("Primary key field 'EXPERIMENTID' is missing in HashMap!");
		} else {
            this.iExperimentid = ((Long)aKeys.get(ExperimentTableAccessor.EXPERIMENTID)).longValue();
		}
		// In getting here, we probably have all we need to continue. So let's...
		PreparedStatement lStat = aConn.prepareStatement("SELECT * FROM experiment WHERE experimentid = ?");
		lStat.setLong(1, this.iExperimentid);
		ResultSet lRS = lStat.executeQuery();
		int hits = 0;
		while(lRS.next()) {
			hits++;
            this.iExperimentid = lRS.getLong("experimentid");
            this.iFk_projectid = lRS.getLong("fk_projectid");
            this.iFk_settingsid = lRS.getLong("fk_settingsid");
            this.iTitle = (String)lRS.getObject("title");
            this.iCreationdate = (Timestamp)lRS.getObject("creationdate");
            this.iModificationdate = (Timestamp)lRS.getObject("modificationdate");
		}
		lRS.close();
		lStat.close();
		if(hits>1) {
			throw new SQLException("More than one hit found for the specified primary keys in the 'experiment' table! Object is initialized to last row returned.");
		} else if(hits == 0) {
			throw new SQLException("No hits found for the specified primary keys in the 'experiment' table! Object is not initialized correctly!");
		}
	}
	/**
	 * This method allows the caller to obtain a basic select for this table.
	 *
	 * @return   String with the basic select statement for this table.
	 */
	public static String getBasicSelect(){
		return "select * from experiment";
	}

	/**
	 * This method allows the caller to obtain all rows for this
	 * table from a persistent store.
	 *
	 * @param   aConn Connection to the persitent store.
	 * @return   ArrayList<ExperimentTableAccessor>   with all entries for this table.
	 */
	public static ArrayList<ExperimentTableAccessor> retrieveAllEntries(Connection aConn) throws SQLException {
		ArrayList<ExperimentTableAccessor>  entities = new ArrayList<ExperimentTableAccessor>();
		Statement stat = aConn.createStatement();
		ResultSet rs = stat.executeQuery(ExperimentTableAccessor.getBasicSelect());
		while(rs.next()) {
			entities.add(new ExperimentTableAccessor(rs));
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
		if(!iUpdated) {
			return 0;
		}
		PreparedStatement lStat = aConn.prepareStatement("UPDATE experiment SET experimentid = ?, fk_projectid = ?, fk_settingsid = ?, title = ?, creationdate = ?, modificationdate = CURRENT_TIMESTAMP WHERE experimentid = ?");
		lStat.setLong(1, this.iExperimentid);
		lStat.setLong(2, this.iFk_projectid);
		lStat.setLong(3, this.iFk_settingsid);
		lStat.setObject(4, this.iTitle);
		lStat.setObject(5, this.iCreationdate);
		lStat.setLong(6, this.iExperimentid);
		int result = lStat.executeUpdate();
		lStat.close();
        iUpdated = false;
		return result;
	}


	/**
	 * This method allows the caller to insert the data represented by this
	 * object in a persistent store.
	 *
	 * @param   aConn Connection to the persitent store.
	 */
	public int persist(Connection aConn) throws SQLException {
		PreparedStatement lStat = aConn.prepareStatement("INSERT INTO experiment (experimentid, fk_projectid, fk_settingsid, title, creationdate, modificationdate) values(?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);
		if(this.iExperimentid == Long.MIN_VALUE) {
			lStat.setNull(1, 4);
		} else {
			lStat.setLong(1, this.iExperimentid);
		}
		if(this.iFk_projectid == Long.MIN_VALUE) {
			lStat.setNull(2, 4);
		} else {
			lStat.setLong(2, this.iFk_projectid);
		}
		if(this.iFk_settingsid == Long.MIN_VALUE) {
			lStat.setNull(3, 4);
		} else {
			lStat.setLong(3, this.iFk_settingsid);
		}
		if(this.iTitle == null) {
			lStat.setNull(4, 12);
		} else {
			lStat.setObject(4, this.iTitle);
		}
		int result = lStat.executeUpdate();

		// Retrieving the generated keys (if any).
		ResultSet lrsKeys = lStat.getGeneratedKeys();
		ResultSetMetaData lrsmKeys = lrsKeys.getMetaData();
		int colCount = lrsmKeys.getColumnCount();
        this.iKeys = new Object[colCount];
		while(lrsKeys.next()) {
			for(int i = 0; i< this.iKeys.length; i++) {
                this.iKeys[i] = lrsKeys.getObject(i+1);
			}
		}
		lrsKeys.close();
		lStat.close();
		// Verify that we have a single, generated key.
		if(this.iKeys != null && this.iKeys.length == 1 && this.iKeys[0] != null) {
			// Since we have exactly one key specified, and only
			// one Primary Key column, we can infer that this was the
			// generated column, and we can therefore initialize it here.
            this.iExperimentid = ((Number) this.iKeys[0]).longValue();
		}
        iUpdated = false;
		return result;
	}

	/**
	 * This method will return the automatically generated key for the insert if 
	 * one was triggered, or 'null' otherwise.
	 *
	 * @return	Object[]	with the generated keys.
	 */
	public Object[] getGeneratedKeys() {
		return iKeys;
	}
	
	/**
	 * Find all experiments of the project.
	 * @param fk_projectid
	 * @param aConn
	 * @return
	 * @throws SQLException
	 */
	public static List<ExperimentTableAccessor> findAllExperimentsOfProject(long fk_projectid, Connection aConn) throws SQLException {
		List<ExperimentTableAccessor> temp = new ArrayList<ExperimentTableAccessor>();
		PreparedStatement ps = aConn.prepareStatement(ExperimentTableAccessor.getBasicSelect()+ " where fk_projectid = ?");
		ps.setLong(1, fk_projectid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			temp.add(new ExperimentTableAccessor(rs));
		}
		rs.close();
		ps.close();
		return temp;
	}

	
	public static ExperimentTableAccessor findExperimentByID(long experimentid, Connection aConn) throws SQLException {
		ExperimentTableAccessor temp = new ExperimentTableAccessor();
		PreparedStatement ps = aConn.prepareStatement(ExperimentTableAccessor.getBasicSelect()+ " where experimentid = ?");
		ps.setLong(1, experimentid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			temp= new ExperimentTableAccessor(rs);
		}
		rs.close();
		ps.close();
		return temp;
	}
	
	/**
	 * Returns the experiment for a selected experiment id and project id.
	 * @param experimentid The experiment id.
	 * @param projectid The project id.
	 * @param aConn The database connection.
	 * @return The selected experiment.
	 * @throws SQLException
	 */
	public static ExperimentTableAccessor findExperimentByIDandProjectID(long experimentid, long projectid, Connection aConn) throws SQLException {
		ExperimentTableAccessor temp = new ExperimentTableAccessor();
		PreparedStatement ps = aConn.prepareStatement(ExperimentTableAccessor.getBasicSelect()+ " where experimentid = ? and fk_projectid = ?");
		ps.setLong(1, experimentid);
		ps.setLong(2, projectid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			temp = new ExperimentTableAccessor(rs);
		}
		rs.close();
		ps.close();
		return temp;
	}
	
//	@Override
//	public int persist(Connection aConn) throws SQLException {
//		int persist = super.persist(aConn);
//		aConn.commit();
//		return persist;
//	}
//	
//
//	@Override
//	public int update(Connection aConn) throws SQLException {
//		int update = super.update(aConn);
//		aConn.commit();
//		return update;
//	}
//	
//	@Override
//	public String toString() {
//		return iTitle;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (obj instanceof ExperimentAccessor) {
//			ExperimentAccessor that = (ExperimentAccessor) obj;
//			return (that.iExperimentid == iExperimentid);
//		}
//		return false;
//	}
	

}