package com.pathfinding.algorithms;

public class AStarNode {

		public int x, y;
		private int gCost, fCost;
		private boolean isFinished, isDiscovered;
		private Position precursor;
		
		public AStarNode(Position pos) {
			
			this.x = pos.x;
			this.y = pos.y;
			setGCost(2147483647);
			setFCost(2147483647);
			setFinished(false);
			setDiscovered(false);
			setPrecursor(null);
		}

		public int getGCost() {
			return gCost;
		}

		public void setGCost(int cost) {
			this.gCost = cost;
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

		public int getFCost() {
			return fCost;
		}

		public void setFCost(int fCost) {
			this.fCost = fCost;
		}
	
}
