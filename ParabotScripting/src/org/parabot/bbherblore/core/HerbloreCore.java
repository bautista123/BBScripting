package org.parabot.bbherblore.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.bbherblore.guis.HerbloreMainGUI;
import org.parabot.bbherblore.strategies.HerbloreBankClose;
import org.parabot.bbherblore.strategies.HerbloreCleaner;
import org.parabot.bbherblore.strategies.HerbloreDeposit;
import org.parabot.bbherblore.strategies.HerbloreMakeExtremes;
import org.parabot.bbherblore.strategies.HerbloreMakeOverloads;
import org.parabot.bbherblore.strategies.HerbloreOpenBank;
import org.parabot.bbherblore.strategies.HerbloreReposition;
import org.parabot.bbherblore.strategies.HerbloreSpawn;
import org.parabot.bbherblore.strategies.HerbloreWithdraw;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.events.MessageEvent;
import org.rev317.api.events.listeners.MessageListener;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.scene.Tile;

@ScriptManifest(author = "Bautista123", category = Category.HERBLORE, description = "Do Anything with herblore", name = "bbAIOHerblore", servers = { "DeviousPK, RecklessPK" }, version = 0.3)
public final class HerbloreCore extends Script implements Paintable,
		MessageListener {
	public static ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	private final static Color color1 = new Color(255, 0, 0, 121);
	private final static Color color2 = new Color(0, 0, 0);
	private final static BasicStroke stroke1 = new BasicStroke(1);
	public static long startTime;
	private final static Font font1 = new Font("Arial", 1, 18);
	private final static Font font2 = new Font("Arial", 1, 12);
	public static int total = 0;

	public boolean onExecute() {
		HerbloreVariables.setWait(true);
		HerbloreMainGUI g = new HerbloreMainGUI();
		g.setVisible(true);
		while (HerbloreVariables.getWait()) {
			Time.sleep(50);
		}
		LogArea.log("bbHerblore Started");
		startTime = System.currentTimeMillis();
		Collections.addAll(strategies, new HerbloreOpenBank(),
				new HerbloreWithdraw(), new HerbloreSpawn(),
				new HerbloreBankClose(), new HerbloreMakeExtremes(),
				new HerbloreCleaner(), new HerbloreMakeOverloads(),
				new HerbloreDeposit(), new HerbloreReposition());
		LogArea.log("Your starting tile is " + HerbloreVariables.getStartTile());
		provide(strategies);
		return true;
	}

	public void onFinish() {
		LogArea.log("bbHerblore has ended");
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

	public String getTimeRan() {
		final long currentTime = System.currentTimeMillis() - startTime;
		String format = String.format(
				"%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours(currentTime),
				TimeUnit.MILLISECONDS.toMinutes(currentTime)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
								.toHours(currentTime)),
				TimeUnit.MILLISECONDS.toSeconds(currentTime)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(currentTime)));
		return format;
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setColor(color1);
		g.fillRect(548, 208, 186, 256);
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(548, 208, 186, 256);
		g.setFont(font1);
		g.drawString("bbHerblore", 594, 228);
		g.setFont(font2);
		total = HerbloreVariables.getXStrMade()
				+ HerbloreVariables.getXAttMade()
				+ HerbloreVariables.getXDefMade()
				+ HerbloreVariables.getXMageMade()
				+ HerbloreVariables.getXRangeMade();
		if (HerbloreVariables.getMakeOvls()) {
			g.drawString("Task: Make Overloads", 588, 251);
			g.drawString("Time: " + getTimeRan(), 552, 448);
			g.drawLine(549, 281, 733, 281);
			g.drawString("Overloads Made: " + HerbloreVariables.getOvlMade()
					+ "(" + perHour(HerbloreVariables.getOvlMade()) + ")", 550,
					419);
			g.drawLine(549, 293, 733, 293);
			g.drawString("Overloads", 612, 292);
			g.drawLine(667, 288, 734, 288);
			g.drawLine(609, 287, 549, 287);
		} else if (HerbloreVariables.getMakeXT()) {
			g.drawString("Task: Make Extremes", 580, 251);
			g.drawString("Time: " + getTimeRan(), 552, 448);
			g.drawLine(549, 281, 733, 281);
			g.drawString(
					"Extremes Made: " + total + "(" + perHour(total) + ")",
					550, 419);
			g.drawLine(549, 293, 733, 293);
			g.drawString("Extremes", 612, 292);
			g.drawLine(667, 288, 734, 288);
			g.drawLine(609, 287, 549, 287);
			g.drawString("Extreme Strength: " + HerbloreVariables.getXStrMade()
					+ "(" + perHour(HerbloreVariables.getXStrMade()) + ")",
					551, 316);
			g.drawString("Extreme Attack: " + HerbloreVariables.getXAttMade()
					+ "(" + perHour(HerbloreVariables.getXAttMade()) + ")",
					551, 334);
			g.drawString("Extreme Defence: " + HerbloreVariables.getXDefMade()
					+ "(" + perHour(HerbloreVariables.getXDefMade()) + ")",
					551, 354);
			g.drawString("Extreme Magic: " + HerbloreVariables.getXMageMade()
					+ "(" + perHour(HerbloreVariables.getXMageMade()) + ")",
					551, 376);
			g.drawString("Extreme Range: " + HerbloreVariables.getXRangeMade()
					+ "(" + perHour(HerbloreVariables.getXRangeMade()) + ")",
					551, 396);
		} else if (HerbloreVariables.getCleanHerbs()) {
			g.drawString("Task: Clean Herbs", 588, 251);
			g.drawString("Time: " + getTimeRan(), 552, 448);
			g.drawLine(549, 281, 733, 281);
			g.drawString(
					"Herbs Cleaned: " + HerbloreVariables.getHerbsCleaned()
							+ "("
							+ perHour(HerbloreVariables.getHerbsCleaned())
							+ ")", 550, 419);
			g.drawLine(549, 293, 733, 293);
			g.drawString("-Cleaner-", 612, 292);
			g.drawLine(667, 288, 734, 288);
			g.drawLine(609, 287, 549, 287);
			g.drawString("Herb: " + HerbloreVariables.getHerbName(), 550, 390);
		} else if (HerbloreVariables.getSpawn()) {
			g.drawString("Task: Spawn Supplies", 580, 251);
			g.drawString("Time: " + getTimeRan(), 552, 448);
			g.drawLine(549, 281, 733, 281);
			g.drawString("Items Spawned: " + HerbloreVariables.getSpawned()
					+ "(" + perHour(HerbloreVariables.getSpawned()) + ")", 550,
					419);
			g.drawLine(549, 293, 733, 293);
			g.drawString("Spawner", 612, 292);
			g.drawLine(667, 288, 734, 288);
			g.drawLine(609, 287, 549, 287);
			g.drawString("Stackables: " + HerbloreVariables.getSpawnStack(),
					550, 363);
			g.drawString(
					"UnStackables: " + HerbloreVariables.getSpawnUnstack(),
					550, 393);

		}

	}

	@Override
	public void messageReceived(MessageEvent m) {
		if (m.getMessage().contains("Extreme attack (3)")) {
			HerbloreVariables.setXAttMade(HerbloreVariables.getXAttMade() + 1);

		} else if (m.getMessage().contains("Extreme strength (3)")) {
			HerbloreVariables.setXStrMade(HerbloreVariables.getXStrMade() + 1);

		} else if (m.getMessage().contains("Extreme defence (3)")) {
			HerbloreVariables.setXDefMade(HerbloreVariables.getXDefMade() + 1);

		} else if (m.getMessage().contains("Extreme ranging (3)")) {
			HerbloreVariables
					.setXRangeMade(HerbloreVariables.getXRangeMade() + 1);

		} else if (m.getMessage().contains("Extreme magic (3)")) {
			HerbloreVariables
					.setXMageMade(HerbloreVariables.getXMageMade() + 1);

		} else if (m.getMessage().contains("You identify the herb")) {
			HerbloreVariables.setHerbsCleaned(HerbloreVariables
					.getHerbsCleaned() + 1);
		}

	}

}