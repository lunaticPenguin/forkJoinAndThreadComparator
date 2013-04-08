package process;

import algorithms.AbstractAlgorithm;

public abstract class AbstractProcessAdapter<T> implements IProcessAdapter {
	
	public final static int TYPE_PROCESS_THREAD = 0;
	public final static int TYPE_PROCESS_WORKERS = 1;
	
	protected AbstractAlgorithm processAlgorithm;
	protected T data;
	
	public AbstractProcessAdapter(AbstractAlgorithm algorithm, T data) {
		processAlgorithm = algorithm;
		this.data = data;
	}
}
