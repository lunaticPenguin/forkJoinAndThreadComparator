package process.forkjoin;

import java.util.concurrent.RecursiveTask;

import algorithms.AbstractAlgorithm;

/**
 * This class is an example of another kind of use of the forkJoin framework.
 * We can use this to execute a job which provides a result at the end of 
 * the compute() method call (of the child class)
 * (Indeed, we can note that the compute() method returns 
 * an "Object" result)
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 * 
 * @param <T>
 */
public abstract class AbstractWorkerTask<T> extends RecursiveTask<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4762278702291692328L;
	
	protected AbstractAlgorithm processAlgorithm;
	
	/**
	 * Template type, to specify at inheritance.
	 */
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
