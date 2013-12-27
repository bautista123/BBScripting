package org.parabot.bbherblore.strategies;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.bbherblore.methods.HerbloreMethods;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;

public class HerbloreSpawn implements Strategy {

	@Override
	public boolean activate() {
		// Start spawning if the bank is open.
		return HerbloreVariables.getSpawn() && Bank.isOpen();
	}

	@Override
	public void execute() {
		// start by checking if the items are stackable
		HerbloreMethods.CheckStacks();
		// is the bank open?
		if (Bank.isOpen()) {
			/*
			 * The items to spawn are non stackable. Everything seems to work
			 * well here no errors.
			 */
			if (Bank.isOpen() && HerbloreVariables.getSpawnUnstack()
					&& HerbloreVariables.getSpawnTimer() == 1) {
				LogArea.log("Unstack spawn is true");
				for (int s : HerbloreConstants.unstackList) {
					Keyboard.getInstance().sendKeys("::item " + s + " 28");
					Time.sleep(300);
					HerbloreMethods.Click(397, 300);
					Time.sleep(500);
					HerbloreVariables
							.setSpawned(HerbloreVariables.getSpawned() + 28);
				}
				/*
				 * The items to spawn are stackable.
				 */
			} else if (Bank.isOpen() && HerbloreVariables.getSpawnStack()
					&& HerbloreVariables.getSpawnTimer() < 1) {
				for (int s : HerbloreConstants.stackList) {
					Keyboard.getInstance().sendKeys("::item " + s + " 100000");
					Time.sleep(500);
					HerbloreVariables
							.setSpawned(HerbloreVariables.getSpawned() + 100000);
				}
				HerbloreMethods.Click(397, 300);
				Time.sleep(500);
				HerbloreVariables.setSpawnTimer(+1);
			}
		}
	}
}
