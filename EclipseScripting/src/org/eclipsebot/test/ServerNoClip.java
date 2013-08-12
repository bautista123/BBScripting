package org.eclipsebot.test;

import java.awt.Graphics;

import org.demmonic.client.listeners.Message;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.ui.ClientUI;

@ScriptDetails(name = "Server No Clip", info = "lool gg fags")
public class ServerNoClip extends Script {

	@Override
	public void draw(Graphics arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart() {
		ClientUI.pushMessage("No clipping enabled.");
		getClient().setPlane(3);
	}
}
