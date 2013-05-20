package models;

import java.util.Observable;
import java.util.Observer;

/**
 * Abstract model, in order to factorize some code.
 * A particularity of this model is that it implements the "Observer"
 * interface and inherits from the "Observable" abstract class.
 * 
 * The explanation is that the model is used to delegate an update of a watched
 * data to its observers.
 * 
 * To summary:
 * This is a model which observes a sub-model which will have some updates.
 * These updates will be notified to potential observers 
 * (this model in this case) by the sub-model and this model is in charge of
 * delegating received update notifications to its own observers
 * (controllers and views).
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 * 
 * @see controllers.AbstractController
 * @see views.AbstractView
 */
public abstract class AbstractModel extends Observable implements Observer {
	
	protected Object data;

	public abstract Object getData();
	
	public abstract void setData(Object data);
}
