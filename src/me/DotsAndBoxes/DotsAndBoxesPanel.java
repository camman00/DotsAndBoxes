package me.DotsAndBoxes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.Timer;

import me.DotsAndBoxes.Grid.Connection;
import me.DotsAndBoxes.Grid.Dots;

@SuppressWarnings("serial")
public class DotsAndBoxesPanel extends JPanel implements MouseListener,MouseMotionListener, ActionListener {
	private Timer timer = new Timer(100,this);
	private HashSet<Dots> dotsToHighlight = new HashSet<Dots>();
	private Dots[] dotsToConnection = new Dots[2];
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	//private HashSet<int[]> test = new HashSet<int[]>();
	/**
	 * The constructor for DotsAndBoxesPanel which sets the background to DARK_GRAY and starts the timer
	 */
	public DotsAndBoxesPanel() {
		timer.start();
		this.setBackground(Color.DARK_GRAY);
	}
	/**
	 * Paints the component drawing all the stuff in the game
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (Dots dot : Dots.dots) {
			g.setColor(Color.BLACK);
			g2d.fillOval(dot.getX(), dot.getY(), 8, 8);
			g.setColor(new Color(137, 132, 55));
			g2d.drawOval(dot.getX(), dot.getY(), 9, 9);
		}
		Color afterColor = g2d.getColor();
		g2d.setColor(new Color(158, 125, 42));
		for(Dots dot : dotsToHighlight) {
			//TODO: Fix
			g2d.drawOval(dot.getX(), dot.getY() - 1, 10, 10);
			g2d.drawOval(dot.getX() - 11, dot.getY() - 11, 11, 11);
			g2d.drawOval(dot.getX() - 12, dot.getY() - 12, 12, 12);
		}
		/*for(int[] t : test) {
			g2d.drawOval(t[0], t[1], 5, 5);
		}*/
		g2d.setColor(afterColor);
		addNewConnection();
		System.out.println(connections.size());
		//TODO: Color the different possible Dots for the connections with a different color
		g2d.dispose();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(dotsToHighlight.size() > 0 && dotsToHighlight.size() < 2) {
			Dots dot = (Dots) dotsToHighlight.toArray()[0];
			if(dotsToConnection[0] == null) {
				dotsToConnection[0] = dot;
			}
			else if(dotsToConnection[1] == null) {
				dotsToConnection[1] = dot;
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		for (Dots dot : Dots.dots) {
			if(dot.isClaimedCompletely() != true) {
				if(dot.getBounds()[0] < e.getX() && dot.getBounds()[1] < e.getY() && dot.getBounds()[2] > e.getX() && dot.getBounds()[3] > e.getY()) {
					dotsToHighlight.add(dot);
				}
			}
		}
		removeHighlights(e);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer) {
			repaint();
		}
	}
	private void removeHighlights(MouseEvent e) {
		for (Dots dot : Dots.dots) {
			if(dot.isClaimedCompletely() != true) {
				if(!(dot.getBounds()[0] < e.getX() && dot.getBounds()[1] < e.getY() && dot.getBounds()[2] > e.getX() && dot.getBounds()[3] > e.getY())) {
					if(dotsToHighlight.contains(dot)) {
						dotsToHighlight.remove(dot);
					}
				}
			}
		}
	}
	private void addNewConnection() {
		if(dotsToConnection[0] != null && dotsToConnection[1] != null) {
			Connection possibleNewConnection = new Connection(dotsToConnection[0],dotsToConnection[1]);
			System.out.println("true");
			if(possibleNewConnection.validate(DotsAndBoxes.grid)) {
				connections.add(possibleNewConnection);
				System.out.println("validae");
			}
			dotsToConnection = new Dots[2];
		}
	}
}
