package org.parabot.bbherblore.strategies;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.bbherblore.methods.HerbloreReturns;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Camera;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.scene.SceneObject;

public class HerbloreOpenBank implements Strategy {

	@Override
	public boolean activate() {
		// Open the bank to bank finished extremes.
		if (HerbloreReturns.needBankXTremes() && HerbloreReturns.playerIdle()
				&& HerbloreVariables.getMakeXT() && !Bank.isOpen()
				&& HerbloreVariables.getStartTile().distanceTo() < 5) {
			LogArea.log("open Bank extremes");
			return true;
			// Open the bank to bank finished overloads.
		} else if (HerbloreReturns.needBankOvl()
				&& HerbloreReturns.playerIdle() && !Bank.isOpen()
				&& HerbloreVariables.getMakeOvls()
				&& HerbloreVariables.getStartTile().distanceTo() < 5) {
			LogArea.log("open Bank overloads");
			return true;
			// Open the bank, your invy is empty.
		} else if (HerbloreReturns.playerIdle() && Inventory.isEmpty()
				&& !Bank.isOpen() && !HerbloreVariables.getCleanHerbs()
				&& HerbloreVariables.getStartTile().distanceTo() < 5) {
			LogArea.log("Empty invy");
			return true;
			// Banking failsafe for overloads.
		} else if (!Bank.isOpen()
				&& Inventory.getCount(HerbloreConstants.OvlSupplies) != 24
				&& HerbloreVariables.getMakeOvls()
				&& HerbloreReturns.playerIdle() && !Inventory.isEmpty()
				&& Inventory.getCount(HerbloreConstants.ovl) < 0
				&& HerbloreVariables.getStartTile().distanceTo() < 5) {
			LogArea.log("Overload banking failsafe.");
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		final SceneObject[] nearestBank = SceneObjects.getNearest(2213);
		final SceneObject bank = nearestBank[0];
		try {
			LogArea.log("open method");
			if (!Bank.isOpen() && bank != null) {
				LogArea.log("is bank null");
				if (!bank.isOnScreen() && !Bank.isOpen()) {
					Camera.turnTo(bank);
				} else if (bank.isOnScreen() && !Bank.isOpen()) {
					if (!Bank.isOpen()) {
						LogArea.log("opening bank");
						bank.interact("Use-quickly");
						Time.sleep(700);
					}
				}
			}
		} catch (Exception fuck) {

		}
	}
}
