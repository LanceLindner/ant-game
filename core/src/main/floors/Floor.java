package main.floors;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.Parameters;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import main.entities.Entity;
import main.entities.Player;
import main.floormanager.FloorManager;
import main.tiles.Tile;
import main.tiles.TileType;

public abstract class Floor {
	public static boolean debugLighting = false;

	public static final int SHADOW_LAYER = 4;
	public static final int RESIDUE_LAYER = 3;
	public static final int DECORATIVE_LAYER = 2;
	public static final int INTERACTABLE_LAYER = 1;
	public static final int FLOOR_LAYER = 0;

	private String name;
	private Tile[][] tiles;
	private Tile selectedTile = new Tile(this, 0, 0, null);
	private Entity selectedEntity;

	private TiledMap tiledMap;
	private AlteredOrthogonalTiledMapRenderer tiledMapRenderer;

	protected ArrayList<Entity> entities = new ArrayList<Entity>();

	private Player player;

	private boolean paused = false;

	public Floor(String floorFileName) {
		name = floorFileName;
		TmxMapLoader tmxMapLoader = new TmxMapLoader();
		Parameters paramaters = new TmxMapLoader.Parameters();
		paramaters.generateMipMaps = true;
		tiledMap = tmxMapLoader.load("assets/floors/" + floorFileName + ".tmx");
		tiledMapToTiles();
		tiledMapRenderer = new AlteredOrthogonalTiledMapRenderer(tiledMap);

		player = new Player(this, tiles.length / 2, tiles[0].length / 2);
		selectedEntity = player;

		FloorManager.addFloor(this);
	}

	private void tiledMapToTiles() {
		int numberOfHorizontalTiles = ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
		int numberOfVerticalTiles = ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();

		int id;
		if (debugLighting == false)
			id = 49;
		else
			id = 97;

		tiles = new Tile[numberOfHorizontalTiles][numberOfVerticalTiles];
		for (int i = 0; i < tiles.length; ++i) {
			for (int j = 0; j < tiles[0].length; ++j) {
				tiles[i][j] = new Tile(this, i, j, getTileType(i, j, INTERACTABLE_LAYER));
				setTiledMapTile(i, j, SHADOW_LAYER, id);
			}
		}
		brightenArea(tiles.length / 2, tiles[0].length / 2, 10);
	}

	public String getName() {
		return name;
	}

	public Player getPlayer() {
		return player;
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void setTiledMapTile(int x, int y, int layer, int id) {
		Cell cell = new Cell();
		cell.setTile(tiledMap.getTileSets().getTile(id));
		((TiledMapTileLayer) tiledMap.getLayers().get(layer)).setCell(x, y, cell);
	}

	public TileType getTileType(int x, int y, int layer) {
		Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(x, y);

		if (cell != null) {
			TiledMapTile tile = cell.getTile();

			if (tile != null) {
				int id = tile.getId();
				return TileType.getTileTypeById(id);
			}
		}
		return null;
	}

	public void selectTile(int x, int y) {
		selectedTile.setSelected(false);
		selectedTile = tiles[x][y];
		selectedTile.setSelected(true);
	}

	public void selectEntity(int x, int y) {
		if (tiles[x][y].getEntity() != null) {
			selectedEntity.setSelected(false);
			selectedEntity = tiles[x][y].getEntity();
			selectedEntity.setSelected(true);
		}
	}

	public void clearAllResidue() {
		for (int i = 0; i < tiles.length; ++i) {
			for (int j = 0; j < tiles[0].length; ++j) {
				tiles[i][j].removeResidue();
			}
		}
	}

	public void brightenArea(int x, int y) {
		brightenArea(x, y, 10);
	}

	public void brightenArea(int x, int y, int radius) {
		for (int i = -radius; i < radius; ++i) {
			for (int j = -radius; j < radius; ++j) {
				int sumOfLegsSquared = i * i + j * j;
				int radiusSquared = radius * radius;

				if (sumOfLegsSquared < radiusSquared) {
					int id = (int) (((1 - (double) sumOfLegsSquared / (double) radiusSquared)) * 6 + 50);
					if (isTileValid(x + i, y + j) == true) {
						TileType tileType = getTileType(x + i, y + j, SHADOW_LAYER);
						if (tileType.getID() < id)
							setTiledMapTile(x + i, y + j, SHADOW_LAYER, id);
					}
				}
			}
		}
	}

	public void render(OrthographicCamera camera, SpriteBatch batch) {
		update();
		player.updateCamera(camera);
		draw(camera, batch);
	}

	public void update() {
		if (paused == false) {
			int[] bounds = correctTileBounds(player.getVisibleTileBounds());

			for (int i = bounds[0]; i < bounds[1]; ++i) {
				for (int j = bounds[2]; j < bounds[3]; ++j) {
					tiles[i][j].update();
				}
			}
			for (Entity entity : entities) {
				entity.update();
			}
			player.update();
		}
	}

	public void draw(OrthographicCamera camera, SpriteBatch batch) {
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(new int[] { FLOOR_LAYER, INTERACTABLE_LAYER, DECORATIVE_LAYER, RESIDUE_LAYER });

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		int[] bounds = correctTileBounds(player.getVisibleTileBounds());

		for (int i = bounds[0]; i < bounds[1]; ++i) {
			for (int j = bounds[2]; j < bounds[3]; ++j) {
				tiles[i][j].draw(batch);
			}
		}
		selectedEntity.selectedDraw(batch, tiledMapRenderer);
		player.draw(batch);
		batch.end();

		tiledMapRenderer.render(new int[] { SHADOW_LAYER });
	}

	public boolean isTileValid(int x, int y) {
		if (x < 0 || x > tiles.length - 1 || y < 0 || y > tiles.length - 1)
			return false;
		return true;
	}

	private int[] correctTileBounds(int[] bounds) {
		if (bounds[0] < 0)
			bounds[0] = 0;
		if (bounds[1] > tiles.length - 1)
			bounds[1] = tiles.length - 1;
		if (bounds[2] < 0)
			bounds[2] = 0;
		if (bounds[3] > tiles.length - 1)
			bounds[3] = tiles.length - 1;
		return bounds;
	}

	public void dispose() {
		tiledMap.dispose();
		tiledMapRenderer.dispose();
	}
}
