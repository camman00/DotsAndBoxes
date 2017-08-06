package me.DotsAndBoxes;

import java.awt.Color;

import javax.swing.JFrame;

import me.DotsAndBoxes.Grid.Grid;

public class DotsAndBoxes {
	private JFrame jFrame;
	private DotsAndBoxesPanel dotsAndBoxesPanel;
	private Grid grid;
	public DotsAndBoxes() {
		/*
		 * Create objects for the classes
		 */
		jFrame = new JFrame();
		dotsAndBoxesPanel = new DotsAndBoxesPanel();
		grid = new Grid(50,10,10);
		/*
		 * Setup the JFrame
		 */
		jFrame.add(dotsAndBoxesPanel);
		jFrame.setTitle("Dots And Boxes");
		jFrame.setSize(grid.getXWidth(), grid.getYWidth());
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(3);
		jFrame.setVisible(true);
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		DotsAndBoxes dotsAndBoxes = new DotsAndBoxes();
	}

}
