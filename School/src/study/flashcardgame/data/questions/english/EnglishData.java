package study.flashcardgame.data.questions.english;

public class EnglishData {
	private static String[][] englishCards = {
			{
					"Ten Plagues",
					"decalogue list of 10 rules of moral behavior that god gave moses that are the basis for ethical conduct",
					"Terms" }, { "Canaan", "the promised land", "Terms" }, };

	public static void setEnglishCards(String[][] i) {
		englishCards = i;
	}

	public static String[][] getEnglishCards() {
		return englishCards;
	}

}
