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
	private boolean isAlive;

	public Ant(Floor floor, int x, int y) {
		super(floor, x, y);
		image = new Texture("assets/sprites/sprites/ant.png");
		isAlive = true;
		direction = 0;
		health = 10;
		brain = new Brain(4, 4);
	}

	@Override
	public void update() {

		Tile TileAhead = getTileAhead();

		if (TileAhead.getTileType().isSolid()) {
			isAlive = false;
		}

		if (isAlive) {

			int[] inputValues = new int[brain.getInputNeurons().length];

			// We will need to work a couple things out in the future, this right now is
			// just for testing
			/*
			 * Index 0: Whether the tile ahead is a wall or not
			 */
		}
	}

	// Will return the tile ahead of the direction the ant is facing, defaults to
	// the tile it is on
	private Tile getTileAhead() {
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

	private void moveForward(Tile tileAhead) {
		x = tileAhead.getX();
		y = tileAhead.getY();
	}

	private void turnLeft() {
		direction = (direction - 1) % 4;
	}

	private void turnRight() {
		direction = (direction + 1) % 4;
	}
}
