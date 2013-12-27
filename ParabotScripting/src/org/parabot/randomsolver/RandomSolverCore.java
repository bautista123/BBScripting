import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Camera;
import org.rev317.api.methods.GroundItems;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Npcs;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.scene.GroundItem;

/**
 * Created with IntelliJ IDEA. User: Zach Date: 11/26/13 Time: 10:06 PM To
 * change this template use File | Settings | File Templates.
 */
@ScriptManifest(author = "b0ss0wn3r", category = Category.COMBAT, description = "Fights rock crabs, loots/bury bones, and eats when health is below 10.", name = "CrabFighter", servers = { "Any 317" }, version = 1)
public final class CrabFighter extends Script implements Paintable {
	ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	static long startTime;
	public static boolean looting = false;

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
		startTime = System.currentTimeMillis();
		strategies.add(new CrabsAttack());
		strategies.add(new Loot());
		strategies.add(new Bury());
		strategies.add(new Eat());
		provide(strategies);
		return true;

	}

	public void onFinish() {

	}

	public class CrabsAttack implements Strategy {
		@Override
		public boolean activate() {
			final GroundItem[] Loot = GroundItems.getNearest(526);
			GroundItem BONES = Loot[1];
			final Npc[] CRAB = Npcs.getNearest(1265);
			Npc CRAB1 = null;
			CRAB1 = CRAB[1];
			return CRAB1 != null && !Players.getLocal().isInCombat()
					&& looting == false && Inventory.getCount(526) < 1
					&& BONES != null && getHP() >= 10;

		}

		public void execute() {
			final GroundItem[] Loot = GroundItems.getNearest(526);
			GroundItem BONES = Loot[1];
			final Npc[] CRAB = Npcs.getNearest(1265);
			Npc CRAB1 = CRAB[1];
			if (CRAB1 != null && !CRAB1.isOnScreen() && !CRAB1.isInCombat()) {
				Camera.turnTo(CRAB1);
				CRAB1.getLocation().clickMM();
			}
			if (CRAB1 != null && !CRAB1.isInCombat() && CRAB1.isOnScreen()
					&& !Players.getLocal().isInCombat()) {
				Camera.turnTo(CRAB1);
				Time.sleep(500);
				CRAB1.interact("Attack");
				LogArea.log("Attacking a crab.");
				LogArea.log("Health level: " + getHP() + "");
				Camera.setPitch(true);
				Time.sleep(4500);

			} else if (CRAB1 != null && CRAB1.isInCombat()) {
				Time.sleep(1000);
			}
			while (CRAB1 != null && Players.getLocal().isInCombat()
					&& getHP() >= 10 || CRAB1 != null
					&& Players.getLocal().isWalking()) {
				if (Players.getLocal().isInCombat()) {
					Time.sleep(2000);
				} else if (Players.getLocal().isWalking()) {
					Time.sleep(500);
				}
			}
			if (BONES.distanceTo() < 2) {
				looting = true;
			} else {
				looting = false;
			}
		}
	}

	public class Loot implements Strategy {
		@Override
		public boolean activate() {
			final GroundItem[] Loot = GroundItems.getNearest(526);
			GroundItem BONES = Loot[1];
			if (BONES != null && looting == true && !Inventory.isFull()
					&& !Players.getLocal().isInCombat()) {
				return true;
			}
			return false;
		}

		public void execute() {
			LogArea.log("Looting Bones.");
			final GroundItem[] Loot = GroundItems.getNearest(526);
			GroundItem BONES = Loot[1];
			if (BONES != null && !Players.getLocal().isInCombat()) {
				Camera.turnTo(BONES);
				Time.sleep(400);
				BONES.interact("Take Bones");
				Time.sleep(1000);
				looting = false;
			}

		}
	}

	public class Bury implements Strategy {
		@Override
		public boolean activate() {
			if (Inventory.getCount(526) >= 1) {
				return true;
			}
			return false;
		}

		public void execute() {
			LogArea.log("Burying bones.");
			if (Inventory.getCount(526) >= 1) {
				for (Item i : Inventory.getItems(526)) {
					i.interact("Bury");
					Time.sleep(500);
				}
			}
		}
	}

	public class Eat implements Strategy {
		@Override
		public boolean activate() {
			if (Inventory.getCount(379) >= 1 && getHP() < 10) {
				return true;
			}
			return false;
		}

		public void execute() {
			final Npc[] CRAB = Npcs.getNearest(1265);
			Npc CRAB1 = CRAB[1];

			LogArea.log("Eating.");
			if (Inventory.getCount(379) >= 1 && getHP() < 10) {
				if (!Tab.INVENTORY.isOpen()) {
					Tab.INVENTORY.open();

				} else if (Tab.INVENTORY.isOpen()) {
					for (final Item i : Inventory.getItems(379)) {
						if (getHP() < 10) {
							i.interact("Eat");
							Time.sleep(1200);
							getHP();
						}
					}

				}
			}
			if (CRAB1 != null && Players.getLocal().isInCombat()) {
				Camera.turnTo(CRAB1);
				Time.sleep(500);
				CRAB1.interact("Attack");
				LogArea.log("Attacking a crab.");
				LogArea.log("Health level: " + getHP() + "");
				Camera.setPitch(true);
				Time.sleep(4500);
			}
		}
	}

	public static final int getHP() {
		String hp = Interfaces.get(3918).getChildren()[11].getText();
		String dirty = hp.replace("@whi@", "");
		return Integer.parseInt(dirty);
	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.white);
		g.setFont(new Font("Verdana", Font.PLAIN, 16));

		g.drawString("CrabFighter", 8, 252);

		g.setFont(new Font("Verdana", Font.PLAIN, 12));

		g.drawString("Runtime: " + runTime(startTime), 8, 280);

	}

}