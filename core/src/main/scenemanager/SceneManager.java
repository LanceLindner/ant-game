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
				stack.add(settingsTable);
			};
		});

		designViewButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stack.add(designViewTable);
			};
		});

		Table antTable = new Table();

		antTable.add(new Button(skin)).width(100).height(100);
		antTable.row();
		antTable.add(new Button(skin)).width(100).height(100);
		antTable.row();
		antTable.add(new Button(skin)).width(100).height(100);
		antTable.row();
		antTable.add(new Button(skin)).width(100).height(100);
		antTable.row();
		antTable.add(new Button(skin)).width(100).height(100);
		antTable.row();
		antTable.add(new Button(skin)).width(100).height(100);

		gameplayButtonTable.debugCell();
		gameplayButtonTable.add(settingsButton).left().top().width(100).height(100);

		gameplayButtonTable.row().expandX().height(Globals.windowHeight - 200);
		gameplayButtonTable.add(antTable).right();
		gameplayButtonTable.row();

		gameplayButtonTable.add(designViewButton).bottom().right().width(100).height(100);

		stack.add(gameplayButtonTable);
	}

	private void initializeSettingsTable() {

		// Right now this is way more complex than would normally be.
		// Normally we would use setBackground
		Button backButton = new Button(skin);

		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stack.removeActor(settingsTable);
			};
		});

		settingsTable.setBackground("transparentBackground");
		settingsTable.add(backButton).top().left();

		settingsTable.setFillParent(true);
		// stack.add(settingsTable);
	}

	private void initializeDesignViewTable() {

		// This is literally the same as settings right no
		Button backButton = new Button(skin);

		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stack.removeActor(designViewTable);
			};
		});

		designViewTable.setBackground("transparentBackground");
		designViewTable.add(backButton).top().right();

		designViewTable.setFillParent(true);
	}
}
