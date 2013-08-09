package org.eclipsebot.creativethiever.strategies;

import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.eclipsebot.creativethiever.data.CreativeThieverVar;

public class CreativeThieverDropper extends Strategy {
	private Script script;

	public CreativeThieverDropper(Script script) {
		this.script = script;
	}

	@Override
	public int execute() {
		CreativeThieverVar.setStatus("Dropping");
		script.inventory.dropAllExcept(996);
		return 300;
	}

	@Override
	public boolean shouldExecute() {

		return script.game.getMyPlayer().getAnimationId() == -1
				&& !script.game.getMyPlayer().isWalking()
				&& script.game.getOpenInterfaceId() == -1
				&& script.inventory.isFull()
				&& !script.game.getMyPlayer().isUnderAttack();
	}

}
