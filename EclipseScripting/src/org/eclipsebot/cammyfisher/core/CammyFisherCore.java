package org.eclipsebot.cammyfisher.core;

import java.awt.Graphics;
import java.util.ArrayList;

import org.demmonic.client.listeners.Message;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.eclipsebot.cammyfisher.strategies.CammyFisherBank;
import org.eclipsebot.cammyfisher.strategies.CammyFisherFish;

@ScriptDetails(name = "Cammy Fisher", info = "Fish and bank any fish in Camelot. By: Bautista")
public class CammyFisherCore extends Script {

	public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	
	@Override
	public void onStart() {	
		ClientUI.pushMessage("Script started.");
		strategies.add(new CammyFisherBank(this));
		strategies.add(new CammyFisherFish(this));
		addStrategies(strategies);


	}

	@Override
	public void onEnd() {
		ClientUI.pushMessage("Script ended.");

	}

	@Override
	public void draw(Graphics g1) {

	}

	@Override
	public void onMessage(Message m) {

	}
}
