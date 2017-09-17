package me.DotsAndBoxes.Grid;

import java.util.ArrayList;
import java.util.HashMap;

import me.DotsAndBoxes.DotsAndBoxes;

public class Square {
	private HashMap<Position,Connection> sortedConnections = new HashMap<Position,Connection>();
	private ArrayList<Connection> unsortedConnections = new ArrayList<Connection>();
	private Connection conn1,conn2,conn3,conn4;
	public Square(Connection conn1,Connection conn2,Connection conn3,Connection conn4) {
		this.conn1 = conn1;
		this.conn2 = conn2;
		this.conn3 = conn3;
		this.conn4 = conn4;
		unsortedConnections.add(conn1);
		unsortedConnections.add(conn2);
		unsortedConnections.add(conn3);
		unsortedConnections.add(conn4);
	}
	public Connection getConn3() {
		return conn3;
	}
	public void setConn3(Connection conn3) {
		this.conn3 = conn3;
	}
	public Connection getConn2() {
		return conn2;
	}
	public void setConn2(Connection conn2) {
		this.conn2 = conn2;
	}
	public Connection getConn1() {
		return conn1;
	}
	public void setConn1(Connection conn1) {
		this.conn1 = conn1;
	}
	public Connection getConn4() {
		return conn4;
	}
	public void setConn4(Connection conn4) {
		this.conn4 = conn4;
	}
	public Square getPotentialSquare() {
		sort();
		System.out.println("Commencing Search");
		for(Connection conn : sortedConnections.values()) {
			if(conn == null) {
				return null;
			}
		}
		if(sortedConnections.size() != 4) {
			return null;
		}
		if(sortedConnections.get(Position.RIGHT).getLesserY() == sortedConnections.get(Position.LEFT).getLesserY()) {
			System.out.println("Check 1");
			//System.out.println(sortedConnections.get(Position.RIGHT).getLesserX() + "SEPERATOR" + sortedConnections.get(Position.LEFT));
			if(sortedConnections.get(Position.LEFT).getLesserX() + DotsAndBoxes.grid.getSpace() == sortedConnections.get(Position.RIGHT).getLesserX()) {
				if(sortedConnections.get(Position.TOP).getLesserX() == sortedConnections.get(Position.BOTTOM).getLesserX()) {
					if(sortedConnections.get(Position.TOP).getLesserY() + DotsAndBoxes.grid.getSpace() == sortedConnections.get(Position.BOTTOM).getLesserY()) {
						return new Square(sortedConnections.get(Position.RIGHT),sortedConnections.get(Position.LEFT),sortedConnections.get(Position.TOP),sortedConnections.get(Position.BOTTOM));
					}
				}
			}
		}
		return null;
	}
	private void sort() {
		sortVertical();
		sortHorizontal();
	}
	private void sortVertical() {
		ArrayList<Connection> vertical = new ArrayList<Connection>();
		for(Connection conn : unsortedConnections) {
			if(conn.isVerticalOrHorizontal()) {
				vertical.add(conn);
			}
		}
		if(vertical.size() != 2) {
			return;
		}
		if(vertical.get(0).getLesserX() < vertical.get(1).getLesserX()) {
			sortedConnections.put(Position.LEFT, vertical.get(0));
			sortedConnections.put(Position.RIGHT, vertical.get(1));
		}
		else {
			sortedConnections.put(Position.RIGHT, vertical.get(0));
			sortedConnections.put(Position.LEFT, vertical.get(1));
		}
		unsortedConnections.remove(vertical.get(0));
		unsortedConnections.remove(vertical.get(1));
	}
	private void sortHorizontal() {
		if(unsortedConnections.get(0).getLesserY() < unsortedConnections.get(1).getLesserY()) {
			sortedConnections.put(Position.TOP, unsortedConnections.get(0));
			sortedConnections.put(Position.BOTTOM, unsortedConnections.get(1));
		}
		else {
			sortedConnections.put(Position.BOTTOM, unsortedConnections.get(0));
			sortedConnections.put(Position.TOP, unsortedConnections.get(1));
		}
	}
}
