package org.scripts;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.demmonic.client.Main;
import org.demmonic.client.accessors.Client;
import org.demmonic.client.accessors.ItemDef;
import org.demmonic.client.accessors.RS2Interface;
import org.demmonic.client.api.methods.RS2ObjectMethods;
import org.demmonic.client.listeners.Message;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2ObjectWrapper;

@ScriptDetails(name = "test", info = "lool")
public class Test extends Script {


	private int timesStolen = 0;
	private int stallId = 0;
	private int randomsSolved = 0;
	
	private String[] helmNames = {
			"helm",
			"mask",
			"hood",
			"coif",
			"bandana",
			"bandanna",
			"hat",
			"disguise",
			"boater",
			"mitre",
			"head",
			"earmuffs",
			"tiara",
			"eye patch",
	};
	
	private String[] swordNames = {
			"sword",
			"scimitar",
			"scimy",
			"sword",
			"2h",
	};
	
	private String[] capeNames = {
			"cape",
			"cloak",
			"ava's",
			"diving apparatus",
			"bonesack",
	};
	
	private int randomType = 0;
	
	public boolean firstLoop = true;
	
	public String username, password;
	
	@Override
	public int onLoop() {
		if (firstLoop) {
			username = JOptionPane.showInputDialog(null, "username");
			password = JOptionPane.showInputDialog(null, "password");
			stallId = Integer.parseInt(JOptionPane.showInputDialog(null, "Stall id"));
			firstLoop = false;
		}
		if (!getClient().isLoggedIn()) {
			sleep(5000);
			getClient().setUsername(username);
			getClient().setPassword(password);
			sleep(1000);
			getClient().setLoginState(2);
			sleep(1000);
			getClient().login(getClient().getUsername(), getClient().getPassword(), true);
			sleep(7000);
		}
		if (getClient().getOpenInterfaceId() == 4543) {
			sleep(500);
			String randomItem1Name = getClient().getInterfaceCache()[4553].getMessage();
			String randomItem2Name = getClient().getInterfaceCache()[4554].getMessage();
			String randomItem3Name = getClient().getInterfaceCache()[4555].getMessage();
			String randomItem4Name = getClient().getInterfaceCache()[4556].getMessage();
			
			RS2Interface model1 = getClient().getInterfaceCache()[4550];
			RS2Interface model2 = getClient().getInterfaceCache()[4551];
			RS2Interface model3 = getClient().getInterfaceCache()[4552];

			String model1Name = getClient().getItemDefForId(model1.getModelId()).getName();
			String model2Name = getClient().getItemDefForId(model2.getModelId()).getName();
			String model3Name = getClient().getItemDefForId(model3.getModelId()).getName();

			solveRandom(new String[] { randomItem1Name, randomItem2Name, randomItem3Name, randomItem4Name }, new String[] { model1Name, model2Name, model3Name });
			return 1000;
		}
		ArrayList<RS2ObjectWrapper> stall = RS2Objects.getRS2ObjectsForName("Crafting Stall");
		if (stall.isEmpty()) {
			return 100;
		}
		if (getClient().getMyPlayer().getAnimation() == -1) {
			getClient().sendAction(3, 900, stall.get(0).getUID(), stall.get(0).getLocalRegionX(), stall.get(0).getLocalRegionY());
			sleep(800);
		}
		if (Inventory.isFull()) {
			Inventory.dropAllExcept(996);
		}
		return 100;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(0, 0, 150, 75);
		g.setColor(new Color(Color.black.getRed(), Color.black.getGreen(), Color.black.getBlue(), 140));
		g.fillRect(1, 1, 149, 74);
		g.setColor(Color.red);
		g.drawString("demmonic's 1337 thiever", 3, 15);
		g.drawString("times stolen: " + timesStolen, 3, 28);
		g.drawString("randoms solved: " + randomsSolved, 3, 41);
	}

	@Override
	public void onMessage(Message message) {
		if (message.message.startsWith("You manage to steal")) {
			timesStolen++;
		}
	}
	
	public void solveRandom(String[] toMatch, String[] names) {
		randomType = 0;
		
		int stage = 0;
		for (int p = 0; p < toMatch.length; p++) {
			for (int j = 0; j < helmNames.length; j++) {
				if (toMatch[p].toLowerCase().contains(helmNames[j])) {
					stage++;
					if (stage == 4) {
						randomType = 0;
						break;
					}
				}
			}
		}
		stage = 0;
		
		for (int p = 0; p < toMatch.length; p++) {
			for (int j = 0; j < capeNames.length; j++) {
				if (toMatch[p].toLowerCase().contains(capeNames[j])) {
					stage++;
					if (stage == 4) {
						randomType = 1;
						break;
					}
				}
			}
		}
		stage = 0;
		
		for (int p = 0; p < toMatch.length; p++) {
			for (int j = 0; j < swordNames.length; j++) {
				if (toMatch[p].toLowerCase().contains(swordNames[j])) {
					stage++;
					if (stage == 4) {
						randomType = 2;
						break;
					}
				}
			}
		}
		
		
		for (int i = 0; i < names.length; i++) {
			ClientUI.pushMessage("item " + i + ": " + names[i]);
			ClientUI.pushMessage("current random type: " + randomType);
			if (randomType == 0) {
				for (int e = 0; e < helmNames.length; e++) {
					if (names[i].toLowerCase().contains(helmNames[e])) {
						getClient().sendAction(1, 315, 444, 14, 4550 + i);
						randomsSolved++;
						return;
					}
				}
			}
			if (randomType == 1) {
				for (int e = 0; e < capeNames.length; e++) {
					if (names[i].toLowerCase().contains(capeNames[e])) {
						getClient().sendAction(1, 315, 444, 14, 4550 + i);
						randomsSolved++;
						return;
					}
				}
			}
			if (randomType == 2) {
				for (int e = 0; e < swordNames.length; e++) {
					if (names[i].toLowerCase().contains(swordNames[e])) {
						getClient().sendAction(1, 315, 444, 14, 4550 + i);
						randomsSolved++;
						return;
					}
				}
			}
		}
	}
}
