package de.mpa.db.storager;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import de.mpa.db.accessor.ProjectAccessor;


/**
 * This class handles the storage of the project parameters.
 * 
 * @author Thilo Muth
 *
 */
public class ProjectStorager extends BasicStorager {
    /**
     * Long variable holds the title.
     */
    private final String title;
 
    /**
     * The Connection instance.
     */
    private final Connection conn;
    
    /**
     * The Project instance.
     */
	private ProjectAccessor project;

	/**
	 * This variable contains the projectid;
	 */
	private long projectid;
	
	/**
	 * This variable contains the taxonid;
	 */
	private long taxonid;
	

	/**
     * Constructor with project title as parameter.
     * @param conn
     * @param projectid
     */
    public ProjectStorager(Connection conn, String title) {
    	this.conn = conn;
        this.title = title;
    }

    /**
     * Loads project + taxon if they already exist in the database.
     *
     * @param file
     */
    public void load() {
        try {
            this.project = ProjectAccessor.findFromTitle(this.title, this.conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stores the MGF-file to the database.
     *
     * @param conn
     * @throws SQLException
     */
    public void store() throws IOException, SQLException {
            
            /* Project section */
            HashMap<Object, Object> projectdata = new HashMap<Object, Object>(8);
            
            if(this.project == null){
            	// The title              
                projectdata.put(ProjectAccessor.TITLE, this.title);
                // Create the project database object.
                ProjectAccessor newProject = new ProjectAccessor(projectdata);
                newProject.persist(this.conn);
                this.projectid = (Long) newProject.getGeneratedKeys()[0];
            } else {
                this.projectid = this.project.getProjectid();
            }
    }
	
	/**
	 * Returns the projectid
	 * @return
	 */
	public long getProjectid() {
		return this.projectid;
	}
	
	/**
	 * Returns the taxonid.
	 * @return
	 */
	public long getTaxonid() {
		return this.taxonid;
	}
	
}

