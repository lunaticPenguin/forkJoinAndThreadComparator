package process;

import java.util.ArrayList;

import algorithms.BinarisationAlgorithm;
import process.threads.ProcessPictureThread;

import models.PictureParts;

/**
 * This class play a role of threads manager in addition to be an adapter instance.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
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
		int numberThreads = this.data.getPartsNumber();
		for (int i = 0 ; i < numberThreads ; ++i) {
			tmpRefThread = new ProcessPictureThread();
			tmpRefThread.setAlgorithm(processAlgorithm);
			tmpRefThread.setData(data);
			tmpRefThread.setPartNumber(i);

			((BinarisationAlgorithm) tmpRefThread.getAlgorithm()).setPartNumber(i); // used for reports
			
			threads.add(tmpRefThread);
		}
	}

	public void execute() {
		init();
		
		int numberThreads = this.data.getPartsNumber();
		for (int i = 0 ; i < numberThreads ; ++i) {
			threads.get(i).start();
		}
		for (int i = 0 ; i < numberThreads ; ++i) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
