package org.eclipsebot.creativebarrows.strategies;

import org.demmonic.client.api.Area;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;

public class CreativeBarrowsTeleportToBank extends Strategy {
	private Script script;
	public Area barrowsArea = new Area(3542, 3266, 3583, 3311);

	public CreativeBarrowsTeleportToBank(Script script) {
		this.script = script;
	}

	@Override
	public int execute() {
		script.game.sendCommand("::home");
		return 1000;
	}

	@Override
	public boolean shouldExecute() {
		if (script.game.getMyPlayer().getAnimationId() == -1
				&& !script.game.getMyPlayer().isWalking()
				&& script.game.getOpenInterfaceId() == -1
				&& barrowsArea.contains(script.game.getMyPlayer())
				&& needsSupplies()) {
			return true;
		} else {
			return false;
		}

	}

	public boolean needsSupplies() {
		if (!script.inventory.contains(140) || !script.inventory.contains(386)) {
			return true;
		} else {
			return false;

		}

	}
}
