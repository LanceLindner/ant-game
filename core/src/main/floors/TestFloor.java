package main.floors;

import main.entities.TestEntity;

public class TestFloor extends Floor {
	public TestFloor() {
		super("test3");
		for (int i = 0; i < 50; ++i) {
			entities.add(new TestEntity(0, 0));
		}
	}
}
