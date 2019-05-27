package main.floors;

import java.util.HashMap;

public class FloorManager {
	private static HashMap<String, Floor> allFloors = new HashMap<String, Floor>();
	private static Floor currentFloor;

	public static Floor getFloor() {
		return currentFloor;
	}

	public static void addFloor(Floor floor) {
		allFloors.put(floor.getName(), floor);
	}

	public static void setCurrentFloor(String name) {
		currentFloor = allFloors.get(name);
	}
}
