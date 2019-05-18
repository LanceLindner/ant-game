package main.tiles;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.audio.AudioManager;
import main.audio.MusicContainer;
import main.entities.Entity;
import main.floors.Floor;

public class Tile {
	private Floor floor;
	private int x;
	private int y;
	private TileType tileType;

	private Entity entity;

	private Entity corpse;

	private MusicContainer musicContainer;

	private double visibility;

	public Tile(Floor floor, int x, int y, TileType tileType) {
		this.floor = floor;
		this.x = x;
		this.y = y;
		this.tileType = tileType;
		if (tileType != null) {
			if (tileType.getMusicName() != null) {
				Music music = AudioManager.loadMusic(tileType.getMusicName());
				musicContainer = new MusicContainer(music, x, y, tileType.getMusicType());
				AudioManager.streamMusic(musicContainer);
			}
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getResidue() {
		int id = floor.getTileType(x, y, Floor.RESIDUE_LAYER).getID();
		if (id < 71 && id > 64)
			return 1;
		else if (id < 86 && id > 71)
			return 1;
		else
			return 0;
	}

	public void setResidue(int type) {
		int id;
		switch (type) {
		case (1):
			id = (int) (Math.random() * 4 + 65);
			break;
		case (2):
			id = (int) (Math.random() * 4 + 71);
			break;
		default:
			id = 112;
		}

		floor.setTiledMapTile(x, y, Floor.RESIDUE_LAYER, id);
	}

	public void removeResidue() {
		setResidue(0);
	}

	public void setCorpse(Entity deadEntity) {
		corpse = deadEntity;
	}

	public boolean containsEntity() {
		return (entity != null);
	}

	public void addEntity(Entity newEntity) {
		entity = newEntity;
	}

	public void removeEntity() {
		entity = null;
	}

	public TileType getTileType() {
		return tileType;
	}

	public void update() {
		// commented out for now because our code scaffolding in Floor already updates
		// enemies once
		/*
		 * if (entity != null) { entity.update(); }
		 */
	}

	public void draw(SpriteBatch batch) {
		if (entity != null) {
			entity.draw(batch);
		}
	}

}
