package main.entities;

import main.floors.Floor;

public class TestEntity extends Entity {
	public TestEntity(Floor floor, int x, int y) {
		super(floor, x, y);
	}

	@Override
	public void update() {
		x += Math.random() * 1.0 - 0.5;
		y += Math.random() * 1.0 - 0.5;
	}
}
