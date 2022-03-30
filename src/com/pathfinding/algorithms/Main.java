package com.pathfinding.algorithms;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		//ui gui = new Gui("Pathfinding");
		new Main(21, 21);
	}
	
	private Frame frame;
	private boolean[][] walls;
	private Position start, end;
	private int rows, columns;
	
	
	public Main(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		frame = new Frame(this, "Pathfinding", rows, columns);
		walls = new boolean[columns][rows];
		
	}

	public void start(){
		
		//Starte den ausgewählten Algorithmus
		if(start != null && end != null) {
			frame.disableMenu();
			Stack solve = null;
			Pathfinder pathfinder = new Pathfinder(this, start, end, walls, rows, columns);
			if(frame.getPanels().getAlgorithm() == "Djkstra")
				solve = pathfinder.djkstra();
			else if(frame.getPanels().getAlgorithm() == "AStar")
				solve = pathfinder.AStar();
			
			if(solve != null) {
				while(!solve.isEmpty()) {
					Position temp = (Position)solve.top();
					frame.changeBlock(temp, "Way");
					solve.pop();
					
				}
			}	
			frame.enableMenu();
			
		}
	}
	public void setFinishedBlocks(Position finishedBlock) {
		frame.changeBlock(finishedBlock, "Finished");
	}
	
	public void setNewDiscoveredBlocks(List<Node> discoveredBlocks) {
		if(discoveredBlocks != null) {
			discoveredBlocks.toFirst();
			while(discoveredBlocks.hasAccess()) {
				Position tPosition = new Position(discoveredBlocks.getContent().x, discoveredBlocks.getContent().y);
				frame.changeBlock(tPosition, "Dicovered");
				discoveredBlocks.next();
			
			}
		}
	}
	
	public void setNewDiscoveredBlocksA(List<AStarNode> discoveredBlocks) {
		if(discoveredBlocks != null) {
			discoveredBlocks.toFirst();
			while(discoveredBlocks.hasAccess()) {
				Position tPosition = new Position(discoveredBlocks.getContent().x, discoveredBlocks.getContent().y);
				frame.changeBlock(tPosition, "Dicovered");
				discoveredBlocks.next();
			
			}
		}
	}
	
	
	
	public Position getStart() {
		return start;
	}

	public void setStart(Position start) {
		this.start = start;
	}
	
	public Position getEnd() {
		return end;
	}

	public void setEnd(Position end) {
		this.end = end;
	}
	
	public void newWall(Position newWall) {
		walls[newWall.x][newWall.y] = true;
	}
	
	public void deleteWall(Position oldWall) {
		walls[oldWall.x][oldWall.y] = false;
	}
	
	public void clearWalls() {
		walls = new boolean[columns][rows];
	}

	public void sleep(long time) {
		try {
			TimeUnit.MILLISECONDS.sleep(10);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
