package process;

import java.util.concurrent.ForkJoinPool;

import process.forkjoin.ProcessPictureWorkerAction;
import models.PictureParts;

/**
 * This class play a role of workers manager in addition to be an adapter instance.
 * @author Corentin Legros
 *
 */
public class ProcessWorkerAdapter extends AbstractProcessAdapter<PictureParts> {

	protected ForkJoinPool workersPool;
	protected ProcessPictureWorkerAction worker;
	
	public ProcessWorkerAdapter() {
		super();
		workersPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		worker = new ProcessPictureWorkerAction();
	}
	
	@Override
	public void execute() {
		workersPool.invoke(worker);
	}

	@Override
	protected void init() {
		System.out.println("Worker adapter has been called! :|");
		execute();
	}

}
