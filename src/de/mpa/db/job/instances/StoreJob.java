package de.mpa.db.job.instances;

import java.sql.SQLException;

import de.mpa.client.model.dbsearch.SearchEngineType;
import de.mpa.db.DBManager;
import de.mpa.db.job.Job;

public class StoreJob extends Job {
	
	/**
	 * The database manager object.
	 */
	private DBManager dbManager;
	
	/**
	 * The QVality results filename.
	 */
	private String qValueFilename;
	
	/**
	 * Search engine type.
	 */
	private SearchEngineType searchEngineType;

	/**
	 * The search engine result filename.
	 */
	private String resultFilename;

	
	/**
	 * Constructs an results storing job (without q-value file).
	 * @param searchEngineType
	 * @param resultsFileName
	 */
	public StoreJob(SearchEngineType searchEngineType, String resultsFileName) {
		this(searchEngineType, resultsFileName, null);
	}
	
	/**
	 * Constructs an results storing job.
	 * @param searchEngineType The search engine type.
	 * @param resultFilename The results filename.
	 * @param qValueFilename The q-value results filename.
	 */
	public StoreJob(SearchEngineType searchEngineType, String resultFilename, String qValueFilename) {
		try {
			this.dbManager = DBManager.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.resultFilename = resultFilename;
		this.qValueFilename = qValueFilename;
		this.searchEngineType = searchEngineType;
		// Set the description
		setFilename(resultFilename);
		setDescription(searchEngineType.name().toUpperCase() + " RESULTS STORING");
	}
	
	@Override
	public void run() {
		try {
			// XXX: worst bug fix in the history of MPA
			qValueFilename = qValueFilename.replaceAll("__", "_");
			dbManager.storeDatabaseSearchResults(searchEngineType, resultFilename, qValueFilename);
		} catch (Exception e) {
			setError(e);
		}
	}

}
