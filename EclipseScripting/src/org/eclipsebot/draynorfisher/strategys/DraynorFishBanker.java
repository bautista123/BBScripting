package org.eclipsebot.draynorfisher.strategys;

import java.util.ArrayList;

import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2ObjectWrapper;
import org.eclipsebot.draynorfisher.data.DraynorFisherVar;

public class DraynorFishBanker extends Strategy {
	private Script script;

	public DraynorFishBanker(Script script) {
		this.script = script;
	}

	public boolean shouldExecute() {
		if(script.inventory.isFull() && DraynorFisherVar.getBank()==true
				&& !script.game.getMyPlayer().isWalking()
				&& script.getClient().isLoggedIn()
				|| !script.inventory.contains(304)
				&& script.getClient().isLoggedIn()){
			ClientUI.pushMessage("im here");
			return true;
		}else {
			return false;
		}
		

	}

	public int execute() {
		DraynorFisherVar.setStatus("Banking.");
		ArrayList<RS2ObjectWrapper> bankList = script.RS2Objects
				.getRS2ObjectsForId(2213);
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

	public void dynamicSleep(long sleepTime, boolean...conditions) {
		long endTime = (System.currentTimeMillis() + sleepTime);
		while (System.currentTimeMillis() < endTime) {
			script.sleep(50);
			if (!script.getClient().isLoggedIn()) {
			    break;
			   }
		}
	}

}