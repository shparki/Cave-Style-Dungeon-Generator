package io.shparki.cubic.object;

import io.shparki.cubic.io.Input;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class CaveGenerator {
	public static final int MIN_WIDTH = 350, MAX_WIDTH = 550;
	
	public static final int MAX_MINERS = MAX_WIDTH * 2;
	public static final int NEW_MINER_CHANCE = 8;
	public static final int SIZE = 3;
	
	public static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	public static final int VALUE_SPACE = 0, VALUE_WALL = 1;
	
	private Random rand = new Random();
	private int width, height;
	private int[][] mapArray, prevArray;
	private ArrayList<Miner> miners = new ArrayList<Miner>();
	private ArrayList<Miner> newMiners = new ArrayList<Miner>();
	
	
	public CaveGenerator(){
		width = rand.nextInt(MAX_WIDTH - MIN_WIDTH) + MIN_WIDTH;
		height = width * 3 / 4;
		mapArray = new int[height][width];
		prevArray = new int[height][width];
		System.out.println("Successfully Initiated with Width: " + width + " & Height: " + height);
	
		miners.add(new Miner(width / 2, height / 2));
		
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				mapArray[y][x] = VALUE_WALL;
			}
		}
	}
	
	public void updateConditions(){
		prevArray = mapArray.clone();
	}
	public void updateAll(){
		updateConditions();
		
		while (miners.size() <= MAX_MINERS && getActiveMiners().size() > 0){
			for (Miner miner : miners) { miner.dig(); }
			
			miners.addAll(newMiners);
			newMiners.clear();
			
			updateConditions();
		}
		
		clearSingletons();
		
		if (!equals()) updateAll();
	}
	
	public void update(){
		
		updateConditions();
		
		if (Input.isKeyDown(KeyEvent.VK_ENTER)){
			if (miners.size() <= MAX_MINERS){
				for (Miner miner : miners){ miner.dig(); }
			
				miners.addAll(newMiners);
				newMiners.clear();
			}
		}
		
		if (Input.isKeyPressed(KeyEvent.VK_BACK_SLASH)){
			updateAll();
		}
		
		if (Input.isKeyPressed(KeyEvent.VK_C)){
			clearSingletons();
		}
	}
	public void render(Graphics2D g2d){
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				if (mapArray[y][x] == VALUE_SPACE){
					g2d.setColor(Color.WHITE);
				} else if (mapArray[y][x] == VALUE_WALL){
					g2d.setColor(Color.BLACK);
				}
				
				g2d.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
			}
		}
		for (Miner miner : miners){
			miner.render(g2d);
		}
	}

	public ArrayList<Miner> getActiveMiners(){
		ArrayList<Miner> activeMiners = new ArrayList<Miner>();
		
		for (Miner miner : miners){
			if (miner.isActive()) activeMiners.add(miner);
		}
		
		return activeMiners;
	}
	public void clearSingletons(){
		
		System.out.println("hi");
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				
				if (mapArray[y][x] == VALUE_WALL){
					if (y + 1 < height && y - 1 >= 0){
						if (mapArray[y + 1][x] == VALUE_SPACE && mapArray[y - 1][x] == VALUE_SPACE){
							mapArray[y][x] = VALUE_SPACE;
						}
					}
					
					if (x + 1 < width && x - 1 >= 0){
						if (mapArray[y][x + 1] == VALUE_SPACE && mapArray[y][x - 1] == VALUE_SPACE){
							mapArray[y][x] = VALUE_SPACE;
						}
					}
					
					if (y + 1 < height && y - 2 >= 0){
						if (mapArray[y + 1][x] == VALUE_SPACE && mapArray[y - 2][x] == VALUE_SPACE){
							mapArray[y][x] = VALUE_SPACE;
						}
					}
				}
				
				if (mapArray[y][x] == VALUE_SPACE){
					if (y + 1 < height && y - 1 >= 0){
						if (mapArray[y + 1][x] == VALUE_WALL && mapArray[y - 1][x] == VALUE_WALL){
							mapArray[y][x] = VALUE_WALL;
						}
					}
					
					if (x + 1 < width && x - 1 >= 0){
						if (mapArray[y][x + 1] == VALUE_WALL && mapArray[y][x - 1] == VALUE_WALL){
							mapArray[y][x] = VALUE_WALL;
						}
					}
				}
			}
		}
	}

	

	
	
	public int[][] getMapArray(){
		return mapArray;
	}
	public boolean equals(){
		
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width ;x++){
				if (prevArray[y][x] != mapArray[y][x]) return false;
			}
		}
		
		return true;
	}
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	private class Miner{
		
		private int x, y;
		private boolean active = true;
		
		public Miner(int x, int y){
			this.x = x; this.y = y;
		}

		
		public boolean isActive() { return active; }
		
		
		public void dig(){
			if (active){
				
				int direction = 0;
				
				if (getAvailableDirections().size() == 0){
					if (!isOnlyActiveMiner()){
						active = false;
						return;
					} else {
						direction = rand.nextInt(4);
					}
				} else {
					int dirIndex = rand.nextInt(getAvailableDirections().size());
					direction = getAvailableDirections().get(dirIndex);
				}
				
				
				if (rand.nextInt(100) < NEW_MINER_CHANCE) newMiners.add(new Miner(x, y));
				
				switch(direction){
				case NORTH: y --; break;
				case EAST: x ++; break;
				case SOUTH: y ++; break;
				case WEST: x --; break;
				}
				
				mapArray[y][x] = VALUE_SPACE;
			}
		}
		
		public ArrayList<Integer> getAvailableDirections(){
			ArrayList<Integer> directions = new ArrayList<Integer>();
			
			if (y - 1 >= 0){
				if (mapArray[y - 1][x] == VALUE_WALL) directions.add(Integer.valueOf(NORTH));
			}
			
			if (x + 1 < width){
				if (mapArray[y][x + 1] == VALUE_WALL) directions.add(Integer.valueOf(EAST));
			}
			
			if (y + 1 < height){
				if (mapArray[y + 1][x] == VALUE_WALL) directions.add(Integer.valueOf(SOUTH));
			}
			
			if (x - 1 >= 0){
				if (mapArray[y][x - 1] == VALUE_WALL) directions.add(Integer.valueOf(WEST));
			}
			return directions;
		}
		public boolean isOnlyActiveMiner(){
			int numActiveMiners = 0;
			
			for (Miner miner : miners){
				if (miner.isActive()){
					numActiveMiners ++;
				}
			}
			
			return (numActiveMiners == 1);
		}
		
		public void render(Graphics2D g2d){
			if (active){
				g2d.setColor(Color.MAGENTA);
				g2d.drawRect(x * SIZE, y * SIZE, SIZE, SIZE);
			}
		}
	}

}
