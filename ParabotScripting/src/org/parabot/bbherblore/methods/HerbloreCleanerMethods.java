package org.parabot.bbherblore.methods;

import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Skill;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;

public class HerbloreCleanerMethods {

	/*
	 * This will set the herb name and herb id for the herb cleaning.
	 */
	public static void Calculate(int i, String s) {
		HerbloreVariables.setHerb(i);
		HerbloreVariables.setHerbName(s);
	}

	/*
	 * Method that cleans all the herbs in your inventory.
	 */
	public static void CleanHerbs() {
		if (!Tab.INVENTORY.isOpen()) {
			Tab.INVENTORY.open();
			Time.sleep(400);
		}
		for (final Item i : Inventory.getItems(HerbloreVariables.getHerb())) {
			LogArea.log("Cleaing herbs");
			i.interact("Clean");
		}
	}

	/*
	 * Closes the leveling up interface. The sleep here might slow down certain
	 * parts of the cleaning if not carefully placed.
	 */
	public static void CloseLevelUpMenu() {
		if ((Interfaces.getChatboxInterfaceId() == 6237)) {
			LogArea.log("close level up thing");
			HerbloreMethods.Click(268, 444);
			Time.sleep(400);

		}
	}

	/*
	 * This method checks what your herblore level is to decide what herb your
	 * should clean during "use best herb" option for the herb cleaner.
	 */
	public static void VerifyHerb() {
		int i = Skill.HERBLORE.getRealLevel();
		if (i <= 1 && i < 25) {
			HerbloreCleanerMethods.Calculate(199, "Guam");
		} else if (i >= 25 && i <= 74) {
			HerbloreCleanerMethods.Calculate(207, "Ranarr");
		} else if (i >= 75) {
			HerbloreCleanerMethods.Calculate(219, "Torstol");
		}
	}

	/*
	 * Method to empty your inventory when needed.
	 */
	public static void EmptyInvy() {
		Keyboard.getInstance().sendKeys("::empty");
		Time.sleep(200);
		LogArea.log("Clear invy");
	}

	/*
	 * Spawn guams.
	 */
	public static void SpawnNonGuams() {
		Keyboard.getInstance().sendKeys(
				"::item " + HerbloreVariables.getHerb() + " 10000");
		Time.sleep(700);
		LogArea.log("Spawn non guam");
	}

	/*
	 * Spawn guams.
	 */
	public static void SpawnGuams() {
		Keyboard.getInstance().sendKeys(
				"::item " + HerbloreVariables.getHerb() + " 28");
		Time.sleep(700);
		LogArea.log("spawn guam");
	}
}
