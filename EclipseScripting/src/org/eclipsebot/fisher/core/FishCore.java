package org.eclipsebot.fisher.core;

import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.*;

import org.demmonic.client.listeners.Message;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.ui.ClientUI;
import org.eclipsebot.fisher.data.FisherVar;
import org.eclipsebot.fisher.strategys.FishBanker;
import org.eclipsebot.fisher.strategys.Fisher;

@ScriptDetails(name = "fisher", info = "Fisrt script")
public class FishCore extends Script {
	static long StartTime;
	public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	private final Color color1 = new Color(0, 204, 204, 149);
	private final Color color2 = new Color(0, 0, 0);

	private final BasicStroke stroke1 = new BasicStroke(1);

	private final Font font1 = new Font("Arial", 1, 13);
	private final Font font2 = new Font("Arial", 1, 18);

	@Override
	public void onStart() {
		StartTime = System.currentTimeMillis();
		ClientUI.pushMessage("Script started.");
		strategies.add(new FishBanker(this));
		strategies.add(new Fisher(this));
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
		g.fillRect(356, 4, 159, 130);
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(356, 4, 159, 130);
		g.setFont(font1);
		g.drawString("Fish Caught: " + FisherVar.getCaught() + "("
				+ perHour(FisherVar.getCaught()) + ")", 357, 80);
		g.drawString("Randoms evaded: " + FisherVar.getRandomsEvaded(), 358, 58);
		g.drawLine(357, 28, 515, 28);
		g.drawString("Time: " + runTime(StartTime), 358, 101);
		g.setFont(font2);
		g.drawString("Bautista's Fisher", 363, 24);
		g.setFont(font1);
		g.drawString("Status: " + FisherVar.getStatus(), 357, 122);

	}

	@Override
	public void onMessage(Message m) {
		if (m.message.contains("You catch some")) {
			FisherVar.setCaught(FisherVar.getCaught() + 1);
		}

	}
}
