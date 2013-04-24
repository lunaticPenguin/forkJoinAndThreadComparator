package process.forkjoin;

import java.util.ArrayList;

import models.PictureParts;

public class ProcessPictureWorkerAction extends AbstractWorkerAction<PictureParts> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int partNumber;
	
	protected boolean hasToBeDivided;
	
	protected ArrayList<ProcessPictureWorkerAction> subWorkers;
	
	public ProcessPictureWorkerAction() {
		hasToBeDivided = true; // by default the worker has a recursive behavior
		subWorkers = new ArrayList<ProcessPictureWorkerAction>();
	}
	
	public void setHasToBeDivided(boolean hasToBeDivided) {
		this.hasToBeDivided = hasToBeDivided;
	}
	
	/**
	 * Allow to set the data part number which has to be processed
	 * by the chosen algorithm
	 */
	public void setPartNumber(int part) {
		partNumber = part;
	}

	@Override
	protected void compute() {
		if (hasToBeDivided) {
			ProcessPictureWorkerAction tmpWorker;
			
			int numberForks = this.data.getPartsNumber() - 1; // because the parent process IS a process
			for (int i = 1 ; i < numberForks ; ++i) {
				tmpWorker = new ProcessPictureWorkerAction();
				tmpWorker.setAlgorithm(processAlgorithm);
				tmpWorker.setData(data);
				tmpWorker.setPartNumber(i);
				tmpWorker.setHasToBeDivided(false); // we stop the recursivity on the first level
				
				((BinarisationAlgorithm) tmpWorker.getAlgorithm()).setPartNumber(0); // used for reports
				
				// In advance of forking the new worker process,
				// we store the worker into a list in order to join it later
				subWorkers.add(tmpWorker);
				tmpWorker.fork();
			}
		}
		
		// launch parent & child process (when their compute method will be call)
		processAlgorithm.setData(this.data.getPart(partNumber));
		processAlgorithm.algo();
		
		// then we join them
		for (ProcessPictureWorkerAction subWorker : subWorkers) {
			subWorker.join();
		}
		
		// we clear the worker's list because they will be no more used
		subWorkers.clear();
	}
}
