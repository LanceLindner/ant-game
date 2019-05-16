package main.entities.ants;

import com.badlogic.gdx.graphics.Texture;

import main.entities.Entity;
import main.entities.brain.Brain;
import main.floors.Floor;
import main.main.Globals;
import main.tiles.Tile;

//will probably be made abstract soon
public class Ant extends Entity {

	private Brain brain;
	private int health;
	private int direction;
	private boolean isAlive;

	private int numberOfInputs;
	private int numberOfOutputs;

	private double cooldown;

	public Ant(Floor floor, int x, int y) {
		super(floor, x, y);
		image = new Texture("assets/sprites/sprites/ant.png");
		isAlive = true;
		direction = 0;
		health = 10;

		brain = new Brain(4, 4);
		brain.addAxon(0, 0);

		numberOfInputs = 4;
		numberOfOutputs = 4;

		cooldown = 0;
	}

	@Override
	public void update() {
		if (cooldown <= 0) {
			Tile tileAhead = getTileAhead();

			double[] inputValues = new double[numberOfInputs];
			inputValues[0] = 0;
			inputValues[1] = 0;
			inputValues[2] = 0;
			inputValues[3] = 0;

			if (isAlive) {

				// We will need to work a couple things out in the future, this right now is
				// just for testing

				/*
				 * Index 0: Whether the tile ahead is not a wall
				 */

				// Will always be equal to 1 because otherwise the if statement wouldn't execute

				if (tileAhead.getTileType() == null || !tileAhead.getTileType().isSolid()) {
					inputValues[0] = 1;
				} else {
					inputValues[0] = 0;
				}

				brain.update(inputValues);

				int[] outputValues = brain.getOutput();

				if (outputValues[0] == 1) {
					moveForward();
				} else {
					turnRight();
				}

			}
			cooldown = 0;
		}
		cooldown -= Globals.deltaTime;
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

	private void moveForward() {
		Tile tileAhead = getTileAhead();

		if (tileAhead.getTileType() != null) {
			if (tileAhead.getTileType().isSolid()) {
				isAlive = false;
				return;
			}
		}

		x = tileAhead.getX();
		y = tileAhead.getY();
	}

	private void turnLeft() {
		direction = ((direction + 3) % 4);
		// moveForward();
	}

	private void turnRight() {
		direction = (direction + 1) % 4;
		// moveForward();
	}
}
