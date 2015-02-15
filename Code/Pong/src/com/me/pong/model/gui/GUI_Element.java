package com.me.pong.model.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.pong.Pong;

public abstract class GUI_Element {
	protected TextureRegion texture;
	protected Rectangle bounds;
	protected Boolean visible;
	protected Vector2 scale;
	protected int index;
	
	protected GUI_Element(String name, Vector2 position, Boolean visible, int index){
		texture = Pong.textureLoader.getRegion(name);
		this.bounds = new Rectangle(position.x, position.y, texture.getRegionWidth(), texture.getRegionHeight());
		this.visible = visible;
		this.index = index;
		
		resize();
	}
	
	private void resize()
	{
	    float x = Gdx.graphics.getWidth();
	    float y = Gdx.graphics.getHeight();

	    scale = new Vector2();
	    scale.x = x / 540; //being your screen size that you're developing with
	    scale.y = y / 960;


	    bounds.x *= scale.x;
	    bounds.y *= scale.y;
	    bounds.width *= scale.x;
	    bounds.height *= scale.y;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getIndex() {
		return index;
	}

	public Boolean getVisible() {
		return visible;
	}
	
	
	
}
