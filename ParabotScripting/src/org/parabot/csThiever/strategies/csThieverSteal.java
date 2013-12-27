package org.parabot.csThiever.strategies;

import org.parabot.core.ui.components.LogArea;
import org.parabot.csThiever.data.csThieverConstants;
import org.parabot.csThiever.data.csThieverVariables;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Camera;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.interactive.Player;
import org.rev317.api.wrappers.scene.SceneObject;

public class csThieverSteal implements Strategy {

	@Override
	public boolean activate() {
		final Player me = Players.getLocal();
		final SceneObject[] nearestSTALL = SceneObjects
				.getNearest((int) csThieverConstants.stalls[csThieverVariables
						.getStallChosen()][0]);
		SceneObject STALL1 = nearestSTALL[0];
		return STALL1 != null && !Inventory.isFull() && me.getAnimation() == -1
				&& !me.isWalking() && !me.isInCombat()
				&& Interfaces.getOpenInterfaceId() == -1;
	}

	@Override
	public void execute() {
		final SceneObject[] STALL = SceneObjects
				.getNearest((int) csThieverConstants.stalls[csThieverVariables
						.getStallChosen()][0]);
		SceneObject STALL1 = null;
		if (STALL.length > 0)
			STALL1 = STALL[0];
		Camera.turnTo(STALL1);
		try {
			if (STALL1.isOnScreen())
				STALL1.interact((String) csThieverConstants.stalls[csThieverVariables
						.getStallChosen()][1]);
		} catch (Exception e) {
			LogArea.log("" + e);
		}
	}

}
