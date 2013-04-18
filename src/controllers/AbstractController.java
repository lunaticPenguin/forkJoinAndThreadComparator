package controllers;

import java.util.Observer;

import views.AbstractView;

import models.AbstractModel;

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
}
