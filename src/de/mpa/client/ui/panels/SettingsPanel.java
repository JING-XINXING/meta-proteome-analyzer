package de.mpa.client.ui.panels;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.JXTitledPanel;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import de.mpa.client.Client;
import de.mpa.client.Constants;
import de.mpa.client.DbSearchSettings;
import de.mpa.client.SearchSettings;
import de.mpa.client.SpecSimSettings;
import de.mpa.client.ui.CheckBoxTreeTable;
import de.mpa.client.ui.ClientFrame;
import de.mpa.client.ui.PanelConfig;
import de.mpa.client.ui.icons.IconConstants;
import de.mpa.db.storager.MascotStorager;
import de.mpa.io.MascotGenericFile;
import de.mpa.io.MascotGenericFileReader;
import de.mpa.io.MascotGenericFileReader.LoadMode;

/**
 * Panel containing search engine settings and processing controls.
 * 
 * @author A. Behne
 */
public class SettingsPanel extends JPanel {
	
	/**
	 * Database search settings panel.
	 */
	private DatabaseSearchSettingsPanel databasePnl;
	
	/**
	 * Spectral library settings panel.
	 */
	private SpectralLibrarySettingsPanel specLibPnl;
	
	/**
	 * The search button.
	 */
	private JButton searchBtn;

	/**
	 * The quick search button.
	 */
	private JButton quickBtn;
	
	/**
	 * Creates the settings panel containing controls for configuring and
	 * starting database searches.
	 * @throws IOException 
	 */
	public SettingsPanel() {
		this.initComponents();
	}

	/**
	 * Initialize the UI components.
	 * @throws IOException 
	 */
	private void initComponents() {
		
		Set<AWTKeyStroke> forwardKeys = getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		Set<AWTKeyStroke> newForwardKeys = new HashSet<AWTKeyStroke>(forwardKeys);
		newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);
		this.setLayout(new BorderLayout());
				
		// database search settings panel
		JPanel settingsPnl = new JPanel(new FormLayout("p", "f:p:g, 5dlu, p"));
		
		databasePnl = new DatabaseSearchSettingsPanel();
		this.specLibPnl = databasePnl.getSpectralLibrarySettingsPanel();
		
		JPanel buttonPnl = new JPanel(new FormLayout("8dlu, p, 7dlu, p:g, 7dlu, p:g, 8dlu", "2dlu, p, 7dlu"));
		
		// create connect button
		final JButton connectBtn = new JButton(IconConstants.DISCONNECT_ICON);
		connectBtn.setRolloverIcon(IconConstants.DISCONNECT_ROLLOVER_ICON);
		connectBtn.setPressedIcon(IconConstants.CONNECT_PRESSED_ICON);
		connectBtn.setToolTipText("Connect to Server");
		final Client client = Client.getInstance();
		
		connectBtn.addActionListener(new ActionListener() {
			/** The flag denoting whether a connection has been established. */
			private boolean connected;
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (connected) {
					connectBtn.setIcon(IconConstants.DISCONNECT_ICON);
					connectBtn.setRolloverIcon(IconConstants.DISCONNECT_ROLLOVER_ICON);
					connectBtn.setPressedIcon(IconConstants.CONNECT_PRESSED_ICON);
					connectBtn.setToolTipText("Connect to Server");
					client.disconnectFromServer();
					connected = false;
				} else {
					try {
						connected = client.connectToServer();
					} catch (Exception e) {
						JXErrorPane.showDialog(e);
					}
					if (connected) {
						connectBtn.setIcon(IconConstants.CONNECT_ICON);
						connectBtn.setRolloverIcon(IconConstants.CONNECT_ROLLOVER_ICON);
						connectBtn.setPressedIcon(IconConstants.CONNECT_PRESSED_ICON);
						connectBtn.setToolTipText("Disconnect from Server");
					} else {
						JOptionPane.showMessageDialog(ClientFrame.getInstance(),
								"Could not connect to server, please check connection settings and try again.",
								"Connection Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				quickBtn.setEnabled(connected);
				searchBtn.setEnabled(connected);
				
			}
		});
		
		quickBtn = new JButton("Quick Search File...", IconConstants.LIGHTNING_ICON);
		quickBtn.setRolloverIcon(IconConstants.LIGHTNING_ROLLOVER_ICON);
		quickBtn.setPressedIcon(IconConstants.LIGHTNING_PRESSED_ICON);
		quickBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				final JFileChooser fc = new JFileChooser();
				fc.setFileFilter(Constants.MGF_FILE_FILTER);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setMultiSelectionEnabled(false);
				int result = fc.showOpenDialog(ClientFrame.getInstance());
				if (result == JFileChooser.APPROVE_OPTION) {
					new QuickSearchWorker(fc.getSelectedFile()).execute();
				}
			}
		});
		quickBtn.setEnabled(false);
		
		// create process button
		searchBtn = new JButton("Start searching", IconConstants.SEARCH_ICON);
		searchBtn.setRolloverIcon(IconConstants.SEARCH_ROLLOVER_ICON);
		searchBtn.setPressedIcon(IconConstants.SEARCH_PRESSED_ICON);
		searchBtn.setFont(searchBtn.getFont().deriveFont(Font.BOLD, searchBtn.getFont().getSize2D()*1.25f));
		searchBtn.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent evt) {
				new ProcessWorker().execute();
			}
		});
		searchBtn.setEnabled(false);

		buttonPnl.add(connectBtn, CC.xy(2, 2));
		buttonPnl.add(quickBtn, CC.xy(4, 2));
		buttonPnl.add(searchBtn, CC.xy(6, 2));
		
		settingsPnl.add(databasePnl, CC.xy(1, 1));
		settingsPnl.add(buttonPnl, CC.xy(1, 3));
		
		JXTitledPanel dbTtlPnl = PanelConfig.createTitledPanel("Search Settings", settingsPnl);
		this.add(dbTtlPnl, BorderLayout.CENTER);
	}

	/**
	 * Worker class for packing/sending input files and dispatching search requests to the server instance.
	 * 
	 * @author Thilo Muth, Alex Behne
	 */
	private class ProcessWorker extends SwingWorker {

		protected Object doInBackground() {
			ProjectPanel projectPanel = ClientFrame.getInstance().getProjectPanel();
			long experimentID = projectPanel.getSelectedExperiment().getID();
			if (experimentID != 0L) {
				CheckBoxTreeTable checkBoxTree = ClientFrame.getInstance().getFilePanel().getSpectrumTable();
				// reset progress
				Client client = Client.getInstance();
				client.firePropertyChange("resetall", 0L, (long) (checkBoxTree.getCheckBoxTreeSelectionModel()).getSelectionCount());
				// appear busy
				firePropertyChange("progress", null, 0);
				searchBtn.setEnabled(false);
				ClientFrame.getInstance().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
				try {
					// Pack and send files.
					client.firePropertyChange("new message", null, "PACKING AND SENDING FILES");
					long packSize = databasePnl.getPackageSize();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					List<String> filenames = null;
					// Collect search settings.
					DbSearchSettings dbss = (databasePnl.isEnabled()) ? databasePnl.gatherDBSearchSettings() : null;
					SpecSimSettings sss = (specLibPnl.isEnabled()) ? specLibPnl.gatherSpecSimSettings() : null;
					SearchSettings settings = new SearchSettings(dbss, sss, experimentID);
					
					// FIXME: Please change that and get files from file tree.
					if (dbss.isMascot()) {
						List<File> datFiles = ClientFrame.getInstance().getFilePanel().getSelectedMascotFiles();
						client.firePropertyChange("resetall", 0, datFiles.size());
						int i = 0;
						for (File datFile : datFiles) {
							client.firePropertyChange("new message", null, "STORING MASCOT FILE " + ++i + "/" + datFiles.size());
							MascotStorager storager = new MascotStorager(Client.getInstance().getDatabaseConnection(), datFile, settings, databasePnl.getMascotParameterMap());
							storager.run();
							client.firePropertyChange("new message", null, "FINISHED STORING MASCOT FILE " + i + "/" + datFiles.size());
						}
					}
					if (dbss.isXTandem() || dbss.isOmssa() || dbss.isCrux() || dbss.isInspect()) {
						filenames = client.packAndSend(packSize, checkBoxTree, projectPanel.getSelectedExperiment().getTitle() + "_" + sdf.format(new Date()) + "_");
					}
					
					client.firePropertyChange("new message", null, "SEARCHES RUNNING");
					// dispatch search request
					client.runSearches(filenames, settings);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			} else {
				JOptionPane.showMessageDialog(ClientFrame.getInstance(), "No experiment selected.", "Error", JOptionPane.ERROR_MESSAGE);
				return 404;
			}
		}

		@Override
		public void done() {
			ClientFrame.getInstance().setCursor(null);
			searchBtn.setEnabled(true);
		}
	}
	
	/**
	 * Convenience worker to process a file without loading its contents into the tree table first.
	 * @author A. Behne
	 */
	private class QuickSearchWorker extends SwingWorker<Object, Object> {
		
		/**
		 * The spectrum file.
		 */
		private File file;
		
		/**
		 * Constructs a quick search worker using the specified file.
		 * @param file the spectrum file
		 */
		public QuickSearchWorker(File file) {
			this.file = file;
		}
		
		@Override
		protected Object doInBackground() throws Exception {
			List<String> filenames = new ArrayList<String>();
			FileOutputStream fos = null;
			Client client = Client.getInstance();
			client.firePropertyChange("indeterminate", false, true);
			client.firePropertyChange("new message", null, "READING SPECTRUM FILE");
			MascotGenericFileReader reader = new MascotGenericFileReader(this.file, LoadMode.SURVEY);
			client.firePropertyChange("indeterminate", true, false);
			client.firePropertyChange("new message", null, "READING SPECTRUM FILE FINISHED");
			List<Long> positions = reader.getSpectrumPositions(false);
			long numSpectra = 0L;
			long maxSpectra = (long) positions.size();
			long packageSize = databasePnl.getPackageSize();
			client.firePropertyChange("resetall", 0L, maxSpectra);
			client.firePropertyChange("new message", null, "PACKING AND SENDING FILES");
			// iterate over all spectra
			File batchFile = null;
			for (int j = 0; j < positions.size(); j++) {
				
				if ((numSpectra % packageSize) == 0) {
					if (fos != null) {
						fos.close();
						client.uploadFile(batchFile.getName(), client.getBytesFromFile(batchFile));
						batchFile.delete();
					}
					batchFile = new File("quick_batch" + (numSpectra/packageSize) + ".mgf");
					filenames.add(batchFile.getName());
					fos = new FileOutputStream(batchFile);
					long remaining = maxSpectra - numSpectra;
					firePropertyChange("resetcur", 0L, (remaining > packageSize) ? packageSize : remaining);
				}
				
				MascotGenericFile mgf = reader.loadSpectrum((int) numSpectra);
				mgf.writeToStream(fos);
				fos.flush();
				firePropertyChange("progressmade", 0L, ++numSpectra);
			}
			fos.close();
			client.uploadFile(batchFile.getName(), client.getBytesFromFile(batchFile));
			batchFile.delete();
			client.firePropertyChange("new message", null, "PACKING AND SENDING FILES FINISHED");
			
			// collect search settings
			DbSearchSettings dbss = (databasePnl.isEnabled()) ? databasePnl.gatherDBSearchSettings() : null;
			SpecSimSettings sss = (specLibPnl.isEnabled()) ? specLibPnl.gatherSpecSimSettings() : null;
			SearchSettings settings = new SearchSettings(dbss, sss,
					ClientFrame.getInstance().getProjectPanel().getSelectedExperiment().getID());
			client.firePropertyChange("new message", null, "SEARCHES RUNNING");
			// dispatch search request
			client.runSearches(filenames, settings);
			return null;
		}
	}

	/**
	 * Returns the database search settings panel.
	 * @return the database search settings panel
	 */
	public DatabaseSearchSettingsPanel getDatabaseSearchSettingsPanel() {
		return databasePnl;
	}
	
}
