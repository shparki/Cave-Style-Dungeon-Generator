package io.shparki.cubic.util;

public class Time {
	
	public static final long SECOND = 1_000_000_000;
	
	private static int targetUPS;
	private static long period;
	
	private static double delta;
	private static int currentFPS, currentUPS;
	private static int FPS, UPS;
	
	
	public static void init(int targetUPS){
		Time.targetUPS = targetUPS;
		period = SECOND / targetUPS;
		
		delta = 0;
		currentFPS = 0; currentUPS = 0;
		FPS = 0; UPS = 0;
	}
	
	
	public static long getTime() { return System.nanoTime(); }
	
	public static double getDelta() { return delta; }
	public static void setDelta(double delta) { Time.delta = delta; }

	
	public static int getCurrentFPS() { return currentFPS; }
	public static void setCurrentFPS(int currentFPS) { Time.currentFPS = currentFPS; }
	public static void incCurrentFPS(int i) { currentFPS += i; }
	
	public static int getCurrentUPS() { return currentUPS; }
	public static void setCurrentUPS(int currentUPS) { Time.currentUPS = currentUPS; }
	public static void incCurrentUPS(int i) { currentUPS += i; }
	
	public static void updateSecond() {
		UPS = currentUPS; FPS = currentFPS;
		currentUPS = 0; currentFPS = 0;
	}
	
	public static int getUPS() { return UPS; }
	public static int getFPS() { return FPS; }
	
	
	public static int getTargetUPS() { return targetUPS; }
	public static void setTargetUPS(int targetUPS) { Time.targetUPS = targetUPS; period = SECOND / targetUPS; }
	public static void incTargetUPS(int i) { targetUPS += i; period = SECOND / targetUPS; }
	
	public static long getPeriod() { return period; }
	
}
