package org.parabot.csThiever.data;

import org.rev317.api.wrappers.scene.Area;
import org.rev317.api.wrappers.scene.Tile;

public class csThieverConstants {
	public static Object stalls[][] = {
			{ Integer.valueOf(4875), "Steal from" },
			{ Integer.valueOf(4878), "Steal from" },
			{ Integer.valueOf(4707), "Steal-from" },
			{ Integer.valueOf(2565), "Steal-from" },
			{ Integer.valueOf(4874), "Steal from" } };
	public static int welcomeInterface = 15944;
	public static int thieved = 0;
	public static int randomInterface = 4543;
	public static Area stallArea = new Area(new Tile(3189, 2329), new Tile(
			3209, 2330), new Tile(3208, 2299), new Tile(3189, 2302));
}
