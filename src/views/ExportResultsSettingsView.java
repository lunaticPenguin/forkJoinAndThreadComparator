package views;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.listeners.ExportResultsChoosenProcessActionListener;
import controllers.listeners.ExportResultsSettingsProcessChoiceActionListener;

import models.AbstractModel;

public class ExportResultsSettingsView extends AbstractView {
	
	/* GUI elements */
	
	protected JPanel mainPanel;
	
	protected JComboBox<String> processChoice;
	protected ArrayList<JComboBox<String>> fileResultsToExport;
	protected JButton exportButton;
	
	public ExportResultsSettingsView(AbstractModel model) {
		super(null);
		processChoice = new JComboBox<String>(new String[] {"Process type", "Thread", "ForkJoin"});
		processChoice.addActionListener(new ExportResultsSettingsProcessChoiceActionListener(this));
		
		fileResultsToExport = new ArrayList<JComboBox<String>>();
	}
	
	/**
	 * Allow to initialize the view with the current results of the application 
	 * execution context.
	 * 
	 * @param HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> threadData (to get the String keys)
	 * @param HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> forkJoinData (to get the String keys)
	 */
	public void init(HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> threadData, 
			HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> forkJoinData) {

		Set<String> filenamesForThread = threadData.keySet();
		Set<String> filenamesForForkJoin = forkJoinData.keySet();
		Iterator<String> it;
		
		JComboBox<String> tmpComboBox;
		tmpComboBox = new JComboBox<String>();
		tmpComboBox.setVisible(false);
		tmpComboBox.setSize(50, 15);
		
		// add empty combobox for display consistency
		JComboBox<String> objEmptyComboBox = new JComboBox<String>();
		objEmptyComboBox.addItem("No available file");
		objEmptyComboBox.setSize(50, 15);
		objEmptyComboBox.setEnabled(false);
		fileResultsToExport.add(objEmptyComboBox);
		
		it = filenamesForThread.iterator();
		while (it.hasNext()) {
			tmpComboBox.addItem(it.next());
		}
		fileResultsToExport.add(tmpComboBox);
		
		// --------------
		
		tmpComboBox = new JComboBox<String>();
		tmpComboBox.setVisible(false);
		tmpComboBox.setSize(50, 15);
		
		it = filenamesForForkJoin.iterator();
		while (it.hasNext()) {
			tmpComboBox.addItem(it.next());
		}
		fileResultsToExport.add(tmpComboBox);
		
		// ---------------------------
		
		
		exportButton = new JButton("Export");
		exportButton.setEnabled(false);
		exportButton.addActionListener(new ExportResultsChoosenProcessActionListener(this));
		
		mainPanel = new JPanel(new FlowLayout());
		mainPanel.add(processChoice);
		mainPanel.add(fileResultsToExport.get(0));
		mainPanel.add(fileResultsToExport.get(1));
		mainPanel.add(fileResultsToExport.get(2));
		mainPanel.add(exportButton);
		
		mainFrame = new JFrame();
		mainFrame.setTitle("Export data");
		mainFrame.setSize(400, 95);
		mainFrame.setLocationRelativeTo(getParentView().getMainFrame());
		mainFrame.setContentPane(mainPanel);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Get the process choice JComboBox
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getProcessChoice() {
		return processChoice;
	}
	
	/**
	 * Get the export button
	 * @return JButton
	 */
	public JButton getExportButton() {
		return exportButton;
	}
	
	/**
	 * Get the list of JCombox with filenames
	 * @return ArrayList<JComboBox<String>>
	 */
	public ArrayList<JComboBox<String>> getAllFilenamesStored() {
		return fileResultsToExport;
	}
	
	public void update(Observable arg0, Object arg1) {
		
	}
}
