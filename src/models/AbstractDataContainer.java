package models;

import java.util.Observable;

public abstract class AbstractDataContainer<T> extends Observable {

	/**
	 * Picture used as reference to build all image parts
	 */
	protected T data;
}
