package com.me.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.me.pong.screens.*;


public class Pong extends Game{

	public static TextureLoader textureLoader;;
	
	@Override
	public void create() {
		textureLoader = new TextureLoader();
		
		Gdx.graphics.setVSync(true);
		Gdx.graphics.setTitle("Pongah");
		
		
		
		setScreen(new Mode(this));
	}
}
