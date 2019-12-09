package de.mpa.client.ui.inputpanel.dialogs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import de.mpa.client.ui.ClientFrame;
import de.mpa.client.ui.sharedelements.ScreenConfig;
import de.mpa.client.ui.sharedelements.icons.IconConstants;
import de.mpa.model.MPAExperiment;
import de.mpa.model.MPAProject;

/**
 * Dialog to ask for a list of experiments
 * @author Robert Heyer
 */
@SuppressWarnings("serial")
public class SelectExperimentDialog extends JDialog{
	/**
	 * The ClientFrame
	 */
	private final ClientFrame owner;

	/**
	 * List of the names of the selected experiments
	 */
	private static final ArrayList<MPAExperiment> expList = new ArrayList<MPAExperiment>();

	/**
	 * @param owner. The owner of the dialog.
	 * @param title. The title of the dialog.
	 */
	public SelectExperimentDialog(ClientFrame owner, String title) {
		super(owner, title, true);
		expList.clear();
		this.owner = owner;
        this.initComponents();
		// Configure size and position
        pack();
		Dimension size = getSize();
        setSize(new Dimension(size.width, size.height + 7));
        setResizable(false);
		ScreenConfig.centerInScreen(this);

		// Show dialog
        setVisible(true);
	}

	/**
	 * Method to build the dialog
	 */
	private void initComponents() {
		// Create BLAST dialog
		JPanel selectExperimentsDlgPnl 	= new JPanel(new FormLayout("5dlu, p:g, 5dlu, p:g, 5dlu", "5dlu, f:p:g, 5dlu, f:p:g, 5dlu"));
		JPanel selectExpDlg 			= new JPanel(new FormLayout("5dlu, p:g, 5dlu", "5dlu, p:g, 5dlu"));
		JTable expTbl 					= new JTable(this.createExpTable());
		JScrollPane expTblSp			= new JScrollPane(expTbl);		
		selectExpDlg.add(expTblSp, CC.xy(2,  2));
		// Configure 'OK' button
		JButton okBtn = new JButton("OK", IconConstants.CHECK_ICON);
		okBtn.setRolloverIcon(IconConstants.CHECK_ROLLOVER_ICON);
		okBtn.setPressedIcon(IconConstants.CHECK_PRESSED_ICON);
		okBtn.setHorizontalAlignment(SwingConstants.LEFT);
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = expTbl.getSelectedRows();
				for (int i : selectedRows) {
					Long expID = (Long) expTbl.getValueAt(i, 0);
					// TODO: get experiment from somewhere else
					MPAExperiment experiment = null;
					try {
						experiment = new MPAExperiment(expID, null);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    SelectExperimentDialog.expList.add(experiment);
				}
                SelectExperimentDialog.this.close();
			}
		});

		// Configure 'Cancel' button
		JButton cancelBtn = new JButton("Cancel", IconConstants.CROSS_ICON);
		cancelBtn.setRolloverIcon(IconConstants.CROSS_ROLLOVER_ICON);
		cancelBtn.setPressedIcon(IconConstants.CROSS_PRESSED_ICON);
		cancelBtn.setHorizontalAlignment(SwingConstants.LEFT);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                SelectExperimentDialog.expList.clear();
                SelectExperimentDialog.this.close();
			}
		});
		selectExperimentsDlgPnl.add(selectExpDlg, CC.xyw(2, 2,3));
		selectExperimentsDlgPnl.add(okBtn,CC.xy(2, 4) );
		selectExperimentsDlgPnl.add(cancelBtn,CC.xy(4, 4) );
		Container cp = getContentPane();
		cp.setLayout(new FormLayout("5dlu, r:p, 5dlu", "5dlu, f:p:g, 5dlu"));
		cp.add(selectExperimentsDlgPnl, CC.xy(2,  2));		
	}

	/**
	 * This method shows the dialog.
	 */
	public static ArrayList<MPAExperiment> showDialog(ClientFrame owner, String title) {
		return new SelectExperimentDialog(owner, title).getExpList(); 
	}

	/**
	 * Close method for the dialog.
	 */
	private void close() {
        this.dispose();
	}

	/**
	 * Method to create the experiment table.
	 * @return Experiment table. The table with all experiments of the project selected in the project panel.
	 */
	private DefaultTableModel createExpTable() {
		DefaultTableModel dtm = new DefaultTableModel( new Object[]{"ID","Experiments"},0);
		MPAProject selProject = ClientFrame.getInstance().getProjectPanel().getSelectedProject();
		for (MPAExperiment exp : selProject.getExperiments()) {
			dtm.addRow(new Object[]{exp.getID(),exp.getTitle()});
		}
		return dtm;
	}

	/**
	 * Gets the list of selected experiments
	 * @return expList. The list of selected experiments.
	 */
	public ArrayList<MPAExperiment> getExpList() {
		return SelectExperimentDialog.expList;
	}
}