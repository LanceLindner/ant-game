package main.floors;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import main.entities.Entity;
import main.entities.Player;
import main.tiles.Tile;
import main.tiles.TileType;

public abstract class Floor {
	public static boolean debugLighting = false;

	public static final int SHADOW_LAYER = 4;
	public static final int RESIDUE_LAYER = 3;
	public static final int DECORATIVE_LAYER = 2;
	public static final int INTERACTABLE_LAYER = 1;
	public static final int FLOOR_LAYER = 0;

	private Tile[][] tiles;

	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tiledMapRenderer;

	protected ArrayList<Entity> entities = new ArrayList<Entity>();

	private Player player;

	public Floor(String floorFileName) {
		tiledMap = new TmxMapLoader().load("assets/floors/" + floorFileName + ".tmx");
		tiledMapToTiles();
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		player = new Player(this, tiles.length / 2, tiles[0].length / 2);
	}

	private void tiledMapToTiles() {
		int numberOfHorizontalTiles = ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
		int numberOfVerticalTiles = ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
		tiles = new Tile[numberOfHorizontalTiles][numberOfVerticalTiles];
		for (int i = 0; i < tiles.length; ++i) {
			for (int j = 0; j < tiles[0].length; ++j) {
				tiles[i][j] = new Tile(this, i, j, getTileType(i, j, INTERACTABLE_LAYER));
				if (debugLighting == false)
					setTiledMapTile(i, j, SHADOW_LAYER, 49);
			}
		}
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
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

	public void lightArea(int x, int y) {
		int radius = 7;

		for (int i = -radius; i < radius; ++i) {
			for (int j = -radius; j < radius; ++j) {
				int sumOfLegsSquared = i * i + j * j;
				int radiusSquared = radius * radius;

				if (sumOfLegsSquared < radiusSquared) {
					int id = (int) (((1 - (double) sumOfLegsSquared / (double) radiusSquared)) * 4 + 50);
					TileType tileType = getTileType(x + i, y + j, SHADOW_LAYER);
					if (tileType.getID() < id)
						setTiledMapTile(x + i, y + j, SHADOW_LAYER, id);
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
		int[] bounds = player.getVisibleTileBounds();

		for (int i = bounds[0]; i < bounds[1]; ++i) {
			for (int j = bounds[2]; j < bounds[3]; ++j) {
				tiles[i][j].update();
			}
		}
		// this for loop will be deleted once we can update entities from the tile
		for (Entity entity : entities) {
			entity.update();
		}
		player.update();
	}

	public void draw(OrthographicCamera camera, SpriteBatch batch) {
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(new int[] { FLOOR_LAYER, INTERACTABLE_LAYER, DECORATIVE_LAYER, RESIDUE_LAYER });

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		int[] bounds = player.getVisibleTileBounds();

		for (int i = bounds[0]; i < bounds[1]; ++i) {
			for (int j = bounds[2]; j < bounds[3]; ++j) {
				tiles[i][j].draw(batch);
			}
		}

		// this for loop will be deleted once we can draw entities from the tile
		for (Entity entity : entities) {
			entity.draw(batch);
		}
		player.draw(batch);
		batch.end();

		tiledMapRenderer.render(new int[] { SHADOW_LAYER });
	}

	public void dispose() {
		tiledMap.dispose();
		tiledMapRenderer.dispose();
	}
}
