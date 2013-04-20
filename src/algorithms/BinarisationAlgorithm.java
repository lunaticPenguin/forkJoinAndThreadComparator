package algorithms;

import java.awt.image.BufferedImage;

import tools.Log;

/**
 * Algorithm which transform a picture as a binary-color picture
 * using thresholding method.
 * @author Corentin Legros
 */
public class BinarisationAlgorithm extends AbstractAlgorithm {
	
	protected int[] pixelsValue;
	protected int threshold;
	protected int indice;
	
	public BinarisationAlgorithm() {
		super();
	}
	
	public void setData(Object image) {
		data = (BufferedImage) image;
		pixelsValue = new int[((BufferedImage) data).getHeight() * ((BufferedImage) data).getWidth()];
		indice = 0;
	}
	
	@Override
	public BufferedImage algo() {
		
		int widthPicture = ((BufferedImage) data).getWidth();
		int heightPicture = ((BufferedImage) data).getHeight();
		
		for (int i = 0 ; i < heightPicture ; ++i) {
			for (int j = 0 ; j < widthPicture ; ++j) {
				if (((BufferedImage) data).getRGB(j, i) > computeCumulativeThresold(((BufferedImage) data).getRGB(j, i))) {
					((BufferedImage) data).setRGB(j, i, 0);
				} else {
					((BufferedImage) data).setRGB(j, i, 1);
				}
			}
			this.dataContainer.update();
		}
		return ((BufferedImage) data);
	}
	
	protected int computeCumulativeThresold(int pixelValue) {
		pixelsValue[indice] = pixelValue;
		
		int average = 0;
		for (int i = 0 ; i < indice ; ++i) {
			average += pixelsValue[i];
		}
		threshold = (threshold + average / (indice + 1)) / 2;
		++indice;
		return threshold;
	}
	
	public Object clone() {
		BinarisationAlgorithm algorithm = null;
	    try {
	    	algorithm = (BinarisationAlgorithm) super.clone();
			algorithm.setData(this.data);
			algorithm.setDataContainer(this.dataContainer);
	    } catch(CloneNotSupportedException cnse) {
	      	Log.warn("Fail during attempt algorithm cloning");
	    }
		return algorithm;
	}
}
