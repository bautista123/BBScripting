package org.eclipsebot.draynorfisher.strategys;

import java.util.ArrayList;

import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2NPCWrapper;
import org.demmonic.client.wrappers.RS2PlayerWrapper;
import org.eclipsebot.draynorfisher.data.DraynorFisherVar;

public class DraynorFisherFish extends Strategy {
	private Script script;

	public DraynorFisherFish(Script script) {
		this.script = script;
	}

	public boolean shouldExecute() {
		final RS2PlayerWrapper me = script.game.getMyPlayer();
		if(me.getAnimationId() == -1 && !me.isWalking()
				&& !script.inventory.isFull() && script.inventory.contains(304)){
			ClientUI.pushMessage("im here123");
			return true;
		}
		return false;
	}

	public int execute() {
		DraynorFisherVar.setStatus("Fishing.");
		ArrayList<RS2NPCWrapper> fishList = script.RS2NPCs.getNPCsForId(316);
		if (fishList.isEmpty()) {
			return 500;
		}
		if (script.game.getMyPlayer().getAnimationId() == -1) {
			RS2NPCWrapper fish = fishList.get(0);
			fish.interact(20);
			dynamicSleep(4000, script.game.getMyPlayer().isWalking());
			while (script.game.getMyPlayer().isWalking()) {
				script.sleep(100);
				if (!script.getClient().isLoggedIn()) {
					break;
				}
			}
		}
		dynamicSleep(4000, script.game.getMyPlayer().getAnimationId() != -1);
		while (script.game.getMyPlayer().getAnimationId() != -1) {
			script.sleep(100);
			if (!script.getClient().isLoggedIn()) {
				break;
			}
		}
		return 100;
	}

	public void dynamicSleep(long sleepTime, boolean... conditions) {
		long endTime = (System.currentTimeMillis() + sleepTime);
		while (System.currentTimeMillis() < endTime) {
			for (boolean a : conditions) {
				if (a) {
					break;
				}
			}
			script.sleep(50);

		}
	}
}