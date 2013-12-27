package org.parabot.bbherblore.strategies;

import java.awt.Point;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.bbherblore.methods.HerbloreReturns;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Menu;

public class HerbloreDeposit implements Strategy {
	public static boolean deposit;

	@Override
	public boolean activate() {
		LogArea.log("Deposit methods.");
		// deposit extremes
		if (HerbloreReturns.needBankXTremes() && HerbloreReturns.playerIdle()
				&& HerbloreVariables.getMakeXT() && Bank.isOpen()) {
			LogArea.log("Bank extremes");
			deposit = true;
			return true;
		} else if (HerbloreReturns.needBankOvl()
				&& HerbloreReturns.playerIdle()
				&& HerbloreVariables.getMakeOvls() && Bank.isOpen()) {
			LogArea.log("Bank overloads");
			deposit = true;
			return true;
			// Banking failsafe for overloads.
		} else if (Bank.isOpen()
				&& Inventory.getCount(HerbloreConstants.OvlSupplies) != 24
				&& HerbloreVariables.getMakeOvls()
				&& HerbloreReturns.playerIdle() && !Inventory.isEmpty()) {
			LogArea.log("Overload banking failsafe.");
			deposit = true;
			return true;
		}

		return false;
	}

	@Override
	public void execute() {
		try {
			if (deposit) {
				LogArea.log("Deposit");
				Menu.interact("Deposit carried tems", new Point(397, 300));
				Time.sleep(700);
				deposit = false;
			}
		} catch (Exception i) {

		}

	}

}
