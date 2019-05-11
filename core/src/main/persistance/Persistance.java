package main.persistance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class Persistance {
	private static Json json = new Json();
	private static SaveData saveData;

	public static void load() {
		Gdx.files.local("saves/").file().mkdirs();
		FileHandle file = Gdx.files.local("saves/save.txt");
		if (file.exists()) {
			saveData = json.fromJson(SaveData.class, file.readString());
		} else {
			saveData = new SaveData();
			save();
		}
	}

	public static void save() {
		Gdx.files.local("saves/").file().mkdirs();
		FileHandle file = Gdx.files.local("saves/save.txt");
		file.writeString(json.prettyPrint(saveData), false);
	}
}
