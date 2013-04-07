package views.listeners;

import java.awt.event.ActionEvent;
import views.MainWindowView;

/**
 * Action listener dedicated to the jcombobox (Algorithms & Process management) in the MainWindowView
 * All views interactions with these widgets have to be handled here.
 * 
 * @author Corentin Legros
 *
 */
public class AlgorithmsProcessListener extends AbstractListener<MainWindowView> {
	
	public AlgorithmsProcessListener(MainWindowView parentView) {
		super(parentView);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		parentView.getProcessButton().setEnabled(false);
		if (parentView.getTypesAlgorithmComboBox().getSelectedIndex() != MainWindowView.ALGORITHM_TYPE_UNSELECTED
				&& parentView.getTypesProcessComboBox().getSelectedIndex() != MainWindowView.PROCESS_TYPE_UNSELECTED) {
			if (parentView.getImageToDisplay() != null) {
				parentView.getProcessButton().setEnabled(true);
			}
		}
	}
	
}
