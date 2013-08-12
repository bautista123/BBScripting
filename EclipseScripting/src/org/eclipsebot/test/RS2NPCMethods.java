package org.eclipsebot.test;

import java.util.ArrayList;

import org.demmonic.client.Current;
import org.demmonic.client.Main;
import org.demmonic.client.accessors.RS2NPC;
import org.demmonic.client.api.Area;
import org.demmonic.client.api.MethodSet;
import org.demmonic.client.wrappers.RS2NPCWrapper;
import org.demmonic.client.wrappers.RS2ObjectWrapper;

public class RS2NPCMethods extends MethodSet {

	public RS2NPCMethods(Current current) {
		super(current);
	}

	public RS2NPCWrapper getClosestRS2NPCNotUnderAttack(int... ids) {
		RS2NPCWrapper[] unsortedNPCs = getNPCsForIds(ids);
		int distance = Integer.MAX_VALUE;
		RS2NPCWrapper closestNPC = null;
		for (RS2NPCWrapper n : unsortedNPCs) {
			int xDistance = -1;
			int yDistance = -1;
			int playerX = getCurrent().game.getMyPlayer().getGlobalX();
			int playerY = getCurrent().game.getMyPlayer().getGlobalY();
			if (n.getGlobalX() >= playerX) {
				xDistance = (n.getGlobalX() - playerX);
			} else {
				xDistance = (playerX - n.getGlobalX());
			}
			if (n.getGlobalY() >= playerY) {
				yDistance = (n.getGlobalY() - playerY);
			} else {
				yDistance = (playerY - n.getGlobalY());
			}
			if ((xDistance + yDistance) < distance && !n.isUnderAttack()) {
				distance = (xDistance + yDistance);
				closestNPC = n;
			}
		}
		return closestNPC;
	}
	
	public RS2NPCWrapper getClosestRS2NPCNotUnderAttack(String... names) {
		RS2NPCWrapper[] unsortedNPCs = getNPCsForNames(names);
		int distance = Integer.MAX_VALUE;
		RS2NPCWrapper closestNPC = null;
		for (RS2NPCWrapper n : unsortedNPCs) {
			int xDistance = -1;
			int yDistance = -1;
			int playerX = getCurrent().game.getMyPlayer().getGlobalX();
			int playerY = getCurrent().game.getMyPlayer().getGlobalY();
			if (n.getGlobalX() >= playerX) {
				xDistance = (n.getGlobalX() - playerX);
			} else {
				xDistance = (playerX - n.getGlobalX());
			}
			if (n.getGlobalY() >= playerY) {
				yDistance = (n.getGlobalY() - playerY);
			} else {
				yDistance = (playerY - n.getGlobalY());
			}
			if ((xDistance + yDistance) < distance && !n.isUnderAttack()) {
				distance = (xDistance + yDistance);
				closestNPC = n;
			}
		}
		return closestNPC;
	}
	
	public RS2NPCWrapper getClosestRS2NPCForIds(int... ids) {
		RS2NPCWrapper[] unsortedNPCs = getNPCsForIds(ids);
		int distance = Integer.MAX_VALUE;
		RS2NPCWrapper closestNPC = null;
		for (RS2NPCWrapper n : unsortedNPCs) {
			int xDistance = -1;
			int yDistance = -1;
			int playerX = getCurrent().game.getMyPlayer().getGlobalX();
			int playerY = getCurrent().game.getMyPlayer().getGlobalY();
			if (n.getGlobalX() >= playerX) {
				xDistance = (n.getGlobalX() - playerX);
			} else {
				xDistance = (playerX - n.getGlobalX());
			}
			if (n.getGlobalY() >= playerY) {
				yDistance = (n.getGlobalY() - playerY);
			} else {
				yDistance = (playerY - n.getGlobalY());
			}
			if ((xDistance + yDistance) < distance) {
				distance = (xDistance + yDistance);
				closestNPC = n;
			}
		}
		return closestNPC;
	}
	
	public RS2NPCWrapper getClosestRS2NPCForIds(Area area, int... ids) {
		RS2NPCWrapper[] unsortedNPCs = getNPCsForIds(area, ids);
		int distance = Integer.MAX_VALUE;
		RS2NPCWrapper closestNPC = null;
		for (RS2NPCWrapper n : unsortedNPCs) {
			int xDistance = -1;
			int yDistance = -1;
			int playerX = getCurrent().game.getMyPlayer().getGlobalX();
			int playerY = getCurrent().game.getMyPlayer().getGlobalY();
			if (n.getGlobalX() >= playerX) {
				xDistance = (n.getGlobalX() - playerX);
			} else {
				xDistance = (playerX - n.getGlobalX());
			}
			if (n.getGlobalY() >= playerY) {
				yDistance = (n.getGlobalY() - playerY);
			} else {
				yDistance = (playerY - n.getGlobalY());
			}
			if ((xDistance + yDistance) < distance) {
				distance = (xDistance + yDistance);
				closestNPC = n;
			}
		}
		return closestNPC;
	}
	
	public RS2NPCWrapper getClosestRS2NPCForNames(String... names) {
		RS2NPCWrapper[] unsortedNPCs = getNPCsForNames(names);
		int distance = Integer.MAX_VALUE;
		RS2NPCWrapper closestNPC = null;
		for (RS2NPCWrapper n : unsortedNPCs) {
			int xDistance = -1;
			int yDistance = -1;
			int playerX = getCurrent().game.getMyPlayer().getGlobalX();
			int playerY = getCurrent().game.getMyPlayer().getGlobalY();
			if (n.getGlobalX() >= playerX) {
				xDistance = (n.getGlobalX() - playerX);
			} else {
				xDistance = (playerX - n.getGlobalX());
			}
			if (n.getGlobalY() >= playerY) {
				yDistance = (n.getGlobalY() - playerY);
			} else {
				yDistance = (playerY - n.getGlobalY());
			}
			if ((xDistance + yDistance) < distance) {
				distance = (xDistance + yDistance);
				closestNPC = n;
			}
		}
		return closestNPC;
	}
	
	public RS2NPCWrapper getClosestRS2NPCForNames(Area area, String... names) {
		RS2NPCWrapper[] unsortedNPCs = getNPCsForNames(area, names);
		int distance = Integer.MAX_VALUE;
		RS2NPCWrapper closestNPC = null;
		for (RS2NPCWrapper n : unsortedNPCs) {
			int xDistance = -1;
			int yDistance = -1;
			int playerX = getCurrent().game.getMyPlayer().getGlobalX();
			int playerY = getCurrent().game.getMyPlayer().getGlobalY();
			if (n.getGlobalX() >= playerX) {
				xDistance = (n.getGlobalX() - playerX);
			} else {
				xDistance = (playerX - n.getGlobalX());
			}
			if (n.getGlobalY() >= playerY) {
				yDistance = (n.getGlobalY() - playerY);
			} else {
				yDistance = (playerY - n.getGlobalY());
			}
			if ((xDistance + yDistance) < distance) {
				distance = (xDistance + yDistance);
				closestNPC = n;
			}
		}
		return closestNPC;
	}
	
	public RS2NPCWrapper[] getNPCs() {
		ArrayList<RS2NPCWrapper> npcList = new ArrayList<RS2NPCWrapper>();
		if (super.getCurrent().getClient().getPlayerArray() != null) {
			RS2NPC[] npcs = getCurrent().getClient().getNpcArray();
			for (int i = 0; i < npcs.length; i++) {
				if (npcs[i] != null) {
					npcList.add(new RS2NPCWrapper(super.getCurrent(), npcs[i], i));
				}
			}
		}
		return npcList.toArray(new RS2NPCWrapper[npcList.size()]);
	}
	
	public RS2NPCWrapper[] getNPCsForNames(String... names) {
		ArrayList<RS2NPCWrapper> npcList = new ArrayList<RS2NPCWrapper>();
		if (super.getCurrent().getClient().getPlayerArray() != null) {
			RS2NPC[] npcs = getCurrent().getClient().getNpcArray();
			for (int i = 0; i < npcs.length; i++) {
				if (npcs[i] != null) {
					for (String s : names) {
						if (npcs[i].getDesc().getName().equals(s)) {
							npcList.add(new RS2NPCWrapper(super.getCurrent(), npcs[i], i));
						}
					}
				}
			}
		}
		return npcList.toArray(new RS2NPCWrapper[npcList.size()]);
	}
	
	public RS2NPCWrapper[] getNPCsForNames(Area area, String... names) {
		ArrayList<RS2NPCWrapper> npcList = new ArrayList<RS2NPCWrapper>();
		if (super.getCurrent().getClient().getPlayerArray() != null) {
			RS2NPC[] npcs = getCurrent().getClient().getNpcArray();
			for (int i = 0; i < npcs.length; i++) {
				if (npcs[i] != null) {
					RS2NPCWrapper currentNPC = new RS2NPCWrapper(super.getCurrent(), npcs[i], i);
					if (area.contains(currentNPC)) {
						for (String s : names) {
							if (npcs[i].getDesc().getName().equals(s)) {
								npcList.add(currentNPC);
							}
						}
					}
				}
			}
		}
		return npcList.toArray(new RS2NPCWrapper[npcList.size()]);
	}
	
	public RS2NPCWrapper[] getNPCsForIds(int... ids) {
		ArrayList<RS2NPCWrapper> npcList = new ArrayList<RS2NPCWrapper>();
		if (super.getCurrent().getClient().getPlayerArray() != null) {
			RS2NPC[] npcs = getCurrent().getClient().getNpcArray();
			for (int i = 0; i < npcs.length; i++) {
				if (npcs[i] != null) {
					for (int j : ids) {
						if (npcs[i].getDesc().getId() == j) {
							npcList.add(new RS2NPCWrapper(super.getCurrent(), npcs[i], i));
						}
					}
				}
			}
		}
		return npcList.toArray(new RS2NPCWrapper[npcList.size()]);
	}
	
	public RS2NPCWrapper[] getNPCsForIds(Area area, int... ids) {
		ArrayList<RS2NPCWrapper> npcList = new ArrayList<RS2NPCWrapper>();
		if (super.getCurrent().getClient().getPlayerArray() != null) {
			RS2NPC[] npcs = getCurrent().getClient().getNpcArray();
			for (int i = 0; i < npcs.length; i++) {
				if (npcs[i] != null) {
					RS2NPCWrapper currentNPC = new RS2NPCWrapper(super.getCurrent(), npcs[i], i);
					if (area.contains(currentNPC)) {
						for (int j : ids) {
							if (npcs[i].getDesc().getId() == j) {
								npcList.add(currentNPC);
							}
						}
					}
				}
			}
		}
		return npcList.toArray(new RS2NPCWrapper[npcList.size()]);
	}
}
