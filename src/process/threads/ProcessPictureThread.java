package process.threads;

import models.PictureParts;

public class ProcessPictureThread extends AbstractProcessThread<PictureParts> {
	protected int partNumber;
	
	public void setPartNumber(int part) {
		partNumber = part;
	}
	
	public void run() {
		processAlgorithm.setData(this.data.getPart(partNumber));
		processAlgorithm.setDataContainer(this.data);
		processAlgorithm.algo();
	}
}
