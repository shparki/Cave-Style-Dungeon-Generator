package io.shparki.cubic;

import io.shparki.cubic.gfx.Window;
import io.shparki.cubic.io.Input;
import io.shparki.cubic.util.Time;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Engine extends Canvas implements Runnable{
	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 960;
	public static final String TITLE = "Cubic";
	public static final String VERSION = "1.0.0a";
	
	public static final int TARGET_UPS = 30;
	
	private boolean running = false;
	private Thread animator;
	
	private BufferStrategy bs;
	private Graphics2D g2d;
	
	private Game game;
	
	
	public Engine(){
		Window.create(WIDTH, HEIGHT, TITLE + " | " + VERSION + " | UPS: 0 | FPS: 0" , this);
		
		game = new Game();
		start();
	}
	
	public synchronized void start(){
		if (!running || animator == null){
			animator = new Thread(this, "Animator");
			animator.start();
		}
	}
	public synchronized void stop(){
		running = false;
	}
	
	public void run(){
		
		long beforeTime = 0;
		long currentTime = System.nanoTime();
		long upsCounter = 0, secCounter = 0;
		
		Time.init(TARGET_UPS);
		init();
		running = true;
		while(running){
			beforeTime = currentTime;
			currentTime = System.nanoTime();
			Time.setDelta(currentTime - beforeTime);
			
			upsCounter += Time.getDelta();
			if (upsCounter >= Time.getPeriod()){
				upsCounter -= Time.getPeriod();
				update();
				Time.incCurrentUPS(1);;
			}
			
			render();
			Time.incCurrentFPS(1);;
			
			secCounter += Time.getDelta();
			if (secCounter >= Time.SECOND){
				secCounter -= Time.SECOND;
				Time.updateSecond();
			}
		}
		System.exit(0);
	}
	
	public void init(){
		game.init();
	}
	
	public void update(){
		game.update();
		
		Window.setTitle(TITLE + " | " + VERSION + " | UPS: " + Time.getUPS() + " | FPS: " + Time.getFPS());
		Window.update();
	}
	public void render(){
		if (bs == null){
			bs = getBufferStrategy();
			if (bs == null){
				createBufferStrategy(3);
				return;
			}
		}
		
		g2d = (Graphics2D) bs.getDrawGraphics();
		
		game.render(g2d);
		
		bs.show();
		g2d.dispose();
	}
	
	
	
}
