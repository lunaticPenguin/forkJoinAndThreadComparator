package algorithms;

import java.awt.image.BufferedImage;

public abstract class AbstractAlgorithm {
	
	/**
	 * Reference on the image which will be worked (or sub-image)
	 */
	protected BufferedImage refPicture;
	
	/**
	 * Constructor
	 * @param imageToProcess
	 */
	public AbstractAlgorithm(BufferedImage imageToProcess) {
		refPicture = imageToProcess;
	}
	
	public abstract BufferedImage algo();
}
