package main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import main.floors.Floor;
import main.main.Globals;
import main.spritesheets.SpriteSheet;

public abstract class Entity {
	protected Floor floor;
	protected double x;
	protected double y;

	protected int direction;

	protected Texture image = Globals.assetManagerManager.getTexture("box");
	protected SpriteSheet spriteSheet;

	public Entity(Floor floor, int x, int y) {
		this.x = x;
		this.y = y;
		this.floor = floor;

		floor.getTile(x, y).addEntity(this);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public abstract void update();

	public void draw(SpriteBatch batch) {
		int drawX = (int) (x * Globals.TILE_SIZE);
		int drawY = (int) (y * Globals.TILE_SIZE);
		draw(batch, drawX, drawY);
	}

	public void draw(SpriteBatch batch, float drawX, float drawY) {
		TextureRegion textureRegion;

		if (spriteSheet != null) {
			textureRegion = spriteSheet.getCurrentFrame(x, y);
		} else {
			textureRegion = new TextureRegion(image);
		}
		batch.draw(new TextureRegion(textureRegion, 0, 0, 16, 16), drawX, drawY, (float) Globals.TILE_SIZE / 2,
				(float) Globals.TILE_SIZE / 2, Globals.TILE_SIZE, Globals.TILE_SIZE, 1, 1, -(direction + 3) * 90, true);
	}
}
