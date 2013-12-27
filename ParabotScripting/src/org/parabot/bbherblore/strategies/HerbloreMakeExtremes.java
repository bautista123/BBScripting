package org.parabot.bbherblore.strategies;

import java.awt.Point;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.bbherblore.methods.HerbloreMakingMethods;
import org.parabot.bbherblore.methods.HerbloreReturns;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Menu;
import org.rev317.api.wrappers.hud.Tab;

public class HerbloreMakeExtremes implements Strategy {

	@Override
	public boolean activate() {
		/*
		 * All the supplies for extremes are in the inventory and THE BANK IS
		 * CLOSED. Begin to make them now.
		 */
		if (HerbloreReturns.haveXTremeSupplies()
				&& HerbloreVariables.getMakeXT() && !Bank.isOpen()) {
			LogArea.log("make the extremes true");
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		try {
			/*
			 * Make extremes. Must fix and issue where the bot stop because the
			 * bank is closed and the supplies in the inventory are wrong is
			 * just differently placed. This is a needed fix.
			 */
			if (Inventory.getCount(HerbloreConstants.xTsupplies) > 0) {
				// There are supplies for extremes in invy with bank closed.
				if (Inventory.getCount(HerbloreConstants.xDefSupplies) > 0) {
					// make extreme def
					HerbloreMakingMethods.MakeExtremes("Super defence (3)",
							"Clean lantadyme");
				} else if (Inventory.getCount(HerbloreConstants.xStrSupplies) > 0) {
					// make extreme str
					HerbloreMakingMethods.MakeExtremes("Super strength (3)",
							"Clean dwarf weed");
				} else if (Inventory.getCount(HerbloreConstants.xAttSupplies) > 0) {
					// make extreme att
					HerbloreMakingMethods.MakeExtremes("Super attack (3)",
							"Clean avantoe");
				} else if (Inventory.getCount(HerbloreConstants.xMageSupplies) > 0) {
					// make extreme mage
					HerbloreMakingMethods.MakeExtremes("Magic potion (3)",
							"Ground mud runes");
				} else if (Inventory.getCount(HerbloreConstants.xRangeSupplies) > 0) {
					// make extreme range
					HerbloreMakingMethods.MakeExtremes("Ranging potion (3)",
							"Grenwall spikes");
				}
			}
		} catch (Exception fuck) {

		}
	}

}
