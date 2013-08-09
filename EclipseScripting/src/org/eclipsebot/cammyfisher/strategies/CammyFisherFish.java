package org.eclipsebot.cammyfisher.strategies;

import java.util.ArrayList;

import org.demmonic.client.api.Path;
import org.demmonic.client.api.Tile;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2NPCWrapper;
import org.demmonic.client.wrappers.RS2PlayerWrapper;

public class CammyFisherFish extends Strategy {
	static Tile fishpath[] = {new Tile(2820,3437), new Tile(2831,3437)};
	private Script script;

	public CammyFisherFish(Script script) {
		this.script = script;
	}

	public boolean shouldExecute() {
		final RS2PlayerWrapper me = script.game.getMyPlayer();
		if (me.getAnimationId() == -1 && !me.isWalking()
				&& !script.inventory.isFull() && script.inventory.contains(302)) {
			ClientUI.pushMessage("im here123");
			return true;
		}
		return false;
	}

	public int execute() {
		ArrayList<RS2NPCWrapper> fishList = script.RS2NPCs.getNPCsForId(312);
		if (fishList.isEmpty()) {
			script.walking.walkPath(new Path(fishpath), false);
			ClientUI.pushMessage("empty array");
			return 500;
		}
		if (script.game.getMyPlayer().getAnimationId() == -1) {
			RS2NPCWrapper fish = fishList.get(0);
			fish.interact(20);
			dynamicSleep(3000, script.game.getMyPlayer().isWalking());
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