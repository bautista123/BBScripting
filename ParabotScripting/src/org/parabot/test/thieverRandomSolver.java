package org.parabot.test;

import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.defs.ItemDef;
import org.rev317.api.wrappers.hud.Interface;
import org.rev317.api.wrappers.interactive.Player;

public class thieverRandomSolver implements Strategy {
	// public static Area bank = new Area (new Tile(2587, 3418, 0) , new
	// Tile(2586, 3420, 0), new Tile(2587, 3422, 0), new Tile(2588, 3420, 0));
	// will fix this later.
	public static Area stallArea = new Area(2333, 3158, 2353, 3186);

	private String[] helmNames = { "helm", "mask", "hood", "coif", "bandana",
			"bandanna", "hat", "disguise", "boater", "mitre", "head",
			"earmuffs", "tiara", "eye patch", "cavalier", "menap", "headgear",
			"snelm", "cap", "goggles", "beret", "fez" };

	private String[] swordNames = { "sword", "scimitar", "scimy", "sword", "2h" };

	private String[] capeNames = { "cape", "cloak", "ava's",
			"diving apparatus", "bonesack", "grain" };

	private int randomType = 0;

	public void solveRandom(String[] toMatch, String[] names) {
		this.randomType = 0;

		int stage = 0;
		for (int p = 0; p < toMatch.length; p++) {
			for (int j = 0; j < this.helmNames.length; j++) {
				if (toMatch[p].toLowerCase().contains(this.helmNames[j])) {
					stage++;
					if (stage == 4) {
						this.randomType = 0;
						break;
					}
				}
			}
		}
		stage = 0;
		for (int p = 0; p < toMatch.length; p++) {
			for (int j = 0; j < this.capeNames.length; j++) {
				if (toMatch[p].toLowerCase().contains(this.capeNames[j])) {
					stage++;
					if (stage == 4) {
						this.randomType = 1;
						break;
					}
				}
			}
		}
		stage = 0;
		for (int p = 0; p < toMatch.length; p++) {
			for (int j = 0; j < this.swordNames.length; j++) {
				if (toMatch[p].toLowerCase().contains(this.swordNames[j])) {
					stage++;
					if (stage == 4) {
						this.randomType = 2;
						break;
					}
				}
			}
		}
		for (int i = 0; i < names.length; i++) {
			LogArea.log("item " + i + ": " + names[i]);
			LogArea.log("current random type: " + this.randomType);
			if (this.randomType == 0) {
				for (int e = 0; e < this.helmNames.length; e++) {
					if (names[i].toLowerCase().contains(this.helmNames[e])) {
						this.script.getClient().sendAction(1, 315, 444, 14,
								4550 + i);
						return;
					}
				}
			}
			if (this.randomType == 1) {
				for (int e = 0; e < this.capeNames.length; e++) {
					if (names[i].toLowerCase().contains(this.capeNames[e])) {
						this.script.getClient().sendAction(1, 315, 444, 14,
								4550 + i);
						return;
					}
				}
			}
			if (this.randomType == 2) {
				for (int e = 0; e < this.swordNames.length; e++) {
					if (names[i].toLowerCase().contains(this.swordNames[e])) {
						this.script.getClient().sendAction(1, 315, 444, 14,
								4550 + i);
						return;
					}
				}
			}
		}
	}

	@Override
	public boolean activate() {

		final Player me = Players.getLocal();
		return (Interfaces.getOpenInterfaceId() != -1)
				|| ((Interfaces.getOpenInterfaceId() == -1) && (!this.stallArea
						.contains(me.getLocation()))) || (me.isInCombat());

	}

	@Override
	public void execute() {

		final Player me = Players.getLocal();
		if (me.isInCombat()) {
			this.script.getClient().sendAction(1, 315, 13261696, 457, 164);
			Time.sleep(300);
			Keyboard.getInstance().sendKeys("::home");
		}
		if (Interfaces.getOpenInterfaceId() == 15944) {
			this.script.game.sendAction(1, 200, 71, 0, 15949);
		}
		if (Interfaces.getOpenInterfaceId() == 4543) {
			String randomItem1Name = Interfaces.getInterface(4553).getText();
			String randomItem2Name = Interfaces.getInterface(4554).getText();
			String randomItem3Name = Interfaces.getInterface(4555).getText();
			String randomItem4Name = Interfaces.getInterface(4556).getText();

			Interface model1 = Interfaces.getInterface(4550);
			Interface model2 = Interfaces.getInterface(4551);
			Interface model3 = Interfaces.getInterface(4552);

			String model1Name = ItemDef.get(model1.getMediaId()).getName();
			String model2Name = ItemDef.get(model2.getMediaId()).getName();
			String model3Name = ItemDef.get(model3.getMediaId()).getName();

			solveRandom(new String[] { randomItem1Name, randomItem2Name,
					randomItem3Name, randomItem4Name }, new String[] {
					model1Name, model2Name, model3Name });
		}
		if ((Interfaces.getOpenInterfaceId() == -1)
				&& (!this.stallArea.contains(me.getLocation()))) {
			this.script.getClient().sendAction(1, 646, 444, 283, 153);
			Keyboard.getInstance().sendKeys("::home");
			Time.sleep(300);
		}

	}
}
