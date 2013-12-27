package study.flashcardgame.data.questions.other;

public class CustomCards {
	private static String[][] customCards = { {
			"You have not opened your question file. Please go to \"Options\" to open a question file.",
			"You have not opened your question file. Please go to \"Options\" to open a question file.",
			"None", "error" } };

	public static void setCustomCards(String[][] i) {
		customCards = i;
	}

	public static String[][] getCustomCards() {
		return customCards;
	}

}
