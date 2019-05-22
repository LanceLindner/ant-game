package main.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

import main.audio.AudioManager;

public class InputManager implements InputProcessor {
	private static double maxZoom = 8.0;
	private static double minZoom = 0.25;

	private static OrthographicCamera camera;

	public InputManager(OrthographicCamera theCamera) {
		camera = theCamera;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (!(camera.zoom >= maxZoom && amount > 0) && !(camera.zoom <= minZoom && amount < 0)) {
			camera.zoom += amount * 0.3;
			// camera.zoom *= Math.pow(2, amount);
			AudioManager.cameraZoomed(camera.zoom, maxZoom);
		}
		return false;
	}

}
