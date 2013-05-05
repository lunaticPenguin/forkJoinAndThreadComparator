package controllers.listeners;

import java.awt.event.ActionEvent;

import models.PictureParts;

import controllers.AppController;
import views.MainWindowView;

/**
 * Action listener dedicated to the processButton (process launch) of the MainWindowView
 * The role this class has to ensure is to launch adequate processes which rights options
 * (algorithms & process type)
 * 
 * @author Corentin Legros
 *
 */
public class ProcessButtonListener extends AbstractActionListener<MainWindowView> {
	
	public ProcessButtonListener(MainWindowView parentView) {
		super(parentView);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		AppController controller = (AppController) parentView.getController();
		// set algorithm type
		controller.setAlgorithmType(parentView.getTypesAlgorithmComboBox().getSelectedIndex());
		
		// set process type (threads/forkJoin)
		controller.setProcessType(parentView.getTypesProcessComboBox().getSelectedIndex());
		
		// set threads/workers number
		((PictureParts) controller.getRefModel().getData()).setPartsNumber(parentView.getNWComboBox().getSelectedIndex());
		
		// then, the process is launched (through a delegation to the specified adapter which handle 
		// the (previously chosen) algorithm work and the appropriate data choice
		controller.launchProcess();
		
		System.out.println("Process launched :3");
	}
}
