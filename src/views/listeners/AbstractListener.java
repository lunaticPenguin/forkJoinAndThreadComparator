package views.listeners;

import java.awt.event.ActionListener;

public abstract class AbstractListener<T> implements ActionListener {
	
	/**
	 * Parent view object which contains the widget where an action was performed
	 */
	T parentView;
	
	public AbstractListener(T parentView) {
		this.parentView = parentView;
	}
}
