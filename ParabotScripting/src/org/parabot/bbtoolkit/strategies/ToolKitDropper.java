package org.parabot.bbtoolkit.strategies;

import org.parabot.bbtoolkit.methods.ToolKitMethods;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;

public class ToolKitDropper implements Strategy {
	public boolean activate() {
		return ToolKitMethods.needDropAll() || ToolKitMethods.needM1D1();
	}

	public void execute() {
		if (ToolKitMethods.needM1D1()) {
			ToolKitMethods.M1D1();
		} else if (ToolKitMethods.needDropAll()) {
			ToolKitMethods.dropAll();
		} else {
			Time.sleep(500);
		}
	}
}