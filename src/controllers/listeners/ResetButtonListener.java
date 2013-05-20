package controllers.listeners;

import java.awt.event.ActionEvent;
import views.MainWindowView;

/**
 * Action listener dedicated to the resetButton (reset image source) of the MainWindowView
 * All views interactions with these widgets have to be handled here.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
public class ResetButtonListener extends AbstractActionListener<MainWindowView> {
	
	public ResetButtonListener(MainWindowView parentView) {
		super(parentView);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		parentView.setImageToDisplay(null);
		parentView.getImagePanel().updateImage(null);
		parentView.getProcessButton().setEnabled(false);
		parentView.getChosenFileLabel().setText("No file specified");
		parentView.getResetButton().setEnabled(false);
	}
	
}
