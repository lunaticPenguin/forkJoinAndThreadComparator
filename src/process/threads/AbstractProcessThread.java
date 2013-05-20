package process.threads;

import process.ProcessThreadAdapter;
import algorithms.AbstractAlgorithm;

/**
 * This class defines an abstract code of a thread, which can be used by
 * the a thread manager (its role is to wait (by the join() method call)
 * the thread's result).
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 *
 * @param <T>
 * @see ProcessThreadAdapter
 */
public abstract class AbstractProcessThread<T> extends Thread {
	
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
			// TODO Auto-generated catch block
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
