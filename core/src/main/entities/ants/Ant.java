package main.entities.ants;

import com.badlogic.gdx.graphics.Texture;

import main.entities.Entity;
import main.entities.brain.Brain;

//will probably be made abstract soon
public class Ant extends Entity {

	private Brain brain;
	private int health;
	private int direction;

	public Ant(int x, int y) {
		super(x, y);
		image = new Texture("assets/sprites/sprites/ant.png");
	}

	@Override
	public void update() {
		// todo: initizalize brain
		// int out[] = brain.getOutput();
	}

	private void turnLeft() {
		direction = (direction - 1) % 4;
	}

	private void turnRight() {
		direction = (direction + 1) % 4;
	}
}
