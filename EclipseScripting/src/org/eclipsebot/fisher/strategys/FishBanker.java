package org.eclipsebot.fisher.strategys;

import java.util.ArrayList;

import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.wrappers.RS2ObjectWrapper;
import org.eclipsebot.fisher.data.FisherVar;

public class FishBanker extends Strategy {
	private Script script;

	public FishBanker(Script script) {
		this.script = script;
	}

	public boolean shouldExecute() {
		return script.inventory.isFull()
				&& !script.game.getMyPlayer().isWalking()
				&& script.getClient().isLoggedIn()
				|| !script.inventory.contains(304)
				&& script.getClient().isLoggedIn();

	}

	public int execute() {
		FisherVar.setStatus("Banking.");
		ArrayList<RS2ObjectWrapper> bankList = script.RS2Objects
				.getRS2ObjectsForId(2213);
		if (!script.bank.isOpen()) {
			RS2ObjectWrapper bank = bankList.get(0);
			bank.interact(900);
			dynamicSleep(script.game.getMyPlayer().isWalking(), 4000);
			while (script.game.getMyPlayer().isWalking()) {
				script.sleep(100);
			}
			dynamicSleep(script.bank.isOpen(), 2000);
			if (bankList.isEmpty()) {
				return 100;
			}
		}
		if (script.bank.isOpen()) {
			if (script.inventory.contains(318)
					|| script.inventory.contains(322)) {
				script.banking.depositAll(318, 322);
				script.sleep(100);
			} else if (script.inventory.getInventoryCount() == 1
					&& script.inventory.contains(304)) {
				script.getClient().sendAction(200, 1265, 7, 3, 5384);
			} else if (!script.inventory.contains(304)
					&& script.inventory.getInventoryCount() == 0) {
				script.banking.withdraw1(304);
			}

		}
		return 100;
	}

	public void dynamicSleep(boolean condition, long sleepTime) {
		long endTime = (System.currentTimeMillis() + sleepTime);
		while (!condition && (System.currentTimeMillis() < endTime)) {
			script.sleep(50);
		}
	}

}
