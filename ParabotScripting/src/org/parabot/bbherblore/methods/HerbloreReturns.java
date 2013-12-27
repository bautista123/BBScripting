package org.parabot.bbherblore.methods;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.interactive.Player;

public class HerbloreReturns {
	public static boolean needStack() {
		if (HerbloreVariables.getSpawnAtt() || HerbloreVariables.getSpawnDef()
				|| HerbloreVariables.getSpawnStr()
				|| HerbloreVariables.getSpawnRange()
				|| HerbloreVariables.getSpawnMAge()
				|| HerbloreVariables.getSpawnAvan()
				|| HerbloreVariables.getSpawnLanta()
				|| HerbloreVariables.getSpawnDwarf()
				|| HerbloreVariables.getSpawnTorstols()) {
			return true;
		}
		return false;
	}

	public static boolean haveOverloadSupplies() {
		if (Inventory.getCount(HerbloreConstants.OvlSupplies) > 0
				&& !Inventory.isEmpty() && !Inventory.isFull()) {
			return true;
		}
		return false;
	}

	public static boolean haveXTremeSupplies() {
		if (Inventory.getCount(HerbloreConstants.xTsupplies) > 0) {
			return true;
		}
		return false;
	}

	public static boolean needBankClose() {
		if (Inventory.getCount(HerbloreConstants.OvlSupplies) > 0
				&& Bank.isOpen() && HerbloreVariables.getMakeOvls()
				&& !Inventory.isFull() && Inventory.getCount() == 24
				|| Inventory.getCount(HerbloreConstants.xTsupplies) > 0
				&& Bank.isOpen() && Inventory.isFull()
				&& HerbloreVariables.getMakeXT()) {
			return true;
		}
		return false;
	}

	public static boolean playerIdle() {
		final Player me = Players.getLocal();
		if (!me.isWalking() && me.getAnimation() == -1) {
			return true;
		}
		return false;
	}

	public static boolean needBankXTremes() {

		if (Inventory.getCount(HerbloreConstants.xT) > 0
				&& Inventory.getCount(HerbloreConstants.primarys) == 0
				&& Inventory.getCount(HerbloreConstants.secondaries) == 0
				&& Inventory.getCount(HerbloreConstants.ovl) == 0
				&& Inventory.getCount(HerbloreConstants.torstol) == 0) {
			return true;
		}
		return false;
	}

	public static boolean needBankOvl() {
		if (Inventory.getCount(HerbloreConstants.ovl) > 0
				&& Inventory.getCount(HerbloreConstants.OvlSupplies) == 0
				&& Inventory.getCount() == 4) {
			return true;
		}
		return false;
	}
}
