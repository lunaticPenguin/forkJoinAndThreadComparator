package controllers;

import java.util.ArrayList;
import java.util.Observable;

import algorithms.AbstractAlgorithm;
import algorithms.BinarisationAlgorithm;

import process.AbstractProcessAdapter;
import process.IProcessAdapter;
import process.ProcessThreadAdapter;
import process.ProcessWorkerAdapter;

import tools.Timer;
import views.AbstractView;
import views.MainWindowView;

import models.AbstractModel;
import models.PictureParts;
import models.ProgressionContainer;

public class AppController extends AbstractController {

	/**
	 * The chosen algorithm type
	 */
	protected int algorithmType;
	
	/**
	 * The chosen process type
	 */
	protected int processType;

	/**
	 * Available process adapters
	 */
	protected ArrayList<AbstractProcessAdapter<PictureParts>> availableAdapters;
	
	/**
	 * Available algorithms (used by process)
	 */
	protected ArrayList<AbstractAlgorithm> availableAlgorithms;
	
	protected Timer timer;
	
	protected int tmpLastPercent;
	
	/**
	 * Reference on the container used for reports
	 */
	protected ProgressionContainer progressionContainer;
	
	public AppController(AbstractModel model, AbstractView view) {
		super(model, view);
		
		// adding available Adapters
		availableAdapters = new ArrayList<AbstractProcessAdapter<PictureParts>>();
		availableAdapters.add(IProcessAdapter.PROCESS_TYPE_UNSELECTED, null); // ugly way, but it works.
		availableAdapters.add(IProcessAdapter.PROCESS_TYPE_THREAD, new ProcessThreadAdapter());
		availableAdapters.add(IProcessAdapter.PROCESS_TYPE_FORKJOIN, new ProcessWorkerAdapter());
		
		// adding available algorithms
		availableAlgorithms = new ArrayList<AbstractAlgorithm>();
		availableAlgorithms.add(AbstractAlgorithm.ALGORITHM_TYPE_UNSELECTED, null); // ugly way, but it works.
		availableAlgorithms.add(AbstractAlgorithm.ALGORITHM_TYPE_BINARISATION, new BinarisationAlgorithm());
		
		timer = Timer.getInstance();
		timer.setPickUpRange(5);
		tmpLastPercent = 0;
	}
	
	/**
	 * @return the algorithmType
	 */
	public int getAlgorithmType() {
		return algorithmType;
	}
	
	/**
	 * @param algorithmType the algorithmType to set
	 */
	public void setAlgorithmType(int algorithmType) {
		this.algorithmType = algorithmType;
	}
	
	/**
	 * @return the processType
	 */
	public int getProcessType() {
		return processType;
	}
	
	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(int processType) {
		this.processType = processType;
	}
	
	/**
	 * This method is in charge of launching the process.
	 * It sets all data to chosen algorithm and specified process adapter
	 * 
	 * @see ProcessButtonListener.actionPerformed()
	 */
	public void launchProcess() {
		AbstractProcessAdapter<PictureParts> adapter;
		adapter = availableAdapters.get(processType);
		adapter.setData((PictureParts) refModel.getData());

		availableAlgorithms.get(algorithmType).setDataContainer((PictureParts) refModel.getData());

		// this line is here in order to set a valid algorithm state to clone it and then change the working part (for each thread/worker)
		availableAlgorithms.get(algorithmType).setData(((PictureParts) refModel.getData()).getPart(0));
		adapter.setAlgorithm(availableAlgorithms.get(algorithmType));
		
		timer.start(processType, ((MainWindowView) refView).getChosenFileLabel().getText(), ((MainWindowView) refView).getNWComboBox().getSelectedIndex());
		adapter.execute();
		timer.stop();
		((MainWindowView) refView).getExportButton().setEnabled(true);
	}
	
	public void update(Observable o, Object arg) {
		// here will be the timer management
		// and all statistics generation of timing measures

		progressionContainer = (ProgressionContainer) arg;
		if (progressionContainer.getPercent() == 0 || progressionContainer.getPercent() > progressionContainer.getLastPercent()
				|| 
				(progressionContainer.getPercent() <= progressionContainer.getLastPercent() && progressionContainer.getPercent() >= 100 - timer.getPickUpRange())) {
			timer.addData(
				progressionContainer.getPartNumber()
			);
			progressionContainer.setLastPercent(progressionContainer.getLastPercent() + timer.getPickUpRange());
		}
	}
}
