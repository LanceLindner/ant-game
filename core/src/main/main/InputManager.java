package main.main;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

import main.audio.AudioManager;
import main.floors.Floor;

public class InputManager implements InputProcessor {
	private final static double maxZoom = 8.0;
	private final static double minZoom = 0.5;

	private static int[] mousePositionByPixel = new int[] { 0, 0 };
	private static double[] mousePositionByScreenSize = new double[] { 0, 0 };

	private static OrthographicCamera camera;
	private static Floor floor;

	public InputManager(OrthographicCamera theCamera, Floor theFloor) {
		camera = theCamera;
		floor = theFloor;
	}

	public int[] getMousePositionByPixel() {
		return mousePositionByPixel;
	}

	public double[] getMousePositionByScreenSize() {
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
		setMousePosition(screenX, screenY);
		// make math accurate later
		int x = (int) (mousePositionByScreenSize[0] * Globals.TILES_PER_WIDTH + floor.getPlayer().getX()
				- Globals.TILES_PER_WIDTH / 2);
		int y = (int) (mousePositionByScreenSize[1] * Globals.TILES_PER_HEIGHT + floor.getPlayer().getY()
				- Globals.TILES_PER_HEIGHT / 2);
		floor.selectTile(x, y);
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
		mousePositionByPixel = new int[] { screenX, Globals.windowHeight - screenY };
		mousePositionByScreenSize = new double[] { (double) screenX / Globals.windowWidth,
				(double) (Globals.windowHeight - screenY) / Globals.windowHeight };
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
