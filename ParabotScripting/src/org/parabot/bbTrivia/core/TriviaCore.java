package org.parabot.bbTrivia.core;

import java.util.ArrayList;

import org.parabot.bbTrivia.strategies.TriviaSolver;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.events.MessageEvent;
import org.rev317.api.events.listeners.MessageListener;

@ScriptManifest(author = "Bautista123 & Capslock", category = Category.OTHER, description = "Answers trivia for pkp!", name = "bbTrivia", servers = { "DeviousPK & RecklessPK" }, version = 0.1)
public final class TriviaCore extends Script implements MessageListener {
	public static ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	public static String[][] trivia = new String[][]{
			{ "What is the capital of Sao Tome and Principe?", "Sao Tome" },
			{ "What is the capital of Tajikistan?", "Dushanbe" },
			{ "What is the capital of Switzerland?", "Bern" },
			{ "What is the capital of Uganda?", "Kampala" },
			{ "What is the capital of Portugal?", "Lisbon" },
			{ "What is the capital of Kuwait?", "Kuwait City" },
			{ "What is the capital of Luxembourg?", "Luxembourg" },
			{ "What is the capital of Iceland?", "Reykjavik" },
			{ "What is Alaskas second most populous city?", "Fairbanks" },
			{ "What is the capital of Malta?", "Valletta" },
			{ "Clinophobia is the fear of?", "Birds" },
			{ "Who invented the telephone?", "Alexander Graham Bell" },
			{ "What is the capital of Spain?", "Madrid" },
			{ "What is the capital of Mozambique?", "Maputo" },
			{ "What is the capital of Canada?", "Ottawa" },
			{ "In which year did Frank Zappa die?", "1993" },
			{ "What is the capital of United Arab Emirates?", "Abu Dhabi" },
			{ "What is the capital of Japan?", "Tokyo" },
			{ "What is the capital of India?", "New Delhi" },
			{ "What month of the year only has 28 days?", "February" },
			{ "Which is the most spoken language?", "Chinese" },
			{ "How many strings does a violin have?", "4" },
			{ "What is the capital of Tuvalu?", "Vaiaku village" },
			{ "What is the capital of Nepal?", "Kathmandu" },
			{ "What is the capital of Ethiopia?", "Addis Ababa" },
			{ "What does a pker say when he dies?", "L2Pk" },
			{ "Wich is Britain's second largest city?", "Birmingham" },
			{ "What is the capital of Austria?", "Vienna" },
			{ "Who is nr 1 owner?", "Pandora" },
			{ "What is the capital of United Kingdom?", "London" },
			{ "What is the capital of Indonesia?", "Jakarta" },
			{ "What colour is the sun", "Orange" },
			{ "sinx*sinx + cosx*cosx = ?", "1" },
			{ "What is the antonym of the word 'synonym'?", "Antonym" },
			{ "Which is the smallest ocean?", "Artic" }, { "", "" },
			{ "sinx = 0 , x = ?", "0" },
			{ "What is the capital of Belgium?", "Brussels" },
			{ "Who does daffy duck hate in loonley toons", "Bugs" },
			{ "40% of mcdonald's sales come from?", "Happy Meals" },
			{ "What is the capital of Haiti?", "Port-au-Prince" },
			{ "How many fingers did Ann Boleyn have?", "11" },
			{ "Queen Elizabeth has four dogs of which breed?", "Corgies" },
			{ "What is the capital of Qatar?", "Doha" },
			{ "What is the capital of Antigua and Barbuda?", "Saint John's" },
			{ "What is the capital of San Marino?", "San Marino" },
			{ "What is the capital of Tunisia?", "Tunis" },
			{ "How many legs does a butterfly have?", "6" },
			{ "What is the capital of Congo,Republic of the?", "Brazzaville" },
			{ "What is the capital of Rwanda?", "Kigali" },
			{ "What is the capital of Belarus?", "Minsk" },
			{ "Finland + Norway + Sweden = ?", "Scandinavia" },
			{ "Name an actor who played James Bond", "Sean Connery" },
			{ "What instrument often plays a roll in jazz", "Saxophone" },
			{ "What is the capital of Denmark?", "Copenhagen" },
			{ "Which female is the highest paid actress?", "Angelina Jolie" },
			{ "What is the capital of Maldives?", "Male" },
			{ "What is capital of Morocco?", "Rabat" },
			{ "...... \"hi\" in Russian", "Privet" },
			{ "Is a tomato a fruit or a vegetable", "Fruit" },
			{ "What is the capital of Dominican Republic?", "Santo Domingo" },
			{ "What is the death of sound?", "Silence" },
			{ "What is the capital of Mali?", "Bamako" },
			{ "What is the capital of Solomon Islands?", "Honiara" },
			{ "Nickname of New York City is?", "Big Apple" },
			{ "What is the biggest island in the world?", "Greenland" },
			{ "Ketchup was originally sold as?", "Medicine" },
			{ "What is the capital of Hawaii?", "Honolulu" },
			{ "How do you spell 60", "Sixty" },
			{ "What is the capital of Macedonia?", "Skopje" },
			{ "How many banks are robbed a day? avg", "20" } };
	static int solves = 0;

	public boolean onExecute() {
		LogArea.log("bbTrivia has started.");
		strategies.add(new TriviaSolver());
		provide(strategies);
		return true;
	}

	public void onFinish() {
		LogArea.log("bbTrivia has ended. You solved " + solves
				+ " trivia quesions");
	}

	@Override
	public void messageReceived(MessageEvent i) {
		for (String[] s : trivia) {
			if (i.getMessage().equalsIgnoreCase(s[0])) {
				LogArea.log("::answer " + s[1]);
				Keyboard.getInstance().sendKeys("::answer " + s[1]);
				Time.sleep(3000);
				solves++;
			}

		}

	}

}