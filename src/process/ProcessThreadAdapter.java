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
		ProcessPictureThread tmpRefThread;
		for (int i = 0 ; i < data.getPartsNumber() ; ++i) {
			tmpRefThread = new ProcessPictureThread();
			tmpRefThread.setAlgorithm(processAlgorithm);
			tmpRefThread.setData(data);
			tmpRefThread.setPartNumber(i);
			threads.add(tmpRefThread);
		}
	}

	public void execute() {
		
		for (int i = 0 ; i < data.getPartsNumber() ; ++i) {
			threads.get(i).start();
		}
	}
}
