package io.shparki.cubic;

import io.shparki.cubic.gfx.Window;
import io.shparki.cubic.io.Input;
import io.shparki.cubic.object.CaveGenerator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Game {
	
	private CaveGenerator cg;
	private double scaleX = 1.0, scaleY = 1.0;
	
	public Game(){  }
	public void init(){  cg = new CaveGenerator(); }
	
	public void update(){ 
		if (Input.isKeyDown(KeyEvent.VK_ENTER)) { cg.update(); }
		if (Input.isKeyDown(KeyEvent.VK_PLUS)){
			scaleX += .1;
			scaleY += .1;
		}
		if (Input.isKeyDown(KeyEvent.VK_MINUS)){
			if (scaleX - .1 >= 1) { scaleX -= .1; }
			if (scaleY - .1 >= 1) { scaleY -= .1; }
		}
		if (Input.isKeyDown(KeyEvent.VK_C)) { cg.clearSingletons(); }
	}
	
	public void render(Graphics2D g2d){
		
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		cg.render(g2d);
		
		g2d.scale(scaleX, scaleY);
	}
}
