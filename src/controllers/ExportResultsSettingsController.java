package controllers;

import java.util.Observable;

import models.AbstractModel;
import views.AbstractView;


/**
 * This controller handle export of internal data.
 * @author Corentin Legros
 */
public class ExportResultsSettingsController extends AbstractController {

	public ExportResultsSettingsController(AbstractModel model,
			AbstractView view) {
		super(null, view);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	
}
