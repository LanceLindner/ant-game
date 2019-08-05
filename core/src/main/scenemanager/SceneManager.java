package main.scenemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import main.entities.brain.Brain;
import main.floors.Floor;
import main.main.Globals;

public class SceneManager {

	private Stage stage;

	private Table rootTable;

	private Table gameplayButtonTable;

	private Table settingsTable;
	private Table designViewTable;

	private TextureAtlas atlas;
	private Skin skin;

	private Floor floor;

	private Brain selectedBrain;

	int selectedNeuron = 0;

	public SceneManager(Stage stage, Floor floor) {
		atlas = new TextureAtlas("assets/skinTest.atlas");
		skin = new Skin(new FileHandle("assets/skinTest.json"), atlas);

		this.floor = floor;

		this.stage = stage;

		// Gdx.input.setInputProcessor(stage);

		rootTable = new Table(skin);
		gameplayButtonTable = new Table();
		settingsTable = new Table(skin);
		designViewTable = new Table(skin);

		initializeGameplayButtonTable();
		initializeSettingsTable();
		initializeDesignViewTable();

		rootTable.setFillParent(true);
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
				rootTable.clearChildren();
				rootTable.addActor(settingsTable);
			};
		});

		designViewButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				rootTable.clearChildren();
				rootTable.addActor(designViewTable);
			};
		});

		Table antTable = new Table();

		antTable.add(new Button(skin)).width(75).height(75).right().expandX();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();
		antTable.row();
		antTable.add(new Button(skin)).width(75).height(75).right();

		gameplayButtonTable.add(settingsButton).left().top().width(75).height(75);

		gameplayButtonTable.row().width(Globals.windowWidth).height(Globals.windowHeight - 150);
		gameplayButtonTable.add(antTable);
		gameplayButtonTable.row();

		gameplayButtonTable.add(designViewButton).bottom().right().width(75).height(75);

		rootTable.add(gameplayButtonTable);
	}

	private void initializeSettingsTable() {

		settingsTable.setFillParent(true);
		settingsTable.top().left();
		settingsTable.setDebug(true);

		Button backButton = new Button(skin, "backButton");

		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				rootTable.clearChildren();
				rootTable.addActor(gameplayButtonTable);
			};
		});

		settingsTable.setBackground("transparentBackground");
		settingsTable.add(backButton);
	}

	private void initializeDesignViewTable() {
		designViewTable.setFillParent(true);
		designViewTable.top().left();
		designViewTable.setDebug(true);

		Button backButton = new Button(skin, "backButton");
		Button saveButton = new Button(skin, "saveButton");
		Button loadButton = new Button(skin, "loadButton");

		final ButtonGroup<Button> neuronButtonsGroup = new ButtonGroup<Button>();

		Button[] neurons = new Button[12];
		for (int i = 0; i < 12; i++) {
			neurons[i] = new Button(skin, "toggle");

			neurons[i].addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					// Sets selectedNeuron to the index of the button in the buttongroup
					selectedNeuron = neuronButtonsGroup.getButtons().indexOf((Button) event.getListenerActor(), false);
				}
			});
		}

		neuronButtonsGroup.add(neurons);

		Table neuronTable = new Table();

		int size = 80;

		neuronTable.add(neurons[0]).height(size).width(size).pad(3);
		neuronTable.add(neurons[1]).height(size).width(size).pad(3);
		neuronTable.add(neurons[2]).height(size).width(size).pad(3);
		neuronTable.row();
		neuronTable.add(neurons[3]).height(size).width(size).pad(3);
		neuronTable.add(neurons[4]).height(size).width(size).pad(3);
		neuronTable.add(neurons[5]).height(size).width(size).pad(3);
		neuronTable.row();
		neuronTable.add(neurons[6]).height(size).width(size).pad(3);
		neuronTable.add(neurons[7]).height(size).width(size).pad(3);
		neuronTable.add(neurons[8]).height(size).width(size).pad(3);
		neuronTable.row();
		neuronTable.add(neurons[9]).height(size).width(size).pad(3);
		neuronTable.add(neurons[10]).height(size).width(size).pad(3);
		neuronTable.add(neurons[11]).height(size).width(size).pad(3);

		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				rootTable.clearChildren();
				rootTable.addActor(gameplayButtonTable);
			}
		});

		designViewTable.setBackground("transparentBackground");

		designViewTable.add(backButton).padRight(50).padBottom(200);

		designViewTable.add(saveButton).top().padRight(5);
		designViewTable.add(loadButton).top();

		designViewTable.row();
		designViewTable.add(neuronTable).colspan(3);
	}
}