package views;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import controllers.listeners.ExportResultsCsvSaveDataActionListener;

import tools.Log;

import models.AbstractModel;

/**
 * This view allow user to save csv data format in the specified file he chooses.
 * @author Corentin Legros
 *
 */

public class ExportResultsCsvView extends AbstractView {
	
	File dataFile;
	
	
	/* GUI elements */
	
	protected JPanel mainPanel;
	
	protected JToolBar toolBar;
	protected JButton saveButton;
	
	protected JTextArea textArea;
	
	protected JFileChooser fileChooser;
	
	public ExportResultsCsvView(AbstractModel model) {
		super(null);
	}
	
	/**
	 * Initialize the view
	 */
	public void init(String csvData) {
		
		fileChooser = new JFileChooser(System.getProperty("user.home"));
		
		saveButton = new JButton(new ImageIcon("data/save.png"));
		saveButton.addActionListener(new ExportResultsCsvSaveDataActionListener(this));
		
		toolBar = new JToolBar(JToolBar.HORIZONTAL);
		toolBar.add(saveButton);
		toolBar.setFloatable(false);
		
		textArea = new JTextArea(70, 20);
		textArea.setEditable(false);
		textArea.setText(csvData);
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(toolBar, BorderLayout.NORTH);
		mainPanel.add(textArea, BorderLayout.CENTER);
		
		mainFrame = new JFrame();
		mainFrame.setTitle("Export data");
		mainFrame.setSize(500, 500);
		mainFrame.setLocationRelativeTo(getParentView().getMainFrame());
		mainFrame.setContentPane(mainPanel);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void update(Observable arg0, Object arg1) {
		
	}
	
	/**
	 * @param dataFile the dataFile to set
	 */
	public void setDataFile(File dataFile) {
		this.dataFile = dataFile;
	}

	/**
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * @return the fileChooser
	 */
	public JFileChooser getFileChooser() {
		return fileChooser;
	}
	
	public void saveData() {
		try {
			PrintWriter pW = new PrintWriter(dataFile);
			pW.write(textArea.getText());
			pW.flush();
			pW.close();
		} catch (FileNotFoundException e) {
			Log.warn("Unable to save csv data file!");
			e.printStackTrace();
		}
	}
}
