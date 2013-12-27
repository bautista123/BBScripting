package org.parabot.bbtoolkit.strategies;

import org.parabot.bbtoolkit.data.ToolKitVar;
import org.parabot.bbtoolkit.methods.ToolKitMethods;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.interactive.Player;

public class ToolKitOpen implements Strategy {
	final Player me = Players.getLocal();

	public boolean activate() {
		return ToolKitVar.getSpawner() && me.getAnimation() == -1
				&& !me.isWalking() && !Bank.isOpen();
	}

	public void execute() {
		ToolKitMethods.OpenBank();
	}
}
