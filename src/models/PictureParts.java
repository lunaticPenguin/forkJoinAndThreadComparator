package models;

import java.awt.image.BufferedImage;

/**
 * Description : Classe permettant de g�n�rer un tableau avec les morceaux de l'image
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 *
 */
public class PictureParts extends AbstractDataContainer<BufferedImage> {
	
	/**
	 * Parts of the pictures
	 */
	private BufferedImage[] parts;
	
	/**
	 * Constructor
	 * @param BufferedImage picture to split in parts
	 * @param xParts number of cols
	 * @param yParts number of rows
	 */
	public PictureParts(BufferedImage picture) {
		data = picture;
	}
	
	
	/**
	 * Initialize internal data with extra pixels handling
	 * @param int nbParts number parts
	 */
	protected void init(int nbParts) {
		
		int heightPart = (int) data.getHeight() / nbParts;
		
		boolean hasAdditionalPart = false;
		if (data.getHeight() % heightPart != 0) {
			hasAdditionalPart = true;
			nbParts += 1;
		}

		parts = new BufferedImage[nbParts];
		nbParts = (hasAdditionalPart) ? nbParts - 1 : nbParts;
		
		int posTileY;
		
		for(int numTileY = 0 ; numTileY < nbParts ; numTileY++) {
			
			posTileY = heightPart * numTileY;
			parts[numTileY] = data.getSubimage(0, posTileY, data.getWidth(), heightPart);
		}
		
		if (hasAdditionalPart) {
			parts[nbParts] = data.getSubimage(0, heightPart * (nbParts), data.getWidth(), data.getHeight() % heightPart);
		}
	}
	
	
	/**
	 * Get a picture part, 
	 * @param x
	 * @param y
	 * @return
	 */
	public BufferedImage getPart(int x) {
		
		if (parts != null) {
			if (x >= 0 && x < parts.length) { // rows range test
				return parts[x];
			}
		}
		return null;
	}
	
	/**
	 * Get all picture parts
	 * @return
	 */
	public BufferedImage[] getAllParts() {
		return parts;
	}
	
	/**
	 * Get image parts number
	 * @return int
	 */
	public int getPartsNumber() {
		return parts.length;
	}
	
	/**
	 * Set the parts number depending on the JComboBox choice
	 * @param int nbParts
	 */
	public void setPartsNumber(int nbParts) {
		init(nbParts);
	}
}
