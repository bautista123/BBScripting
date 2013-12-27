package study.flashcardgame.data;

import java.util.ArrayList;

public class Variables {
	private static ArrayList<ArrayList<Integer>> cardNumber = new ArrayList<ArrayList<Integer>>();
	private static boolean start = false;
	private static int index = 0;
	private static boolean essay = false;
	private static ArrayList<Integer> statistics = new ArrayList<Integer>();
	/*
	 * question amount[0] essay question amount[1] broken question amount[2]
	 * category amount[3] question amount seen[4]
	 */
	private static int questionsSeen = 0;
	private static String[][] cards;
	private static String subject;
	private static boolean emptyArray;

	public static void setEmptyArray(boolean i) {
		emptyArray = i;
	}

	public static boolean getEmptyArray() {
		return emptyArray;
	}

	public static void setSubject(String i) {
		subject = i;
	}

	public static String getSubject() {
		return subject;
	}

	public static void setCards(String[][] i) {
		cards = i;
	}

	public static String[][] getCards() {
		return cards;
	}

	public static void setQuestionsSeen(int i) {
		questionsSeen = i;
	}

	public static int getQuestionsSeen() {
		return questionsSeen;
	}

	public static void setStatistics(ArrayList i) {
		statistics = i;
	}

	public static ArrayList getStatistics() {
		return statistics;
	}

	public static void setEssay(boolean i) {
		essay = i;
	}

	public static boolean getEssay() {
		return essay;
	}

	public static void setIndex(int i) {
		index = i;
	}

	public static int getIndex() {
		return index;
	}

	public static void setCardNumber(ArrayList i) {
		cardNumber = i;
	}

	public static ArrayList getCardNumber() {
		return cardNumber;
	}

	public static void setStart(boolean i) {
		start = i;
	}

	public static boolean getStart() {
		return start;

	}
}
