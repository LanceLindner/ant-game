package main.scenemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import main.floors.Floor;
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

	private Floor floor;

	public SceneManager(Stage stage, Floor floor) {
		atlas = new TextureAtlas("assets/skinTest.atlas");
		skin = new Skin(new FileHandle("assets/skinTest.json"), atlas);

		this.floor = floor;

		this.stage = stage;
		stack = new Stack();

		Gdx.input.setInputProcessor(stage);

		rootTable = new Table();
		gameplayButtonTable = new Table();
		settingsTable = new Table(skin);
		designViewTable = new Table(skin);

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
		Button settingsButton = new Button(skin, "optionsButton");
		Button designViewButton = new Button(skin, "designViewButton");

		settingsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stack.removeActor(gameplayButtonTable);
				stack.add(settingsTable);
			};
		});

		designViewButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stack.removeActor(gameplayButtonTable);
				stack.add(designViewTable);
			};
		});

		Table antTable = new Table();

		antTable.add(new Button(skin)).width(75).height(75);
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75);
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75);
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75);
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75);
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75);

		gameplayButtonTable.debugCell();
		gameplayButtonTable.add(settingsButton).left().top().width(75).height(75);

		gameplayButtonTable.row().expandX().height(Globals.windowHeight - 150);
		gameplayButtonTable.add(antTable).right();
		gameplayButtonTable.row();

		gameplayButtonTable.add(designViewButton).bottom().right().width(75).height(75);

		stack.add(gameplayButtonTable);
	}

	private void initializeSettingsTable() {
		Button backButton = new Button(skin, "backButton");

		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stack.removeActor(settingsTable);
				stack.addActor(gameplayButtonTable);
			};
		});

		settingsTable.setBackground("transparentBackground");
		settingsTable.add(backButton).padBottom(Globals.windowHeight - 75).padRight(Globals.windowWidth - 75);

		settingsTable.setFillParent(true);
	}

	private void initializeDesignViewTable() {
		Button backButton = new Button(skin, "backButton");

		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stack.removeActor(designViewTable);
				stack.addActor(gameplayButtonTable);
			};
		});

		designViewTable.setBackground("transparentBackground");
		designViewTable.add(backButton).padBottom(Globals.windowHeight - 75).padRight(Globals.windowWidth - 75);

	}
}
