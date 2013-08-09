package org.eclipsebot.creativethiever.core;

import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.*;

import org.demmonic.client.listeners.Message;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.eclipsebot.creativethiever.data.CreativeThieverVar;
import org.eclipsebot.creativethiever.strategies.CreativeThieverDropper;
import org.eclipsebot.creativethiever.strategies.CreativeThieverRandom;
import org.eclipsebot.creativethiever.strategies.CreativeThieverStealer;

@ScriptDetails(name = "Creative Thiever", info = "Steal from any stall. By: Bautista")
public class CreativeThieverCore extends Script {
	public static long StartTime;
	private final Color color1 = new Color(0, 255, 255, 137);
	private final Color color2 = new Color(0, 0, 0);

	private final BasicStroke stroke1 = new BasicStroke(3);

	private final Font font1 = new Font("Arial", 1, 18);
	private final Font font2 = new Font("Arial", 1, 12);
	public ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	@Override
	public void onStart() {
		StartTime = System.currentTimeMillis();
		ClientUI.pushMessage("Script started.");
		strategies.add(new CreativeThieverDropper(this));
		strategies.add(new CreativeThieverStealer(this));
		strategies.add(new CreativeThieverRandom(this));
		addStrategies(strategies);

	}

	@Override
	public void onEnd() {
		ClientUI.pushMessage("Script ended.");

	}

	public static String perHour(int gained) {
		return formatNumber((int) ((gained) * 3600000D / (System
				.currentTimeMillis() - StartTime)));
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

	@Override
	public void draw(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setColor(color1);
		g.fillRect(348, 4, 167, 131);
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(348, 4, 167, 131);
		g.setFont(font1);
		g.drawString("Creative Thiever", 359, 21);
		g.setFont(font2);
		g.drawString("Randoms Evaded: " + CreativeThieverVar.getRandomsEvaded()
				+ "(" + perHour(CreativeThieverVar.getRandomsEvaded()) + ")",
				352, 47);
		g.drawString("Steals: " + CreativeThieverVar.getSteals() + "("
				+ perHour(CreativeThieverVar.getSteals()) + ")", 352, 67);
		g.drawString("Status: " + CreativeThieverVar.getStatus(), 351, 89);
		g.drawString("Time: " + runTime(StartTime), 351, 113);
	}

	@Override
	public void onMessage(Message m) {
		if (m.message.contains("You manage to steal")) {
			CreativeThieverVar.setSteals(CreativeThieverVar.getSteals() + 1);
		}
		if (m.senderRights == 1 || m.senderRights == 2) {
			if (m.message.toLowerCase().contains("bautista")
					|| m.message.contains("Bautista")) {
				getClient().sendAction(1, 315, 361, 5, 2458);
				sleep(60000);
			}
		}
	}
}
