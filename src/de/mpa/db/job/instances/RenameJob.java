package de.mpa.db.job.instances;

import de.mpa.db.job.Job;

public class RenameJob extends Job {
	protected String oldname; 
	protected String newname;	
	
	public RenameJob(){
		
	}
	
	/**
	 * Constructor for the RenameJob.
	 * 
	 * @param oldname
	 * @param newname
	 */
	public RenameJob(String oldname, String newname) {
		this.oldname = oldname;
		this.newname = newname;
        this.initJob();
	}	

	/**
	 * Initializes the job, setting up the commands for the ProcessBuilder.
	 */
	protected void initJob() {
        this.setDescription("RENAME JOB: " + this.oldname + " TO " + this.newname);
		// Java commands
        this.procCommands.add("mv");
        this.procCommands.add(this.oldname);
        this.procCommands.add(this.newname);
        this.procCommands.trimToSize();
        this.procBuilder = new ProcessBuilder(this.procCommands);

		// set error out and std out to same stream
        this.procBuilder.redirectErrorStream(true);
	}

}
