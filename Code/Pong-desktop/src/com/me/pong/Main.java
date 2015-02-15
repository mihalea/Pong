package com.me.pong;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Pong";
		cfg.useGL20 = true;
		cfg.width = 540;
		cfg.height = 960;
		
		Settings settings = new Settings();
		settings.ignoreBlankImages = true; 
        //TexturePacker2.process(settings, "images", "packed", "game");
		
		new LwjglApplication(new Pong(), cfg);
	}
}
