package org.parabot.bbherblore.strategies;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.bbherblore.methods.HerbloreWithdrawMethods;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;

public class HerbloreWithdraw implements Strategy {

	@Override
	public boolean activate() {

		return Bank.isOpen() && Inventory.isEmpty()
				&& !HerbloreVariables.getSpawn()
				&& !HerbloreVariables.getCleanHerbs();
	}

	@Override
	public void execute() {
		try {
			LogArea.log("withdraw");
			/*
			 * There is no overload supplies in the inventory. Withdraw them
			 * now.
			 */
			if (HerbloreVariables.getMakeOvls()
					&& Inventory.getCount(HerbloreConstants.OvlSupplies) == 0) {
				LogArea.log("withdraw overload supplies");
				HerbloreWithdrawMethods
						.WithdrawSupplies(HerbloreConstants.OvlSupplies);
				/*
				 * There is no extreme supplies in the inventory. Begin to
				 * withdraw them now.
				 */
			} else if (HerbloreVariables.getMakeXT()
					&& Inventory.getCount(HerbloreConstants.xTsupplies) == 0) {
				LogArea.log("withdraw extreme supplies");
				if (HerbloreVariables.getXtCount() == 0
						&& Inventory.getCount(HerbloreConstants.xTsupplies) == 0) {
					HerbloreVariables.setXtCount(1);
					HerbloreWithdrawMethods
							.WithdrawSupplies(HerbloreConstants.xDefSupplies);
				}
				if (HerbloreVariables.getXtCount() == 1
						&& Inventory.getCount(HerbloreConstants.xTsupplies) == 0) {
					HerbloreVariables.setXtCount(2);
					HerbloreWithdrawMethods
							.WithdrawSupplies(HerbloreConstants.xStrSupplies);
				}
				if (HerbloreVariables.getXtCount() == 2
						&& Inventory.getCount(HerbloreConstants.xTsupplies) == 0) {
					HerbloreVariables.setXtCount(3);
					HerbloreWithdrawMethods
							.WithdrawSupplies(HerbloreConstants.xAttSupplies);
				}
				if (HerbloreVariables.getXtCount() == 3
						&& Inventory.getCount(HerbloreConstants.xTsupplies) == 0) {
					HerbloreVariables.setXtCount(4);
					HerbloreWithdrawMethods
							.WithdrawSupplies(HerbloreConstants.xMageSupplies);
				}
				if (HerbloreVariables.getXtCount() == 4
						&& Inventory.getCount(HerbloreConstants.xTsupplies) == 0) {
					HerbloreVariables.setXtCount(0);
					HerbloreWithdrawMethods
							.WithdrawSupplies(HerbloreConstants.xRangeSupplies);
				}
			}

		} catch (Exception fuck) {

		}

	}
}
