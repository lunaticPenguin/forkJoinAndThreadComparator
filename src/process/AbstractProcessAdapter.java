package process;

import models.AbstractDataContainer;
import algorithms.AbstractAlgorithm;

/**
 * This class define a base of an adapter able to play the role of a manager.
 * The goal is to use its adapter and manager abilities in child classes.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 *
 * @param <T>
 */
public abstract class AbstractProcessAdapter<T> implements IProcessAdapter<T> {
	
	protected AbstractAlgorithm processAlgorithm;
	protected T data;
	
	public AbstractProcessAdapter() {
		
	}
	
	/**
	 * This method allow developers to specify the algorithm which will be used,
	 * by this adapter
	 * @param T data
	 */
	public void setAlgorithm(AbstractAlgorithm algorithm) {
		processAlgorithm = algorithm;
		processAlgorithm.setDataContainer((AbstractDataContainer<?>) data);
	}
	
	/**
	 * This method allow developers to bind the data to an adapter,
	 * in order to send this data into an algorithm
	 * @param T data
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	protected abstract void init();
}
