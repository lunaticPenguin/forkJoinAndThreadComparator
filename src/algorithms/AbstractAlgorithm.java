package algorithms;

import java.awt.image.BufferedImage;

import models.AbstractDataContainer;

import tools.Timer;

public abstract class AbstractAlgorithm implements Cloneable {
	
	public final static int ALGORITHM_TYPE_UNSELECTED = 0;
	public final static int ALGORITHM_TYPE_BINARISATION = 1;
	public final static int ALGORITHM_TYPE_CONVOLUTION = 2;
	
	/**
	 * Reference of the data container (plays full role of an observable model)
	 */
	protected AbstractDataContainer<?> dataContainer;
	
	/**
	 * Reference on the image which will be worked (or sub-image)
	 */
	protected Object data;
	protected Timer timer;
	
	/**
	 * Constructor
	 * @param imageToProcess
	 */
	public AbstractAlgorithm() {
		timer = Timer.getInstance();
	}
	
	public abstract BufferedImage algo();
	
	public Object getData() {
		return data;
	}
	
	public abstract void setData(Object data);
	
	public void setDataContainer(AbstractDataContainer<?> dataContainer) {
		this.dataContainer = dataContainer;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
