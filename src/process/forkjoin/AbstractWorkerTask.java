package process.forkjoin;

import java.util.concurrent.RecursiveTask;

import algorithms.AbstractAlgorithm;

public abstract class AbstractWorkerTask<T> extends RecursiveTask<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4762278702291692328L;
	
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
