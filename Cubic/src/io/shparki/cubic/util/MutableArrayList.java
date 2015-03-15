package io.shparki.cubic.util;

import java.util.ArrayList;
import java.util.Collection;

public class MutableArrayList<E> extends ArrayList<E>{
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 1;
	public static final int DIRECTION_SOUTH = 2;
	public static final int DIRECTION_WEST = 3;
	
	private static final long serialVersionUID = 1L;
	
	private int width = 0, height = 0;
	
	
	public MutableArrayList(){
		super();
	}
	public MutableArrayList(Collection<? extends E> c){
		super(c);
	}
	public MutableArrayList(int initialCapacity){
		super(initialCapacity);
	}
	
	
	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }
	public void incWidth(int i) { width += i; }
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }
	public void incHeight(int i) { height += i; }
	
	public void increase(int direction){
		
		if (direction == DIRECTION_NORTH){
			for (int i =0; i < width; i++){
				add(0, null);
			}
			height ++;
		}
		
		if (direction == DIRECTION_EAST){
			ArrayList<E> tempList = new ArrayList<E>();
			int counter = 0;
			
			for (int i = 0; i < height; i ++){
				for (int j = 0; j < width; j++){
					tempList.add(get(counter));
					counter ++;
				}
				tempList.add(null);
			}
			
			clear();
			
			for (int i = 0; i < tempList.size(); i++){
				add(tempList.get(i));
			}
			
			width ++;
		}
		
		if (direction == DIRECTION_SOUTH){
			for (int i = 0; i < width; i++){
				add(null);
			}
		}
		
		if (direction == DIRECTION_WEST){
			ArrayList<E> tempList = new ArrayList<E>();
			int counter = 0;
			
			for (int i = 0; i < height; i ++){
				tempList.add(null);
				for (int j = 0; j < width; j++){
					tempList.add(get(counter));
					counter ++;
				}
			}
			
			clear();
			
			for (int i = 0; i < tempList.size(); i++){
				add(tempList.get(i));
			}
			
			width ++;
		}
		
	}
	
	public String toString(){
		String returnString = "";
		for (int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				returnString += (get(y * width + x) + " ");
			}
			returnString += "\n";
		}
		
		return returnString;
	}
	
}
