package test.dotgame;

import java.util.ArrayList;

public class DotCom {

	static ArrayList<String> locationCells;
	static boolean alive = true;
	static int numberOfHits = 0;
	private String name;

	void setLocationCells(ArrayList<String> cellLocations) {
		if (cellLocations.size() > 2) {
			locationCells = cellLocations;
			System.out.println("Array set.");
		}
	}

	void setName(String i) {
		name = i;
	}

	ArrayList<String> getLocationCells() {
		return locationCells;
	}

	public String checkYourself(String userInput) {
		String result = "miss";
		int index = locationCells.indexOf(userInput);
		if (index >= 0) {
			locationCells.remove(index);
			if (locationCells.isEmpty()) {
				result = "kill";
			} else {
				result = "hit";
			}
		}
		return result;
	}

}