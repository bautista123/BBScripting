package org.eclipsebot.test.thiever;

import org.demmonic.client.accessors.RS2Interface;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.demmonic.client.wrappers.RS2ObjectWrapper;

public class StealingStrategy extends Strategy {

	private Script script;
	
	public StealingStrategy(Script script) {
		this.script = script;
	}
	
	private int randomType = 0;
	
	public boolean firstLoop = true;
	
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
			"snelm",
			"cap",
			"goggle",
			"beret",
	};
	
	private String[] swordNames = {
			"sword",
			"scimitar",
			"scimy",
			"sword",
			"2h",
	};
	
	private String[] capeNames = {
			"grain",
			"cape",
			"cloak",
			"ava's",
			"diving apparatus",
			"bonesack",
	};
	
	@Override
	public int execute() {
		if (script.getClient().getOpenInterfaceId() == 4543) {
			script.sleep(500);
			String randomItem1Name = script.getClient().getInterfaceCache()[4553].getMessage();
			String randomItem2Name = script.getClient().getInterfaceCache()[4554].getMessage();
			String randomItem3Name = script.getClient().getInterfaceCache()[4555].getMessage();
			String randomItem4Name = script.getClient().getInterfaceCache()[4556].getMessage();
			
			RS2Interface model1 = script.getClient().getInterfaceCache()[4550];
			RS2Interface model2 = script.getClient().getInterfaceCache()[4551];
			RS2Interface model3 = script.getClient().getInterfaceCache()[4552];

			String model1Name = script.getClient().getItemDefForId(model1.getModelId()).getName();
			String model2Name = script.getClient().getItemDefForId(model2.getModelId()).getName();
			String model3Name = script.getClient().getItemDefForId(model3.getModelId()).getName();

			solveRandom(new String[] { randomItem1Name, randomItem2Name, randomItem3Name, randomItem4Name }, new String[] { model1Name, model2Name, model3Name });
			return 1000;
		}
		RS2ObjectWrapper[] stall = script.RS2Objects.getRS2ObjectsForIds(4874);
		if (stall == null) {
			return 100;
		}
		if (script.getClient().getMyPlayer().getAnimation() == -1) {
			script.getClient().sendAction(3, 900, stall[0].getUID(), stall[0].getLocalX(), stall[0].getLocalY());
			script.sleep(800);
		}
		if (script.inventory.isFull()) {
			script.inventory.dropAllExcept(996);
		}
		return 100;
	}

	@Override
	public boolean shouldExecute() {
		return true;
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
						script.getClient().sendAction(1, 315, 444, 14, 4550 + i);
						return;
					}
				}
			}
			if (randomType == 1) {
				for (int e = 0; e < capeNames.length; e++) {
					if (names[i].toLowerCase().contains(capeNames[e])) {
						script.getClient().sendAction(1, 315, 444, 14, 4550 + i);
						return;
					}
				}
			}
			if (randomType == 2) {
				for (int e = 0; e < swordNames.length; e++) {
					if (names[i].toLowerCase().contains(swordNames[e])) {
						script.getClient().sendAction(1, 315, 444, 14, 4550 + i);
						return;
					}
				}
			}
		}
	}
}
