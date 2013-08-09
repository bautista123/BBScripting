package org.eclipsebot.creativethiever.data;

public class CreativeThieverVar {
	private static int steals;
	private static int randomsEvaded;
	private static String status;
	
	public static void setStatus(String i){
		status=i;
	}
	public static String getStatus(){
		return status;
	}

	public static void setSteals(int i) {
		steals = i;
	}

	public static int getSteals() {
		return steals;
	}
	public static void setRandomsEvaded(int i) {
		randomsEvaded = i;
	}

	public static int getRandomsEvaded() {
		return randomsEvaded;
	}
}
