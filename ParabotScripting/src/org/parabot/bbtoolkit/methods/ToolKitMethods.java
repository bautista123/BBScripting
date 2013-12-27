package org.parabot.bbtoolkit.methods;

import org.parabot.bbtoolkit.data.ToolKitVar;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Game;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.hud.Item;
import org.rev317.api.wrappers.interactive.Player;
import org.rev317.api.wrappers.scene.SceneObject;

public class ToolKitMethods {

	// all banking types+bank failsafes
	public static void ClearInvy() {
		if (Inventory.getCount() != 0
				&& Inventory.getCount(ToolKitVar.getSpawnID()) < 1
				&& ToolKitVar.getSpawner()) {
			Mouse.getInstance().click(399, 298, true);
			Time.sleep(500);

		} else if (Inventory.isFull() && Bank.isOpen()
				&& ToolKitVar.getSpawner()) {
			Mouse.getInstance().click(399, 298, true);
			Time.sleep(500);

		} else if (Inventory.isEmpty() && Bank.isOpen()
				&& ToolKitVar.getSpawner()) {
			Keyboard.getInstance().sendKeys(
					"::item " + ToolKitVar.getSpawnID() + " 28");
			Time.sleep(600);
		}
	}

	// open the bank
	public static void OpenBank() {
		final SceneObject[] nearestBank = SceneObjects.getNearest(2213);
		final SceneObject bank = nearestBank[0];
		if (!Bank.isOpen() && Bank.getNearestBanks() != null
				&& ToolKitVar.getSpawner()) {
			LogArea.log("opening bank");
			bank.interact("Use-quickly");
			Time.sleep(500);
		}
	}

	// typer methods
	public static String[] multipleMessages() {
		return ToolKitVar.getMessage().split("/");
	}

	public static void typeMessage() {
		if (ToolKitVar.getTyper()) {
			if (ToolKitVar.getMessage().contains("/")) {
				String[] messageArray = multipleMessages();
				for (String s : messageArray) {
					Keyboard.getInstance().sendKeys(s);
					Time.sleep(ToolKitVar.getInterval());
				}
			} else if (!ToolKitVar.getMessage().contains("/")) {
				Keyboard.getInstance().sendKeys(ToolKitVar.getMessage());
				Time.sleep(ToolKitVar.getInterval());
			}

		}
	}

	// dropper code begins here
	public static void M1D1() {
		if (Inventory.getCount(ToolKitVar.getDroppedID()) >= 1) {
			for (final Item i : Inventory.getItems(ToolKitVar.getDroppedID())) {
				i.interact("Drop");
				Time.sleep(300);
			}
		}
	}

	public static void dropAll() {
		if (Inventory.getCount() == 28
				&& Inventory.getCount(ToolKitVar.getDroppedID()) == 28) {
			Keyboard.getInstance().sendKeys("::empty");
			Time.sleep(300);
		} else if (Inventory.getCount() == 28
				&& Inventory.getCount(ToolKitVar.getDroppedID()) >= 1) {
			for (final Item i : Inventory.getItems(ToolKitVar.getDroppedID())) {
				i.interact("Drop");
				Time.sleep(300);
			}
		}
	}

	public static boolean needM1D1() {
		final Player me = Players.getLocal();
		return Inventory.getCount(ToolKitVar.getDroppedID()) >= 1
				&& !ToolKitVar.getDropT() && ToolKitVar.getDropper()
				&& me.getAnimation() == -1 && !me.isWalking()
				&& Game.isLoggedIn();
	}

	public static boolean needDropAll() {
		final Player me = Players.getLocal();
		return Inventory.getCount(ToolKitVar.getDroppedID()) >= 1
				&& ToolKitVar.getDropT() && ToolKitVar.getDropper()
				&& Inventory.getCount() == 28 && me.getAnimation() == -1
				&& !me.isWalking() && Game.isLoggedIn();
	}

}
