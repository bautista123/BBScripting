package org.eclipsebot.draynorfisher.data;

public class DraynorFisherVar {

	private static String status;
	private static int caught;
	private static int randomsEvaded;
	private static boolean guiwait;
	private static boolean drop;
	private static boolean bank;
	
	public static void setDrop(boolean i){
		drop=i;
	}
	public static boolean getDrop(){
		return drop;
	}
	public static void setBank(boolean i){
		bank=i;
	}
	public static boolean getBank(){
		return bank;
	}
	
	public static void setGuiWait(boolean i){
		guiwait=i;
	}
	public static boolean getGuiWait(){
		return guiwait;
	}

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