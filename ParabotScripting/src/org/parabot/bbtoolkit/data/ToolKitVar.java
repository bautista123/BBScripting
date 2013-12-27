package org.parabot.bbtoolkit.data;

public class ToolKitVar {
	private static boolean clicking;
	private static boolean spawning;
	private static int SpawnID;
	private static boolean paint;
	private static int interval;
	private static boolean typing;
	private static String message;
	private static int DroppedID;
	private static boolean dropping;
	private static boolean DropT;
	private static boolean guiWait;
	private static int loaderV;

	public static int getLoaderV() {
		return loaderV;
	}

	public static void setLoaderV(int i) {
		loaderV = i;
	}

	public static boolean getGuiWait() {
		return guiWait;
	}

	public static void setGuiWait(boolean i) {
		guiWait = i;
	}

	public static boolean getDropT() {
		return DropT;
	}

	public static void setDropT(boolean i) {
		DropT = i;
	}

	public static boolean getDropper() {
		return dropping;
	}

	public static void setDropper(boolean i) {
		dropping = i;
	}

	public static int getDroppedID() {
		return DroppedID;
	}

	public static void setDroppedID(int i) {
		DroppedID = i;
	}

	public static String getMessage() {
		return message;
	}

	public static void setMessage(String i) {
		message = i;
	}

	public static boolean getTyper() {
		return typing;
	}

	public static void setTyper(boolean i) {
		typing = i;
	}

	public static int getInterval() {
		return interval;
	}

	public static void setInterval(int i) {
		interval = i;
	}

	public static boolean getPaint() {
		return paint;
	}

	public static void setPaint(boolean i) {
		paint = i;
	}

	public static int getSpawnID() {
		return SpawnID;
	}

	public static void setSpawnID(int i) {
		SpawnID = i;
	}

	public static boolean getClicker() {
		return clicking;
	}

	public static void setClicker(boolean i) {
		clicking = i;
	}

	public static void setSpawner(boolean i) {
		spawning = i;
	}

	public static boolean getSpawner() {
		return spawning;
	}
}
