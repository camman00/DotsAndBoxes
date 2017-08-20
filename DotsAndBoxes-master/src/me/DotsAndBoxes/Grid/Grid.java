package me.DotsAndBoxes.Grid;

public class Grid {
	private int space;
	private int horizontalRows;
	private int verticalRows;

	/**
	 * Space defines the amt of space vertically and horizontally Horizontal
	 * rows defines the amt of horizontal rows in the game Vertical rows defines
	 * the amt of vertical rows in the game
	 * 
	 * @param space
	 * @param horizontalRows
	 * @param verticalRows
	 */
	public Grid(int space, int horizontalRows, int verticalRows) {
		this.space = space;
		this.horizontalRows = horizontalRows;
		this.verticalRows = verticalRows;
	}

	/**
	 * Get the overall X Width of the entire game
	 * 
	 * @return
	 */
	public int getXWidth() {
		return (space * horizontalRows) + space;
	}

	/**
	 * Get the overall Y Width of the entire game
	 * 
	 * @return
	 */
	public int getYWidth() {
		return (space * verticalRows) + space;
	}

	/**
	 * Get the space
	 * 
	 * @return
	 */
	public int getSpace() {
		return space;
	}

	/**
	 * Set the space
	 * 
	 * @deprecated
	 * @param space
	 */
	public void setSpace(int space) {
		this.space = space;
	}

	/**
	 * Get the horizontal rows
	 * 
	 * @return
	 */
	public int getHorizontalRows() {
		return horizontalRows;
	}

	/**
	 * Set the horizontal rows
	 * 
	 * @deprecated
	 * @param horizontalRows
	 */
	public void setHorizontalRows(int horizontalRows) {
		this.horizontalRows = horizontalRows;
	}

	/**
	 * Get the vertical rows
	 * 
	 * @return
	 */
	public int getVerticalRows() {
		return verticalRows;
	}

	/**
	 * Set the vertical rows
	 * 
	 * @deprecated
	 * @param verticalRows
	 */
	public void setVerticalRows(int verticalRows) {
		this.verticalRows = verticalRows;
	}

}
