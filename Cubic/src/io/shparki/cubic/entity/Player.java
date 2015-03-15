package io.shparki.cubic.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	
	public static final int WIDTH = 50;
	public static final int HEIGHT = WIDTH * 5 / 2;
	
	
	private int x, y;
	
	public Player(int x, int y){
		this.x = x; this.y = y;
	}
	
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public void incX(int i) { x += i; }
	
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public void incY(int i) { y += i; }
	
	public void render(Graphics2D g2d){
		g2d.setColor(Color.MAGENTA);
		g2d.drawRect(x, y, WIDTH, HEIGHT);
	}
}
