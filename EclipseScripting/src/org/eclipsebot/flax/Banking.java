package org.eclipsebot.flax;

import java.util.ArrayList;

import org.demmonic.client.api.Area;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.wrappers.RS2ObjectWrapper;

public class Banking extends Strategy {

	private Script script;
	
	public Banking(Script script) {
		this.script = script;
	}
	
	private Area bankArea = new Area(2721, 3486, 2730, 3494);
	
	@Override
	public boolean shouldExecute() {
		return script.inventory.isFull() && bankArea.contains(script.game.getMyPlayer());
	}
	
	@Override
	public int execute() {
		if (!script.bank.isOpen()) {
			ArrayList<RS2ObjectWrapper> bankList = script.RS2Objects.getRS2ObjectsForIds(2213);
			if (bankList.isEmpty()) {
				return 100;
			}
			RS2ObjectWrapper bank = bankList.get(0);
			bank.interact(900);
			dynamicSleep(script.game.getMyPlayer().isWalking(), 4000);
			while (script.game.getMyPlayer().isWalking()) {
				script.sleep(100);
				if (!script.getClient().isLoggedIn()) {
					break;
				}
			}
			dynamicSleep(script.bank.isOpen(), 4000);
		}
		if (script.bank.isOpen()) {
			script.banking.depositAll(1780);
		}
		return 100;
	}
	
	public void dynamicSleep(boolean condition, long sleepTime) {
		long endTime = (System.currentTimeMillis() + sleepTime);
		while (!condition && (System.currentTimeMillis() < endTime)) {
			script.sleep(50);
		}
	}
	
}