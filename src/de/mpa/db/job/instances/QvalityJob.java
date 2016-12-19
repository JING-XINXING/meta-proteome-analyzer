package de.mpa.db.job.instances;

import java.io.File;

import de.mpa.db.job.Job;
import de.mpa.util.PropertyLoader;

/**
 * This class respresents the qvality job for the FDR calculation. It takes the
 * target and the null model aka. the decoy as input. The instance uses QVality:
 * Lukas Käll, John D. Storey and William Stafford Noble "qvality:
 * Nonparametric estimation of q values and posterior error probabilities"
 * Bioinformatics, 25(7):964-966, February 2009
 * 
 * @author Thilo Muth
 *
 */
public class QvalityJob extends Job {
	private File targetFile;
	private File decoyFile;
	private String qvaluedOutput;
	private boolean reverseScoring;

	/**
	 * Constructor for the QValityJob
	 * 
	 * @param targetFilename
	 * @param decoyFilename
	 * @param reverseScoring
	 */
	public QvalityJob(String targetFilename, String decoyFilename, boolean reverseScoring) {
		this.targetFile = new File(targetFilename);
		this.decoyFile = new File(decoyFilename);
		this.reverseScoring = reverseScoring;
		qvaluedOutput = targetFile.getAbsolutePath().substring(0, targetFile.getAbsolutePath().lastIndexOf("_target"))
				+ "_qvalued.out";
		initJob();
	}

	/**
	 * Initializes the job, setting up the commands for the ProcessBuilder.
	 */
	private void initJob() {
		// set the description
		setDescription("QVALITY JOB");

		String pathQvality = PropertyLoader.getProperty(PropertyLoader.BASE_PATH)
				+ PropertyLoader.getProperty(PropertyLoader.PATH_QVALITY);
		String appQvality = pathQvality + PropertyLoader.getProperty(PropertyLoader.APP_QVALITY);

		// full path to executable
		procCommands.add(appQvality);

		// Reverse scoring mechanism: Low score are better than high scores
		if (reverseScoring) {
			procCommands.add("-r");
		}

		// Link to the input files
		procCommands.add(targetFile.getPath());
		procCommands.add(decoyFile.getPath());

		// Link to output file
		procCommands.add("-o");
		procCommands.add(qvaluedOutput);
		procCommands.trimToSize();

		log.info("qvality commands: " + procCommands.toString());
		procBuilder = new ProcessBuilder(procCommands);

		// set error out and std out to same stream
		procBuilder.redirectErrorStream(true);
	}

	public String getQValuedOutput() {
		return qvaluedOutput;
	}

}
