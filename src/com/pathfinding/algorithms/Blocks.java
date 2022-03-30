package com.pathfinding.algorithms;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Blocks extends JPanel{

	private String blockType;
	
	private Main main;
	private Panels context;
	
	private Position position;
	
	public Blocks(Main main, Panels context, int row, int column) {
		blockType = "Clear";
		
		this.main = main;
		
		this.context = context;
		
		position = new Position(column, row);
		setBackground(Color.white);
		
		event e = new event();
		addMouseListener(e);
		
	}
	
	
	public void setToFinishedBlock() {
		if(blockType != "Start" && blockType != "End") {
			setBackground(Color.cyan);
			blockType = "Finished";
		}
	}
	
	public void setToNewDiscoveredBlock() {
		if(blockType != "Start" && blockType != "End") {
			setBackground(Color.yellow);
			blockType = "Discovered";
		}
	}
	
	public void setToWayBlock() {
		if(blockType != "Start" && blockType != "End") {
			setBackground(Color.blue);
			blockType = "Way";
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public class event implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			//System.out.println("Width: " + getWidth() +" Heigth: "+ getHeight());
		}

		public void mouseEntered(MouseEvent arg0) {
			
			if(context.getMouseDown()) {
				if(context.getPlacingOption() == "Walls") {
					if(blockType == "Clear") {
						setBackground(Color.black);
						blockType = "Wall";
						main.newWall(position);
					}
				}else if(context.getPlacingOption() == "Erase") {
						if(blockType == "Wall") {
							
							main.deleteWall(position);
						}else if(blockType == "Start") {
							context.deleteStartintPoint();
							main.setStart(null);
						}else if(blockType == "End") {
							context.deleteEndPoint();
							main.setEnd(null);
						}
						setBackground(Color.white);
						blockType = "Clear";
				
				}	
			}
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(!context.getMouseDown()) {
				context.setMouseDown(true);
			}
			if(context.getPlacingOption() == "Walls") {
				if(blockType == "Clear") {
					setBackground(Color.black);
					blockType = "Wall";
					main.newWall(position);
				}
			}else if(context.getPlacingOption() == "Erase") {
				if(blockType == "Wall") {
					
					main.deleteWall(position);
				}else if(blockType == "Start") {
					context.deleteStartintPoint();
					main.setStart(null);
				}else if(blockType == "End") {
					context.deleteEndPoint();
					main.setEnd(null);
				}
				setBackground(Color.white);
				blockType = "Clear";
			}else if(context.getPlacingOption() == "Start") {
				if(blockType != "End") {
					context.deleteStartintPoint();
					context.setStart(position);
					main.setStart(position);
					setBackground(Color.green);
					blockType = "Start";
				}
				
			}else if(context.getPlacingOption() == "Finish") {
				if(blockType != "Start") {
					context.deleteEndPoint();
					context.setEnd(position);
					main.setEnd(position);
					setBackground(Color.red);
					blockType = "End";
				}
				
			}else {
				System.out.println("An error has occured");
			}
		}
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(context.getMouseDown()) {
				context.setMouseDown(false);
			}
		}
		
	}
	
	public void setBlockType(String newType) {
		 blockType = newType;
	}
	
	public String getBlockType() {
		return blockType;
	}
	
	
}
