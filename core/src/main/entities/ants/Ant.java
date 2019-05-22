package main.entities.ants;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.audio.AudioManager;
import main.audio.SoundType;
import main.entities.Entity;
import main.entities.brain.Brain;
import main.floors.Floor;
import main.main.Globals;
import main.tiles.Tile;

public class Ant extends Entity {

	private Brain brain;
	private int health;
	private int direction;
	private boolean isAlive;
	private double antSpeed;

	private Tile[][] tilesWithinSight;
	private double cooldown;

	public Ant(Floor floor, int x, int y, int direction) {
		this(floor, x, y, direction, null);
	}

	public Ant(Floor floor, int x, int y, int direction, Brain brain) {
		super(floor, x, y);
		spriteSheet = new main.spritesheets.SpriteSheet("ant");

		this.direction = direction;

		isAlive = true;
		health = 10;
		antSpeed = 5;
		cooldown = 0;

		floor.brightenArea(x, y);

		tilesWithinSight = getTilesInSight();

		if (brain == null) {
			this.brain = new Brain();
			this.brain.addRandomAxons(50);
		} else {
			this.brain = brain;
		}
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
			int[] inputValues = new int[Globals.NUMBER_OF_INPUT_NEURONS];

			if (isAlive) {

				tilesWithinSight = getTilesInSight();

				/*
				 * There are 12 tiles, each with 4 values, which means there are 48 possible
				 * inputs.
				 */

				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 3; j++) {

						// Index 0 of each tile is whether the tile is solid or not
						if (tilesWithinSight[i][j].getTileType() == null
								|| !tilesWithinSight[i][j].getTileType().isSolid()) {
							inputValues[(i * 12) + (j * 4)] = 0;
						}

						// Index 1 of each tile is whether it contains an entity or not
						if (tilesWithinSight[i][j].containsEntity()) {
							inputValues[(i * 12) + (j * 4) + 1] = 1;
						}

						// Index 2 of each tile is whether the tile contains residue 1
						if (tilesWithinSight[i][j].getResidue() == 1) {
							inputValues[(i * 12) + (j * 4) + 2] = 1;
						}

						// Index 3 of each tile is whether the tile contains residue 2
						if (tilesWithinSight[i][j].getResidue() == 2) {
							inputValues[(i * 12) + (j * 4) + 3] = 1;
						}
					}
				}

				brain.update(inputValues);

				int[] outputValues = brain.getOutput();

				// Output values
				// ------------------------
				// Index 0: Remove Residue
				// Index 1: Set residue 1
				// Index 2: Set residue 2
				// Index 3: Do nothing
				// Index 4: Turn right
				// Index 5: Turn left

				// Additional information
				// ------------------------
				// The above list is in the order of which action is performed first
				// Removing a residue and setting a residue at the same time is redundant
				// Set residue 1 will override set residue 2
				// Do nothing will override turn right, which overrides turn left
				// You can set one residue and perform one movement option in one action

				// Remove residue
				if (outputValues[0] == 1) {
					removeResidue();
				}

				// Set residue tree
				if (outputValues[1] == 1) {
					setResidue(1);
				} else if (outputValues[2] == 1) {
					setResidue(2);
				}

				// Movement tree
				if (outputValues[3] == 1) {
					moveForward();
					cooldown = 1;
				} else {
					if (outputValues[4] == 1) {
						turnRight();
					} else if (outputValues[5] == 1) {
						turnLeft();
					}
					moveForward();
					cooldown = 1;
				}
			}
			tilesWithinSight = getTilesInSight();
		}
		if (isAlive && cooldown > 0) {
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
		tilesWithinSight[1][1].removeEntity();

		Tile tile = tilesWithinSight[2][1];
		tile.setCorpse(this);
		x = tile.getX();
		y = tile.getY();
		AudioManager.playSound(SoundType.getSoundTypeById(1).getSound(), x, y);
		spriteSheet.setToDie();
	}
}
