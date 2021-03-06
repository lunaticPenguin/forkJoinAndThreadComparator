package algorithms;

import models.AbstractDataContainer;

/**
 * Common algorithm definition
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
public abstract class AbstractAlgorithm implements Cloneable {
	
	/**
	 * Reference of the data container (plays full role of an observable model)
	 */
	protected AbstractDataContainer<?> dataContainer;
	
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

	public abstract Object algo();
	
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
