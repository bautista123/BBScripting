package org.parabot.bbherblore.methods;

import java.awt.Point;

import org.parabot.bbherblore.data.HerbloreConstants;
import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Menu;
import org.rev317.api.wrappers.hud.Tab;

public class HerbloreMakingMethods {

	public static void MakeOverloads() {
		if (Inventory.getCount(HerbloreConstants.OvlSupplies) > 0) {
			if (Tab.INVENTORY.isOpen()) {
				HerbloreMethods.Click(705, 409);
				Time.sleep(300);
				Menu.interact("Use Clean torstol with Extreme defence (3)",
						new Point(704, 231));
				Time.sleep(500);
				Menu.interact("Make 1", new Point(260, 421));
				Time.sleep(100);
				HerbloreMethods.Click(537, 480);
			} else if (!Tab.INVENTORY.isOpen()) {
				Tab.INVENTORY.open();
			}
			LogArea.log("Making overloads");
			HerbloreVariables.setOvlMade(HerbloreVariables.getOvlMade() + 1);
		}
	}

	public static void MakeExtremes(String pot, String herb) {
		if (Inventory.getCount(HerbloreConstants.xTsupplies) > 0) {
			if (Tab.INVENTORY.isOpen()) {
				HerbloreMethods.Click(706, 443);
				Time.sleep(500);
				Menu.interact("Use " + herb + " with " + pot, new Point(620,
						340));
				Time.sleep(500);
				Menu.interact("Make 1", new Point(261, 413));
				Time.sleep(500);
				HerbloreMethods.Click(538, 485);
			} else if (!Tab.INVENTORY.isOpen()) {
				Tab.INVENTORY.open();
			}
		}
		LogArea.log("make the extremes");

	}

}
