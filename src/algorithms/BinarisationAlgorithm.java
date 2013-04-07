package algorithms;

import java.awt.image.BufferedImage;

/**
 * Algorithm which transform a picture as a binary-color picture
 * using thresholding method.
 * @author Corentin Legros
 */
public class BinarisationAlgorithm extends AbstractAlgorithm {
	
	protected int[] pixelsValue;
	protected int threshold;
	protected int indice;
	
	public BinarisationAlgorithm(BufferedImage imageToProcess) {
		super(imageToProcess);
		pixelsValue = new int[refPicture.getHeight() * refPicture.getWidth()];
		indice = 0;
	}
	
	@Override
	public BufferedImage algo() {
		
		int widthPicture = refPicture.getWidth();
		int heightPicture = refPicture.getHeight();
		
		for (int i = 0 ; i < heightPicture ; ++i) {
			for (int j = 0 ; j < widthPicture ; ++j) {
				if (refPicture.getRGB(j, i) > computeCumulativeThresold(refPicture.getRGB(j, i))) {
					refPicture.setRGB(j, i, 0);
				} else {
					refPicture.setRGB(j, i, 1);
				}
			}
		}
		return refPicture;
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

}
