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
	public static final int SHADOW_LAYER = 3;
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

		player = new Player(0, 0);
		entities.add(player);
	}

	private void tiledMapToTiles() {
		int numberOfHorizontalTiles = ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
		int numberOfVerticalTiles = ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
		tiles = new Tile[numberOfHorizontalTiles][numberOfVerticalTiles];
		for (int i = 0; i < tiles.length; ++i) {
			for (int j = 0; j < tiles[0].length; ++j) {
				tiles[i][j] = new Tile(this, i, j, getTileType(i, j, INTERACTABLE_LAYER));
			}
		}
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	public TileType getTileType(int col, int row, int layer) {
		Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);

		if (cell != null) {
			TiledMapTile tile = cell.getTile();

			if (tile != null) {
				int id = tile.getId();
				return TileType.getTileTypeById(id);
			}
		}
		return null;
	}

	public void render(OrthographicCamera camera, SpriteBatch batch) {
		update();
		player.updateCamera(camera);
		draw(camera, batch);
	}

	public void update() {
		for (Entity entity : entities) {
			entity.update();
		}
	}

	public void draw(OrthographicCamera camera, SpriteBatch batch) {
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(new int[] { FLOOR_LAYER, INTERACTABLE_LAYER, DECORATIVE_LAYER });

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		for (Entity entity : entities) {
			entity.draw(batch);
		}
		batch.end();

		tiledMapRenderer.render(new int[] { SHADOW_LAYER });
	}

	public void dispose() {
		tiledMap.dispose();
		tiledMapRenderer.dispose();
	}
}
