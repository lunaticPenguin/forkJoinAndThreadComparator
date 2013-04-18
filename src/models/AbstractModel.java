package models;

import java.util.Observable;
import java.util.Observer;

/**
 * Abtract model, in order to factorize some code
 * @author Corentin Legros
 */
public abstract class AbstractModel extends Observable implements Observer {
	
	protected Object data;

	public abstract Object getData();
	
	public abstract void setData(Object data);
}
