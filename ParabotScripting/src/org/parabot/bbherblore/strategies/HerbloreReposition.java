package org.parabot.bbherblore.strategies;

import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.bbherblore.methods.HerbloreMethods;
import org.parabot.bbherblore.methods.HerbloreReturns;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;

public class HerbloreReposition implements Strategy {

	@Override
	public boolean activate() {
		if (HerbloreVariables.getStartTile().distanceTo() > 5
				&& !HerbloreVariables.getCleanHerbs()) {
			LogArea.log("repositioning");
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (HerbloreVariables.getStartTile().distanceTo() > 5
				&& HerbloreReturns.playerIdle() && !Bank.isOpen()
				&& HerbloreVariables.getStartTile().distanceTo() > 5) {
			if (!HerbloreVariables.getStartTile().isOnMinimap()) {
				HerbloreMethods.TeleportHome();
			} else if (HerbloreVariables.getStartTile().isOnMinimap()) {
				HerbloreVariables.getStartTile().clickMM();
				Time.sleep(4000);
			}
		}

	}

}
