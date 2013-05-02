package controllers.listeners;

import java.awt.event.ActionEvent;
import views.ExportResultsSettingsView;

/**
 * Listener used when the user change the process type value in the
 * list.
 * It changes the displayed list (files processed with threads method and
 * files processed with forkjoin method)
 * 
 * @author Corentin Legros
 *
 */
public class ExportResultsSettingsProcessChoiceActionListener extends AbstractActionListener<ExportResultsSettingsView> {
	
	public ExportResultsSettingsProcessChoiceActionListener(ExportResultsSettingsView parentView) {
		super(parentView);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		int activeIndex = parentView.getProcessChoice().getSelectedIndex();
		if (activeIndex != 0 && parentView.getAllFilenamesStored().get(activeIndex).getItemCount() != 0) {
			
			int inactiveIndex = activeIndex == 1 ? 2 : 1;
			parentView.getAllFilenamesStored().get(0).setVisible(false);
			parentView.getAllFilenamesStored().get(activeIndex).setVisible(true);
			parentView.getAllFilenamesStored().get(inactiveIndex).setVisible(false);
			parentView.getExportButton().setEnabled(true);
		} else {
			parentView.getAllFilenamesStored().get(0).setVisible(true);
			parentView.getAllFilenamesStored().get(1).setVisible(false);
			parentView.getAllFilenamesStored().get(2).setVisible(false);
			parentView.getExportButton().setEnabled(false);
		}
	}
}
