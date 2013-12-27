package org.parabot.crabfighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Camera;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Npcs;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.Skill;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.interactive.Player;

/**
 * Created with IntelliJ IDEA. User: Zach Date: 11/26/13 Time: 10:06 PM To
 * change this template use File | Settings | File Templates.
 */
@ScriptManifest(author = "b0ss0wn3r & Bautista123", category = Category.COMBAT, description = "Fights rock crabs, loots/bury bones, and eats when health is below 10.", name = "CrabFighter", servers = { "Any 317" }, version = 2)
public final class CrabFighter extends Script implements Paintable {
	ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	static long startTime;
	public static String status;
	private final Color color1 = new Color(255, 255, 255);
	private final Font font1 = new Font("Verdana", 1, 12);
	private final Font font2 = new Font("Verdana", 1, 15);
	public static boolean looting = false;
	public static int bones = 526;
	public static int foodID = 379;
	public static int Rock_Crab = 1265;
	public static int StartingExp;
	public static int TotalExp;
	public static int StrLGained, AttLGained, DefLGained, HpLGained;
	public static int CurrentDef, CurrentAtt, CurrentStr, CurrentHp;
	public static int StartDef, StartStr, StartAtt, StartHp;
	public static int CurrentDefXp, CurrentStrXp, CurrentAttXp, CurrentHpXp;
	public static int StartDefXp, StartAttXp, StartHpXp, StartStrXp;

	public static String perHour(int gained) {
		return formatNumber((int) ((gained) * 3600000D / (System
				.currentTimeMillis() - startTime)));
	}

	public static String formatNumber(int start) {
		DecimalFormat nf = new DecimalFormat("0.0");
		double i = start;
		if (i >= 1000000) {
			return nf.format((i / 1000000)) + "m";
		}
		if (i >= 1000) {
			return nf.format((i / 1000)) + "k";
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
	public boolean onExecute() {
		LogArea.log("Script Started");
		startTime = System.currentTimeMillis();
		StartingExp = Skill.ATTACK.getExperience()
				+ Skill.STRENGTH.getExperience()
				+ Skill.DEFENSE.getExperience()
				+ Skill.HITPOINTS.getExperience();
		StartAttXp = Skill.ATTACK.getExperience();
		StartStrXp = Skill.STRENGTH.getExperience();
		StartDefXp = Skill.DEFENSE.getExperience();
		StartHpXp = Skill.HITPOINTS.getExperience();
		StartAtt = Skill.ATTACK.getLevel();
		StartStr = Skill.STRENGTH.getLevel();
		StartDef = Skill.DEFENSE.getLevel();
		StartHp = Skill.HITPOINTS.getRealLevel();
		Collections.addAll(strategies, new Attack(), new Eat());
		provide(strategies);
		return true;

	}

	public void onFinish() {

	}

	public class Attack implements Strategy {
		final Npc[] CRAB = Npcs.getNearest(Rock_Crab);
		Npc CRAB1 = CRAB[1];
		final Player me = Players.getLocal();

		@Override
		public boolean activate() {

			return CRAB1 != null && !CRAB1.isInCombat() && !me.isInCombat()
					&& getHP() >= 10;

		}

		public void execute() {
			try {
				final Npc[] CRAB = Npcs.getNearest(1265);
				Npc CRAB1 = CRAB[1];
				if (CRAB1 != null && !CRAB1.isOnScreen() && !CRAB1.isInCombat()) {
					Camera.turnTo(CRAB1);
					CRAB1.getLocation().clickMM();
				}
				if (CRAB1 != null && !CRAB1.isInCombat() && CRAB1.isOnScreen()
						&& !me.isInCombat()) {
					Camera.turnTo(CRAB1);
					CRAB1.interact("Attack");
					status = "Attacking a crab.";
					Time.sleep(550);
					Camera.setPitch(true);
				} else if (CRAB1 != null && CRAB1.isInCombat()) {
					status = "All NPCs in combat";
					Time.sleep(500);
				}
				while (CRAB1 != null && me.isInCombat() && getHP() >= 10
						|| CRAB1 != null && me.isWalking()) {
					status = "In Combat";
					// DynamicSleep would help a lot
					Time.sleep(500);
				}

			} catch (NullPointerException fuck) {
			}
		}
	}

	public class Eat implements Strategy {
		@Override
		public boolean activate() {
			if (getHP() < 10) {
				return true;
			}
			return false;
		}

		public void execute() {
			try {
				status = "Eating.";
				if (Inventory.getCount(foodID) < 1 && getHP() < 10) {
					Keyboard.getInstance().sendKeys("::home");
					status = "Out of food";
				}
				if (Inventory.getCount(foodID) >= 1 && getHP() < 10) {
					if (!Tab.INVENTORY.isOpen()) {
						Tab.INVENTORY.open();
						status = "Opening INVY";
					} else if (Tab.INVENTORY.isOpen()) {
						for (final Item i : Inventory.getItems(foodID)) {
							if (getHP() < 10) {
								i.interact("Eat");
								Time.sleep(1200);
							}
						}

					}
				}

			} catch (NullPointerException o) {
			}
		}
	}

	public static final int getHP() {
		String hp = Interfaces.get(3918).getChildren()[11].getText();
		String dirty = hp.replace("@whi@", "");
		return Integer.parseInt(dirty);
	}

	@Override
	public void paint(Graphics g1) {
		TotalExp = Skill.ATTACK.getExperience()
				+ Skill.STRENGTH.getExperience()
				+ Skill.DEFENSE.getExperience()
				+ Skill.HITPOINTS.getExperience() - StartingExp;

		CurrentAttXp = Skill.ATTACK.getExperience() - StartAttXp;
		CurrentDefXp = Skill.DEFENSE.getExperience() - StartDefXp;
		CurrentStrXp = Skill.STRENGTH.getExperience() - StartStrXp;
		CurrentHpXp = Skill.HITPOINTS.getExperience() - StartHpXp;
		CurrentDef = Skill.DEFENSE.getLevel();
		CurrentAtt = Skill.ATTACK.getLevel();
		CurrentStr = Skill.STRENGTH.getLevel();
		CurrentHp = Skill.HITPOINTS.getRealLevel();
		AttLGained = CurrentAtt - StartAtt;
		HpLGained = CurrentHp - StartHp;
		StrLGained = CurrentStr - StartStr;
		DefLGained = CurrentDef - StartDef;
		Graphics2D g = (Graphics2D) g1;
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("Time Ran: " + runTime(startTime), 27, 73);
		g.setFont(font2);
		g.drawString("Crab Fighter by b0ss0wn3r", 27, 48);
		g.setFont(font1);
		g.drawString("Current Action: " + status, 27, 325);
		g.drawString("Attack Level: " + CurrentAtt + "(" + AttLGained + ")",
				27, 98);
		g.drawString("Strength Level: " + CurrentStr + "(" + StrLGained + ")",
				27, 148);
		g.drawString("Defence Level: " + CurrentDef + "(" + DefLGained + ")",
				27, 198);
		g.drawString("Health Level: " + CurrentHp + "(" + HpLGained + ")", 27,
				248);
		g.drawString("Strength Exp Gained: " + (CurrentStrXp) + "("
				+ perHour(CurrentStrXp) + ")", 27, 173);
		g.drawString("Attack Exp Gained: " + (CurrentAttXp) + "("
				+ perHour(CurrentAttXp) + ")", 27, 123);
		g.drawString("Defence Exp Gained: " + (CurrentDefXp) + "("
				+ perHour(CurrentDefXp) + ")", 27, 223);
		g.drawString("Health Exp Gained: " + (CurrentHpXp) + "("
				+ perHour(CurrentHpXp) + ")", 27, 273);
		g.drawString("Total Exp Gained: " + TotalExp + "(" + perHour(TotalExp)
				+ ")", 27, 300);
	}

}