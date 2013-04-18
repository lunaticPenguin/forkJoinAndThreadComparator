package models;

import java.util.Observable;

/**
 * Manager playing model role : it handles process orders and is able to fetch the picture data.
 * @author Corentin Legros
 */

public class ProcessManagerModel extends AbstractModel {
	
	public ProcessManagerModel() {
		
	}
	
	public void setData(PictureParts data) {
		data.addObserver(this);
		this.data = data;
	}
	
	public PictureParts getData() {
		return (PictureParts) data;
	}

	@Override
	public void setData(Object data) {
		this.data = (PictureParts) data;
	}

	@Override
	/**
	 * Allow to notify observers (views & model) to get notified of changes in model
	 */
	public void update(Observable o, Object arg) {
		notifyObservers();
	}
}
