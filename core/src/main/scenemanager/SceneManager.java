package main.scenemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import main.main.Globals;

public class SceneManager {

	private Stage stage;
	private Stack stack;

	private Table rootTable;

	private Table gameplayButtonTable;

	private Table settingsTable;
	private Table designViewTable;

	private TextureAtlas atlas;
	private Skin skin;

	public SceneManager(Stage stage) {
		atlas = new TextureAtlas("assets/skinTest.atlas");
		skin = new Skin(new FileHandle("assets/skinTest.json"), atlas);

		this.stage = stage;
		stack = new Stack();

		Gdx.input.setInputProcessor(stage);

		rootTable = new Table();
		gameplayButtonTable = new Table();
		settingsTable = new Table();
		designViewTable = new Table();

		initializeGameplayButtonTable();
		initializeSettingsTable();
		initializeDesignViewTable();

		rootTable.setFillParent(true);
		stack.setFillParent(true);
		rootTable.addActor(stack);
		stage.addActor(rootTable);
	}

	public void render() {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	private void initializeGameplayButtonTable() {

		Button settingsButton = new Button(skin);
		Button designViewButton = new Button(skin);

		gameplayButtonTable.add(settingsButton).left().top().width(100).height(100);

		gameplayButtonTable.row();
		gameplayButtonTable.add().width(Globals.windowWidth).height(Globals.windowHeight - 200);
		gameplayButtonTable.row();

		gameplayButtonTable.add(designViewButton).bottom().right().width(100).height(100);

		stack.add(gameplayButtonTable);
	}

	private void initializeSettingsTable() {
		// Not implemented yet
	}

	private void initializeDesignViewTable() {
		// Not implemented yet
	}
}
