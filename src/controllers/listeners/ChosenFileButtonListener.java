package controllers.listeners;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import models.PictureParts;
import models.ProcessManagerModel;

import tools.Log;
import views.MainWindowView;
import views.filters.ImageFileFilter;

/**
 * Action listener dedicated to the chosenFileButton (image choice) of the MainWindowView
 * The role this class has to handle the chose of a new image file when the user click on the loading button.
 * @author Corentin Legros
 *
 */
public class ChosenFileButtonListener extends AbstractActionListener<MainWindowView>{

	protected JFileChooser dialogFileChooser;
	
	public ChosenFileButtonListener(MainWindowView parentView) {
		super(parentView);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (dialogFileChooser == null) {
			dialogFileChooser = new JFileChooser(System.getProperty("user.home"));
			dialogFileChooser.addChoosableFileFilter(new ImageFileFilter(new String[] {"gif","tif", "jpeg", "jpg", "tiff", "png", "bmp"}, "All files with an image extension"));
		}
		
		dialogFileChooser.showOpenDialog(parentView.getChosenFileButton());
		File loadedFile = dialogFileChooser.getSelectedFile();
		
		if (loadedFile == null) {
			parentView.getProcessButton().setEnabled(false);
			parentView.getChosenFileLabel().setText("No file specified");
		} else {
			
			BufferedImage tmpImg = null;
			try {
				tmpImg = ImageIO.read(loadedFile);
			} catch (IOException e) {
				Log.warn(String.format("Unable to read image source file (%s)", loadedFile.getPath()));
			}
			
			if (tmpImg != null) {
				// if file ok : we put it's name in the label object and enable reset button
				parentView.getResetButton().setEnabled(true);
				parentView.getChosenFileLabel().setText(loadedFile.getName());

				parentView.setImageToDisplay(tmpImg);
				parentView.getImagePanel().updateImage(tmpImg);
				
				// this fix a fucking bug!
				ProcessManagerModel model = (ProcessManagerModel) parentView.getModel();
				model.setData(new PictureParts(tmpImg));
				
				Log.info(String.format("File chosen : %s", loadedFile.getPath()));
			}
		}
	}
	
}
