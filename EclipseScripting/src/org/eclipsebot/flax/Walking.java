package org.eclipsebot.flax;

import org.demmonic.client.api.Area;
import org.demmonic.client.api.Path;
import org.demmonic.client.api.Tile;
import org.demmonic.client.script.Script;
import org.demmonic.client.script.ScriptDetails;
import org.demmonic.client.script.Strategy;

public class Walking extends Strategy {

	private Script script;
	
	public Walking(Script script) {
		this.script = script;
	}

	private Tile flaxTile = new Tile(2741, 3442);
	private Tile bankPath[] = {
			new Tile(2728, 3450),
			new Tile(2725, 3490),
	};
	private Area flaxArea = new Area(2736, 3436, 2752, 3454);
	private Area bankArea = new Area(2721, 3486, 2730, 3494);
	
	@Override
	public boolean shouldExecute() {
		return !script.inventory.isFull() && !flaxArea.contains(script.game.getMyPlayer()) ||
				script.inventory.isFull() && !bankArea.contains(script.game.getMyPlayer());
	}
	
	@Override
	public int execute() {
		if (!script.inventory.isFull() && !flaxArea.contains(script.game.getMyPlayer())) {
			System.out.println("?");
			script.walking.walkTo(flaxTile);
		} else if (script.inventory.isFull() && !bankArea.contains(script.game.getMyPlayer())) {
			script.walking.walkPath(new Path(bankPath), false);
		}
		dynamicSleep(script.game.getMyPlayer().isWalking(), 3000);
		while (script.game.getMyPlayer().isWalking()) {
			script.sleep(50);
			if (!script.getClient().isLoggedIn()) {
				break;
			}
		}
		return 500;
	}
	
	public void dynamicSleep(boolean condition, long sleepTime) {
		long endTime = (System.currentTimeMillis() + sleepTime);
		while (!condition && (System.currentTimeMillis() < endTime)) {
			script.sleep(50);
		}
	}
}
