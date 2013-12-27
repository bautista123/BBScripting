package org.parabot.bbherblore.data;

import java.util.ArrayList;

import org.rev317.api.wrappers.scene.Tile;

public class HerbloreConstants {
	public static Tile home = new Tile(3200, 3428);
	public static Tile home2 = new Tile(3183, 3431);
	public static final int ovl = 15333;
	public static final int avantoe = 261;
	public static final int supAtt = 145;
	public static final int supStr = 157;
	public static final int dwarfWeed = 267;
	public static final int supDef = 163;
	public static final int lanta = 2481;
	public static final int magePot = 3042;
	public static final int mudRunes = 9594;
	public static final int rangePot = 169;
	public static final int gSpikes = 12539;
	public static final int torstol = 269;
	public static final int xStr = 15313;
	public static final int xDef = 15317;
	public static final int xAtt = 15309;
	public static final int xMage = 15321;
	public static final int xRange = 15325;
	public static final int[] xMageSupplies = { magePot, mudRunes };
	public static final int[] xAttSupplies = { supAtt, avantoe };
	public static final int[] xStrSupplies = { supStr, dwarfWeed };
	public static final int[] xDefSupplies = { supDef, lanta };
	public static final int[] xRangeSupplies = { rangePot, gSpikes };
	public static final int[] OvlSupplies = { xDef, xStr, xAtt, xMage, xRange,
			torstol };
	public static final int[] xTsupplies = { magePot, avantoe, supAtt, supStr,
			dwarfWeed, supDef, lanta, magePot, mudRunes, rangePot, gSpikes,
			torstol };
	public static final int[] xT = { xRange, xAtt, xDef, xStr, xMage };
	public static final int[] primarys = { supAtt, supStr, supDef, rangePot,
			magePot };
	public static final int[] secondaries = { gSpikes, mudRunes, lanta,
			avantoe, dwarfWeed };
	public static ArrayList<Integer> unstackList = new ArrayList<Integer>();
	public static ArrayList<Integer> stackList = new ArrayList<Integer>();
}
