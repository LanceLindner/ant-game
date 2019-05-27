package main.floors;

import java.util.HashMap;

public class FloorManager {
	private static HashMap<String, Floor> allFloors = new HashMap<String, Floor>();
	private Floor currentFloor;

	public void addFloor(Floor floor) {
		allFloors.put(floor.getName(), floor);
	}
}
