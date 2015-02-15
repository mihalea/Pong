package com.me.pong.model.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.math.Vector2;

public class GUI_Font {
	protected BitmapFont font;
	protected Boolean visible;
	protected Vector2 scale, position;
	protected int index;
	protected String text;
	
	public GUI_Font(String name, String string, Color color, Vector2 position, boolean centered, boolean visible, int index){
		font = new BitmapFont(Gdx.files.internal("fonts/" + name + ".fnt"),
								Gdx.files.internal("fonts/" + name + ".png"),
								false);
		font.setColor(color);
		
		this.position = position;
		this.text = string;
		this.visible = visible;
		this.index = index;
		
		if(centered){
			TextBounds b = font.getBounds(string);
			position.x = Gdx.graphics.getWidth()/2 - b.width/2;
		}
		
		resize();
	}
	
	private void resize()
	{
	    float x = Gdx.graphics.getWidth();
	    float y = Gdx.graphics.getHeight();

	    scale = new Vector2();
	    scale.x = x / 540; //being your screen size that you're developing with
	    scale.y = y / 960;
	    
	    font.setScale(scale.x, scale.y);
	    position.mul(scale.x, scale.y);
	}

	public BitmapFont getFont() {
		return font;
	}

	public Boolean getVisible() {
		return visible;
	}

	public Vector2 getPosition() {
		return position;
	}

	public int getIndex() {
		return index;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
