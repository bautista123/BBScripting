package org.eclipsebot.draynorfisher.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.demmonic.client.listeners.Message;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.eclipsebot.draynorfisher.data.DraynorFisherVar;
import org.eclipsebot.draynorfisher.gui.DraynorFisherGUI;
import org.eclipsebot.draynorfisher.strategys.DraynorFishBanker;
import org.eclipsebot.draynorfisher.strategys.DraynorFisherFish;

@ScriptDetails(name = "Draynor Fisher", info = "Fish and bank shrimp and anchovies at draynor. By: Bautista")
public class DraynorFishCore extends Script {
	static long StartTime;
	public ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	private final Color color1 = new Color(0, 204, 204, 149);
	private final Color color2 = new Color(0, 0, 0);

	private final BasicStroke stroke1 = new BasicStroke(1);

	private final Font font1 = new Font("Arial", 1, 13);
	private final Font font2 = new Font("Arial", 1, 18);

	@Override
	public void onStart() {
		DraynorFisherGUI g = new DraynorFisherGUI();
        g.setVisible(true);
		DraynorFisherVar.setGuiWait(true);
		while(DraynorFisherVar.getGuiWait()){
			sleep(100);
		}
		StartTime = System.currentTimeMillis();
		ClientUI.pushMessage("Script started.");
		strategies.add(new DraynorFishBanker(this));
		strategies.add(new DraynorFisherFish(this));
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
		g.drawString("Fish Caught: " + DraynorFisherVar.getCaught() + "("
				+ perHour(DraynorFisherVar.getCaught()) + ")", 357, 80);
		g.drawString("Randoms evaded: " + DraynorFisherVar.getRandomsEvaded(), 358, 58);
		g.drawLine(357, 28, 515, 28);
		g.drawString("Time: " + runTime(StartTime), 358, 101);
		g.setFont(font2);
		g.drawString("Bautista's Fisher", 363, 24);
		g.setFont(font1);
		g.drawString("Status: " + DraynorFisherVar.getStatus(), 357, 122);

	}

	@Override
	public void onMessage(Message m) {
		if (m.message.contains("You catch some")) {
			DraynorFisherVar.setCaught(DraynorFisherVar.getCaught() + 1);
		}

	}
}
