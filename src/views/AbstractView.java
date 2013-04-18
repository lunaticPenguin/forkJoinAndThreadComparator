package views;

import java.util.Observer;

import javax.swing.JFrame;

import models.AbstractModel;
import controllers.AbstractController;

public abstract class AbstractView implements Observer {
	
	protected AbstractModel refModel;
	protected AbstractController refController;
	
	/**
	 * UI main element
	 */
	protected JFrame mainFrame;
	
	public AbstractView(AbstractModel model) {
		refModel = model;
		refModel.addObserver(this);
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
	
	
}
