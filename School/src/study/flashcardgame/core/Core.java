package study.flashcardgame.core;

import java.util.ArrayList;
import java.util.Collections;

import study.flashcardgame.data.Variables;
import study.flashcardgame.data.questions.english.EnglishData;
import study.flashcardgame.data.questions.other.CustomCards;
import study.flashcardgame.data.questions.religion.ReligionData;
import study.flashcardgame.gui.StartGUI;

public class Core {
	private static int questionAmount = 0;// [0]
	private static int essayQuestionAmount = 0;// [1]
	private static int brokenQuestionAmount = 0;// [2]
	private static int categoriesAmount = 0;// [3]
	private static ArrayList<String> categories = new ArrayList<String>();

	public static void main(String[] args) {
		StartGUI gi = new StartGUI();
		if (!Variables.getStart()) {
			gi.setVisible(true);
		}
	}

	public static String getCardQuestion() {
		return formatArray(Variables.getCards()[(int) Variables.getCardNumber()
				.get(Variables.getIndex())][0]);
	}

	public static String getCardAnswer() {
		return formatArray(Variables.getCards()[(int) Variables.getCardNumber()
				.get(Variables.getIndex())][1]);
	}

	public static String getCardCatagory() {
		return formatArray(Variables.getCards()[(int) Variables.getCardNumber()
				.get(Variables.getIndex())][2]);
	}

	public static void CheckEssay() {
		if (Variables.getCards()[(int) Variables.getCardNumber().get(
				Variables.getIndex())].length > 3
				&& !Variables.getCards()[(int) Variables.getCardNumber().get(
						Variables.getIndex())][3].toString().equalsIgnoreCase(
						"error")) {
			Variables.setEssay(true);
		} else if (Variables.getCards()[(int) Variables.getCardNumber().get(
				Variables.getIndex())].length > 3
				&& Variables.getCards()[(int) Variables.getCardNumber().get(
						Variables.getIndex())][3].toString().equalsIgnoreCase(
						"error")) {
			Variables.setEmptyArray(true);
			Variables.setEssay(false);
		}

	}

	public static void countEssayQuestions() {
		for (int j = 0; j < Variables.getCards().length; j++) {
			if (Variables.getCards()[j].length > 3) {
				if (Variables.getCards()[j][3].toString().toLowerCase() == "true") {
					essayQuestionAmount++;
				} else if (Variables.getCards()[j][3].toString().toLowerCase() == "error") {
					Variables.setEmptyArray(true);
				}
			}
		}
	}

	public static void SetArrayLength(ArrayList i) {
		for (int j = 0; j < Variables.getCards().length; j++) {
			if (Variables.getCards()[j].length < 1) {
				brokenQuestionAmount++;
			}
			i.add(j);
			questionAmount++;
		}
	}

	public static void addToStatArrayList(ArrayList i) {
		for (int j = 0; j < 4; j++) {
			if (j == 0) {
				i.add(questionAmount);
			} else if (j == 1) {
				i.add(essayQuestionAmount);
			} else if (j == 2) {
				i.add(brokenQuestionAmount);
			} else if (j == 3) {
				i.add(categoriesAmount);
			}

		}
	}

	public static void categoryCounter() {
		for (int j = 0; j < Variables.getCards().length; j++) {
			if (Variables.getCards()[j][2] != ""
					&& !categories.contains(Variables.getCards()[j][2]
							.toLowerCase())) {
				categories.add(Variables.getCards()[j][2].toLowerCase());
			}
		}
	}

	public static void onStart() {
		if (Variables.getStart()) {
			if (Variables.getSubject().equalsIgnoreCase("Religion")) {
				Variables.setCards(ReligionData.getReligionCards());
			} else if (Variables.getSubject().equalsIgnoreCase("English")) {
				Variables.setCards(EnglishData.getEnglishCards());
			} else if (Variables.getSubject().equalsIgnoreCase("Custom")) {
				Variables.setCards(CustomCards.getCustomCards());
			}
			Variables.setStart(false);
			SetArrayLength(Variables.getCardNumber());
			Collections.shuffle(Variables.getCardNumber());
			countEssayQuestions();
			categoryCounter();
			for (int i = 0; i < categories.size(); i++) {
				categoriesAmount++;
			}
			addToStatArrayList(Variables.getStatistics());
		}
	}

	public static void LoopIndex() {
		if (Variables.getIndex() == Variables.getCardNumber().size()) {
			Variables.setIndex(0);
		}
	}

	public static void setUpCustomCards() {

	}

	public static String formatArray(String str) {
		if (str == null) { // Handle null.
			return null;
		}
		StringBuilder sb = new StringBuilder(); // An output buffer.
		int count = 0;
		for (char ch : str.toCharArray()) { // loop over the characters.
			if (ch == ' ') { // test for space.
				count++;
			}
			if (count == 11) { // every sixth space.
				count = 0;
				sb.append(" \n");
			}
			sb.append(ch);
		}
		return sb.toString();// have to make it a string array

	}
}
