package views;

import java.util.Observer;

import javax.swing.JFrame;

import models.AbstractModel;
import controllers.AbstractController;

public abstract class AbstractView implements Observer {
	
	protected AbstractModel refModel;
	protected AbstractController refController;
	protected AbstractView parentView;
	
	/**
	 * UI main element
	 */
	protected JFrame mainFrame;
	
	public AbstractView(AbstractModel model) {
		refModel = model;
		if (refModel != null) {
			refModel.addObserver(this);
		}
	}

	/**
	 * Get the model bound to this view
	 * @return AbstractModel the model
	 */
	public AbstractModel getModel() {
		return refModel;
	}

	/**
	 * Get the controller bound to this view
	 * @return AbstractController the controller
	 */
	public AbstractController getController() {
		return refController;
	}
	
	/**
	 * Set the parent view, if it exists.
	 * @param parentView
	 */
	public void setParentView(AbstractView parentView) {
		this.parentView = parentView;
	}
	
	/**
	 * Get the parent view if specified
	 * @return
	 */
	public AbstractView getParentView() {
		return parentView;
	}
	
	/**
	 * Get the main frame of the current view
	 * @return JFrame
	 */
	public JFrame getMainFrame() {
		return mainFrame;
	}
}
