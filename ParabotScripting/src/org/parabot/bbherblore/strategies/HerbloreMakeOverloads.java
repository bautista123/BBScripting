package org.parabot.bbherblore.strategies;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.bbherblore.methods.HerbloreMakingMethods;
import org.parabot.bbherblore.methods.HerbloreReturns;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;

public class HerbloreMakeOverloads implements Strategy {

	@Override
	public boolean activate() {
		if (HerbloreReturns.haveOverloadSupplies()
				&& HerbloreVariables.getMakeOvls() && !Bank.isOpen()) {
			LogArea.log("Making Overloads");
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		/*
		 * Have all the overload supplies. Need to make them now.
		 */
		if (Inventory.getCount(HerbloreConstants.OvlSupplies) > 0) {
			HerbloreMakingMethods.MakeOverloads();
		}
	}

}
