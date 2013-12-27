package org.parabot.bbherblore.methods;

import java.awt.Point;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.rev317.api.wrappers.hud.Tab;

public class HerbloreMethods {

	public static void CheckStacks() {
		if (HerbloreReturns.needStack()) {
			HerbloreVariables.setSpawnStack(true);
		}
		if (HerbloreVariables.getSpawnMud()
				|| HerbloreVariables.getSpawnSpikes()) {
			HerbloreVariables.setSpawnUnstack(true);
			if (!HerbloreVariables.getSpawnStack()) {
				HerbloreVariables.setSpawnTimer(+1);
			}
		}
	}

	public static void TeleportHome() {
		if (!Tab.MAGIC.isOpen()) {
			LogArea.log("Bank failsafe");
			Tab.MAGIC.open();
			Time.sleep(200);
			if (Tab.MAGIC.isOpen()) {
				HerbloreMethods.Click(708, 223);
				Time.sleep(500);
				if (!HerbloreVariables.getVarrock()) {
					HerbloreMethods.Click(640, 326);
					Time.sleep(1000);
					Tab.INVENTORY.open();
					Time.sleep(2000);
				} else if (HerbloreVariables.getVarrock()) {
					HerbloreMethods.Click(629, 287);
					Time.sleep(1000);
					Tab.INVENTORY.open();
					Time.sleep(2000);
					HerbloreConstants.home.clickMM();
					Time.sleep(5000);
					HerbloreConstants.home2.clickMM();
					Time.sleep(5000);
				}
				if (HerbloreVariables.getStartTile().isOnMinimap()) {
					HerbloreVariables.getStartTile().clickMM();
					Time.sleep(2000);
				}

			}
		}
	}

	public static void Click(int i, int s) {
		Mouse.getInstance().click(new Point(i, s), true);
	}
}
