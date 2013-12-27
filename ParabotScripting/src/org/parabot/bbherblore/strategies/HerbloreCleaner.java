package org.parabot.bbherblore.strategies;

import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.bbherblore.methods.HerbloreCleanerMethods;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;

public class HerbloreCleaner implements Strategy {

	@Override
	public boolean activate() {

		return HerbloreVariables.getCleanHerbs() && !Bank.isOpen();
	}

	@Override
	public void execute() {
		// Using the best possible herb is selected.
		if (HerbloreVariables.getUseBest()) {
			// Pick what herb is currently needed.
			HerbloreCleanerMethods.VerifyHerb();
			if (Inventory.getCount(HerbloreVariables.getHerb()) > 0) {
				// The inventory has herbs to clean. Clean them.
				HerbloreCleanerMethods.CleanHerbs();
			}
			if (Inventory.getCount(HerbloreVariables.getHerb()) == 0
					&& !Inventory.isEmpty()) {
				/*
				 * There is no herbs to clean in the inventory but the inventory
				 * has items. Empty the invy. There seems to be a bug where the
				 * script sometimes doesnt run this.
				 */
				HerbloreCleanerMethods.CloseLevelUpMenu();
				HerbloreCleanerMethods.EmptyInvy();
			}
			if (Inventory.getCount(HerbloreVariables.getHerb()) == 0
					&& Inventory.isEmpty()) {
				// The invy is empty so spawn herbs to clean.
				HerbloreCleanerMethods.CloseLevelUpMenu();
				if (HerbloreVariables.getHerb() != 199) {
					// spawn herbs that arent guams.
					HerbloreCleanerMethods.SpawnNonGuams();
				} else if (HerbloreVariables.getHerb() == 199) {
					// the chosen herb is guams. spawn them
					HerbloreCleanerMethods.SpawnGuams();
				}

			}
			/*
			 * Not spawning the best possible herb. Spawning herb of choice.
			 */
		} else if (!HerbloreVariables.getUseBest()) {
			if (Inventory.getCount(HerbloreVariables.getHerb()) > 0) {
				// There is herbs to clean in the invy. Clean them.
				HerbloreCleanerMethods.CleanHerbs();
			}
			if (Inventory.getCount(HerbloreVariables.getHerb()) == 0
					&& Inventory.isEmpty()) {
				// Invy is empty. need herbs. Spawn them.
				HerbloreCleanerMethods.CloseLevelUpMenu();
				if (HerbloreVariables.getHerb() != 199) {
					// Spawn if herb isnt guam
					HerbloreCleanerMethods.SpawnNonGuams();
				} else if (HerbloreVariables.getHerb() == 199) {
					// spawn if herb is guam
					HerbloreCleanerMethods.SpawnGuams();
				}
			}
			if (Inventory.getCount(HerbloreVariables.getHerb()) == 0
					&& !Inventory.isEmpty()) {
				// Invy is not empty and has no herbs. empty it.
				HerbloreCleanerMethods.CloseLevelUpMenu();
				HerbloreCleanerMethods.EmptyInvy();
			}
		}

	}

}
