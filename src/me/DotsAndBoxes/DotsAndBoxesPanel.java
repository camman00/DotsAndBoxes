package me.DotsAndBoxes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import me.DotsAndBoxes.Grid.Connection;
import me.DotsAndBoxes.Grid.Dots;
import me.DotsAndBoxes.Grid.Square;

@SuppressWarnings("serial")
public class DotsAndBoxesPanel extends JPanel implements MouseListener,MouseMotionListener, ActionListener {
	private Timer timer = new Timer(1,this);
	private BufferedImage dot = load("src/dot.png");
	private BufferedImage highlightdot = load("src/highlightdot.png");
	private BufferedImage pipevertical = load("src/pipe.png");
	private BufferedImage pipehorizontal = load("src/pipev.png");
	private HashSet<Dots> dotsToHighlight = new HashSet<Dots>();
	private Dots[] dotsToConnection = new Dots[2];
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private ArrayList<Square> squares = new ArrayList<Square>();
	private ArrayList<Connection> tempConnection = new ArrayList<Connection>();
	private ArrayList<Connection> transferConnection = new ArrayList<Connection>();
	//private HashSet<int[]> test = new HashSet<int[]>();
	/**
	 * The constructor for DotsAndBoxesPanel which sets the background to DARK_GRAY and starts the timer
	 */
	public DotsAndBoxesPanel() {
		timer.start();
		this.setBackground(new Color(15, 45, 94));
	}
	/**
	 * Paints the component drawing all the stuff in the game
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for(Connection conn : connections) {
			Dots dot1 = conn.getDot1();
			Dots dot2 = conn.getDot2();
			//vertical
			if(conn.isVerticalOrHorizontal()) {
				for (int i = conn.getStartValue(); i > conn.getStartValue() - DotsAndBoxes.grid.getSpace(); i--) {
					g2d.drawImage(pipevertical, dot1.getX() - pipevertical.getWidth() - DotsAndBoxes.grid.getSpace() / 15, i - pipevertical.getHeight() - 15, this);
				}
			}
			else {
				for (int i = conn.getStartValue(); i > conn.getStartValue() - DotsAndBoxes.grid.getSpace(); i--) {
					g2d.drawImage(pipehorizontal,(int) (i + pipehorizontal.getWidth() * 4.5), dot1.getY() - pipehorizontal.getHeight() - 5, this);
				}
			}
			
		}
		for (Dots dot : Dots.dots) {
			g.setColor(Color.BLACK);
			/*g2d.fillOval(dot.getX(), dot.getY(), 8, 8);
			g.setColor(new Color(137, 132, 55));
			g2d.drawOval(dot.getX(), dot.getY(), 9, 9);*/
			g2d.drawImage(this.dot, dot.getX() - this.dot.getWidth(),dot.getY() - this.dot.getHeight(),this);
		}
		Color afterColor = g2d.getColor();
		g2d.setColor(new Color(158, 125, 42));
		for(Dots dot : dotsToHighlight) {
			//TODO: Fix
			/*g2d.drawOval(dot.getX(), dot.getY() - 1, 10, 10);
			g2d.drawOval(dot.getX() - 11, dot.getY() - 11, 11, 11);
			g2d.drawOval(dot.getX() - 12, dot.getY() - 12, 12, 12);*/
			g2d.drawImage(highlightdot, dot.getX() - highlightdot.getWidth(),dot.getY() - highlightdot.getHeight(),this);
		}
		if(dotsToConnection[0] != null) {
			g2d.drawImage(highlightdot, dotsToConnection[0].getX() - highlightdot.getWidth(),dotsToConnection[0].getY() - highlightdot.getHeight(),this);
		}
		/*for(int[] t : test) {
			g2d.drawOval(t[0], t[1], 5, 5);
		}*/
		g2d.setColor(afterColor);
		addNewConnection();
		for(int x = 0;x + 3 < connections.size();x++) {
			Connection conn1 = connections.get(x);
			Connection conn2 = connections.get(x + 1);
			Connection conn3 = connections.get(x + 2);
			Connection conn4 = connections.get(x + 3);
			Square potentialSquare = new Square(conn1,conn2,conn3,conn4);
			Square validateSquare = potentialSquare.getPotentialSquare() == null ? null : potentialSquare.getPotentialSquare();
			if(validateSquare != null) {
				squares.add(validateSquare);
				connections.remove(conn1);
				connections.remove(conn2);
				connections.remove(conn3);
				connections.remove(conn4);
			}
		}
		for (int i = 0; i < connections.size() ; i++) {
			sort(connections.get(i));
		}
		connections = tempConnection;
		//tempConnection.clear();
		System.out.println(connections.size());
		//System.out.println(squares.size() + "SQUARES");
		//System.out.println(connections.size());
		//TODO: Color the different possible Dots for the connections with a different color
		if(connections.size() > 3) {
			for (int i = 1; i < DotsAndBoxes.grid.getHorizontalRows() + 1; i++) {
				for (int j = 1; j < DotsAndBoxes.grid.getVerticalRows() + 1; j++) {
					Connection conn1 = Connection.getConnectionViaCoords(i * DotsAndBoxes.grid.getSpace(), j * DotsAndBoxes.grid.getSpace())[0];
					Connection conn2 = Connection.getConnectionViaCoords(i * DotsAndBoxes.grid.getSpace() + 1, j * DotsAndBoxes.grid.getSpace())[0];
					if(Connection.getConnectionViaCoords(i * DotsAndBoxes.grid.getSpace(), j * DotsAndBoxes.grid.getSpace())[1] != null) {
						conn2 = Connection.getConnectionViaCoords(i * DotsAndBoxes.grid.getSpace(), j * DotsAndBoxes.grid.getSpace())[1];
					}
					Connection conn3 = Connection.getConnectionViaCoords(i * DotsAndBoxes.grid.getSpace() + 1, j * DotsAndBoxes.grid.getSpace() + 1)[0];
					Connection conn4 = Connection.getConnectionViaCoords(i * DotsAndBoxes.grid.getSpace(), j * DotsAndBoxes.grid.getSpace() + 1)[0];
					if(Connection.getConnectionViaCoords(i * DotsAndBoxes.grid.getSpace() + 1, j * DotsAndBoxes.grid.getSpace())[1] != null) {
						conn4 = Connection.getConnectionViaCoords(i * DotsAndBoxes.grid.getSpace() + 1, j * DotsAndBoxes.grid.getSpace())[1];
					}
					if(conn1 != null && conn2 != null && conn3 != null && conn4 != null) {
						Square potentialSquare = new Square(conn1,conn2,conn3,conn4);
						Square validateSquare = potentialSquare.getPotentialSquare() == null ? null : potentialSquare.getPotentialSquare();
						if(validateSquare != null) {
							squares.add(validateSquare);
							connections.remove(conn1);
							connections.remove(conn2);
							connections.remove(conn3);
							connections.remove(conn4);
							connections.remove(conn1);
							connections.remove(conn2);
							connections.remove(conn3);
							connections.remove(conn4);
							connections.add(conn1);
							connections.add(conn2);
							connections.add(conn3);
							connections.add(conn4);
						}
					}
				}
			}
		}
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
				//Check if two dots are similar
				System.out.println("validae");
			}
			dotsToConnection = new Dots[2];
		}
	}
	private BufferedImage load(String path) {
		try {
			BufferedImage bi = ImageIO.read(new File(path));
			return bi;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*private boolean canMakeSquare() {
		if(connections.size() < 4) {
			return false;
		}
		for (int i = 0; i < connections.size(); i++) {
			int count = 0;
			Connection connection1 = connections.get(i);
			Connection connection2 = i + 1 < connections.size() ? connections.get(i + 1) : connections.get(i);
			Connection connection3 = i + 2 < connections.size() ? connections.get(i + 2) : connections.get(i);
			Connection connection4 = i + 3 < connections.size() ? connections.get(i + 3) : connections.get(i);
			Connection lowestConnection = getLesserConnection(connection1, connection2, connection3, connection4);
			if(lowestConnection.getLesserX() + DotsAndBoxes.grid.getSpace() == connection1.getLesserX()) {
				count++;
			}
			else if(lowestConnection.getLesserX() + DotsAndBoxes.grid.getSpace() == connection2.getLesserX()) {
				count++;
			}
			else if(lowestConnection.getLesserX() + DotsAndBoxes.grid.getSpace() == connection3.getLesserX()) {
				count++;
			}
			else if(lowestConnection.getLesserX() + DotsAndBoxes.grid.getSpace() == connection4.getLesserX()) {
				count++;
			}
			if(lowestConnection.getLesserY() + DotsAndBoxes.grid.getSpace() == connection1.getLesserY()) {
				count++;
			}
			else if(lowestConnection.getLesserY() + DotsAndBoxes.grid.getSpace() == connection2.getLesserY()) {
				count++;
			}
			else if(lowestConnection.getLesserY() + DotsAndBoxes.grid.getSpace() == connection3.getLesserY()) {
				count++;
			}
			else if(lowestConnection.getLesserY() + DotsAndBoxes.grid.getSpace() == connection4.getLesserY()) {
				count++;
			}
			if(lowestConnection.getLesserX() + DotsAndBoxes.grid.getSpace() == connection1.getLesserX() && lowestConnection.getLesserY() + DotsAndBoxes.grid.getSpace() == connection1.getLesserY()) {
				count++;
			}
			else if(lowestConnection.getLesserX() + DotsAndBoxes.grid.getSpace() == connection2.getLesserX() && lowestConnection.getLesserY() + DotsAndBoxes.grid.getSpace() == connection2.getLesserY()) {
				count++;
			}
			else if(lowestConnection.getLesserX() + DotsAndBoxes.grid.getSpace() == connection3.getLesserX() && lowestConnection.getLesserY() + DotsAndBoxes.grid.getSpace() == connection3.getLesserY()) {
				count++;
			}
			else if(lowestConnection.getLesserX() + DotsAndBoxes.grid.getSpace() == connection4.getLesserX() && lowestConnection.getLesserY() + DotsAndBoxes.grid.getSpace() == connection4.getLesserY()) {
				count++;
			}
			if(count == 3) {
				return true;
			}
		}
		return false;
			
	}
	private Connection getLesserConnection(Connection con1,Connection con2,Connection con3,Connection con4) {
		if(con1.getLesserX() < con2.getLesserX() && con1.getLesserX() < con3.getLesserX() && con1.getLesserX() < con4.getLesserX()) {
			if(getLesserConnectionY(con1, con2, con3, con4).getLesserX() == con1.getLesserX() && getLesserConnectionY(con1, con2, con3, con4).getLesserY() == con1.getLesserY()) {
				return con1;
			}
		}
		else if(con2.getLesserX() < con1.getLesserX() && con2.getLesserX() < con3.getLesserX() && con2.getLesserX() < con4.getLesserX()) {
			if(getLesserConnectionY(con1, con2, con3, con4).getLesserX() == con2.getLesserX() && getLesserConnectionY(con1, con2, con3, con4).getLesserY() == con2.getLesserY()) {
				return con2;
			}
		}
		else if(con3.getLesserX() < con1.getLesserX() && con3.getLesserX() < con2.getLesserX() && con3.getLesserX() < con4.getLesserX()) {
			if(getLesserConnectionY(con1, con2, con3, con4).getLesserX() == con3.getLesserX() && getLesserConnectionY(con1, con2, con3, con4).getLesserY() == con3.getLesserY()) {
				return con3;
			}
		}
		else {
			if(getLesserConnectionY(con1, con2, con3, con4).getLesserX() == con4.getLesserX() && getLesserConnectionY(con1, con2, con3, con4).getLesserY() == con4.getLesserY()) {
				return con4;
			}
		}
		return null;
	}
	/**
	 * @deprecated
	 * @param con1
	 * @param con2
	 * @param con3
	 * @param con4
	 * @return
	 */
	/*
	private Connection getLesserConnectionY(Connection con1,Connection con2,Connection con3,Connection con4) {
		if(con1.getLesserY() < con2.getLesserY() && con1.getLesserY() < con3.getLesserY() && con1.getLesserY() < con4.getLesserY()) {
			return con1;
		}
		else if(con2.getLesserY() < con1.getLesserY() && con2.getLesserY() < con3.getLesserY() && con2.getLesserY() < con4.getLesserY()) {
			return con2;
		}
		else if(con3.getLesserY() < con1.getLesserY() && con3.getLesserY() < con2.getLesserY() && con3.getLesserY() < con4.getLesserY()) {
			return con3;
		}
		else {
			return con4;
		}
	}
	*/
	private void sort(Connection conn) {
		if(connections.size() > 3) {
			//tempConnection.clear();
			ArrayList<Connection> conns = new ArrayList<Connection>();
			conns.removeAll(transferConnection);
			if(transferConnection.isEmpty()) {
				transferConnection = connections;
			}
			Connection leastConnection = null;
			for(Connection connz : transferConnection) {
				if(leastConnection == null) {
					leastConnection = conn;
				}
				if(leastConnection.getLesserY() > connz.getLesserY()) {
					leastConnection = connz;
				}
				else if(leastConnection.getLesserY() == connz.getLesserY()) {
					if(connz.getLesserX() < leastConnection.getLesserX()) {
						leastConnection = connz;
					}
				}
			}
			transferConnection.remove(leastConnection);
			tempConnection.add(leastConnection);
		}
	}
}
