package controllers;

import java.util.ArrayList;
import java.util.Observable;

import algorithms.AbstractAlgorithm;
import algorithms.BinarisationAlgorithm;

import process.AbstractProcessAdapter;
import process.IProcessAdapter;
import process.ProcessThreadAdapter;

import views.AbstractView;

import models.AbstractModel;
import models.PictureParts;

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
	
	
	public AppController(AbstractModel model, AbstractView view) {
		super(model, view);
		
		// adding available Adapters
		availableAdapters = new ArrayList<AbstractProcessAdapter<PictureParts>>();
		availableAdapters.add(IProcessAdapter.PROCESS_TYPE_UNSELECTED, null); // ugly way, but it works.
		availableAdapters.add(IProcessAdapter.PROCESS_TYPE_THREAD, new ProcessThreadAdapter());
		
		// adding available algorithms
		availableAlgorithms = new ArrayList<AbstractAlgorithm>();
		availableAlgorithms.add(AbstractAlgorithm.ALGORITHM_TYPE_UNSELECTED, null); // ugly way, but it works.
		availableAlgorithms.add(AbstractAlgorithm.ALGORITHM_TYPE_BINARISATION, new BinarisationAlgorithm());
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
		adapter.execute();
	}
	
	public void update(Observable o, Object arg) {
		// here will be the timer management
		// and all statistics generation of timing measures
	}
}
