package me.DotsAndBoxes.Grid;

import java.util.ArrayList;

public class Dots {
	public static ArrayList<Dots> dots = new ArrayList<Dots>();
	private int x;
	private int y;
	private boolean claimed;
	/*
	 * Is this dot connected to anything
	 */
	private boolean isClaimedCompletely;
	/**
	 * The constructor which defines a new dot with an x and y location
	 * @param x
	 * @param y
	 */
	public Dots(int x, int y) {
		this.x = x;
		this.y = y;
		setClaimedCompletely(false);
	}
	/**
	 * Statically load the dots
	 * @param grid
	 */
	public static void loadDots(Grid grid) {
		for (int i = 0; i < grid.getHorizontalRows(); i++) {
			for (int j = 0; j < grid.getVerticalRows(); j++) {
				dots.add(new Dots((i * grid.getSpace()) + grid.getSpace(), (j * grid.getSpace()) + grid.getSpace()));
			}
		}
	}
	/**
	 * Get a dots x location
	 * @return
	 */
	public int getX() {
		return x;
	}
	/**
	 * Set a dots x location
	 * @deprecated
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Get a dots y location
	 * @return
	 */
	public int getY() {
		return y;
	}
	/**
	 * Set a dots y location
	 * @deprecated
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Get the bounds of this dot. Item 1 and 2 of the array is point one of the box and Item 3 and 4 are
	 * the second point
	 * *-----
	 * |    |
	 * |    |
	 * -----*
	 * @return
	 */
	public int[] getBounds() {
		int[] returnint = {x - 10,y,x + 10,y + 30};
		return returnint;
	}
	/**
	 * Get if this dot is completely claimed on all 4 sides
	 * @return
	 */
	public boolean isClaimedCompletely() {
		return isClaimedCompletely;
	}
	/**
	 * Set if this dot is completely claimed on all 4 sides
	 * @param isClaimedCompletely
	 */
	public void setClaimedCompletely(boolean isClaimedCompletely) {
		this.isClaimedCompletely = isClaimedCompletely;
	}
	public boolean isClaimed() {
		return claimed;
	}
	public void setClaimed(boolean claimed) {
		this.claimed = claimed;
	}

	
}
