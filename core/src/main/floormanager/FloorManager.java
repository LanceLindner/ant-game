package main.floormanager;

import java.util.ArrayList;
import java.util.HashMap;

import main.floors.Floor;

public class FloorManager {
	private static HashMap<String, Floor> allFloors = new HashMap<String, Floor>();
	private static Floor currentFloor;

	private static ArrayList<FloorObserver> observers = new ArrayList<FloorObserver>();

	public static Floor getFloor() {
		return currentFloor;
	}

	public static void setCurrentFloor(String name) {
		currentFloor = allFloors.get(name);
		for (FloorObserver floorObserver : observers) {
			floorObserver.updateFloor(currentFloor);
		}
	}

	public static void addFloor(Floor floor) {
		allFloors.put(floor.getName(), floor);
	}

	public static void addObserver(FloorObserver floorObserver) {
		observers.add(floorObserver);
	}

	public static void updateCurrentFloor() {
		currentFloor.update();
	}
}
