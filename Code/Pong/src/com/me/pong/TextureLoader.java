package com.me.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureLoader {
	TextureAtlas atlas;
	
	public TextureLoader(){
		atlas = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
	}
	
	public TextureRegion getRegion(String name){
		return atlas.findRegion(name);
	}
}
