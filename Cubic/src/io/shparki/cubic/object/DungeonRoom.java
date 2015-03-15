package io.shparki.cubic.object;


public class DungeonRoom {
	
	boolean alive = false;
	public DungeonRoom(){
		
	}
	
	public boolean isAlive() { return alive; }
	public void kill() { alive = false; }
	public void rez() { alive = false; }
	
}
