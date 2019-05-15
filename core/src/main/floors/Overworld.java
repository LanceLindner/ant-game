package main.floors;

import main.entities.TestEntity;

public class Overworld extends Floor {
	public Overworld() {
		super("overworld");
		for (int i = 0; i < 50; ++i) {
			entities.add(new TestEntity(0, 0));
		}
	}

}
