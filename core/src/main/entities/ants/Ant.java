package main.entities.ants;

import main.entities.Entity;

public class Ant extends Entity {

	private Brain brain;
	private int health;
	private int direction;

	public Ant(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
		int out[] = brain.getOutput();
	}

	private void turnLeft() {
		direction = (direction - 1) % 4;
	}

	private void turnRight() {
		direction = (direction + 1) % 4;
	}
}
