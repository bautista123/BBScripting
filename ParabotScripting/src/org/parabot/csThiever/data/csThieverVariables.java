package org.parabot.csThiever.data;

public class csThieverVariables {
	private static int stallchosen;
	private static boolean isRunning;
	private static int currentLevel;
	private static int startLevel;
	private static int thieves;

	public static void setThieves(int i) {
		thieves = i;
	}

	public static int getThieves() {
		return thieves;
	}

	public static void setStartLevel(int i) {
		startLevel = i;
	}

	public static int getStartLevel() {
		return startLevel;
	}

	public static void setCurrentLevel(int i) {
		currentLevel = i;
	}

	public static int getCurrentLevel() {
		return currentLevel;
	}

	public static void setIsRunning(boolean i) {
		isRunning = i;
	}

	public static boolean getIsRunning() {
		return isRunning;
	}

	public static void setStallChosen(int i) {
		stallchosen = i;
	}

	public static int getStallChosen() {
		return stallchosen;
	}
}
