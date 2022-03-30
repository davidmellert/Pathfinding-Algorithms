package com.pathfinding.algorithms;


public class Pathfinder {
	
	private Position start, end;
	private boolean[][] walls;
	private int rows, columns;
	private Main main;
	
	
	public Pathfinder(Main main, Position start, Position end, boolean[][] walls, int rows, int columns) {
		
		this.start = start;
		this.end = end;
		this.walls = walls;
		this.rows = rows;
		this.columns = columns;
		
		this.main = main;
	}
	
	//Nachdem die Kosten eines Punktes verändert wurden soll auch die LIste wieder sortiert werden, damit keine Fehler auftreten
	
	public Stack djkstra(){
		List<Node> newPositions = new List<Node>();
		Node[][] allPositions = new Node[columns][rows];
		for(int i=0; i< columns; i++) {
			for(int j=0; j<rows; j++) {
				allPositions[i][j] = new Node(new Position(i, j));
			}
		}
		allPositions[start.x][start.y].setCost(0);
		
		newPositions.append(allPositions[start.x][start.y]);
		
		while(!newPositions.isEmpty() && allPositions[end.x][end.y].getPrecursor() == null) {
			newPositions.toFirst();
			
			Node temp = newPositions.getContent();
			allPositions[temp.x][temp.y].setFinished(true);
			newPositions.toFirst();
			newPositions.remove();
			Position left, right, up, down;
			
			if(temp.x > 0) {
				Position newestPos = new Position(temp.x -1, temp.y);
				if(!walls[newestPos.x][newestPos.y] && !allPositions[newestPos.x][newestPos.y].isFinished()) {
					left = newestPos;
				
				}else {
					left = null;
				}
			}else
				left = null;
			
			if(temp.x < columns-1) {
				Position newestPos = new Position(temp.x +1, temp.y);
				if(!walls[newestPos.x][newestPos.y]&& !allPositions[newestPos.x][newestPos.y].isFinished()) {
					right = newestPos;
				
				}else {
					right = null;
				}
			}else
				right = null;
			
			if(temp.y > 0) {
				Position newestPos = new Position(temp.x, temp.y -1);
				if(!walls[newestPos.x][newestPos.y] && !allPositions[newestPos.x][newestPos.y].isFinished()) {
					up = newestPos;
				
				}else {
					up = null;
				}
			}else
				up = null;
			
			if(temp.y < rows-1) {
				Position newestPos = new Position(temp.x, temp.y +1);
				if(!walls[newestPos.x][newestPos.y] && !allPositions[newestPos.x][newestPos.y].isFinished()) {
					down = newestPos;
				
				}else {
					down = null;
				}
			}else
				down = null;
			
			if(left != null) {
				if(temp.getCost()+1 < allPositions[left.x][left.y].getCost()) {
					allPositions[left.x][left.y].setCost(temp.getCost()+1);
					allPositions[left.x][left.y].setPrecursor(new Position(temp.x, temp.y));
				}
				if(!allPositions[left.x][left.y].isDiscovered()) {
					allPositions[left.x][left.y].setDiscovered(true);
					newPositions = this.addInRightPlace(newPositions, allPositions[left.x][left.y]);
				}
			}
			if(right != null) {
				
				if(temp.getCost()+1 < allPositions[right.x][right.y].getCost()) {
					allPositions[right.x][right.y].setCost(temp.getCost()+1);
					allPositions[right.x][right.y].setPrecursor(new Position(temp.x, temp.y));
				}
				if(!allPositions[right.x][right.y].isDiscovered()) {
					allPositions[right.x][right.y].setDiscovered(true);
					newPositions = this.addInRightPlace(newPositions, allPositions[right.x][right.y]);
				}
				
			}
			if(up != null) {
				
				
				if(temp.getCost()+1 < allPositions[up.x][up.y].getCost()) {
					allPositions[up.x][up.y].setCost(temp.getCost()+1);
					allPositions[up.x][up.y].setPrecursor(new Position(temp.x, temp.y));
				}
				if(!allPositions[up.x][up.y].isDiscovered()) {
					allPositions[up.x][up.y].setDiscovered(true);
					newPositions = this.addInRightPlace(newPositions, allPositions[up.x][up.y]);
				}
			}
			if(down != null) {
				
				if(temp.getCost()+1 < allPositions[down.x][down.y].getCost()) {
					allPositions[down.x][down.y].setCost(temp.getCost()+1);
					allPositions[down.x][down.y].setPrecursor(new Position(temp.x, temp.y));
				}
				if(!allPositions[down.x][down.y].isDiscovered()) {
					allPositions[down.x][down.y].setDiscovered(true);
					newPositions = this.addInRightPlace(newPositions, allPositions[down.x][down.y]);
				}
				
			}
			main.setNewDiscoveredBlocks(newPositions);
			main.setFinishedBlocks(new Position(temp.x, temp.y));
			
				
		}
		
		if(newPositions.isEmpty() && allPositions[end.x][end.y].getPrecursor() == null) {
			return null;
		}else if(allPositions[end.x][end.y].getPrecursor() != null){
			Stack way = new Stack();
			Node current = allPositions[end.x][end.y];
			while(current != allPositions[start.x][start.y]) {
				way.push(new Position(current.x, current.y));
				Position nextPos = current.getPrecursor();
				current = allPositions[nextPos.x][nextPos.y];
			}
			
			return way;
			
		}
		
		return null;
	}
	
	public Stack AStar() {
		List<AStarNode> newPositions = new List<AStarNode>();
		AStarNode[][] allPositions = new AStarNode[columns][rows];
		for(int i=0; i< columns; i++) {
			for(int j=0; j<rows; j++) {
				allPositions[i][j] = new AStarNode(new Position(i, j));
			}
		}
		allPositions[start.x][start.y].setGCost(0);
		
		newPositions.append(allPositions[start.x][start.y]);
		
		while(!newPositions.isEmpty() && allPositions[end.x][end.y].getPrecursor() == null) {
			newPositions.toFirst();
			
			AStarNode temp = newPositions.getContent();
			allPositions[temp.x][temp.y].setFinished(true);
			newPositions.toFirst();
			newPositions.remove();
			Position left, right, up, down;
			
			if(temp.x > 0) {
				Position newestPos = new Position(temp.x -1, temp.y);
				if(!walls[newestPos.x][newestPos.y] && !allPositions[newestPos.x][newestPos.y].isFinished()) {
					left = newestPos;
				
				}else {
					left = null;
				}
			}else
				left = null;
			
			if(temp.x < columns-1) {
				Position newestPos = new Position(temp.x +1, temp.y);
				if(!walls[newestPos.x][newestPos.y]&& !allPositions[newestPos.x][newestPos.y].isFinished()) {
					right = newestPos;
				
				}else {
					right = null;
				}
			}else
				right = null;
			
			if(temp.y > 0) {
				Position newestPos = new Position(temp.x, temp.y -1);
				if(!walls[newestPos.x][newestPos.y] && !allPositions[newestPos.x][newestPos.y].isFinished()) {
					up = newestPos;
				
				}else {
					up = null;
				}
			}else
				up = null;
			
			if(temp.y < rows-1) {
				Position newestPos = new Position(temp.x, temp.y +1);
				if(!walls[newestPos.x][newestPos.y] && !allPositions[newestPos.x][newestPos.y].isFinished()) {
					down = newestPos;
				
				}else {
					down = null;
				}
			}else
				down = null;
			
			if(left != null) {
				
				if(temp.getGCost()+1 < allPositions[left.x][left.y].getGCost()) {
					allPositions[left.x][left.y].setGCost(temp.getGCost()+1);
					allPositions[left.x][left.y].setPrecursor(new Position(temp.x, temp.y));
				}
				if(!allPositions[left.x][left.y].isDiscovered()) {
					allPositions[left.x][left.y].setDiscovered(true);
					int fCost = allPositions[left.x][left.y].getGCost() + calculateHCost(new Position(left.x,  left.y), end);
					allPositions[left.x][left.y].setFCost(fCost);
					newPositions = this.addInRightPlaceA(newPositions, allPositions[left.x][left.y]);
				}
			}
			if(right != null) {
				
				if(temp.getGCost()+1 < allPositions[right.x][right.y].getGCost()) {
					allPositions[right.x][right.y].setGCost(temp.getGCost()+1);
					allPositions[right.x][right.y].setPrecursor(new Position(temp.x, temp.y));
				}
				if(!allPositions[right.x][right.y].isDiscovered()) {
					allPositions[right.x][right.y].setDiscovered(true);
					int fCost = allPositions[right.x][right.y].getGCost() + calculateHCost(new Position(right.x,  right.y), end);
					allPositions[right.x][right.y].setFCost(fCost);
					newPositions = this.addInRightPlaceA(newPositions, allPositions[right.x][right.y]);
				}
				
			}
			if(up != null) {
				
				
				if(temp.getGCost()+1 < allPositions[up.x][up.y].getGCost()) {
					allPositions[up.x][up.y].setGCost(temp.getGCost()+1);
					allPositions[up.x][up.y].setPrecursor(new Position(temp.x, temp.y));
				}
				if(!allPositions[up.x][up.y].isDiscovered()) {
					allPositions[up.x][up.y].setDiscovered(true);
					int fCost = allPositions[up.x][up.y].getGCost() + calculateHCost(new Position(up.x,  up.y), end);
					allPositions[up.x][up.y].setFCost(fCost);
					newPositions = this.addInRightPlaceA(newPositions, allPositions[up.x][up.y]);
				}
			}
			if(down != null) {
				
				if(temp.getGCost()+1 < allPositions[down.x][down.y].getGCost()) {
					allPositions[down.x][down.y].setGCost(temp.getGCost()+1);
					allPositions[down.x][down.y].setPrecursor(new Position(temp.x, temp.y));
				}
				if(!allPositions[down.x][down.y].isDiscovered()) {
					allPositions[down.x][down.y].setDiscovered(true);
					int fCost = allPositions[down.x][down.y].getGCost() + calculateHCost(new Position(down.x,  down.y), end);
					allPositions[down.x][down.y].setFCost(fCost);
					newPositions = this.addInRightPlaceA(newPositions, allPositions[down.x][down.y]);
				}
				
			}
			main.setNewDiscoveredBlocksA(newPositions);
			main.setFinishedBlocks(new Position(temp.x, temp.y));
			
				
		}
		
		if(newPositions.isEmpty() && allPositions[end.x][end.y].getPrecursor() == null) {
			return null;
		}else if(allPositions[end.x][end.y].getPrecursor() != null){
			Stack way = new Stack();
			AStarNode current = allPositions[end.x][end.y];
			while(current != allPositions[start.x][start.y]) {
				way.push(new Position(current.x, current.y));
				Position nextPos = current.getPrecursor();
				current = allPositions[nextPos.x][nextPos.y];
			}
			
			return way;
			
		}
		
		return null;
	}
	
	private List<Node> addInRightPlace(List<Node> tempList, Node newNode){
		tempList.toFirst();
		while(tempList.hasAccess() && newNode.getCost() > tempList.getContent().getCost()) {
			tempList.next();
		}
		
		if(!tempList.hasAccess()) {
			tempList.append(newNode);
		}else {
				tempList.insert(newNode);
			
		}
		return tempList;
	}

	private List<AStarNode> addInRightPlaceA(List<AStarNode> tempList, AStarNode newNode){
		tempList.toFirst();
		while(tempList.hasAccess() && newNode.getFCost() > tempList.getContent().getFCost()) {
			tempList.next();
		}
		
		if(!tempList.hasAccess()) {
			tempList.append(newNode);
		}else {
				tempList.insert(newNode);
			
		}
		return tempList;
	}
	
	private int calculateHCost(Position startPoint, Position endPoint) {
		int hCost = 0;
		
		if(startPoint != null && endPoint != null) {
			hCost = (startPoint.x - endPoint.x) * (startPoint.x - endPoint.x) + (startPoint.y - endPoint.y) * (startPoint.y - endPoint.y);
		}
		return hCost;
	}
	
}
