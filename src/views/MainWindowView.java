package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import views.listeners.AbstractListener;
import views.listeners.AlgorithmsProcessListener;
import views.listeners.ChosenFileButtonListener;
import views.listeners.ProcessButtonListener;
import views.listeners.ResetButtonListener;

import controllers.AppController;

import models.AbstractModel;

/**
 * Provide the main window of the application.
 * It uses generic listeners to ensure a maximum of flexibility.
 * 
 * @author Corentin Legros
 */
public class MainWindowView extends AbstractView {
	
	/**
	 * Static class fields which defined combobox array indexes
	 */
	public final static int ALGORITHM_TYPE_UNSELECTED = 0;
	public final static int ALGORITHM_TYPE_BINARISATION = 1;
	public final static int ALGORITHM_TYPE_CONVOLUTION = 2;
	

	public final static int PROCESS_TYPE_UNSELECTED = 0;
	public final static int PROCESS_TYPE_THREAD = 1;
	public final static int PROCESS_TYPE_FORKJOIN = 2;
	
	
	
	/**
	 * UI elements
	 */
	protected JPanel mainPanel;
	
	protected JPanel topPanel;
	protected JPanel filePanel;
	protected JButton chosenFileButton;
	protected JLabel chosenFileLabel;
	protected JButton resetButton;

	protected JPanel actionPanel;
	protected JComboBox<String> typesAlgorithmComboBox;
	protected JComboBox<String> typesProcessComboBox;
	protected JButton processButton;
	
	protected ImagePanel imagePanel;
	protected BufferedImage imageToDisplay;
	
	public MainWindowView(AbstractModel model) {
		super(model);
		refController = new AppController();
		buildFrameUI();
	}

	private void buildFrameUI() {
		
		/*
		 * FilePanel settings
		 */
		
		filePanel = new JPanel();
		filePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		chosenFileButton = new JButton("Chose picture file");
		chosenFileButton.addActionListener(new ChosenFileButtonListener(this));
		
		chosenFileLabel = new JLabel("No file specified");
		resetButton = new JButton("X");
		resetButton.setEnabled(false);
		resetButton.addActionListener(new ResetButtonListener(this));
		resetButton.setToolTipText("Reset image");
		
		filePanel.add(chosenFileButton);
		filePanel.add(chosenFileLabel);
		filePanel.add(resetButton);
		
		/*
		 * actionPanel settings
		 */
		actionPanel = new JPanel();
		actionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		AbstractListener<MainWindowView> aPListener = new AlgorithmsProcessListener(this);
		typesAlgorithmComboBox = new JComboBox<String>(new String[]{"Algorithm type", "Binarisation"}); // , "Convolution"
		typesAlgorithmComboBox.addActionListener(aPListener);
		typesProcessComboBox = new JComboBox<String>(new String[]{"Process type", "Thread"}); // , "ForkJoin"
		typesProcessComboBox.addActionListener(aPListener);
		
		processButton = new JButton("Go!");
		processButton.setEnabled(false);
		processButton.addActionListener(new ProcessButtonListener(this));
		
		actionPanel.add(typesAlgorithmComboBox);
		actionPanel.add(typesProcessComboBox);
		actionPanel.add(processButton);
		
		
		/*
		 * imagePanel settings
		 */
		imagePanel = new ImagePanel();
		imagePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		
		/*
		 * topPanel settings
		 */
		topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(filePanel);
		topPanel.add(actionPanel);
		
		/*
		 * MainPanel settings
		 */
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(imagePanel, BorderLayout.CENTER);
		
		/*
		 * MainFrame settings
		 */
		mainFrame = new JFrame();
		mainFrame.setTitle("Compare performance of forkJoin vs thread");
		mainFrame.setSize(800, 800);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setContentPane(mainPanel);
		mainFrame.setVisible(true);
		
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * UI elements accessors
	 */
	
	/**
	 * @return the chosenFileButton
	 */
	public JButton getChosenFileButton() {
		return chosenFileButton;
	}

	/**
	 * @param chosenFileButton the chosenFileButton to set
	 */
	public void setChosenFileButton(JButton chosenFileButton) {
		this.chosenFileButton = chosenFileButton;
	}

	/**
	 * @return the chosenFileLabel
	 */
	public JLabel getChosenFileLabel() {
		return chosenFileLabel;
	}

	/**
	 * @param chosenFileLabel the chosenFileLabel to set
	 */
	public void setChosenFileLabel(JLabel chosenFileLabel) {
		this.chosenFileLabel = chosenFileLabel;
	}

	/**
	 * @return the resetButton
	 */
	public JButton getResetButton() {
		return resetButton;
	}

	/**
	 * @param resetButton the resetButton to set
	 */
	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}

	/**
	 * @return the typesAlgorithmComboBox
	 */
	public JComboBox<String> getTypesAlgorithmComboBox() {
		return typesAlgorithmComboBox;
	}

	/**
	 * @param typesAlgorithmComboBox the typesAlgorithmComboBox to set
	 */
	public void setTypesAlgorithmComboBox(JComboBox<String> typesAlgorithmComboBox) {
		this.typesAlgorithmComboBox = typesAlgorithmComboBox;
	}

	/**
	 * @return the typesProcessComboBox
	 */
	public JComboBox<String> getTypesProcessComboBox() {
		return typesProcessComboBox;
	}

	/**
	 * @param typesProcessComboBox the typesProcessComboBox to set
	 */
	public void setTypesProcessComboBox(JComboBox<String> typesProcessComboBox) {
		this.typesProcessComboBox = typesProcessComboBox;
	}

	/**
	 * @return the processButton
	 */
	public JButton getProcessButton() {
		return processButton;
	}

	/**
	 * @param processButton the processButton to set
	 */
	public void setProcessButton(JButton processButton) {
		this.processButton = processButton;
	}

	/**
	 * @return the imageToDisplay
	 */
	public BufferedImage getImageToDisplay() {
		return imageToDisplay;
	}

	/**
	 * @param imageToDisplay the imageToDisplay to set
	 */
	public void setImageToDisplay(BufferedImage imageToDisplay) {
		this.imageToDisplay = imageToDisplay;
	}
	
	/**
	 * @return ImagePanel the imagePanel
	 */
	public ImagePanel getImagePanel() {
		return imagePanel;
	}
	
	/**
	 * Shortcut method.
	 * (Dirty way)
	 */
	public void renderImage() {
		imagePanel.updateImage(imageToDisplay);
	}

	
	// -------------------------
	
	/**
	 * Method used on model update
	 * the internal BufferedImage is refresh there
	 */
	public void update(Observable arg0, Object arg1) {
		// here will come the cool stuff :)
	}
}
