package org.parabot.csThiever.strategies;

import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Inventory;

public class csThieverDropper implements Strategy {

	@Override
	public boolean activate() {
		if (Inventory.isFull()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		LogArea.log("ready to drop");
	}

}
