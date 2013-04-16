package models;

/**
 * Manager playing model role : it handles process orders and is able to fetch the picture data.
 * @author Corentin Legros
 */

public class ProcessManagerModel extends AbstractModel {
	
	public ProcessManagerModel() {
		
	}
	
	public void setData(PictureParts data) {
		this.data = data;
	}
	
	public PictureParts getData() {
		return (PictureParts) data;
	}

	@Override
	public void setData(Object data) {
		this.data = (PictureParts) data;
	}
}
