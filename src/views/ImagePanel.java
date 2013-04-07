package views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * Class allowing view to render an image.
 * @author Corentin Legros
 *
 */
public class ImagePanel extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Reference on the image to display
	 */
	BufferedImage refImageToDisplay;
	
	/**
	 * Allow listener to update the internal image state.
	 * If the image is set to null, the visible flag 
	 */
	public void updateImage(BufferedImage imageToDisplay) {
		refImageToDisplay = imageToDisplay;
		setVisible(imageToDisplay != null);
	}
	
	/**
	 * Override internal render components method
	 * in order to display the image.
	 */
	protected void paintComponent(Graphics g) {
		if (refImageToDisplay != null) {
			g.drawImage(refImageToDisplay, 0, 0, null);
		}
	}
}
