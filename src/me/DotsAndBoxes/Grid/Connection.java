package me.DotsAndBoxes.Grid;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Connection {
	/**
	 * Player 1 = true, player 2 = false;
	 */
	private static ArrayList<Connection> connections = new ArrayList<Connection>();
	public static boolean player1or2;
	private Dots dot1;
	private Dots dot2;
	/**
	 * Constructor for a connection between two dots
	 * @param dot1
	 * @param dot2
	 */
	public Connection(Dots dot1,Dots dot2) {
		this.dot1 = dot1;
		this.dot2 = dot2;
		//Check for square
		player1or2 = !player1or2;
		connections.add(this);
	}
	/**
	 * Validates if the connection is 1x1 dot away in all directions. If true then the connection should be drawn
	 * @param grid
	 * @return
	 */
	public boolean validate(Grid grid) {
		return (dot1.getX() + grid.getSpace() == dot2.getX() || dot1.getX() - grid.getSpace() == dot2.getX() || dot1.getY() + grid.getSpace() == dot2.getY() || dot1.getY() - grid.getSpace() == grid.getSpace()) && (dot1.getX() == dot2.getX() || dot1.getY() == dot2.getY());
	}
	/**
	 * Validates the connection compared to all connections to make sure that there is no overlaps
	 * @param connections
	 * @param possibleConnection
	 * @return
	 */
	public boolean validate(ArrayList<Connection> connections,Connection possibleConnection) {
		for(Connection connection : connections) {
			/*
			 * Checks if the connections are the same
			 */
			if(connection.dot1.getX() == possibleConnection.dot1.getX() && connection.dot2.getX() == possibleConnection.dot2.getX() && connection.dot1.getY() == possibleConnection.dot1.getY() && connection.dot2.getY() == possibleConnection.dot2.getY()) {
				return false;
			}
		}
		return true;
	}
	public Dots getDot1() {
		return dot1;
	}
	public Dots getDot2() {
		return dot2;
	}
	/**
	 * Returns true if vertical false if not
	 */
	public boolean isVerticalOrHorizontal() {
		if(dot1.getX() == dot2.getX()) {
			return true;
		}
		return false;
	}
	public int getStartValue() {
		if(isVerticalOrHorizontal()) {
			return dot1.getY() > dot2.getY() ? dot1.getY() : dot2.getY();
		}
		else {
			return dot1.getX() > dot2.getX() ? dot2.getX() : dot1.getX();
		}
	}
	public static void setDefaultPlayer() {
		player1or2 = true;
	}
	public int getLesserX() {
		if(dot1.getX() < dot2.getX()) {
			return dot1.getX();
		}
		return dot2.getX();
	}
	public int getLesserY() {
		if(dot1.getY() < dot2.getY()) {
			return dot1.getY();
		}
		return dot2.getY();
	}
	/**
	 * @deprecated
	 * @param x
	 * @param y
	 * @return
	 */
	public static Connection[] getConnectionViaCoords(int x,int y) {
		Connection[] connReturn = new Connection[2];
		for(Connection conn : connections) {
			if(conn.getLesserX() == x) {
				if(conn.getLesserY() == y) {
					if(connReturn[0] != null) {
						connReturn[1] = conn;
					}
					else {
						connReturn[0] = conn;
					}
				}
			}
		}
		return connReturn;
	}
}
