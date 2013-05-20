package process;

import java.util.concurrent.ForkJoinPool;

import algorithms.BinarisationAlgorithm;

import process.forkjoin.ProcessPictureWorkerAction;
import models.PictureParts;

/**
 * This class play a role of workers manager in addition to be an adapter instance.
 * It use a threads pool through the ForkJoinPool class.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
public class ProcessWorkerAdapter extends AbstractProcessAdapter<PictureParts> {

	protected ForkJoinPool workersPool;
	protected ProcessPictureWorkerAction worker;
	
	public ProcessWorkerAdapter() {
		super();
		workersPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
	}
	
	@Override
	public void execute() {
		init();
		workersPool.invoke(worker);
		worker = null;
	}

	@Override
	protected void init() {
		worker = new ProcessPictureWorkerAction();
		worker.setAlgorithm(processAlgorithm);
		worker.setData(data);
		worker.setPartNumber(0);
		

		((BinarisationAlgorithm) worker.getAlgorithm()).setPartNumber(0); // used for reports
		
	}

}
