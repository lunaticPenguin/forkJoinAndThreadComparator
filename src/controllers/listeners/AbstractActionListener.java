package controllers.listeners;

import java.awt.event.ActionListener;

/**
 * Listener on a generic view objects, allowing developers to easily creates a new one
 * and bound it to a view.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 *
 * @param Custom view class <T>
 */
public abstract class AbstractActionListener<T> implements ActionListener {
	
	/**
	 * Parent view object which contains the widget where an action was performed
	 */
	protected T parentView;
	
	public AbstractActionListener(T parentView) {
		this.parentView = parentView;
	}
}
