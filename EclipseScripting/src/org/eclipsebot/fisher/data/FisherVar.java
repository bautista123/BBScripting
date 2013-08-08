package org.eclipsebot.fisher.data;

public class FisherVar {

	private static String status;
	private static int caught;
	private static int randomsEvaded;

	public static void setRandomsEvaded(int i) {
		randomsEvaded = i;
	}

	public static int getRandomsEvaded() {
		return randomsEvaded;
	}

	public static int getCaught() {

		return caught;
	}

	public static void setCaught(int i) {
		caught = i;
	}

	public static void setStatus(String s) {
		status = s;
	}

	public static String getStatus() {
		return status;
	}

}
