package org.parabot.bbtoolkit.core;

import java.util.ArrayList;

import org.parabot.bbtoolkit.data.ToolKitVar;
import org.parabot.bbtoolkit.gui.ToolKitMainGUI;
import org.parabot.bbtoolkit.strategies.ToolKitClicker;
import org.parabot.bbtoolkit.strategies.ToolKitDropper;
import org.parabot.bbtoolkit.strategies.ToolKitOpen;
import org.parabot.bbtoolkit.strategies.ToolKitSpawner;
import org.parabot.bbtoolkit.strategies.ToolKitTyper;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;

@ScriptManifest(author = "Bautista123", category = Category.OTHER, description = "Tool to help you in RSPS", name = "bbToolKit", servers = { "DeviousPK & RecklessPK" }, version = 0.4)
public final class ToolKitCore extends Script {
	public static ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	public boolean onExecute() {
		ToolKitVar.setClicker(false);
		ToolKitVar.setDropper(false);
		ToolKitVar.setSpawner(false);
		ToolKitVar.setTyper(false);
		LogArea.log("bbToolKit has started.");
		ToolKitVar.setGuiWait(true);
		ToolKitMainGUI gui = new ToolKitMainGUI();
		gui.setVisible(true);
		while (ToolKitVar.getGuiWait()) {
			sleep(50);
		}
		strategies.add(new ToolKitOpen());
		strategies.add(new ToolKitSpawner());
		strategies.add(new ToolKitDropper());
		strategies.add(new ToolKitTyper());
		strategies.add(new ToolKitClicker());
		provide(strategies);
		return true;
	}

	public void onFinish() {
		LogArea.log("bbToolKit has ended");
	}

}