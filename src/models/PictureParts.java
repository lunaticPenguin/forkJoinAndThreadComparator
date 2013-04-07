package models;

import java.awt.image.BufferedImage;

/**
 * Description : Classe permettant de g�n�rer un tableau avec les morceaux de l'image
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 *
 */
public class PictureParts {
	
	/**
	 * Picture used as reference to build all image parts
	 */
	private BufferedImage refImage;
	
	/**
	 * Parts of the pictures
	 */
	private BufferedImage[][] parts;
	
	/**
	 * Constructor
	 * @param BufferedImage picture to split in parts
	 * @param xParts number of cols
	 * @param yParts number of rows
	 */
	public PictureParts(BufferedImage picture, int xParts, int yParts) {
		
		refImage = picture;
		xParts = xParts <= 0 || xParts > 10 ? 2 : xParts;
		yParts = yParts <= 0 || yParts > 10 ? 2 : yParts;
		
		parts = new BufferedImage[yParts][xParts];
		
		init(xParts, yParts);
	}
	
	
	/**
	 * Initialize internal data
	 * 
	 * @param int xParts x parts number
	 * @param int yParts y parts number
	 */
	protected void init(int xParts, int yParts) {
		int widthPart = (int) refImage.getWidth() / xParts;
		int heightPart = (int) refImage.getHeight() / yParts;
		
		int posTileX;
		int posTileY;
		
		for(int numTileY = 0 ; numTileY < xParts ; numTileY++) {
			for(int numTileX = 0 ; numTileX < yParts ; numTileX++) {
				
				posTileX = widthPart * numTileX;
				posTileY = heightPart * numTileY;
				
				/*System.out.println
				(
					"Params de l'image (" + this.buffPicture.getWidth() + "; " + this.buffPicture.getHeight() +") \n" +
					"Extraction de la Tile ("+ numTileX +"; "+ numTileY +") \n" +
					"Taille de la Tile (" + sizeWidth + "; " + sizeHeight + ")\n" +
					"Position de la Tile (" + posTileX +  "; " + posTileY + ")" +
					"Taille tableau (" + this.pieces.length + "; " + this.pieces + ")"
				);*/
				
				parts[numTileY][numTileX] = refImage.getSubimage(posTileX, posTileY, widthPart, heightPart);
			}
		}
	}
	
	
	/**
	 * Get a picture part, 
	 * @param x
	 * @param y
	 * @return
	 */
	public BufferedImage getPart(int x, int y) {
		
		if (parts != null) {
			if (y > 0 && y < parts.length) { // rows range test
				if (x > 0 && x < parts[0].length) { // columns range test
					return parts[y][x];
				}
			}
		}
		return null;
	}
	
	/**
	 * Get all picture parts
	 * @return
	 */
	public BufferedImage[][] getAllParts() {
		return parts;
	}
}
