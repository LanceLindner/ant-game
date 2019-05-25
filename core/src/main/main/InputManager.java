package main.main;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

import main.audio.AudioManager;

public class InputManager implements InputProcessor {
	private final static double maxZoom = 8.0;
	private final static double minZoom = 0.5;

	private static int[] mousePositionByPixel = new int[] { 0, 0 };
	private static int[] mousePositionByScreenSize = new int[] { 0, 0 };

	private static OrthographicCamera camera;

	public InputManager(OrthographicCamera theCamera) {
		camera = theCamera;
	}

	public int[] getMousePositionByPixel() {
		return mousePositionByPixel;
	}

	public int[] getMousePositionByScreenSize() {
		return mousePositionByScreenSize;
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
		setMousePosition(screenX, screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		zoom(amount);
		return false;
	}

	public static void setMousePosition(int screenX, int screenY) {
		mousePositionByPixel = new int[] { screenX, screenY };
		mousePositionByScreenSize = new int[] { screenX / Globals.windowWidth, screenY / Globals.windowHeight };
	}

	public static void zoom(double amount) {
		if (!(camera.zoom >= maxZoom && amount > 0) && !(camera.zoom <= minZoom && amount < 0)) {
			camera.zoom += amount * 0.3;
			// camera.zoom *= Math.pow(2, amount);
			AudioManager.cameraZoomed(camera.zoom, maxZoom);
		}
	}

	public static void setZoom(int zoom) {
		camera.zoom = zoom;
	}
}
