package com.me.pong.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.pong.Pong;
import com.me.pong.controller.World;


public class WorldRenderer {

	World world;
	Boolean debug;
	
	SpriteBatch batch;
	ShapeRenderer debugRenderer;
	
	OrthographicCamera camera;
	
	Vector2 screenSize;
	
	BitmapFont font = new BitmapFont();
	
	public WorldRenderer(World world, Boolean debug){
		this.world = world;
		this.debug = debug;
		
		init();
	}
	
	private void init(){
		screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		debugRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(screenSize.x , screenSize.y);
		camera.position.set(screenSize.x/2, screenSize.y/2, 0);
		camera.update();
	}
	
	public void render(){
		batch.begin();
			/** Draw background **/
			batch.draw(Pong.textureLoader.getRegion("gameBackground"), 0, 0);
			
			/** Draw first player **/
			Rectangle bounds = world.getPlayer().getBounds();
			batch.draw(world.getPlayer().getTexture(), bounds.x, bounds.y, bounds.width, bounds.height);
			
			/** Draw second player **/
			if(world.getAI()){
				bounds = world.getPaddleAI().getBounds();
				batch.draw(world.getPaddleAI().getTexture(), bounds.x, bounds.y, bounds.width, bounds.height);
			}
			else{
				bounds = world.getSecondPlayer().getBounds();
				batch.draw(world.getSecondPlayer().getTexture(), bounds.x, bounds.y, bounds.width, bounds.height);
			}
			
			/** Draw ball **/
			bounds = world.getBall().getBounds();
			batch.draw(world.getBall().getTexture(), bounds.x, bounds.y);
			
			if(debug)
				font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
			
		batch.end();
		
		
		if(debug){
			debugRenderer.setProjectionMatrix(camera.combined);
			debugRenderer.begin(ShapeType.Rectangle);
			
				/** DRAW PLAYER **/
				debugRenderer.setColor(1,0,0,1);
				bounds = world.getPlayer().getBounds();			
				debugRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
				
				/** DRAW AI **/
				if(world.getAI()){
					bounds = world.getPaddleAI().getBounds();
					debugRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
				}
				else{
					bounds = world.getSecondPlayer().getBounds();
					debugRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
				}
	
				/** DRAW BALL **/
				debugRenderer.setColor(1,1,1,1);
				bounds = world.getBall().getBounds();
				debugRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
			
			debugRenderer.end();
		}
	}
	
}
