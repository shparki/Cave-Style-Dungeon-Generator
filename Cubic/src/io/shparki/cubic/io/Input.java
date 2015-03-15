package io.shparki.cubic.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Input implements KeyListener, MouseListener, MouseMotionListener{
	
	private static ArrayList<Integer> keysDown = new ArrayList<Integer>();
	private static ArrayList<Integer> keysPressed = new ArrayList<Integer>();
	private static ArrayList<Integer> keysReleased = new ArrayList<Integer>();
	
	private static ArrayList<Integer> mouseDown = new ArrayList<Integer>();
	private static ArrayList<Integer> mousePressed = new ArrayList<Integer>();
	private static ArrayList<Integer> mouseReleased = new ArrayList<Integer>();
	
	private static int mouseX, mouseY;
	private static int mousePressX, mousePressY;
	private static int mouseReleaseX, mouseReleaseY;
	
	public static boolean isKeyDown(int keyCode) { return keysDown.contains(Integer.valueOf(keyCode)); }
	public static boolean isKeyPressed(int keyCode) { return keysPressed.contains(Integer.valueOf(keyCode)); }
	public static boolean isKeyReleased(int keyCode) { return keysReleased.contains(Integer.valueOf(keyCode)); }
	
	public static boolean isMouseDown(int mouseCode) { return mouseDown.contains(Integer.valueOf(mouseCode)); }
	public static boolean isMousePressed(int mouseCode) { return mousePressed.contains(Integer.valueOf(mouseCode)); }
	public static boolean isMouseReleased(int mouseCode) { return mouseReleased.contains(Integer.valueOf(mouseCode)); }
	
	public static int getMouseX() { return mouseX; } public static int getMouseY() { return mouseY; }
	public static int getMousePressX() { return mousePressX; } public static int getMousePressY() { return mousePressY; }
	public static int getMouseReleaseX() { return mouseReleaseX; } public static int getMouseReleaseY() { return mouseReleaseY; }
	
	public static void update(){
		keysPressed.clear();
		keysReleased.clear();
		
		mousePressed.clear();
		mouseReleased.clear();
	}
	
	public void mouseMoved(MouseEvent e) { mouseX = e.getX(); mouseY = e.getY(); }
	
	public void mousePressed(MouseEvent e) {
		if (!isMousePressed(e.getButton()) && !isMouseDown(e.getButton())){
			mousePressed.add(Integer.valueOf(e.getButton()));
			mousePressX = e.getX(); mousePressY = e.getY();
		}
		if (!isMouseDown(e.getButton())){
			mouseDown.add(Integer.valueOf(e.getButton()));
		}
	}
	public void mouseReleased(MouseEvent e) {
		if (!isMouseReleased(e.getButton()) && isMouseDown(e.getButton())){
			mouseReleased.add(Integer.valueOf(e.getButton()));
			mouseReleaseX = e.getX(); mouseReleaseY = e.getY();
		}
		if (isMouseDown(e.getButton())){
			mouseDown.remove(Integer.valueOf(e.getButton()));
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (!isKeyPressed(e.getKeyCode()) && !isKeyDown(e.getKeyCode())){
			keysPressed.add(Integer.valueOf(e.getKeyCode()));
		}
		if (!isKeyDown(e.getKeyCode())){
			keysDown.add(Integer.valueOf(e.getKeyCode()));
		}
	}
	public void keyReleased(KeyEvent e) {
		if (!isKeyReleased(e.getKeyCode()) && isKeyDown(e.getKeyCode())){
			keysReleased.add(Integer.valueOf(e.getKeyCode()));
		}
		if (isKeyDown(e.getKeyCode())){
			keysDown.remove(Integer.valueOf(e.getKeyCode()));
		}
	}

	
	public void mouseClicked(MouseEvent e) { }
	public void keyTyped(KeyEvent arg0) {  }
	public void mouseExited(MouseEvent arg0) {  }
	public void mouseEntered(MouseEvent arg0) {  }
	public void mouseDragged(MouseEvent arg0) {  }
}
