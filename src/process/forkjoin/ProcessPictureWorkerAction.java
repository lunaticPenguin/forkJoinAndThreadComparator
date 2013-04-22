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
			for (int i = 1 ; i < this.data.getPartsNumber() ; ++i) {
				tmpWorker = (ProcessPictureWorkerAction) this.fork();
				tmpWorker.setAlgorithm(processAlgorithm);
				tmpWorker.setHasToBeDivided(false); // we stop the recursivity on the first level
				
				tmpWorker.setData(data);
				tmpWorker.setPartNumber(i);
				subWorkers.add(tmpWorker);
			}
			processAlgorithm.setData(this.data.getPart(partNumber));
			processAlgorithm.algo();
			
			// just wait a little bit for the result! :)
			for (ProcessPictureWorkerAction subWorker : subWorkers) {
				subWorker.join();
			}
		}
	}
}
