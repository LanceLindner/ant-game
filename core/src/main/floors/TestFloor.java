package main.floors;

import main.entities.TestEntity;

public class TestFloor extends Floor {
	public TestFloor() {
		super("test4");
		for (int i = 0; i < 50; ++i) {
			entities.add(new TestEntity(this, 0, 0));
		}
	}
}
