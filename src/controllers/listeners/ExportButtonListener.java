package controllers.listeners;

import java.awt.event.ActionEvent;

import tools.StatisticsRecorder;
import views.ExportResultsSettingsView;
import views.MainWindowView;


/**
 * Listener called when the user decides to export stored data.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
public class ExportButtonListener extends AbstractActionListener<MainWindowView> {
	
	public ExportButtonListener(MainWindowView parentView) {
		super(parentView);
	}
	
	public void actionPerformed(ActionEvent e) {
		ExportResultsSettingsView exportView = new ExportResultsSettingsView(null);
		exportView.setParentView(parentView);
		exportView.init(StatisticsRecorder.getInstance().getThreadProcessData(), StatisticsRecorder.getInstance().getForkJoinProcessData());
	}
}
