package org.scripts.flax;

import java.util.ArrayList;

import org.demmonic.client.api.Area;
import org.demmonic.client.api.Tile;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.Strategy;
import org.demmonic.client.wrappers.RS2ObjectWrapper;

public class Picking extends Strategy {

	private Script script;
	
	public Picking(Script script) {
		this.script = script;
	}
	
	private Area flaxArea = new Area(2736, 3436, 2752, 3454);

	
	@Override
	public boolean shouldExecute() {
		return !script.inventory.isFull() && flaxArea.contains(script.game.getMyPlayer());
	}
	
	@Override
	public int execute() {
		ArrayList<RS2ObjectWrapper> flax = script.RS2Objects.getRS2ObjectsForIds(flaxArea, 2646);
		if (flax.isEmpty()) {
			return 100;
		}
		RS2ObjectWrapper toPick = flax.get(0);
		toPick.interact(900);
		return 500;
	}
}
