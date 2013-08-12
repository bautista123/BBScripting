package org.eclipsebot.creativethiever.strategies;

import org.demmonic.client.api.Area;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2ObjectWrapper;
import org.eclipsebot.creativethiever.data.CreativeThieverVar;

public class CreativeThieverStealer extends Strategy {
	private Script script;
	/* public Area stallArea = new Area(2330, 3162, 2350, 3187); */
	public Area stallArea = new Area(2333, 3158, 2353, 3186);

	public CreativeThieverStealer(Script script) {
		this.script = script;
	}

	@Override
	public int execute() {
		RS2ObjectWrapper[] stallList = script.RS2Objects
				.getRS2ObjectsForIds(4874);
		RS2ObjectWrapper[] stall = stallList;

		if (stall == null) {
			ClientUI.pushMessage("empty array");
			script.sleep(100);
		}
		if (stall != null) {
			CreativeThieverVar.setStatus("Stealing.");
			script.getClient().sendAction(3, 900, stall[0].getUID(),
					stall[0].getLocalX(), stall[0].getLocalY());
			script.sleep(400);
		}
		return 100;
	}

	@Override
	public boolean shouldExecute() {

		if (script.game.getMyPlayer().getAnimationId() == -1
				&& !script.game.getMyPlayer().isWalking()
				&& !script.inventory.isFull()
				&& script.game.getOpenInterfaceId() == -1
				&& stallArea.contains(script.game.getMyPlayer())
				&& !script.game.getMyPlayer().isUnderAttack()) {
			return true;
		} else {
			return false;
		}
	}
}
