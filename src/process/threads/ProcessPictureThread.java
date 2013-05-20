package process.threads;

import models.PictureParts;

/**
 * This class is an implementation of the AbstractProcessThread.
 * It is able to do a work on a BufferedImage (from a PictureParts).
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
public class ProcessPictureThread extends AbstractProcessThread<PictureParts> {
	protected int partNumber;
	
	/**
	 * Allow to set the data part number which has to be processed
	 * by the chosen algorithm
	 */
	public void setPartNumber(int part) {
		partNumber = part;
	}
	
	public void run() {
		processAlgorithm.setData(this.data.getPart(partNumber));
		processAlgorithm.algo();
	}
}
