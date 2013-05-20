package controllers;

import java.util.Observer;

import views.AbstractView;

import models.AbstractModel;

/**
 * Common controller definition.
 * It implements the Observer interface in order to watch the model's 
 * (PictureParts) changes.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 * 
 * @see models.AbstractModel (/!\ be careful on this class, it is an unusual case)
 * @see views.AbstractView
 */
public abstract class AbstractController implements Observer {
	
	/**
	 * Reference to the model used by this controller
	 */
	AbstractModel refModel;
	
	/**
	 * Reference to the view used by this controller
	 */
	AbstractView refView;
	
	public AbstractController(AbstractModel model, AbstractView view) {
		refModel = model;
		refView = view;
		refModel.addObserver(this);
	}

	/**
	 * @return the refModel
	 */
	public AbstractModel getRefModel() {
		return refModel;
	}

	/**
	 * @param refModel the refModel to set
	 */
	public void setRefModel(AbstractModel refModel) {
		this.refModel = refModel;
	}

	/**
	 * @return the refView
	 */
	public AbstractView getRefView() {
		return refView;
	}

	/**
	 * @param refView the refView to set
	 */
	public void setRefView(AbstractView refView) {
		this.refView = refView;
	}
}
