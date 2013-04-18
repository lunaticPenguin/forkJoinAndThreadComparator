package algorithms;

import java.awt.image.BufferedImage;

public abstract class AbstractAlgorithm {
	
	public final static int ALGORITHM_TYPE_UNSELECTED = 0;
	public final static int ALGORITHM_TYPE_BINARISATION = 1;
	public final static int ALGORITHM_TYPE_CONVOLUTION = 2;
	
	/**
	 * Reference on the image which will be worked (or sub-image)
	 */
	protected Object data;
	
	/**
	 * Constructor
	 * @param imageToProcess
	 */
	public AbstractAlgorithm() {}
	
	public abstract BufferedImage algo();
	
	public Object getData() {
		return data;
	}
	
	public abstract void setData(Object data);
}
