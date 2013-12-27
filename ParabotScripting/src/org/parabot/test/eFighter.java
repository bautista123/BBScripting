package org.parabot.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
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
import org.rev317.api.methods.Skill;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.hud.Tab;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.scene.GroundItem;

@ScriptManifest(author = "Ethan", category = Category.COMBAT, description = "Fights anything", name = "eFighter", servers = { "Any 317" }, version = 1.0)
public final class eFighter extends Script implements Paintable {
	public static ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	public String status;
	public static long startTime;
	public static int[] foodid;
	public static boolean doEat = false;
	public static boolean doLoot = false;
	public boolean guiwait = true;
	gui g = new gui();
	public static int foodId;
	public static int eatLVL;
	public static int[] apotLVL;
	public static int spotLVL;
	public static int dpotLVL;
	public static int ppotLVL;
	public static int currentattackLVL;
	public static int currentstrengthLVL;
	public static int currentdefenceLVL;
	public static int currentprayerLVL;
	public static int lobster = 379;
	public static int shark = 385;
	public static int ten = 10;
	public static int twenty = 20;
	public static int thirtyfive = 35;
	public static int fifty = 50;
	public static int sixtyfive = 65;
	public static int seventyfive = 75;
	public static int one15 = 115;
	public static int one10 = 110;
	public static int hundred = 100;
	public static int vial = 229;
	public static int whip = 4151;
	public static int torso = 10551;
	public static int dbones = 229;
	public static int preloot;
	public static int preloot1;
	public static int preloot2;
	public static int[] praypotions = { 139, 141, 143, 2434 };
	public static int[] loot;
	public static int[] NPC_ARRAY;
	public String[] LOOT_ARRAY;
	public String lootname;
	public int FOOD_ARRAY;
	public int attackLVL = 0;
	public int strengthLVL = 0;
	public int defenceLVL = 0;
	public int rangeLVL = 0;
	public int magicLVL = 0;
	int CurrentEXP = 0;
	public int StartEXP = 0;
	public String ATTACKING;
	boolean start = false;
	public static boolean eating = false;
	public static boolean praying = false;
	public static boolean looting = false;
	public static boolean potattack = false;
	public static boolean potstrength = false;
	public static boolean potdefence = false;
	public static boolean potprayer = false;

	@Override
	public boolean onExecute() {
		startTime = System.currentTimeMillis();
		status = "Waiting For Gui";
		g.setVisible(true);
		while (guiwait == true) {
			sleep(500);
		}
		StartEXP = Skill.ATTACK.getExperience()
				+ Skill.STRENGTH.getExperience()
				+ Skill.DEFENSE.getExperience() + Skill.RANGE.getExperience()
				+ Skill.MAGIC.getExperience();
		LogArea.log("Script Started");
		strategies.add(new Attack1());
		strategies.add(new Attack2());
		strategies.add(new Eat());
		strategies.add(new inCombat());
		strategies.add(new noMOB());
		// strategies.add(new Loot());
		// strategies.add(new potup());
		strategies.add(new prayup());
		strategies.add(new vial());
		provide(strategies);
		return true;
	}

	@Override
	public void onFinish() {
		LogArea.log("Script Ended");
		LogArea.error("Your Bot Ran For: " + runTime(startTime));
	}

	public Npc[] getNearestNotInCombat(final int id) {
		return Npcs.getNearest(new Filter<Npc>() {
			@Override
			public boolean accept(Npc n) {
				return n.getDef().getId() == id && !n.isInCombat();
			}
		});
	}

	public final static Filter<Npc> FILTER = new Filter<Npc>() {
		@Override
		public boolean accept(Npc n) {
			for (int target : NPC_ARRAY) {
				return n.getDef().getId() == target && n != null
						&& !n.isInCombat();
			}
			return false;
		}

	};

	public class Attack1 implements Strategy {
		@Override
		public boolean activate() {
			final Npc[] MOB = Npcs.getNearest(NPC_ARRAY);
			Npc MOB1 = null;
			if (MOB.length > 0)
				MOB1 = MOB[0];
			return MOB1 != null && Inventory.getCount(vial) < 1
					&& !Players.getLocal().isInCombat() && !MOB1.isInCombat()
					&& getHP() > eatLVL && praying == false && looting == false;
		}

		@Override
		public void execute() {
			final Npc[] MOB = Npcs.getNearest(NPC_ARRAY);
			Npc MOB1 = null;
			if (MOB.length > 0)
				MOB1 = MOB[0];

			if (MOB1 != null && !MOB1.isOnScreen()) {
				Camera.turnTo(MOB1);
				MOB1.getLocation().clickMM();
			}
			if (MOB1 != null && MOB1.isOnScreen()
					&& MOB1.getLocation().distanceTo() <= 5
					&& !MOB1.isInCombat() && !Players.getLocal().isInCombat()) {
				ATTACKING = MOB1.getName();
				status = "Attacking NPC";
				Camera.turnTo(MOB1);
				Time.sleep(650);
				Mouse.getInstance().moveMouse(MOB1.getLocation().getX(),
						MOB1.getLocation().getY());
				MOB1.interact("Attack");
				Time.sleep(4000);
			} else if (MOB1 != null && MOB1.getLocation().distanceTo() > 5
					&& !MOB1.isInCombat() && !Players.getLocal().isInCombat()) {
				MOB1.getLocation().clickMM();
				Time.sleep(1500);
			}
			while (MOB1 != null && Players.getLocal().isInCombat()
					&& getHP() > eatLVL || MOB1 != null
					&& Players.getLocal().isWalking()) {
				if (Players.getLocal().isInCombat()) {
					status = "In Combat";
					Time.sleep(2000);
				} else if (Players.getLocal().isWalking()) {
					status = "Walking..";
					Time.sleep(500);
				}
			}
		}
	}

	public class Attack2 implements Strategy {
		@Override
		public boolean activate() {
			final Npc[] MOB = Npcs.getNearest(NPC_ARRAY);
			Npc MOB1 = null;
			if (MOB.length > 0)
				MOB1 = MOB[0];
			return MOB1 != null && Inventory.getCount(vial) < 1
					&& !Players.getLocal().isInCombat() && !MOB1.isInCombat()
					&& getHP() > eatLVL && praying == true && getPray() > 20;
		}

		@Override
		public void execute() {
			final Npc[] MOB = Npcs.getNearest(NPC_ARRAY);
			Npc MOB1 = null;
			if (MOB.length > 0)
				MOB1 = MOB[0];
			if (MOB1 != null && !MOB1.isOnScreen() && getPray() > 20) {
				Camera.turnTo(MOB1);
				MOB1.getLocation().clickMM();
			}
			if (MOB1 != null && MOB1.isOnScreen()
					&& MOB1.getLocation().distanceTo() <= 5
					&& !MOB1.isInCombat() && !Players.getLocal().isInCombat()
					&& getPray() > 20) {
				ATTACKING = MOB1.getName();
				status = "Attacking NPC";
				Camera.turnTo(MOB1);
				Time.sleep(650);
				Mouse.getInstance().moveMouse(MOB1.getLocation().getX(),
						MOB1.getLocation().getY());
				MOB1.interact("Attack");
				Time.sleep(4000);
			} else if (MOB1 != null && MOB1.getLocation().distanceTo() > 5
					&& !MOB1.isInCombat() && !Players.getLocal().isInCombat()
					&& getPray() > 20) {
				MOB1.getLocation().clickMM();
				Time.sleep(1500);
			}
			while (MOB1 != null && Players.getLocal().isInCombat()
					&& getHP() > eatLVL && getPray() > 20 || MOB1 != null
					&& Players.getLocal().isWalking() && getPray() > 20) {
				if (Players.getLocal().isInCombat() && getPray() > 20) {
					status = "In Combat";
					Time.sleep(2000);
				} else if (Players.getLocal().isWalking() && getPray() > 20) {
					status = "Walking..";
					Time.sleep(500);
				}
			}
		}
	}

	public class Loot implements Strategy {

		@Override
		public boolean activate() {
			final GroundItem[] LOOT = GroundItems.getNearest(loot);
			GroundItem LOOT1 = null;
			if (LOOT.length > 0)
				LOOT1 = LOOT[0];
			final GroundItem[] whip = GroundItems.getNearest(preloot);
			GroundItem whip1 = null;
			if (whip.length > 0)
				whip1 = whip[0];
			final GroundItem[] torso = GroundItems.getNearest(preloot1);
			GroundItem torso1 = null;
			if (torso.length > 0)
				torso1 = torso[0];
			final GroundItem[] dbones = GroundItems.getNearest(preloot2);
			GroundItem dbones1 = null;
			if (dbones.length > 0)
				dbones1 = dbones[0];
			return looting == true && LOOT1 != null
					&& !Players.getLocal().isInCombat() || whip1 != null
					&& !Players.getLocal().isInCombat() || torso1 != null
					&& !Players.getLocal().isInCombat() || dbones1 != null
					&& !Players.getLocal().isInCombat();
		}

		@Override
		public void execute() {
			status = "Looting...";
			final GroundItem[] LOOT = GroundItems.getNearest(loot);
			GroundItem LOOT1 = null;
			if (LOOT.length > 0)
				LOOT1 = LOOT[0];
			final GroundItem[] whip = GroundItems.getNearest(preloot);
			GroundItem whip1 = null;
			if (whip.length > 0)
				whip1 = whip[0];
			final GroundItem[] torso = GroundItems.getNearest(preloot1);
			GroundItem torso1 = null;
			if (torso.length > 0)
				torso1 = torso[0];
			final GroundItem[] dbones = GroundItems.getNearest(preloot2);
			GroundItem dbones1 = null;
			if (dbones.length > 0)
				dbones1 = dbones[0];
			if (LOOT1 != null && !Players.getLocal().isInCombat()) {
				Camera.turnTo(LOOT1);
				Time.sleep(500);
				// LOOT1.interact("Take");
				Time.sleep(1000);
			}
			if (whip1 != null && !Players.getLocal().isInCombat()) {
				lootname = "Abyssal whip";
				Camera.turnTo(whip1);
				Time.sleep(500);
				// whip1.interact("Take "+ lootname);
				Time.sleep(1000);
			}
			if (torso1 != null && !Players.getLocal().isInCombat()) {
				lootname = "Fighter torso";
				Camera.turnTo(torso1);
				Time.sleep(500);
				// torso1.interact("Take "+ lootname);
				Time.sleep(1000);
			}
			if (dbones1 != null && !Players.getLocal().isInCombat()) {
				lootname = "Dragon bones";
				Camera.turnTo(dbones1);
				Time.sleep(500);
				// dbones1.interact("Take "+ lootname);
				Time.sleep(1000);
			}
		}
	}

	public class vial implements Strategy {

		@Override
		public boolean activate() {
			return Inventory.getCount(vial) >= 1;
		}

		@Override
		public void execute() {
			status = "Dropping Vials";
			if (Inventory.getCount(vial) >= 1) {
				for (final Item i : Inventory.getItems(vial)) {
					i.interact("Drop");
					Time.sleep(750);
				}
			}
		}
	}

	public class noMOB implements Strategy {

		@Override
		public boolean activate() {
			final Npc[] MOB = Npcs.getNearest(NPC_ARRAY);
			Npc MOB1 = null;
			if (MOB.length > 0)
				MOB1 = MOB[0];
			return MOB1 == null;
		}

		@Override
		public void execute() {
			status = "All Gone";
			Time.sleep(150);
		}
	}

	public class inCombat implements Strategy {

		@Override
		public boolean activate() {
			return Players.getLocal().isInCombat() && getPray() > 20
					&& getHP() > eatLVL;
		}

		@Override
		public void execute() {
			if (Players.getLocal().isInCombat() && getPray() > 20
					&& getHP() > eatLVL) {
				status = "In Combat";
				Time.sleep(1500);
			}
		}

	}

	public int[] convertStringArraytoIntArray(String[] sarray) throws Exception {

		if (sarray != null) {
			int intarray[] = new int[sarray.length];
			for (int i = 0; i < sarray.length; i++) {
				intarray[i] = Integer.parseInt(sarray[i]);
			}
			return intarray;
		}
		return null;
	}

	public String runTime(long i) {
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

	// START: Code generated using Enfilade's Easel
	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	public static final int getHP() {
		String hp = Interfaces.get(3918).getChildren()[11].getText();
		String dirty = hp.replace("@whi@", "");
		return Integer.parseInt(dirty);
	}

	public static final int getPray() {
		String pray = Interfaces.get(3946).getChildren()[9].getText();
		String dirty = pray.replace("@whi@", "");
		return Integer.parseInt(dirty);
	}

	private final Color color1 = new Color(255, 255, 255);

	Color Purple = new Color(128, 0, 128);
	Color LowPurple = new Color(224, 102, 255);
	Color Indigo = new Color(75, 0, 130);

	private final Font font1 = new Font("Times New Roman", 1, 10);
	private final Font font2 = new Font("Times New Roman", 1, 11);
	private final Font font3 = new Font("Times New Roman", 1, 12);
	private final Font font4 = new Font("Verdana", 1, 12);

	private final Image img1 = getImage("http://i.imgur.com/pZtsn9c.png");

	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		int x = Mouse.getInstance().getPoint().getLocation().x;
		int y = Mouse.getInstance().getPoint().getLocation().y;
		Point p = new Point(x, y);
		((Graphics2D) g).setRenderingHints(new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));

		Graphics2D spinG = (Graphics2D) g.create();
		Graphics2D spinGRev = (Graphics2D) g.create();
		Graphics2D g2d = (Graphics2D) g;
		spinGRev.setColor(Purple);
		spinGRev.rotate(System.currentTimeMillis() % 2000d / 2000d * (-360d)
				* 2 * Math.PI / 180.0, x, y);
		spinGRev.setStroke(new BasicStroke(1));
		spinGRev.drawLine(x - 8, y, x + 8, y);
		spinGRev.drawLine(x, y - 8, x, y + 8);
		spinG.setColor(LowPurple);
		spinG.rotate(System.currentTimeMillis() % 2000d / 2000d * (360d) * 2
				* Math.PI / 180.0, x, y);
		spinG.drawLine(x - 6, y, x + 6, y);
		spinG.drawLine(x, y - 6, x, y + 6);
		g2d.setColor(Indigo);
		g2d.drawOval(x - 10, y - 10, 20, 20);
		attackLVL = Skill.ATTACK.getLevel();
		strengthLVL = Skill.STRENGTH.getLevel();
		defenceLVL = Skill.DEFENSE.getLevel();
		rangeLVL = Skill.RANGE.getLevel();
		magicLVL = Skill.MAGIC.getLevel();
		CurrentEXP = Skill.ATTACK.getExperience()
				+ Skill.STRENGTH.getExperience()
				+ Skill.DEFENSE.getExperience() + Skill.RANGE.getExperience()
				+ Skill.MAGIC.getExperience();
		CurrentEXP -= StartEXP;
		g.drawImage(img1, 0, 317, null);
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("" + ATTACKING, 138, 436);
		g.setFont(font2);
		g.drawString("" + runTime(startTime), 92, 371);
		g.setFont(font3);
		g.drawString("" + status, 74, 392);
		g.drawString("" + CurrentEXP, 98, 414);
		g.setFont(font4);
		g.drawString("" + attackLVL, 335, 372);
		g.drawString("" + strengthLVL, 350, 386);
		g.drawString("" + defenceLVL, 346, 402);
		g.drawString("" + rangeLVL, 335, 417);
		g.drawString("" + magicLVL, 334, 433);
	}

	// END: Code generated using Enfilade's Easel
	public class Eat implements Strategy {

		@Override
		public boolean activate() {

			return Inventory.getCount(foodId) >= 1 && getHP() <= eatLVL
					&& eating == true;
		}

		@Override
		public void execute() {
			status = "Eating";
			final Npc[] MOB = Npcs.getNearest(NPC_ARRAY);
			Npc MOB1 = null;
			if (MOB.length > 0)
				MOB1 = MOB[0];
			if (Inventory.getCount(foodId) >= 1 && getHP() <= eatLVL) {
				if (!Tab.INVENTORY.isOpen()) {
					Tab.INVENTORY.open();
				} else if (Tab.INVENTORY.isOpen() && getHP() <= eatLVL) {
					for (final Item i : Inventory.getItems(foodId)) {
						if (getHP() <= eatLVL) {
							i.interact("Eat");
							Time.sleep(1200);
							getHP();
							if (MOB1 != null && Players.getLocal().isInCombat())
								Camera.turnTo(MOB1);
							MOB1.interact("Attack");
						}
					}
				}
			}
		}
	}

	public class prayup implements Strategy {

		@Override
		public boolean activate() {
			return getPray() <= 20 && Inventory.getCount(praypotions) > 0
					&& praying == true && potprayer == true;
		}

		@Override
		public void execute() {
			if (!Tab.INVENTORY.isOpen()) {
				Tab.open(Tab.INVENTORY);
			} else if (Tab.INVENTORY.isOpen()) {
				if (getPray() <= 20 && Inventory.getCount(praypotions) > 0) {
					status = "Potting Up";
					for (final Item i : Inventory.getItems(praypotions)) {
						if (getPray() <= 20) {
							i.interact("Drink");
							Time.sleep(1500);
							getPray();
						}
					}
				}

			}
		}
	}

	public class potup implements Strategy {

		@Override
		public boolean activate() {
			currentattackLVL = Skill.ATTACK.getLevel();
			return potattack == true || potstrength == true
					|| potdefence == true;
		}

		@Override
		public void execute() {
			if (getHP() > eatLVL && Players.getLocal().isInCombat()) {
				if (potattack == true && Players.getLocal().isInCombat()) {

				}
			}
		}
	}

	public class gui extends JFrame {
		public gui() {
			initComponents();
		}

		private void button1ActionPerformed(ActionEvent e) {
			final String[] s2 = npcid.getText().toLowerCase().split(",");
			NPC_ARRAY = new int[s2.length];
			try {
				NPC_ARRAY = convertStringArraytoIntArray(s2);
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			if (prayerbox.isSelected()) {
				potprayer = true;
			}
			if (pot.isSelected()) {
				praying = true;
			}
			if (eatfood.isSelected()) {
				eating = true;
				String chosen = foodbox.getSelectedItem().toString();
				if (chosen.equals("lobster")) {
					foodId = lobster;
				} else if (chosen.equals("shark")) {
					foodId = shark;
				}
				String chosen1 = health.getSelectedItem().toString();
				if (chosen1.equals("10 HP")) {
					eatLVL = ten;
				} else if (chosen1.equals("20 HP")) {
					eatLVL = twenty;
				} else if (chosen1.equals("35 HP")) {
					eatLVL = thirtyfive;
				} else if (chosen1.equals("50 HP")) {
					eatLVL = fifty;
				} else if (chosen1.equals("65 HP")) {
					eatLVL = sixtyfive;
				} else if (chosen1.equals("75 HP")) {
					eatLVL = seventyfive;
				}
			}
			if (whip.isSelected()) {
				eFighter.preloot = eFighter.whip;
			}
			if (checkBox1.isSelected()) {
				eFighter.preloot1 = eFighter.torso;
			}
			if (checkBox2.isSelected()) {
				eFighter.preloot2 = eFighter.dbones;
			}
			if (Looting.isSelected()) {
				looting = true;
				final String[] s3 = lootid.getText().toLowerCase().split(",");
				loot = new int[s3.length];
				try {
					loot = convertStringArraytoIntArray(s3);
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
			guiwait = false;
			g.dispose();
			LogArea.log("Start was clicked");
		}

		private void initComponents() {
			// JFormDesigner - Component initialization - DO NOT MODIFY
			// //GEN-BEGIN:initComponents
			// Generated using JFormDesigner Evaluation license - Ethan Brookman
			tabbedPane1 = new JTabbedPane();
			panel1 = new JPanel();
			label1 = new JLabel();
			label2 = new JLabel();
			npcid = new JTextField();
			label3 = new JLabel();
			panel2 = new JPanel();
			label4 = new JLabel();
			foodbox = new JComboBox<>();
			label5 = new JLabel();
			label6 = new JLabel();
			eatfood = new JCheckBox();
			health = new JComboBox<>();
			panel3 = new JPanel();
			label7 = new JLabel();
			Looting = new JCheckBox();
			label8 = new JLabel();
			lootid = new JTextField();
			label9 = new JLabel();
			label15 = new JLabel();
			whip = new JCheckBox();
			checkBox1 = new JCheckBox();
			checkBox2 = new JCheckBox();
			panel4 = new JPanel();
			label10 = new JLabel();
			attackbox = new JCheckBox();
			strenthbox = new JCheckBox();
			defencebox = new JCheckBox();
			overloadbox = new JCheckBox();
			prayerbox = new JCheckBox();
			label11 = new JLabel();
			attackat = new JTextField();
			strengthat = new JTextField();
			defenceat = new JTextField();
			overloadat = new JTextField();
			label12 = new JLabel();
			pot = new JCheckBox();
			label13 = new JLabel();
			label14 = new JLabel();
			button1 = new JButton();

			// ======== this ========
			setTitle("eFighter - The A.I.O Fighter");
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setMinimumSize(new Dimension(290, 300));
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			// ======== tabbedPane1 ========
			{
				tabbedPane1.setFont(new Font("Tahoma", Font.BOLD, 11));

				// ======== panel1 ========
				{

					// JFormDesigner evaluation mark
					panel1.setBorder(new javax.swing.border.CompoundBorder(
							new javax.swing.border.TitledBorder(
									new javax.swing.border.EmptyBorder(0, 0, 0,
											0), "Ethan's Scripting",
									javax.swing.border.TitledBorder.CENTER,
									javax.swing.border.TitledBorder.BOTTOM,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									java.awt.Color.red), panel1.getBorder()));
					panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
						public void propertyChange(
								java.beans.PropertyChangeEvent e) {
							if ("border".equals(e.getPropertyName()))
								throw new RuntimeException();
						}
					});

					panel1.setLayout(null);

					// ---- label1 ----
					label1.setText("Combat Options:");
					label1.setFont(new Font("Toledo", Font.BOLD, 16));
					panel1.add(label1);
					label1.setBounds(new Rectangle(new Point(60, 5), label1
							.getPreferredSize()));

					// ---- label2 ----
					label2.setText("NPC's ID's To Fight:");
					label2.setFont(new Font("Tahoma", Font.BOLD, 12));
					panel1.add(label2);
					label2.setBounds(75, 90, 120,
							label2.getPreferredSize().height);
					panel1.add(npcid);
					npcid.setBounds(30, 110, 200,
							npcid.getPreferredSize().height);

					// ---- label3 ----
					label3.setText("Ex: 1265,1234,4321");
					label3.setFont(label3.getFont().deriveFont(
							label3.getFont().getStyle() | Font.BOLD));
					panel1.add(label3);
					label3.setBounds(new Rectangle(new Point(75, 135), label3
							.getPreferredSize()));

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for (int i = 0; i < panel1.getComponentCount(); i++) {
							Rectangle bounds = panel1.getComponent(i)
									.getBounds();
							preferredSize.width = Math.max(bounds.x
									+ bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y
									+ bounds.height, preferredSize.height);
						}
						Insets insets = panel1.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel1.setMinimumSize(preferredSize);
						panel1.setPreferredSize(preferredSize);
					}
				}
				tabbedPane1.addTab("Combat", panel1);

				// ======== panel2 ========
				{
					panel2.setLayout(null);

					// ---- label4 ----
					label4.setText("Eating Options:");
					label4.setFont(new Font("Toledo", Font.BOLD, 16));
					panel2.add(label4);
					label4.setBounds(new Rectangle(new Point(70, 5), label4
							.getPreferredSize()));

					// ---- foodbox ----
					foodbox.setModel(new DefaultComboBoxModel<>(new String[] {
							"lobster", "shark" }));
					panel2.add(foodbox);
					foodbox.setBounds(100, 100, 60,
							foodbox.getPreferredSize().height);

					// ---- label5 ----
					label5.setText("Food Type:");
					label5.setFont(new Font("Tahoma", Font.BOLD, 11));
					panel2.add(label5);
					label5.setBounds(new Rectangle(new Point(100, 80), label5
							.getPreferredSize()));

					// ---- label6 ----
					label6.setText("HP To Eat:");
					label6.setFont(new Font("Tahoma", Font.BOLD, 11));
					panel2.add(label6);
					label6.setBounds(new Rectangle(new Point(100, 135), label6
							.getPreferredSize()));

					// ---- eatfood ----
					eatfood.setText("Eat Food?");
					panel2.add(eatfood);
					eatfood.setBounds(95, 35, 85,
							eatfood.getPreferredSize().height);

					// ---- health ----
					health.setModel(new DefaultComboBoxModel<>(new String[] {
							"10 HP", "20 HP", "35 HP", "50 HP", "65 HP",
							"75 HP" }));
					panel2.add(health);
					health.setBounds(100, 155, 60,
							health.getPreferredSize().height);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for (int i = 0; i < panel2.getComponentCount(); i++) {
							Rectangle bounds = panel2.getComponent(i)
									.getBounds();
							preferredSize.width = Math.max(bounds.x
									+ bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y
									+ bounds.height, preferredSize.height);
						}
						Insets insets = panel2.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel2.setMinimumSize(preferredSize);
						panel2.setPreferredSize(preferredSize);
					}
				}
				tabbedPane1.addTab("Eating", panel2);

				// ======== panel3 ========
				{
					panel3.setLayout(null);

					// ---- label7 ----
					label7.setText("Looting Section:");
					label7.setFont(new Font("Toledo", Font.BOLD, 16));
					label7.setEnabled(false);
					panel3.add(label7);
					label7.setBounds(new Rectangle(new Point(65, 5), label7
							.getPreferredSize()));

					// ---- Looting ----
					Looting.setText("Loot Items?");
					Looting.setEnabled(false);
					panel3.add(Looting);
					Looting.setBounds(new Rectangle(new Point(90, 30), Looting
							.getPreferredSize()));

					// ---- label8 ----
					label8.setText("Items To Loot:");
					label8.setFont(new Font("Tahoma", Font.BOLD, 11));
					label8.setEnabled(false);
					panel3.add(label8);
					label8.setBounds(90, 65, label8.getPreferredSize().width,
							15);

					// ---- lootid ----
					lootid.setEnabled(false);
					panel3.add(lootid);
					lootid.setBounds(55, 85, 155,
							lootid.getPreferredSize().height);

					// ---- label9 ----
					label9.setText("Ex: 995, 532, 111");
					label9.setFont(new Font("Tahoma", Font.BOLD, 11));
					label9.setEnabled(false);
					panel3.add(label9);
					label9.setBounds(new Rectangle(new Point(85, 110), label9
							.getPreferredSize()));

					// ---- label15 ----
					label15.setText("Pre-Loaded Items");
					label15.setFont(label15.getFont().deriveFont(
							label15.getFont().getStyle() | Font.BOLD));
					label15.setEnabled(false);
					panel3.add(label15);
					label15.setBounds(new Rectangle(new Point(80, 140), label15
							.getPreferredSize()));

					// ---- whip ----
					whip.setText("Abyssal Whip");
					whip.setEnabled(false);
					panel3.add(whip);
					whip.setBounds(10, 160, whip.getPreferredSize().width, 20);

					// ---- checkBox1 ----
					checkBox1.setText("Fighter Torso");
					checkBox1.setEnabled(false);
					panel3.add(checkBox1);
					checkBox1.setBounds(10, 180,
							checkBox1.getPreferredSize().width, 20);

					// ---- checkBox2 ----
					checkBox2.setText("Dragon Bones");
					checkBox2.setEnabled(false);
					panel3.add(checkBox2);
					checkBox2.setBounds(165, 160,
							checkBox2.getPreferredSize().width, 20);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for (int i = 0; i < panel3.getComponentCount(); i++) {
							Rectangle bounds = panel3.getComponent(i)
									.getBounds();
							preferredSize.width = Math.max(bounds.x
									+ bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y
									+ bounds.height, preferredSize.height);
						}
						Insets insets = panel3.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel3.setMinimumSize(preferredSize);
						panel3.setPreferredSize(preferredSize);
					}
				}
				tabbedPane1.addTab("Looting", panel3);

				// ======== panel4 ========
				{
					panel4.setLayout(null);

					// ---- label10 ----
					label10.setText("Potions Section:");
					label10.setFont(new Font("Toledo", Font.BOLD, 16));
					panel4.add(label10);
					label10.setBounds(new Rectangle(new Point(60, 5), label10
							.getPreferredSize()));

					// ---- attackbox ----
					attackbox.setText("Super Attack");
					attackbox.setEnabled(false);
					panel4.add(attackbox);
					attackbox.setBounds(15, 55, 100,
							attackbox.getPreferredSize().height);

					// ---- strenthbox ----
					strenthbox.setText("Super Strength");
					strenthbox.setEnabled(false);
					panel4.add(strenthbox);
					strenthbox.setBounds(new Rectangle(new Point(15, 80),
							strenthbox.getPreferredSize()));

					// ---- defencebox ----
					defencebox.setText("Super Defence");
					defencebox.setEnabled(false);
					panel4.add(defencebox);
					defencebox.setBounds(new Rectangle(new Point(15, 105),
							defencebox.getPreferredSize()));

					// ---- overloadbox ----
					overloadbox.setText("Overload's");
					overloadbox.setEnabled(false);
					panel4.add(overloadbox);
					overloadbox.setBounds(new Rectangle(new Point(15, 130),
							overloadbox.getPreferredSize()));

					// ---- prayerbox ----
					prayerbox.setText("Prayer Potion");
					panel4.add(prayerbox);
					prayerbox.setBounds(90, 150,
							prayerbox.getPreferredSize().width, 20);

					// ---- label11 ----
					label11.setText("Type:");
					label11.setFont(new Font("Tahoma", Font.BOLD, 11));
					panel4.add(label11);
					label11.setBounds(new Rectangle(new Point(35, 40), label11
							.getPreferredSize()));

					// ---- attackat ----
					attackat.setEnabled(false);
					panel4.add(attackat);
					attackat.setBounds(210, 55, 35,
							attackat.getPreferredSize().height);

					// ---- strengthat ----
					strengthat.setEnabled(false);
					panel4.add(strengthat);
					strengthat.setBounds(210, 80, 35,
							strengthat.getPreferredSize().height);

					// ---- defenceat ----
					defenceat.setEnabled(false);
					panel4.add(defenceat);
					defenceat.setBounds(210, 105, 35,
							defenceat.getPreferredSize().height);

					// ---- overloadat ----
					overloadat.setBackground(new Color(240, 240, 240));
					overloadat.setEnabled(false);
					panel4.add(overloadat);
					overloadat.setBounds(210, 130, 35, 20);

					// ---- label12 ----
					label12.setText("Pot At:");
					label12.setFont(new Font("Tahoma", Font.BOLD, 11));
					panel4.add(label12);
					label12.setBounds(210, 40, 40, 12);

					// ---- pot ----
					pot.setText("Use Pots?");
					panel4.add(pot);
					pot.setBounds(new Rectangle(new Point(100, 30), pot
							.getPreferredSize()));

					// ---- label13 ----
					label13.setText("If you wish to use Prayer Potions");
					label13.setFont(label13.getFont().deriveFont(
							label13.getFont().getStyle() | Font.BOLD));
					panel4.add(label13);
					label13.setBounds(40, 170, 185,
							label13.getPreferredSize().height);

					// ---- label14 ----
					label14.setText("The bot will detect when you're low on prayer");
					label14.setFont(label14.getFont().deriveFont(
							label14.getFont().getStyle() | Font.BOLD,
							label14.getFont().getSize() - 1f));
					panel4.add(label14);
					label14.setBounds(20, 185, 240, 14);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for (int i = 0; i < panel4.getComponentCount(); i++) {
							Rectangle bounds = panel4.getComponent(i)
									.getBounds();
							preferredSize.width = Math.max(bounds.x
									+ bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y
									+ bounds.height, preferredSize.height);
						}
						Insets insets = panel4.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel4.setMinimumSize(preferredSize);
						panel4.setPreferredSize(preferredSize);
					}
				}
				tabbedPane1.addTab("Potions", panel4);
			}
			contentPane.add(tabbedPane1);
			tabbedPane1.setBounds(0, 0, 275, 235);

			// ---- button1 ----
			button1.setText("Start Bot");
			button1.setFont(new Font("Tahoma", Font.BOLD, 12));
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					button1ActionPerformed(e);
				}
			});
			contentPane.add(button1);
			button1.setBounds(0, 230, 275, 35);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for (int i = 0; i < contentPane.getComponentCount(); i++) {
					Rectangle bounds = contentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width,
							preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height,
							preferredSize.height);
				}
				Insets insets = contentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				contentPane.setMinimumSize(preferredSize);
				contentPane.setPreferredSize(preferredSize);
			}
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization
			// //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY
		// //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - Ethan Brookman
		private JTabbedPane tabbedPane1;
		private JPanel panel1;
		private JLabel label1;
		private JLabel label2;
		private JTextField npcid;
		private JLabel label3;
		private JPanel panel2;
		private JLabel label4;
		private JComboBox<String> foodbox;
		private JLabel label5;
		private JLabel label6;
		private JCheckBox eatfood;
		private JComboBox<String> health;
		private JPanel panel3;
		private JLabel label7;
		private JCheckBox Looting;
		private JLabel label8;
		private JTextField lootid;
		private JLabel label9;
		private JLabel label15;
		private JCheckBox whip;
		private JCheckBox checkBox1;
		private JCheckBox checkBox2;
		private JPanel panel4;
		private JLabel label10;
		private JCheckBox attackbox;
		private JCheckBox strenthbox;
		private JCheckBox defencebox;
		private JCheckBox overloadbox;
		private JCheckBox prayerbox;
		private JLabel label11;
		private JTextField attackat;
		private JTextField strengthat;
		private JTextField defenceat;
		private JTextField overloadat;
		private JLabel label12;
		private JCheckBox pot;
		private JLabel label13;
		private JLabel label14;
		private JButton button1;
		// JFormDesigner - End of variables declaration //GEN-END:variables
	}

}