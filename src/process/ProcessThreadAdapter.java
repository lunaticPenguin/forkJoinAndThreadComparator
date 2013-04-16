package process;

import models.PictureParts;

/**
 * This class play a role of threads manager in addition to be an adapter instance.
 * @author Corentin Legros
 *
 */
public class ProcessThreadAdapter extends AbstractProcessAdapter<PictureParts> {
	
	public ProcessThreadAdapter() {
		super();
	}

	public void execute() {
		processAlgorithm.setData(this.data.getPart(0));
		processAlgorithm.algo();
	}
}
