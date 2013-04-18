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
	public PictureParts(BufferedImage picture, int nbParts) {
		
		data = picture;
		nbParts = nbParts <= 0 || nbParts > 10 ? 2 : nbParts;
		
		parts = new BufferedImage[nbParts];
		
		init(nbParts);
	}
	
	
	/**
	 * Initialize internal data
	 * 
	 * @param int nbParts number parts
	 */
	protected void init(int nbParts) {
		
		int heightPart = (int) data.getHeight() / nbParts;
		
		int posTileY;
		
		for(int numTileY = 0 ; numTileY < nbParts ; numTileY++) {
			
			posTileY = heightPart * numTileY;
			
			/*System.out.println
			(
				"Params de l'image (" + this.buffPicture.getWidth() + "; " + this.buffPicture.getHeight() +") \n" +
				"Extraction de la Tile ("+ numTileX +"; "+ numTileY +") \n" +
				"Taille de la Tile (" + sizeWidth + "; " + sizeHeight + ")\n" +
				"Position de la Tile (" + posTileX +  "; " + posTileY + ")" +
				"Taille tableau (" + this.pieces.length + "; " + this.pieces + ")"
			);*/
			
			parts[numTileY] = data.getSubimage(0, posTileY, data.getWidth(), heightPart);
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
}
