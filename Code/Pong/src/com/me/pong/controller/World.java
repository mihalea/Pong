package com.me.pong.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.pong.model.Ball;
import com.me.pong.model.PaddleAI;
import com.me.pong.model.PaddlePlayer;
import com.me.pong.screens.GamePlay;

public class World{
	
	
	public class Score{
		int first,second;
		
		public Score(){
			reset();
		}
		
		public void increaseFirst(){
			first++;
		}
		
		public void increaseSecond(){
			second++;
		}
		
		public int getFirst(){
			return first;
		}
		
		public String getFirstAsString(){
			return new Integer(first).toString();
		}
		
		public int getSecond(){
			return second;
		}
		
		public String getSecondAsString(){
			return new Integer(second).toString();
		}
		
		public void reset(){
			first = second = 0;
		}
	}
	
	
	final float PLAYER_SPEED = 250f;
	
	PaddlePlayer player, secondPlayer;
	PaddleAI paddleAI;
	Ball ball;
	boolean isAI;
	Vector2 scale;
	Map<Integer , Boolean> keys = new HashMap<Integer, Boolean>();
	Score score;
	Screen parent;
	
	
	
	public World(Screen parent, boolean AI){
		this.isAI = AI;
		this.parent = parent;
		init();
	}
	
	private void init(){	
		score = new Score();
		
		scale = new Vector2(Gdx.graphics.getWidth() / 540,
				Gdx.graphics.getHeight() / 960);	
		
		player = new PaddlePlayer(new Vector2(540/2-128/2,150), scale, Keys.LEFT, Keys.RIGHT);
		
		if(isAI)
			paddleAI = new PaddleAI(new Vector2(540/2-128/2,960-150-32), scale);
		else
			secondPlayer = new PaddlePlayer(new Vector2(540/2-128/2,960-150-32), scale, Keys.A, Keys.D);
		
		ball = new Ball(scale);
		
		keys.put(player.getLeft(), false);
		keys.put(player.getRight(), false);	
		if(isAI == false){
			keys.put(secondPlayer.getLeft(), false);
			keys.put(secondPlayer.getRight(), false);
		}
	}
	
	public PaddlePlayer getPlayer(){
		return player;
	}
	
	public PaddlePlayer getSecondPlayer(){
		return secondPlayer;
	}
	
	public PaddleAI getPaddleAI(){
		return paddleAI;
	}
	
	public boolean getAI(){
		return isAI;
	}
	
	public Ball getBall(){
		return ball;
	}
	
	public void setKeyDown(int key){
		if(keys.containsKey(key)){
			keys.put(key, true);
		}
	}
	
	public void setKeyUp(int key){
		if(keys.containsKey(key)){
			keys.put(key, false);
		}
	}
	
	public void update(float delta){	
		/** UPDATE PLAYER **/
		
		if(keys.get(player.getLeft())){
			player.getVelocity().x -= PLAYER_SPEED;
		}
		
		if(keys.get(player.getRight())){
			player.getVelocity().x += PLAYER_SPEED;
		}
		
		if(player.getPosition().x + player.getVelocity().x * delta < 0)
			player.getVelocity().x = 0;
		
		else if(player.getPosition().x + player.getVelocity().x * delta > Gdx.graphics.getWidth()-player.getSize().x)
			player.getVelocity().x = 0;
		
		player.getPosition().add(player.getVelocity().tmp().mul(scale.x * delta, scale.y * delta));
		player.getVelocity().set(0,0);
		
		/** UPDATE OPTIONAL PLAYER **/
		if(isAI == false){
			if(keys.get(secondPlayer.getLeft())){
				secondPlayer.getVelocity().x -= PLAYER_SPEED;
			}
			
			if(keys.get(secondPlayer.getRight())){
				secondPlayer.getVelocity().x += PLAYER_SPEED;
			}
			
			if(secondPlayer.getPosition().x + secondPlayer.getVelocity().x * delta < 0)
				secondPlayer.getVelocity().x = 0;
			
			else if(secondPlayer.getPosition().x + secondPlayer.getVelocity().x * delta > Gdx.graphics.getWidth()-secondPlayer.getSize().x)
				secondPlayer.getVelocity().x = 0;
			
			secondPlayer.getPosition().add(secondPlayer.getVelocity().tmp().mul(scale.x * delta, scale.y * delta));
			secondPlayer.getVelocity().set(0,0);
		}
		/** UPDATE AI ELSE **/
		else{
			if(ball.getPosition().x < paddleAI.getPosition().x + paddleAI.getSize().x/2 - 3 * PLAYER_SPEED * delta)
				paddleAI.getVelocity().x -= PLAYER_SPEED;
			else if(ball.getPosition().x > paddleAI.getPosition().x + paddleAI.getSize().x/2 + 3 * PLAYER_SPEED * delta)
				paddleAI.getVelocity().x += PLAYER_SPEED;
			
			if(paddleAI.getPosition().x + paddleAI.getVelocity().x * delta < 0)
				paddleAI.getVelocity().x = 0;
			
			else if(paddleAI.getPosition().x + paddleAI.getVelocity().x * delta > Gdx.graphics.getWidth()-paddleAI.getSize().x)
				paddleAI.getVelocity().x = 0;
			
			paddleAI.getPosition().add(paddleAI.getVelocity().tmp().mul(scale.x * delta, scale.y * delta));
			paddleAI.getVelocity().set(0,0);
		}
		
		/** MOVE BALL **/
		
		if(ball.getPosition().x + ball.getVelocity().x * delta < 0)
			ball.getVelocity().x *= -1;
		else if(ball.getPosition().x + ball.getVelocity().x * delta > Gdx.graphics.getWidth() - ball.getSize().x)
			ball.getVelocity().x *= -1;
		
		if(ball.getPosition().y + ball.getVelocity().y * delta < 0){
			score.increaseSecond();
			((GamePlay)parent).increaseSecond();
			ball.reset();
			return;
			//ball.getVelocity().y *= -1;
		}
		else if(ball.getPosition().y + ball.getVelocity().y * delta > Gdx.graphics.getHeight() - ball.getSize().y)
		{
			score.increaseFirst();
			((GamePlay)parent).increaseFirst();
			ball.reset();
			return;
		}
		
		/** BALL COLLISION **/
		Rectangle bounds = ball.getBounds();
		bounds.x += ball.getVelocity().x * delta;
		if(bounds.overlaps(player.getBounds()) || 
				(isAI && bounds.overlaps(paddleAI.getBounds()) || 
				(isAI == false && bounds.overlaps(secondPlayer.getBounds()))))
			ball.getVelocity().x *= -1;

		
		bounds = ball.getBounds();
		bounds.y += ball.getVelocity().y * delta;
		if(bounds.overlaps(player.getBounds())){
			ball.getVelocity().y *= -1;
			ball.getVelocity().x += player.getVelocity().x * 0.0001;
		}
		else if(isAI && bounds.overlaps(paddleAI.getBounds())){
			ball.getVelocity().y *= -1;
			ball.getVelocity().x += paddleAI.getVelocity().x * 0.0001;
		}
		else if(isAI == false && bounds.overlaps(secondPlayer.getBounds())){
			ball.getVelocity().y *= -1;
			ball.getVelocity().x += secondPlayer.getVelocity().x * 0.0001;
		}
			
		ball.getPosition().add(ball.getVelocity().tmp().mul(scale.x * delta, scale.y * delta));
		ball.getVelocity().mul(1.0005f);
		
		/** Check for screen boundaries **/
		if(ball.getPosition().x < 0){
			ball.getPosition().x = 0;
		}
		else if(ball.getPosition().x > Gdx.graphics.getWidth() - ball.getSize().x){
			ball.getPosition().x = Gdx.graphics.getWidth() - ball.getSize().x;
		}
	}
	
	public void setAllKeysUp(){
		keys.put(player.getLeft(), false);
		keys.put(player.getRight(), false);
		
		if(isAI == false){
			keys.put(secondPlayer.getLeft(), false);
			keys.put(secondPlayer.getRight(), false);
		}
	}

	public Vector2 getScale() {
		return scale;
	}

	public Score getScore() {
		return score;
	}
	
	
	
}
