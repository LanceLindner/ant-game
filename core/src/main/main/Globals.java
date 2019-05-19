package main.main;

import AssetManagement.AssetManagerManager;

public class Globals {
	public static final int TILE_SIZE = 16;
	public static final int TILES_PER_WIDTH = 11;
	public static final int TILES_PER_HEIGHT = 11;
	public static final float DEFAULT_FONT_SCALE = 0.2f;

	public static int windowWidth;
	public static int windowHeight;
	public static int windowWidthInTilePixels;
	public static int windowHeightInTilePixels;

	public static double globalTime = 0;
	public static double deltaTime = 1;

	public static AssetManagerManager assetManagerManager = new AssetManagerManager();
}
