package me.DotsAndBoxes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import me.DotsAndBoxes.Grid.Dots;

@SuppressWarnings("serial")
public class DotsAndBoxesPanel extends JPanel {
	public DotsAndBoxesPanel() {
		this.setBackground(Color.DARK_GRAY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		for (Dots dot : Dots.dots) {
			g.setColor(Color.BLACK);
			g.drawLine(dot.getX() - 1, dot.getY(), dot.getX() + 1, dot.getY());
			g.drawLine(dot.getX(), dot.getY() - 1, dot.getX(), dot.getY() + 1);
			g.setColor(new Color(0, 0, 0, 120));
			g.drawLine(dot.getX() - 2, dot.getY() - 1, dot.getX(), dot.getY() - 1);
			g.drawLine(dot.getX() + 2, dot.getY() - 1, dot.getX() + 2, dot.getY() - 1);
		}

	}
}
