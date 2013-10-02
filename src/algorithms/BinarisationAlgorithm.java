package algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

import tools.Log;

/**
 * Algorithm which transform a picture as a binary-color picture
 * using thresholding method.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
public class BinarisationAlgorithm extends AbstractAlgorithm {
	
	protected int[] pixelsValue;
	protected int threshold;
	protected int indice;
	protected int partNumber;
	
	public BinarisationAlgorithm() {
		super();
		threshold = -1;
	}
	
	public void setData(Object image) {
		data = (BufferedImage) image;
		pixelsValue = new int[((BufferedImage) data).getHeight() * ((BufferedImage) data).getWidth()];
		indice = 0;
	}
	
	@Override
	public Object algo() {
		
		int widthPicture = ((BufferedImage) data).getWidth();
		int heightPicture = ((BufferedImage) data).getHeight();
		
		for (int i = 0 ; i < heightPicture ; ++i) {
			for (int j = 0 ; j < widthPicture ; ++j) {
				if (((BufferedImage) data).getRGB(j, i) < getThreshold()) {
					((BufferedImage) data).setRGB(j, i, Color.BLACK.getRGB());
				} else {
					((BufferedImage) data).setRGB(j, i, Color.WHITE.getRGB());
				}
			}
			this.dataContainer.update(partNumber, (int) ((float)i / (float) heightPicture * 100f));
		}
		return ((BufferedImage) data);
	}
	
	
	/**
	 * This method can compute the threshold for each buffered image.
	 * To get a really good defined image using this method, the solution is to increase the
	 * sub-image number (by adding new thread(s)/worker(s)).
	 * 
	 * Another solution is to split each sub-image into multiple zones which have to
	 * compute their respective threshold. Then, using the x, y pixel position
	 * we should be able to compare the pixel with the zone threshold.
	 * 
	 * @return int threshold
	 */
	protected int getThreshold() {
		
		if (threshold == -1) {
			
			int min = 0;
			int max = 0;
			int currentValue = 0;
			
			int widthPicture = ((BufferedImage) data).getWidth();
			int heightPicture = ((BufferedImage) data).getHeight();
			
			for (int i = 0 ; i < heightPicture ; ++i) {
				for (int j = 0 ; j < widthPicture ; ++j) {
					currentValue = ((BufferedImage) data).getRGB(j, i);
					min = (currentValue < min) ? currentValue : min;
					max = (currentValue > max) ? currentValue : max;
				}
			}
			threshold = (min - max) / 2;
		}
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
	
	public void setPartNumber(int intPartNumber) {
		partNumber = intPartNumber;
	}
}
