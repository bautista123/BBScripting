package org.eclipsebot.fisher.strategys;

import java.util.ArrayList;

import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.wrappers.RS2NPCWrapper;
import org.demmonic.client.wrappers.RS2PlayerWrapper;
import org.eclipsebot.fisher.data.FisherVar;

public class Fisher extends Strategy {
	private Script script;

	public Fisher(Script script) {
		this.script = script;
	}

	public boolean shouldExecute() {
		final RS2PlayerWrapper me = script.game.getMyPlayer();
		return me.getAnimationId() == -1 && !me.isWalking()
				&& !script.inventory.isFull() && script.inventory.contains(304)
				&& script.getClient().isLoggedIn();
	}

	public int execute() {
		FisherVar.setStatus("Fishing.");
		ArrayList<RS2NPCWrapper> fishList = script.RS2NPCs.getNPCsForId(316);
		if (fishList.isEmpty()) {
			return 500;
		}
		RS2NPCWrapper fish = fishList.get(0);
		fish.interact(20);
		dynamicSleep(script.game.getMyPlayer().isWalking(), 4000);
		while (script.game.getMyPlayer().isWalking()) {
			script.sleep(100);
		}
		dynamicSleep(script.game.getMyPlayer().getAnimationId() != -1, 4000);
		while (script.game.getMyPlayer().getAnimationId() != -1) {
			script.sleep(100);
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
