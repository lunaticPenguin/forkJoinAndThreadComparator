package process;

import java.util.ArrayList;
import process.threads.ProcessPictureThread;

import models.PictureParts;

/**
 * This class play a role of threads manager in addition to be an adapter instance.
 * @author Corentin Legros
 *
 */
public class ProcessThreadAdapter extends AbstractProcessAdapter<PictureParts> {
	
	protected ArrayList<ProcessPictureThread> threads;
	
	public ProcessThreadAdapter() {
		super();
		threads = new ArrayList<ProcessPictureThread>();
	}
	
	protected void init() {
		threads.clear();
		ProcessPictureThread tmpRefThread;
		int numberThreads = this.data.getPartsNumber() - 1; // because the parent process IS a process
		for (int i = 0 ; i < numberThreads ; ++i) {
			tmpRefThread = new ProcessPictureThread();
			tmpRefThread.setAlgorithm(processAlgorithm);
			tmpRefThread.setData(data);
			tmpRefThread.setPartNumber(i);
			threads.add(tmpRefThread);
		}
	}

	public void execute() {
		init();
		
		int numberThreads = this.data.getPartsNumber() - 1; // because the parent process IS a process
		for (int i = 0 ; i < numberThreads ; ++i) {
			threads.get(i).start();
		}
	}
}
