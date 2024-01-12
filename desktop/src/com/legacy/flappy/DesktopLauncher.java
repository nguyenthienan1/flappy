package com.legacy.flappy;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.legacy.flappy.FlappyGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
//		config.useVsync(false);
		config.setMaximized(true);
		config.setTitle("Flappy Bird");
		new Lwjgl3Application(new FlappyGame(), config);
	}
}
