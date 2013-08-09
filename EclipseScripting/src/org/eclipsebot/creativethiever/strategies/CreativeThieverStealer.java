package org.eclipsebot.creativethiever.strategies;

import java.util.ArrayList;

import org.demmonic.client.api.Area;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2ObjectWrapper;
import org.eclipsebot.creativethiever.data.CreativeThieverVar;

public class CreativeThieverStealer extends Strategy {
	private Script script;
	public Area stallArea = new Area(2330, 3162, 2350, 3187);

	public CreativeThieverStealer(Script script) {
		this.script = script;
	}

	@Override
	public int execute() {
		ArrayList<RS2ObjectWrapper> stallList = script.RS2Objects
				.getRS2ObjectsForIds(4874);
		if (stallList.isEmpty()) {
			ClientUI.pushMessage("empty array");
			script.sleep(100);
		}
		if (!stallList.isEmpty()) {
			CreativeThieverVar.setStatus("Stealing.");
			RS2ObjectWrapper stall = stallList.get(0);
			stall.interact(900);
			script.sleep(400);
		}
		return 100;
	}

	@Override
	public boolean shouldExecute() {

		if (script.game.getMyPlayer().getAnimationId() == -1
				&& !script.game.getMyPlayer().isWalking()
				&& script.game.getOpenInterfaceId() == -1
				&& !script.inventory.isFull()
				&& stallArea.contains(script.game.getMyPlayer())
				&& !script.game.getMyPlayer().isUnderAttack()) {
			return true;
		} else {
			return false;
		}
	}
}
