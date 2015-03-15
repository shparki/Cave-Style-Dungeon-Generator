package io.shparki.cubic.object;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tile {
	
	private static final Color BORDER_COLOR = Color.GRAY;
	
	private int x = 0, y = 0;
	private int size = 0;
	private Color color = Color.WHITE;
	private boolean shouldRender = true;
	private double scale = 1;
	
	public Tile(int x, int y, int size, Color color){
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}
	public Tile(int size, Color color) { this(0, 0, size, color); }
	public Tile() { this(0, 0, 0, Color.WHITE); }
	
	
	public void update(){
	}
	public void render(Graphics2D g2d){
		if (shouldRender){
			g2d.setColor(color);
			g2d.fillRect(x, y, (int) (size * scale), (int)(size * scale));
			g2d.setColor(BORDER_COLOR);
			g2d.drawRect(x, y, (int) (size * scale), (int) (size * scale));
			shouldRender = false;
		}
	}
	
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	
	public double getScale() { return scale; }
	public void setScale(double scale) { this.scale = scale; }
	public void incScale(double i) { scale += i; }
	
	public void setColor(Color color) { this.color = color; }
	public Color getColor() { return color; }
	
	
	public Tile getClone() { return new Tile(x, y, size, color); }
	
}
