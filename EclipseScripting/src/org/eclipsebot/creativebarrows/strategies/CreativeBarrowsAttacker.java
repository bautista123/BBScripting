package org.eclipsebot.creativebarrows.strategies;

import org.demmonic.client.api.Area;
import org.demmonic.client.api.Tile;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2NPCWrapper;

public class CreativeBarrowsAttacker extends Strategy {
	private Script script;
	public Area barrowsArea = new Area(3542, 3266, 3583, 3311);
	public Area ahrimsArea = new Area(3559, 3283, 3571, 3295);

	public CreativeBarrowsAttacker(Script script) {
		this.script = script;
	}

	@Override
	public int execute() {
		RS2NPCWrapper brother = script.RS2NPCs
				.getClosestRS2NPCNotUnderAttack(2025);
		if (ahrimsArea.contains(script.game.getMyPlayer())
				&& !script.game.getMyPlayer().isUnderAttack()) {
			ClientUI.pushMessage("attack");
			brother.interact(2412);

		}

		if (!ahrimsArea.contains(script.game.getMyPlayer())) {
			script.prayerTab.togglePrayer(script.prayerTab.BURSTOFSTRENGTH);
			ClientUI.pushMessage("helloooo");
			script.walking.walkTo(new Tile(3565, 3289));
			script.sleep(2000);
		}
		return 0;
	}

	@Override
	public boolean shouldExecute() {
		if (script.game.getMyPlayer().getAnimationId() == -1
				&& !script.game.getMyPlayer().isWalking()
				&& script.game.getOpenInterfaceId() == -1
				&& barrowsArea.contains(script.game.getMyPlayer())
				&& !script.inventory.isEmpty() && hasSupplies()) {
			return true;
		} else {
			return false;
		}

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
