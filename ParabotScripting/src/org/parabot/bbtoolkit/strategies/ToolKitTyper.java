package org.parabot.bbtoolkit.strategies;

import org.parabot.bbtoolkit.data.ToolKitVar;
import org.parabot.bbtoolkit.methods.ToolKitMethods;
import org.parabot.environment.scripts.framework.Strategy;

public class ToolKitTyper implements Strategy {
	public boolean activate() {
		return ToolKitVar.getTyper();
	}

	public void execute() {
		if (ToolKitVar.getTyper()) {
			ToolKitMethods.typeMessage();
		}

	}
}