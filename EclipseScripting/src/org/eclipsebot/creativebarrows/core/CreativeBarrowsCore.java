package org.eclipsebot.creativebarrows.core;

import java.awt.Graphics;
import java.util.ArrayList;

import org.demmonic.client.listeners.Message;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.eclipsebot.creativebarrows.strategies.CreativeBarrowsAttacker;
import org.eclipsebot.creativebarrows.strategies.CreativeBarrowsTeleport;
import org.eclipsebot.creativebarrows.strategies.CreativeBarrowsTeleportToBank;
import org.eclipsebot.creativebarrows.strategies.CreativeBarrowsWithdraw;

@ScriptDetails(name = "Creative Barrows", info = "Completes the barrows minigame. By: Bautista")
public class CreativeBarrowsCore extends Script {
	public ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	@Override
	public void onStart() {
		ClientUI.pushMessage("Script started.");
		strategies.add(new CreativeBarrowsWithdraw(this));
		strategies.add(new CreativeBarrowsTeleport(this));
		strategies.add(new CreativeBarrowsAttacker(this));
		strategies.add(new CreativeBarrowsTeleportToBank(this));
		addStrategies(strategies);
	}

	@Override
	public void onEnd() {
		ClientUI.pushMessage("Script stopped.");

	}

	@Override
	public void draw(Graphics arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub

	}

}
