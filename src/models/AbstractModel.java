package models;

import java.util.Observable;

/**
 * Abtract model, in order to factorize some code
 * @author Corentin Legros
 */
public abstract class AbstractModel extends Observable {
	
	protected Object data;

	public abstract Object getData();
	
	public abstract void setData(Object data);
}
