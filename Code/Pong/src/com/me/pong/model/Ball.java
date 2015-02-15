package com.me.pong.model;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.pong.Pong;

public class Ball {

	final float MAX_SPEED = 250f, MIN_SPEED = 50f;
	final float START_SPEED = 300f;
	
	Vector2 size;
	Vector2 position;
	Vector2 velocity;
	
	Vector2 scale;
	
	TextureRegion texture;
	
	public Ball(Vector2 scale){
		this.scale = scale;
		
		init();
		load();
		resize();
	}

	private void init() {
		size = new Vector2(32, 32);
		position = new Vector2();
		velocity = new Vector2();
		
		reset();
	}
	
	
	private void load() {
		texture = Pong.textureLoader.getRegion("ball");
	}
	
	private void resize()
	{
	    position = new Vector2(position.x * scale.x, position.y * scale.y);
	    size.x *= scale.x;
	    size.y *= scale.y;
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(position.x, position.y, size.x, size.y);
	}

	public Vector2 getSize() {
		return size;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}
	
	public void reset(){
		position.set(Gdx.graphics.getWidth()/2 - size.x/2, Gdx.graphics.getHeight()/2 - size.y/2);
		
		Random rand = new Random(System.currentTimeMillis());
		/** X MOVEMENT **/
		if(rand.nextBoolean() == true){ // LEFT
			velocity.x = rand.nextFloat() * -(MAX_SPEED-MIN_SPEED) - MIN_SPEED;
		}
		else{ 							//RIGHT
			velocity.x = rand.nextFloat() * +(MAX_SPEED-MIN_SPEED) + MIN_SPEED;
		}
		
		
		double tempY = Math.sqrt(Math.abs(START_SPEED * START_SPEED - Math.abs(velocity.x) * Math.abs(velocity.x)));
		/** Y MOVEMENT **/
		if(rand.nextBoolean() == true){ // UP
			velocity.y = (float) tempY;
		}
		else{ 							// DOWN
			velocity.y = (float) -tempY;
		}
		
		//System.out.println(velocity.x + " " + velocity.y);
	}

	public TextureRegion getTexture() {
		return texture;
	}
	
	
}
