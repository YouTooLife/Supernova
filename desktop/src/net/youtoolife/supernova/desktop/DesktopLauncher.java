package net.youtoolife.supernova.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.youtoolife.supernova.Supernova;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = 1280;
		//config.height = 768;
		config.width = config.getDesktopDisplayMode().width;
		config.height = config.getDesktopDisplayMode().height;
		config.fullscreen = true;
		new LwjglApplication(new Supernova(), config);
	}
}
