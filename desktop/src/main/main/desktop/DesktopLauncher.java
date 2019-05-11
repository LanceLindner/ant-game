package main.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import main.main.Main;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.x = 0;
		config.y = 0;
		config.width = 880;
		config.height = 880;
		TexturePacker.process("assets/sprites/sprites", "assets/sprites/atlas", "atlas");
		new LwjglApplication(new Main(), config);
	}
}
