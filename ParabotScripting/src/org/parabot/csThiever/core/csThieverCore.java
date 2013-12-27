package org.parabot.csThiever.core;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.parabot.core.ui.components.LogArea;
import org.parabot.csThiever.data.csThieverVariables;
import org.parabot.csThiever.gui.csThieverGUI;
import org.parabot.csThiever.strategies.csThieverDropper;
import org.parabot.csThiever.strategies.csThieverRandoms;
import org.parabot.csThiever.strategies.csThieverSteal;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.events.MessageEvent;
import org.rev317.api.events.listeners.MessageListener;
import org.rev317.api.methods.Skill;

@ScriptManifest(author = "Bautista123 & Capslock", category = Category.THIEVING, description = "Steal from any stall and make cash", name = "csThiever", servers = { "Creative-Scape" }, version = 0.3)
public class csThieverCore extends Script implements Paintable, MessageListener {
	public static long startTime;
	public static ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	public boolean onExecute() {
		csThieverVariables.setStartLevel(Skill.THIEVING.getRealLevel());
		startTime = System.currentTimeMillis();
		csThieverGUI GUI = new csThieverGUI();
		GUI.setVisible(true);
		csThieverVariables.setIsRunning(true);
		while (csThieverVariables.getIsRunning()) {
			Time.sleep(100);
		}
		LogArea.log("Scripted has started");
		strategies.add(new csThieverSteal());
		strategies.add(new csThieverRandoms());
		strategies.add(new csThieverDropper());
		provide(strategies);
		LogArea.log("Strategies added");
		return true;
	}

	public static String runTime(long i) {
		DecimalFormat nf = new DecimalFormat("00");
		long millis = System.currentTimeMillis() - i;
		long hours = millis / (1000 * 60 * 60);
		millis -= hours * (1000 * 60 * 60);
		long minutes = millis / (1000 * 60);
		millis -= minutes * (1000 * 60);
		long seconds = millis / 1000;
		return nf.format(hours) + ":" + nf.format(minutes) + ":"
				+ nf.format(seconds);
	}

	public static String perHour(int gained) {
		return formatNumber((int) ((gained) * 3600000D / (System
				.currentTimeMillis() - startTime)));
	}

	public static String formatNumber(int start) {
		DecimalFormat nf = new DecimalFormat("0.0");
		double i = start;
		if (i >= 1000000) {
			return nf.format((i / 1000000)) + "M";
		}
		if (i >= 1000) {
			return nf.format((i / 1000)) + "K";
		}
		return "" + start;
	}

	public void onFinish() {
		LogArea.error("Script has stopped. It ran for " + runTime(startTime));
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		csThieverVariables.setCurrentLevel(Skill.THIEVING.getRealLevel());
		g.setFont(new Font("Verdana", Font.PLAIN, 20));
		g.drawString("csThiever", 550, 452);
		g.setFont(new Font("Verdana", Font.PLAIN, 12));
		g.drawString(
				"Level (Gained): "
						+ Skill.THIEVING.getLevel()
						+ " ("
						+ (csThieverVariables.getCurrentLevel() - csThieverVariables
								.getStartLevel()) + ")", 550, 427);
		g.drawString("Runtime: " + runTime(startTime), 550, 407);
		g.drawString("Steals: " + csThieverVariables.getThieves() + "("
				+ perHour(csThieverVariables.getThieves()) + ")", 550, 387);
	}

	public void messageReceived(MessageEvent m) {
		if (m.getMessage().contains("You manage to steal")) {
			csThieverVariables.setThieves(csThieverVariables.getThieves() + 1);
		}
	}
}
