package main.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import main.main.Globals;

public class Player extends Entity {
	private double velocityMutliplier = 2;

	public Player(int x, int y) {
		super(x, y);
	}

	@Override
	public void move() {
		if (Gdx.input.isKeyPressed(Keys.W)) {
			y += velocityMutliplier * Globals.deltaTime;
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			x -= velocityMutliplier * Globals.deltaTime;
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			y -= velocityMutliplier * Globals.deltaTime;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			x += velocityMutliplier * Globals.deltaTime;
		}
	}
}
