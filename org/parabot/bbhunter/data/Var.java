package org.parabot.bbhunter.data;

/**
 * Created with IntelliJ IDEA.
 * User: Bautista
 * Date: 8/1/13
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Var {
    private static long startTime;
    private static boolean guiwait;
    private static int impling;
    private static boolean go = false;
    private static int caught;
    private static int money;
    private static int profit;
    private static int loginC;
    private static String status;

    public static void setStartTime(long i){
        startTime=i;
    }
    public static long getStartTime(){
        return startTime;

    }

    public static void setLoginC(int l) {
        loginC = l;
    }

    public static int getLoginC() {
        return loginC;
    }

    public static void setGuiwait(boolean gui1) {
        guiwait = gui1;
    }

    public static boolean getGuiwait() {
        return guiwait;
    }

    public static void setProfit(int p) {
        profit = p;
    }

    public static int getProfit() {
        return profit;
    }

    public static void setMoney(int m) {
        money = m;
    }

    public static int getMoney() {
        return money;
    }

    public static void setStatus(String st) {
        status = st;
    }

    public static String getStatus() {
        return status;
    }

    public static void setImpling(int impling1) {
        impling = impling1;
    }

    public static int getImpling() {
        return impling;
    }

    public static void setGo(boolean go1) {
        go = go1;
    }

    public static boolean getGo() {
        return go;
    }

    public static void setCaught(int c1) {
        caught = c1;

    }

    public static int getCaught() {
        return caught;
    }

}
