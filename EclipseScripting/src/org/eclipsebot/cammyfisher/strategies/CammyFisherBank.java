package org.eclipsebot.cammyfisher.strategies;

import java.util.ArrayList;

import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2ObjectWrapper;

public class CammyFisherBank extends Strategy {
	private Script script;

	public CammyFisherBank(Script script) {
		this.script = script;
	}

	public boolean shouldExecute() {
		if (script.inventory.isFull() && !script.game.getMyPlayer().isWalking()
				&& script.getClient().isLoggedIn()
				|| !script.inventory.contains(302)
				&& script.getClient().isLoggedIn()) {
			ClientUI.pushMessage("im here");
			return true;
		} else {
			return false;
		}

	}

	public int execute() {
		ArrayList<RS2ObjectWrapper> bankList = script.RS2Objects
				.getRS2ObjectsForIds(2213);
		if (script.bank.isOpen()) {
			if (script.inventory.contains(378)) {
				script.banking.depositAll(378);
				script.sleep(100);
			} else if (script.inventory.getInventoryCount() == 1
					&& script.inventory.contains(302)) {
				script.getClient().sendAction(200, 1265, 7, 3, 5384);
			} else if (!script.inventory.contains(302)
					&& script.inventory.getInventoryCount() == 0) {
				script.banking.withdraw1(302);
			}

		}
		if (!script.bank.isOpen()) {
			RS2ObjectWrapper bank = bankList.get(0);
			bank.interact(900);
			dynamicSleep(2000, script.game.getMyPlayer().isWalking());
			while (script.game.getMyPlayer().isWalking()) {
				script.sleep(100);
				if (!script.getClient().isLoggedIn()) {
					break;
				}
			}
			dynamicSleep(2000, script.bank.isOpen());
			if (bankList.isEmpty()) {
				return 100;
			}
		}

		return 100;
	}

	public void dynamicSleep(long sleepTime, boolean... conditions) {
		long endTime = (System.currentTimeMillis() + sleepTime);
		while (System.currentTimeMillis() < endTime) {
			script.sleep(50);
			if (!script.getClient().isLoggedIn()) {
				break;
			}
		}
	}

}
