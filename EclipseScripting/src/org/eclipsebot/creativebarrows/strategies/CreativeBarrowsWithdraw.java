package org.eclipsebot.creativebarrows.strategies;

import org.demmonic.client.api.Area;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2ObjectWrapper;

public class CreativeBarrowsWithdraw extends Strategy {
	public Area homeArea = new Area(2330, 3162, 2364, 3186);
	private static Script script;

	public CreativeBarrowsWithdraw(Script script) {
		this.script = script;
	}

	@Override
	public int execute() {
		RS2ObjectWrapper bankList[] = script.RS2Objects
				.getRS2ObjectsForIds(6084);
		if (!script.bank.isOpen()) {
			if ((bankList.length == -1)) {
				ClientUI.pushMessage("Bank array empty.");
			}
			if (!(bankList.length == -1)) {
				RS2ObjectWrapper bank = bankList[0];
				bank.interact(900);
				script.sleep(1000);
			}
		}
		if (script.bank.isOpen()) {
			ClientUI.pushMessage("hello");

			if (!hasSupplies() && !script.inventory.isEmpty()) {
				script.banking.depositAll();
				script.sleep(200);
			}
			if (!hasSupplies() && script.inventory.isEmpty()) {
				script.banking.withdraw10(140);
				script.sleep(100);
				script.banking.withdraw1(170);
				script.sleep(100);
				script.banking.withdrawX(386, 3);
				script.sleep(100);
				script.getClient().sendAction(1, 200, 99680256, 207, 5384);
				script.sleep(400);
			}
			if (hasSupplies()) {
				script.getClient().sendAction(1, 200, 99680256, 207, 5384);
				script.sleep(400);
			}
		}
		return 0;
	}

	@Override
	public boolean shouldExecute() {
		if (script.game.getMyPlayer().getAnimationId() == -1
				&& !script.game.getMyPlayer().isWalking()
				&& homeArea.contains(script.game.getMyPlayer())
				&& !hasSupplies()) {
			return true;
		}
		return false;
	}

	public boolean hasSupplies() {
		if (script.inventory.contains(140) && script.inventory.contains(170)
				&& script.inventory.contains(386)
				&& script.inventory.getInventoryCount() == 14) {
			return true;
		} else {
			return false;

		}

	}

}
