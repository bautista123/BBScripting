package org.parabot.csThiever.strategies;

import java.awt.Point;

import org.parabot.core.ui.components.LogArea;
import org.parabot.csThiever.data.csThieverConstants;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Game;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.methods.Menu;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.interactive.Player;

public class csThieverRandoms implements Strategy {
	final Player me = Players.getLocal();

	@Override
	public boolean activate() {
		if (Interfaces.getOpenInterfaceId() != -1 || me.isInCombat()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (me.isInCombat()) {
			if (csThieverConstants.stallArea.contains(me.getLocation())) {
				Keyboard.getInstance().sendKeys("::train");
				Time.sleep(1000);
			} else if (csThieverConstants.stallArea.contains(me.getLocation())) {
				Keyboard.getInstance().sendKeys("::home");
				Time.sleep(1000);
			}
		}
		if (Interfaces.getOpenInterfaceId() == csThieverConstants.welcomeInterface) {
			Menu.interact("Close", new Point(470, 16));
			Time.sleep(500);
		} else if (Interfaces.getOpenInterfaceId() == csThieverConstants.randomInterface) {
			if (!Tab.LOGOUT.isOpen() && Game.isLoggedIn()) {
				Tab.LOGOUT.open();
			} else if (Tab.LOGOUT.isOpen() && Game.isLoggedIn()) {
				Menu.interact("Ok", new Point(640, 376));
				Time.sleep(500);
			}
			if (!Game.isLoggedIn()) {
				Mouse.getInstance().click(new Point(455, 291));
				Time.sleep(1000);
				Mouse.getInstance().click(new Point(306, 320));
				/*
				 * sleep(new Condition() {
				 * 
				 * @Override public boolean shouldBreak() {
				 * LogArea.log("dynamic sleep"); return
				 * Interfaces.getOpenInterfaceId() == 15944; } }, 6000);
				 */
				Time.sleep(4000);
				LogArea.log("dynamic sleep done");
			}
		}
	}

	public interface Condition {
		public boolean shouldBreak();
	}

	public void sleep(Condition c, int timeout) {
		long endTime = System.currentTimeMillis() + timeout;
		while ((System.currentTimeMillis() < endTime) && !c.shouldBreak())
			Time.sleep(100);
	}

}
