package de.mpa.db.mysql.storager;


/**
 * This interface declares two main methods used for storing in the database:
 * 1.) load the data 
 * 2.) store to the database
 * Additionally it's designed as a thread.
 * 
 * @author Thilo Muth
 */
public interface Storager extends Runnable {

    /**
     * Loads the file containing relevant data.
     */
    void load();
    
    /**
     * Store all data persistently into the database.
     */
    void store() throws Exception;
}

