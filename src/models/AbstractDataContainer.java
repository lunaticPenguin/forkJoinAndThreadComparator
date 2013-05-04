package models;

import java.util.Observable;

/**
 * Class in charge of containing computed data and delegate the update signal.
 * Then this signal is delegate to others observers like controllers and views.
 */
public abstract class AbstractDataContainer<T> extends Observable {

	/**
	 * Picture used as reference to build all image parts
	 */
	protected T data;
	
	/**
	 * Reference on an array object in charge of transporting the sampled data
	 * for each data parts
	 */
	protected ProgressionContainer[] progressionContainers;
	
	/**
	 * Allow to update the data container in order to delegate the captured data update signal
	 */
	public void update(int partNum, int percent) {
		progressionContainers[partNum].setPartNumber(partNum);
		progressionContainers[partNum].setPercent(percent);
		setChanged();
		notifyObservers(progressionContainers[partNum]);
	}
}
