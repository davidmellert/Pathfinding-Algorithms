package com.pathfinding.algorithms;

public class Node {

	
	public int x, y;
	private int cost;
	private boolean isFinished, isDiscovered;
	private Position precursor;
	
	public Node(Position pos) {
		
		this.x = pos.x;
		this.y = pos.y;
		setCost(2147483647);
		setFinished(false);
		setDiscovered(false);
		setPrecursor(null);
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Position getPrecursor() {
		return precursor;
	}

	public void setPrecursor(Position precursor) {
		this.precursor = precursor;
	}

	public boolean isDiscovered() {
		return isDiscovered;
	}

	public void setDiscovered(boolean isDiscovered) {
		this.isDiscovered = isDiscovered;
	}
	
	
}
