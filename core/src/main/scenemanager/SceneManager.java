package main.scenemanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SceneManager {

	private Stage stage;

	public SceneManager(Stage stage) {
		this.stage = stage;
	}

	public void render() {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
}
