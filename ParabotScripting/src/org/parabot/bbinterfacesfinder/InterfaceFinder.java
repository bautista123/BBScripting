package org.parabot.bbinterfacesfinder;

import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Interfaces;

import java.awt.*;
import java.util.ArrayList;

/**
 * DO NOT STEAL MY CODE ASSHOLE User: Bautista Date: 8/4/13 DO NOT STEAL MY
 * CODE, I'LL KILL YOUR FAMILY
 */
@ScriptManifest(author = "Bautista123", category = Category.OTHER, description = "Find interface IDs", name = "BBInterfaceFinder", servers = { "Any" }, version = 0.1)
public final class InterfaceFinder extends Script implements Paintable {
	public static ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	public static int lastIntID = 0;
	public static int lastintid = 0;
	public static String s;
	public static int r = 0;
	public static int i = 0;
	private final Color color1 = new Color(255, 0, 0);
	private final Font font1 = new Font("Arial", 1, 20);

	public boolean onExecute() {
		LogArea.log("Script started.");
		strategies.add(new find());
		provide(strategies);
		return true;
	}

	public void onFinish() {
		LogArea.log("Script ended.");
	}

	public class find implements Strategy {
		public boolean activate() {
			return true;
		}

		public void execute() {
			int i = Interfaces.getOpenInterfaceId();
			int r = Interfaces.getChatboxInterfaceId();
			if (r == lastintid) {

			} else {
				LogArea.log("ChatBox id: " + r);
			}
			lastintid = r;
			if (i == lastIntID) {

			} else {
				s = String.valueOf(i);
			}

			lastIntID = i;
		}
	}

	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("Interface ID: " + s, 7, 28);
	}
}