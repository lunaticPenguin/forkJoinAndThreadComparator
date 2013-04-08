package controllers.listeners;

import java.awt.event.ActionEvent;
import views.MainWindowView;

/**
 * Action listener dedicated to the processButton (process launch) of the MainWindowView
 * The role this class has to ensure is to launch adequate processes which rights options
 * (algorithms & process type)
 * 
 * @author Corentin Legros
 *
 */
public class ProcessButtonListener extends AbstractListener<MainWindowView> {
	
	public ProcessButtonListener(MainWindowView parentView) {
		super(parentView);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Need to launch process :3");
	}
}
