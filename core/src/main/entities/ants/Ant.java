package main.entities.ants;

public class Ant {

	private Brain brain;
	private int health;
	private int direction;

	public Ant() {

	}

	public void move() {
		int out[] = brain.getOutput();
	}

	private void turnLeft() {
		direction = (direction - 1) % 4;
	}

	private void turnRight() {
		direction = (direction + 1) % 4;
	}
}
