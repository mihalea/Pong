package com.me.pong.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.pong.Pong;

abstract public class Paddle {

	//Texture texture;
	Vector2 size;
	Vector2 position;
	Vector2 velocity;

	Vector2 scale;
	
	TextureRegion texture;
	
	public Paddle(Vector2 position, Vector2 scale){
		this.position = position;
		this.scale = scale;
		
		init();
		load();
		resize();
	}
	
	private void init(){
		size = new Vector2(128, 32);
		velocity = new Vector2();
	}
	
	private void load(){
		texture = Pong.textureLoader.getRegion("paddle");
	}
	
	private void resize()
	{
	    float x = Gdx.graphics.getWidth();
	    float y = Gdx.graphics.getHeight();

	    scale.x = x / 540; //being your screen size that you're developing with
	    scale.y = y / 960;

	    position = new Vector2(position.x * scale.x, position.y * scale.y);
	    size.x *= scale.x;
	    size.y *= scale.y;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(position.x , position.y , size.x , size.y);
	}	

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}
	
	public Vector2 getSize(){
		return size;
	}

	public TextureRegion getTexture() {
		return texture;
	}
	
	
	
}
