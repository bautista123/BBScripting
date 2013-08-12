package org.eclipsebot.creativebarrows.strategies;

import org.demmonic.client.api.Area;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2ObjectWrapper;

public class CreativeBarrowsTeleport extends Strategy {
	private Script script;

	public CreativeBarrowsTeleport(Script script) {
		this.script = script;
	}

	public Area homeArea = new Area(2330, 3162, 2364, 3186);

	@Override
	public int execute() {
		if (homeArea.contains(script.game.getMyPlayer())) {
			RS2ObjectWrapper teleList[] = script.RS2Objects
					.getRS2ObjectsForIds(2473);
			if ((teleList.length == -1)) {
				ClientUI.pushMessage("empty array");
				script.sleep(100);
			}
			if (!(teleList.length == -1)) {
				RS2ObjectWrapper portal = teleList[0];
				portal.interact(502);
				script.sleep(300);

			}
		}
		return 200;
	}

	@Override
	public boolean shouldExecute() {
		if (script.game.getMyPlayer().getAnimationId() == -1
				&& !script.game.getMyPlayer().isWalking()
				&& script.game.getOpenInterfaceId() == -1
				&& homeArea.contains(script.game.getMyPlayer())
				&& !script.inventory.isEmpty() && hasSupplies()) {
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
