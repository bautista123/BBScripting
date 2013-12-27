package org.parabot.test;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Inventory;
import org.rev317.api.wrappers.hud.Item;

public class wield implements Strategy {
	public static int[] armour = {};

	@Override
	public boolean activate() {

		return true;
	}

	@Override
	public void execute() {
		for (int i = 0; i < armour.length; i++) {
			for (Item c : Inventory.getItems()) {
				c.interact("Wield");
				Time.sleep(1000);
			}
		}

	}

}
