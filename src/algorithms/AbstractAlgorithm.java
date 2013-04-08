package algorithms;

import java.awt.image.BufferedImage;

import tools.Timer;

public abstract class AbstractAlgorithm {
	
	/**
	 * Reference on the image which will be worked (or sub-image)
	 */
	protected BufferedImage refPicture;
	protected Timer timer;
	
	/**
	 * Constructor
	 * @param imageToProcess
	 */
	public AbstractAlgorithm(BufferedImage imageToProcess) {
		refPicture = imageToProcess;
		timer = Timer.getInstance();
	}
	
	public abstract BufferedImage algo();
}
