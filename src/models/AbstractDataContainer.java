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
	 * Reference on an object in charge of transporting the sampled data
	 */
	protected ProgressionContainer progressionContainer;
	
	/**
	 * Allow to update the data container in order to delegate the captured data update signal
	 */
	public void update(int partNum, int percent) {
		progressionContainer.setPartNumber(partNum);
		progressionContainer.setPercent(percent);
		setChanged();
		notifyObservers(progressionContainer);
	}
}
