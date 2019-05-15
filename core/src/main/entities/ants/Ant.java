package main.entities.ants;

import com.badlogic.gdx.graphics.Texture;

import main.entities.Entity;
import main.entities.brain.Brain;
import main.floors.Floor;
import main.tiles.Tile;

//will probably be made abstract soon
public class Ant extends Entity {

	private Brain brain;
	private int health;
	private int direction;

	public Ant(Floor floor, int x, int y) {
		super(floor, x, y);
		image = new Texture("assets/sprites/sprites/ant.png");
	}

	@Override
	public void update() {
		// todo: initizalize brain
		// int out[] = brain.getOutput();
	}

	// Will return the tile ahead of the direction the ant is facing, defaults to
	// the tile it is on
	private Tile getBlockAhead() {
		switch (direction) {
		case 0:
			return floor.getTile((int) x, (int) (y + 1));
		case 1:
			return floor.getTile((int) (x + 1), (int) y);
		case 2:
			return floor.getTile((int) x, (int) (y - 1));
		case 3:
			return floor.getTile((int) (x - 1), (int) y);
		default:
			return floor.getTile((int) x, (int) y);

		}
	}

	private void turnLeft() {
		direction = (direction - 1) % 4;
	}

	private void turnRight() {
		direction = (direction + 1) % 4;
	}
}
