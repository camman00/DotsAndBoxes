package me.DotsAndBoxes.Grid;

import java.util.ArrayList;

public class Dots {
	public static ArrayList<Dots> dots = new ArrayList<Dots>();
	private int x;
	private int y;

	public Dots(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static void loadDots(Grid grid) {
		for (int i = 0; i < grid.getHorizontalRows(); i++) {
			for (int j = 0; j < grid.getVerticalRows(); j++) {
				dots.add(new Dots((i * grid.getSpace()) + grid.getSpace(), (j * grid.getSpace()) + grid.getSpace()));
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
