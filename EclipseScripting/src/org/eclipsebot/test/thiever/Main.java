package org.eclipsebot.test.thiever;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.demmonic.client.listeners.Message;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.script.Strategy;

@ScriptDetails(name = "test", info = "lool")
public class Main extends Script {

	private int timesStolen = 0;
	

	ArrayList<Strategy> strats = new ArrayList<Strategy>();
	
	@Override
	public void onStart() {
		strats.add(new StealingStrategy(this));
		addStrategies(strats);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(0, 0, 150, 75);
		g.setColor(new Color(Color.black.getRed(), Color.black.getGreen(), Color.black.getBlue(), 140));
		g.fillRect(1, 1, 149, 74);
		g.setColor(Color.red);
		g.drawString("demmonic's 1337 thiever", 3, 15);
		g.drawString("times stolen: " + timesStolen, 3, 28);
	}

	@Override
	public void onMessage(Message message) {
		if (message.message.startsWith("You manage to steal")) {
			timesStolen++;
		}
	}

	@Override
	public void onEnd() {
		// TODO Auto-generated method stub
		
	}
	
	
}
