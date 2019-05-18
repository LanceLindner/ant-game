package main.entities.ants;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import main.audio.AudioManager;
import main.audio.SoundType;
import main.entities.Entity;
import main.entities.brain.Brain;
import main.floors.Floor;
import main.main.AssetManagerManager;
import main.main.Globals;
import main.tiles.Tile;

//will probably be made abstract soon
public class Ant extends Entity {

	private Brain brain;
	private int health;
	private int direction;
	private boolean isAlive;
	private double antSpeed;

	private int numberOfInputNeurons = 4;
	private int numberOfHiddenNeurons = 4;
	private int numberOfOutputNeurons = 4;

	private double cooldown;

	public Ant(Floor floor, int x, int y, int direction) {
		super(floor, x, y);
		image = AssetManagerManager.getTexture("ant");
		isAlive = true;
		this.direction = direction;
		health = 10;

		floor.brightenArea(x, y);

		brain = new Brain(numberOfInputNeurons, numberOfHiddenNeurons, numberOfOutputNeurons);

		brain.addRandomAxons(25);
		antSpeed = 10;

		cooldown = 0;
	}

	@Override
	public void draw(SpriteBatch batch) {
		float drawX = (float) (x * Globals.TILE_SIZE);
		float drawY = (float) (y * Globals.TILE_SIZE);

		// drawY += 2 * Globals.TILE_SIZE;
		// System.out.println(direction);
		switch (direction) {
		case 0:

			drawY -= cooldown * Globals.TILE_SIZE;
			break;
		case 1:
			drawX -= cooldown * Globals.TILE_SIZE;
			break;
		case 2:
			drawY += cooldown * Globals.TILE_SIZE;
			break;
		case 3:
			drawX += cooldown * Globals.TILE_SIZE;
			break;
		default:
			break;

		}

		batch.draw(new TextureRegion(image, 0, 0, 16, 16), drawX, drawY, (float) Globals.TILE_SIZE / 2,
				(float) Globals.TILE_SIZE / 2, Globals.TILE_SIZE, Globals.TILE_SIZE, 1, 1, -(direction + 3) * 90, true);
	}

	@Override
	public void update() {
		if (cooldown <= 0 && isAlive) {
			Tile tileAhead = getTileAhead();

			int[] inputValues = new int[numberOfInputNeurons];
			inputValues[0] = 0;
			inputValues[1] = 0;
			inputValues[2] = 0;
			inputValues[3] = 0;

			if (isAlive) {

				// We will need to work a couple things out in the future, this right now is
				// just for testing

				/*
				 * Index 0: Whether the tile ahead is a wall
				 */

				// Will always be equal to 1 because otherwise the if statement wouldn't execute

				if (tileAhead.getTileType() == null || !tileAhead.getTileType().isSolid()) {
					inputValues[0] = 0;
				} else {
					inputValues[0] = 1;
				}

				if (tileAhead.containsEntity()) {
					inputValues[1] = 1;
				} else {
					inputValues[1] = 0;
				}

				brain.update(inputValues);

				int[] outputValues = brain.getOutput();

				if (outputValues[0] == 1) {
					turnRight();
				}
				if (outputValues[1] == 1) {
					turnLeft();
				}
				if (outputValues[2] == 1) {
					setResidue();
				}
				if (outputValues[3] == 1) {
					removeResidue();
				}
				moveForward();

			}
			cooldown = 1;
		}
		if (isAlive) {
			cooldown -= antSpeed * Globals.deltaTime;
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

	private void moveForward() {
		Tile tileAhead = getTileAhead();

		if (tileAhead.getTileType() != null) {
			if (tileAhead.getTileType().isSolid()) {
				isAlive = false;
				cooldown = 0;
				floor.getTile((int) x, (int) y).removeEntity();

				x = tileAhead.getX();
				y = tileAhead.getY();

				die();
				return;
			}
		}

		if (tileAhead.containsEntity()) {
			isAlive = false;
			cooldown = 0;
			floor.getTile((int) x, (int) y).removeEntity();

			x = tileAhead.getX();
			y = tileAhead.getY();

			die();
			return;
		}
		floor.getTile((int) x, (int) y).removeEntity();
		x = tileAhead.getX();
		y = tileAhead.getY();
		floor.getTile((int) x, (int) y).addEntity(this);
		floor.brightenArea((int) x, (int) y);
	}

	private void turnLeft() {
		direction = ((direction + 3) % 4);
		// moveForward();
	}

	private void turnRight() {
		direction = (direction + 1) % 4;
		// moveForward();
	}

	private void setResidue() {
		floor.getTile((int) x, (int) y).setResidue(1);
	}

	private void removeResidue() {
		floor.getTile((int) x, (int) y).removeResidue();
	}

	private void die() {
		Tile tileAhead = getTileAhead();
		tileAhead.removeEntity();
		tileAhead.setCorpse(this);
		AudioManager.playSound(SoundType.getSoundTypeById(1).getSound());
	}
}
