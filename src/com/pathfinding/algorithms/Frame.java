package com.pathfinding.algorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Frame implements ActionListener{
	
	private Main main;
	
	private JButton startAlgorithm, clear;
	private JRadioButtonMenuItem menuItemDijkstra, menuItemAStar, menuItemWalls, menuItemStart, menuItemFinish, menuItemEraser;
	private Panels panel;
	
	public Frame(Main main, String title, int rows, int columns) {
		this.main = main;
		
		JFrame gui = new JFrame();
		gui.setTitle(title);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gui.setSize(600, 660);
		gui.setResizable(false);
		
	
		JMenuBar menu = new JMenuBar();
		UIManager.put("MenuBar.background", Color.orange);
		JMenu algorithm = new JMenu("Algorithm");
		JMenu placingOptions = new JMenu("Placing options");
		startAlgorithm = new JButton("Start");
		startAlgorithm.setBackground(Color.orange);
		startAlgorithm.setBorderPainted(false);
		startAlgorithm.setFocusable(false);
		startAlgorithm.addActionListener(this);
		
		clear = new JButton("Clear");
		clear.setBackground(Color.orange);
		clear.setBorderPainted(false);
		clear.setFocusable(false);
		clear.addActionListener(this);
		
		ButtonGroup algorithmsGroup = new ButtonGroup();
		ButtonGroup placingOptionsGroup = new ButtonGroup();
		
		
		menuItemDijkstra = new JRadioButtonMenuItem("Djkstra");
		algorithmsGroup.add(menuItemDijkstra);
		menuItemDijkstra.addActionListener(this);
		algorithm.add(menuItemDijkstra);
		menuItemDijkstra.setSelected(true);
		
		menuItemAStar = new JRadioButtonMenuItem("A-Star");
		algorithmsGroup.add(menuItemAStar);
		menuItemAStar.addActionListener(this);
		algorithm.add(menuItemAStar);
		
		
		menuItemWalls = new JRadioButtonMenuItem("Walls");
		placingOptionsGroup.add(menuItemWalls);
		menuItemWalls.addActionListener(this);
		placingOptions.add(menuItemWalls);
		menuItemWalls.setSelected(true);
		
		
		menuItemStart = new JRadioButtonMenuItem("Start");
		placingOptionsGroup.add(menuItemStart);
		menuItemStart.addActionListener(this);
		placingOptions.add(menuItemStart);
		
		menuItemFinish = new JRadioButtonMenuItem("Finish");
		placingOptionsGroup.add(menuItemFinish);
		menuItemFinish.addActionListener(this);
		placingOptions.add(menuItemFinish);
		
		menuItemEraser = new JRadioButtonMenuItem("Eraser");
		placingOptionsGroup.add(menuItemEraser);
		menuItemEraser.addActionListener(this);
		placingOptions.add(menuItemEraser);
		
		
		menu.add(algorithm);
		menu.add(placingOptions);
		menu.add(startAlgorithm);
		menu.add(clear);
		
		gui.setJMenuBar(menu);
	
		panel = new Panels(main, rows, columns);
		
		Container pane = gui.getContentPane();
		
		pane.setLayout(new GridLayout(1,1));
		//pane.setSize(550, 550);

		pane.add(panel);
		
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
	}
	
	public void disableMenu() {
		startAlgorithm.setEnabled(false);
		clear.setEnabled(false);
		menuItemDijkstra.setEnabled(false);
		menuItemAStar.setEnabled(false);
		menuItemWalls.setEnabled(false);
		menuItemStart.setEnabled(false);
		menuItemFinish.setEnabled(false);
		menuItemEraser.setEnabled(false);
	}
	
	public void enableMenu() {
		startAlgorithm.setEnabled(true);
		clear.setEnabled(true);
		menuItemDijkstra.setEnabled(true);
		menuItemAStar.setEnabled(true);
		menuItemWalls.setEnabled(true);
		menuItemStart.setEnabled(true);
		menuItemFinish.setEnabled(true);
		menuItemEraser.setEnabled(true);
	}
	
	public void changeBlock(final Position newBlock, final String blockType) {
		SwingWorker<Position, Void> worker = new SwingWorker<Position, Void>(){

			@Override
			protected Position doInBackground() throws Exception {
				// TODO Auto-generated method stub
				//System.out.println("BAckground");
				try {
				Thread.sleep(10);
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				return newBlock;
			}

			@Override
			protected void done() {
				// TODO Auto-generated method stub
				super.done();
				if(blockType == "Dicovered") {
					getPanels().getBlocks()[newBlock.x][newBlock.y].setToNewDiscoveredBlock();
				}else if(blockType == "Finished") {
					getPanels().getBlocks()[newBlock.x][newBlock.y].setToFinishedBlock();
				}else if(blockType == "Way") {
					getPanels().getBlocks()[newBlock.x][newBlock.y].setToWayBlock();
				}else {
					System.out.println("ERRRRROR!");
				}
					
			}
			
			
		};
		worker.execute();
		
	}
	

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = (Object) e.getSource();
		
		if(object == startAlgorithm) {
	
				main.start();
			
		}
		if(object == clear) {
			
			panel.clear();
		}
		if(object == menuItemDijkstra) {
		
			panel.setAlgorithm("Djkstra");
		}
		if(object == menuItemAStar) {
			
			panel.setAlgorithm("AStar");
		}
		if(object == menuItemWalls) {
			
			panel.setPlacingOption("Walls");
		}
		if(object == menuItemStart) {
			
			panel.setPlacingOption("Start");
		}
		if(object == menuItemFinish) {
			
			panel.setPlacingOption("Finish");
		}
		if(object == menuItemEraser) {
			
			panel.setPlacingOption("Erase");
		}
	}
	
	public Panels getPanels() {
		return panel;
	}
}
