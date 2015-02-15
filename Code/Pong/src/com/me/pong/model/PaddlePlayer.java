package com.me.pong.model;

import com.badlogic.gdx.math.Vector2;

public class PaddlePlayer extends Paddle {

	int left, right;
	
	public PaddlePlayer(Vector2 position, Vector2 scale, int left, int right) {
		super(position, scale);
		
		this.left = left;
		this.right = right;
	}
	
	public int getLeft(){
		return left;
	}
	
	public int getRight(){
		return right;
	}

}
