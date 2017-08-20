package me.DotsAndBoxes.Grid;

import java.util.ArrayList;

public class Connection {
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
}
