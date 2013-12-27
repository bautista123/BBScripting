package test.dotgame;

import java.util.ArrayList;

public class DotComBust {
	static GameHelper helper = new GameHelper();
	static ArrayList<DotCom> DotComList = new ArrayList<DotCom>();
	static int numOfGuesses = 0;

	public static void main(String[] args) {

	}

	void setUpGame() {
		DotCom one = new DotCom();
		one.setName("ebay.com");
		DotCom two = new DotCom();
		two.setName("youtube.com");
		DotCom three = new DotCom();
		three.setName("java.com");
		DotComList.add(one);
		DotComList.add(two);
		DotComList.add(three);

		System.out.println("Your goal is to sink all three dotComs.");
		System.out
				.println("The dotComs are: ebay.com, youtube.com and java.com");
		System.out
				.println("Try sinking them all in the fewest number of guesses!");
	}

	void startPlaying() {

	}

	void finishGame() {

	}

	void checkUserGuess() {

	}
}
