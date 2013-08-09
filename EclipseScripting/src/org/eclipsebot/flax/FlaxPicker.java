package org.scripts.flax;

import java.awt.Graphics;
import java.util.ArrayList;

import org.demmonic.client.listeners.Message;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.script.Strategy;

@ScriptDetails(info = "picks flax", name = "flax picker")
public class FlaxPicker extends Script {

	ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	
	@Override
	public void onStart() {
		strategies.add(new Banking(this));
		strategies.add(new Picking(this));
		strategies.add(new Walking(this));
		addStrategies(strategies);
	}
	
	@Override
	public void onEnd() {
		
	}
	
	@Override
	public void draw(Graphics arg0) {
		
	}

	@Override
	public void onMessage(Message arg0) {
		
	}
}
