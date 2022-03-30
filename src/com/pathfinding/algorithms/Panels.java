package com.pathfinding.algorithms;

import java.awt.*;
import javax.swing.*;

public class Panels extends JPanel{

	private Blocks[][] panels;
	
	private Main main;
	
	private Position start, end;
	
	private int rows, columns;
	
	private String algorithm;
	private String placingOption;
	
	private boolean mouseDown;
	
	public Panels(Main main, int rows, int columns) {
		mouseDown = false;
		
		this.main = main;
		
		start = null;
		end = null;
		
		this.rows = rows;
		this.columns = columns;
		
		algorithm = "Dijkstra";
		placingOption = "Walls";
		
		this.setLayout(new GridLayout(rows, columns));
		
		panels = new Blocks[columns][rows];
		for(int i=0; i< rows; i++) {
			for(int j=0; j<columns; j++) {
				panels[j][i] = new Blocks(main, this, i, j);
				this.add(panels[j][i]);
				
			}
		}
		
		
	}
	public void clear() {
		main.clearWalls();
		main.setStart(null);
		main.setEnd(null);
		
 		for(int i=0; i< rows; i++) {
			for(int j=0; j<columns; j++) {
				panels[j][i].setBackground(Color.white);
				panels[j][i].setBlockType("Clear");
				
			}
		}
		this.deleteStartintPoint();
		this.deleteEndPoint();
	}
	
	public void deleteStartintPoint() {
		if(start != null) {
			panels[start.x][start.y].setBackground(Color.white);
			panels[start.x][start.y].setBlockType("Clear");
			start = null;
		}
	}
	
	public void deleteEndPoint() {
		if(end != null) {
			panels[end.x][end.y].setBackground(Color.white);
			panels[end.x][end.y].setBlockType("Clear");
			end = null;
		}
	}
	
	
	public boolean getMouseDown() {
		return mouseDown;
	}
	public void setMouseDown(boolean newState) {
		mouseDown = newState;
	}

	public String getPlacingOption() {
		return placingOption;
	}

	public void setPlacingOption(String placingOption) {
		this.placingOption = placingOption;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public void setStart(Position newPosition) {
		start = newPosition;
	}
	
	public void setEnd(Position newPosition) {
		end = newPosition;
	}
	
	public Blocks[][] getBlocks(){
		return panels;
	}
	
}
