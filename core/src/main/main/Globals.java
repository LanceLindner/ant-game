package main.main;

import AssetManagement.AssetManagerManager;

public class Globals {
	public static final int TILE_SIZE = 16;
	public static final int TILES_PER_WIDTH = 11;
	public static final int TILES_PER_HEIGHT = 11;
	public static final float DEFAULT_FONT_SCALE = 0.2f;

	public static final int NUMBER_OF_QUALITIES_PER_TILE = 4;
	public static final int NUMBER_OF_VISIBLE_TILES_X = 3;
	public static final int NUMBER_OF_VISIBLE_TILES_Y = 4;
	public static final int NUMBER_OF_VISIBLE_TILES = NUMBER_OF_VISIBLE_TILES_X * NUMBER_OF_VISIBLE_TILES_Y;
	public static final int NUMBER_OF_INPUT_NEURONS = NUMBER_OF_QUALITIES_PER_TILE * NUMBER_OF_VISIBLE_TILES;
	public static final int NUMBER_OF_HIDDEN_NEURONS = 48;
	public static final int NUMBER_OF_OUTPUT_NEURONS = 6;

	public static int windowWidth;
	public static int windowHeight;
	public static int windowWidthInTilePixels;
	public static int windowHeightInTilePixels;

	public static double globalTime = 0;
	public static double deltaTime = 1;

	public static AssetManagerManager assetManagerManager = new AssetManagerManager();
}
