package models;

/**
 * This class is in charge of transporting data which are used for reports and statistics
 * from the process to the controller.
 * 
 * @author Guillaume Cornet
 * @author Corentin Legros
 */
public class ProgressionContainer {
	
	/**
	 * Number associated to the sub-image part
	 * @param integer intPartNum
	 */
	protected int intPartNum;
	
	protected int percent;
	
	protected int lastPercent;
	
	public ProgressionContainer() {
		percent = 0;
		lastPercent = 0;
	}

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

	/**
	 * @return the lastPercent
	 */
	public int getLastPercent() {
		return lastPercent;
	}

	/**
	 * @param lastPercent the lastPercent to set
	 */
	public void setLastPercent(int lastPercent) {
		this.lastPercent = lastPercent;
	}
}
