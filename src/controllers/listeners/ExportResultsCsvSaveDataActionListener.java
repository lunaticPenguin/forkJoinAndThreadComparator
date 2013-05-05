package controllers.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import views.ExportResultsCsvView;


/**
 * Listener responsible for chosing data file to save generated values
 * 
 * @author Corentin Legros
 */
public class ExportResultsCsvSaveDataActionListener extends AbstractActionListener<ExportResultsCsvView> {

	public ExportResultsCsvSaveDataActionListener(
			ExportResultsCsvView parentView) {
		super(parentView);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (parentView.getFileChooser().showSaveDialog(parentView.getMainFrame()) == JFileChooser.APPROVE_OPTION) {
			parentView.setDataFile(parentView.getFileChooser().getSelectedFile());
			parentView.saveData();
		}
	}
}
