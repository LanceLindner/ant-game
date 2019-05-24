package main.scenemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

	public SceneManager(Stage stage) {
		this.stage = stage;
		stack = new Stack();

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

		gameplayButtonTable.debugCell();
		gameplayButtonTable.add().width(Globals.windowWidth).height(Globals.windowHeight / 2);
		gameplayButtonTable.row();
		gameplayButtonTable.add().width(Globals.windowWidth).height(Globals.windowHeight / 2);

		rootTable.setDebug(true, true);

		// gameplayButtonTable.addActor(new Button());

		stack.add(gameplayButtonTable);
	}

	private void initializeSettingsTable() {

	}

	private void initializeDesignViewTable() {

	}
}
