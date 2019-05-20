package main.entities.ants;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.SpriteSheets.SpriteSheet;
import main.audio.AudioManager;
import main.audio.SoundType;
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
	private double antSpeed;

	private Tile[][] tilesWithinSight;
	private int numberOfInputNeurons = 4;
	private int numberOfHiddenNeurons = 4;
	private int numberOfOutputNeurons = 4;

	private double cooldown;

	public Ant(Floor floor, int x, int y, int direction) {
		super(floor, x, y);
		image = Globals.assetManagerManager.getTexture("ant");
		spriteSheet = new SpriteSheet("ant");

		this.direction = direction;

		isAlive = true;
		health = 10;
		antSpeed = 1;
		cooldown = 0;

		floor.brightenArea(x, y);

		brain = new Brain(numberOfInputNeurons, numberOfHiddenNeurons, numberOfOutputNeurons);

		brain.addRandomAxons(25);

		antSpeed = 3;

		tilesWithinSight = getTilesInSight();
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
		super.draw(batch, drawX, drawY, direction);
	}

	@Override
	public void update() {
		if (cooldown <= 0 && isAlive) {

			int[] inputValues = new int[numberOfInputNeurons];
			inputValues[0] = 0;
			inputValues[1] = 0;
			inputValues[2] = 0;
			inputValues[3] = 0;

			if (isAlive) {

				tilesWithinSight = getTilesInSight();

				// We will need to work a couple things out in the future, this right now is
				// just for testing

				/*
				 * Index 0: Whether the tile ahead is a wall
				 */

				// Will always be equal to 1 because otherwise the if statement wouldn't execute

				if (tilesWithinSight[2][1].getTileType() == null || !tilesWithinSight[2][1].getTileType().isSolid()) {
					inputValues[0] = 0;
				} else {
					inputValues[0] = 1;
				}

				if (tilesWithinSight[2][1].containsEntity()) {
					inputValues[1] = 1;
				} else {
					inputValues[1] = 0;
				}

				brain.update(inputValues);

				int[] outputValues = brain.getOutput();

				if (outputValues[0] == 1) {
					turnRight();
				} else if (outputValues[1] == 1) {
					turnLeft();
				}
				if (outputValues[2] == 1) {
					setResidue(1);
				}
				if (outputValues[3] == 1) {
					setResidue(2);
				}
				/*
				 * if (outputValues[4] == 1) { removeResidue(); }
				 */
				moveForward();
				tilesWithinSight = getTilesInSight();

			}
			if (isAlive) {
				cooldown = 1;
				tilesWithinSight = getTilesInSight();
			}
		}
		if (isAlive) {
			cooldown -= antSpeed * Globals.deltaTime;
		}
	}

	// Will return 2d array of tiles within the ant's sight depending on the
	// direction.
	private Tile[][] getTilesInSight() {

		Tile[][] tilesToReturn = new Tile[4][4];

		switch (direction) {
		case 0:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					tilesToReturn[i][j] = floor.getTile((int) (x + (j - 1)), (int) (y + (i - 1)));
					// System.out.println(j - 1);
				}
			}
			return tilesToReturn;
		case 1:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					tilesToReturn[i][j] = floor.getTile((int) (x + (i - 1)), (int) (y + (j - 1)));
					// System.out.println(i - 1);
				}
			}
			return tilesToReturn;
		case 2:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					tilesToReturn[i][j] = floor.getTile((int) (x + (-j + 1)), (int) (y + (-i + 1)));
					// System.out.println(j + 1);
				}
			}
			return tilesToReturn;
		case 3:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					tilesToReturn[i][j] = floor.getTile((int) (x + (-i + 1)), (int) (y + (-j + 1)));
					// System.out.println(i + 1);
				}
			}
			return tilesToReturn;
		default:
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					tilesToReturn[i][j] = floor.getTile((int) (x + (i - 1)), (int) (y + (j - 1)));
				}
			}
			return tilesToReturn;
		}
	}

	// The tile ahead is tilesWithinSight[2][1]
	private void moveForward() {
		if (tilesWithinSight[2][1].getTileType() != null) {
			if (tilesWithinSight[2][1].getTileType().isSolid()) {
				die();
				return;
			}
		}

		if (tilesWithinSight[2][1].containsEntity()) {
			die();
			return;
		}
		floor.getTile((int) x, (int) y).removeEntity();
		x = tilesWithinSight[2][1].getX();
		y = tilesWithinSight[2][1].getY();
		floor.getTile((int) x, (int) y).addEntity(this);
		floor.brightenArea((int) x, (int) y);
	}

	private void turnLeft() {
		direction = ((direction + 3) % 4);
		tilesWithinSight = getTilesInSight();
		// moveForward();
	}

	private void turnRight() {
		direction = (direction + 1) % 4;
		tilesWithinSight = getTilesInSight();
		// moveForward();
	}

	private void setResidue(int type) {
		floor.getTile((int) x, (int) y).setResidue(type);
	}

	private void removeResidue() {
		floor.getTile((int) x, (int) y).removeResidue();
	}

	private void die() {
		isAlive = false;
		cooldown = 0;
		Tile tile = tilesWithinSight[1][1];
		tile.removeEntity();
		tile.setCorpse(this);
		AudioManager.playSound(SoundType.getSoundTypeById(1).getSound(), x, y);
		spriteSheet.setToIdle();
	}
}
