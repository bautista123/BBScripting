package org.eclipsebot.creativethiever.strategies;

import org.demmonic.client.accessors.RS2Interface;
import org.demmonic.client.api.Area;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.ui.ClientUI;
import org.eclipsebot.creativethiever.data.CreativeThieverVar;

public class CreativeThieverRandom extends Strategy {
	private Script script;

	public CreativeThieverRandom(Script script) {
		this.script = script;
	}

	public Area stallArea = new Area(2333, 3158, 2353, 3186);
	private String[] helmNames = { "helm", "mask", "hood", "coif", "bandana",
			"bandanna", "hat", "disguise", "boater", "mitre", "head",
			"earmuffs", "tiara", "eye patch", "cavalier", "menap", "headgear",
			"snelm", "cap", "goggles", "beret", "fez"

	};

	private String[] swordNames = { "sword", "scimitar", "scimy", "sword",
			"2h", };

	private String[] capeNames = { "cape", "cloak", "ava's",
			"diving apparatus", "bonesack", "grain"

	};

	private int randomType = 0;

	public boolean shouldExecute() {
		return script.game.getOpenInterfaceId() != -1
				|| script.game.getOpenInterfaceId() == -1
				&& !stallArea.contains(script.game.getMyPlayer())
				|| script.game.getMyPlayer().isUnderAttack();
	}

	public int execute() {
		if (script.game.getMyPlayer().isUnderAttack()) {
			CreativeThieverVar.setStatus("Solving Random.");
			script.getClient().sendAction(1, 315, 13261696, 457, 164);
			script.sleep(500);
			script.game.sendCommand("::home");
			CreativeThieverVar.setRandomsEvaded(CreativeThieverVar
					.getRandomsEvaded() + 1);
		}
		if (script.game.getOpenInterfaceId() == 15944) {
			script.game.sendAction(1, 200, 71, 0, 15949);
		}
		if (script.game.getOpenInterfaceId() == 4543) {
			String randomItem1Name = script.getClient().getInterfaceCache()[4553]
					.getMessage();
			String randomItem2Name = script.getClient().getInterfaceCache()[4554]
					.getMessage();
			String randomItem3Name = script.getClient().getInterfaceCache()[4555]
					.getMessage();
			String randomItem4Name = script.getClient().getInterfaceCache()[4556]
					.getMessage();

			RS2Interface model1 = script.getClient().getInterfaceCache()[4550];
			RS2Interface model2 = script.getClient().getInterfaceCache()[4551];
			RS2Interface model3 = script.getClient().getInterfaceCache()[4552];

			String model1Name = script.getClient()
					.getItemDefForId(model1.getModelId()).getName();
			String model2Name = script.getClient()
					.getItemDefForId(model2.getModelId()).getName();
			String model3Name = script.getClient()
					.getItemDefForId(model3.getModelId()).getName();

			solveRandom(new String[] { randomItem1Name, randomItem2Name,
					randomItem3Name, randomItem4Name }, new String[] {
					model1Name, model2Name, model3Name });
			CreativeThieverVar.setStatus("Solving Random.");

		}
		if (script.game.getOpenInterfaceId() == -1
				&& !stallArea.contains(script.game.getMyPlayer())) {
			script.getClient().sendAction(1, 646, 444, 283, 153);
			script.game.sendCommand("::home");
			script.sleep(300);
		}
		return 200;
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
						script.getClient()
								.sendAction(1, 315, 444, 14, 4550 + i);
						CreativeThieverVar.setRandomsEvaded(CreativeThieverVar
								.getRandomsEvaded() + 1);
						return;
					}
				}
			}
			if (randomType == 1) {
				for (int e = 0; e < capeNames.length; e++) {
					if (names[i].toLowerCase().contains(capeNames[e])) {
						script.getClient()
								.sendAction(1, 315, 444, 14, 4550 + i);
						CreativeThieverVar.setRandomsEvaded(CreativeThieverVar
								.getRandomsEvaded() + 1);
						return;
					}
				}
			}
			if (randomType == 2) {
				for (int e = 0; e < swordNames.length; e++) {
					if (names[i].toLowerCase().contains(swordNames[e])) {
						script.getClient()
								.sendAction(1, 315, 444, 14, 4550 + i);
						CreativeThieverVar.setRandomsEvaded(CreativeThieverVar
								.getRandomsEvaded() + 1);
						return;
					}
				}
			}
		}
	}

}
