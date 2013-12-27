package org.parabot.bbtoolkit.strategies;

import java.awt.Point;

import org.parabot.bbtoolkit.data.ToolKitVar;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;

public class ToolKitClicker implements Strategy {

	public boolean activate() {
		return ToolKitVar.getClicker();
	}

	public void execute() {
		if (ToolKitVar.getClicker()) {
			Point p = Mouse.getInstance().getPoint();
			Mouse.getInstance().click((int) p.getX(), (int) p.getY(), true);
			Time.sleep(ToolKitVar.getInterval());

		}
	}
}
