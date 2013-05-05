package process.forkjoin;

import java.util.concurrent.RecursiveAction;

import algorithms.AbstractAlgorithm;

public abstract class AbstractWorkerAction<T> extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6998050359240523048L;
	
	protected AbstractAlgorithm processAlgorithm;
	protected T data;
	
	/**
	 * This method allow developers to specify the algorithm which will be used,
	 * by this adapter
	 * @param T data
	 */
	public void setAlgorithm(AbstractAlgorithm algorithm) {
		try {
			this.processAlgorithm = (AbstractAlgorithm) algorithm.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method allow developers to get the specified algorithm
	 * @return AbstractAlgorithm
	 */
	public AbstractAlgorithm getAlgorithm() {
		return processAlgorithm;
	}
	
	/**
	 * This method allow developers to bind the data to an adapter,
	 * in order to send this data into an algorithm
	 * @param T data
	 */
	public void setData(T data) {
		this.data = data;
	}
}
