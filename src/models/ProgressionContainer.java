package models;

/**
 * This class is in charge of transporting data which are used for reports and statistics
 * from the process to the controller.
 * 
 * @author Corentin Legros
 */
public class ProgressionContainer {
	
	/**
	 * Number associated to the sub-image part
	 * @param integer intPartNum
	 */
	protected int intPartNum;
	
	protected int percent;

	/**
	 * @return the intPartNum
	 */
	public int getPartNumber() {
		return intPartNum;
	}

	/**
	 * @param intPartNum the intPartNum to set
	 */
	public void setPartNumber(int intPartNum) {
		this.intPartNum = intPartNum;
	}

	/**
	 * @return the percent
	 */
	public int getPercent() {
		return percent;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(int percent) {
		this.percent = percent;
	}
}
