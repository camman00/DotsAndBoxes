package me.DotsAndBoxes;

import javax.swing.JFrame;
import me.DotsAndBoxes.Grid.Dots;
import me.DotsAndBoxes.Grid.Grid;

public class DotsAndBoxes {
	private JFrame jFrame;
	private DotsAndBoxesPanel dotsAndBoxesPanel;
	public static Grid grid;
	/**
	 * Main constructor of the project
	 */
	public DotsAndBoxes() {
		/*
		 * Create objects for the classes
		 */
		jFrame = new JFrame();
		grid = new Grid(75, 10, 10);
		dotsAndBoxesPanel = new DotsAndBoxesPanel();
		Dots.loadDots(grid);
		/*
		 * Setup the JFrame
		 */
		jFrame.add(dotsAndBoxesPanel);
		jFrame.setTitle("Dots And Boxes");
		jFrame.setSize(grid.getXWidth(), grid.getYWidth());
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(3);
		jFrame.setVisible(true);
		jFrame.addMouseListener(dotsAndBoxesPanel);
		jFrame.addMouseMotionListener(dotsAndBoxesPanel);
	}
	/**
	 * Main method
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		DotsAndBoxes dotsAndBoxes = new DotsAndBoxes();
	}

}
