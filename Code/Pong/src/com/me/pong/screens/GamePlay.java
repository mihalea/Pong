package com.me.pong.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.me.pong.controller.GUI;
import com.me.pong.controller.World;
import com.me.pong.model.PaddlePlayer;
import com.me.pong.model.gui.GUI_Font;
import com.me.pong.view.GUI_Renderer;
import com.me.pong.view.WorldRenderer;

public class GamePlay implements Screen, InputProcessor{

	Game parent;
	WorldRenderer renderer;
	World world;
	GUI gui;
	GUI_Renderer guiRenderer;
	boolean AI;
	
	public GamePlay(Game parent, boolean AI){
		this.parent = parent;
		this.AI = AI;
	}
	
	@Override
	public void render(float delta) {
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
		
		world.update(delta);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();
		guiRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		

	}

	@Override
	public void show() {
		
		world = new World(this, AI);
		renderer = new WorldRenderer(world, false);
		gui = new GUI();
		guiRenderer = new GUI_Renderer(gui);
		setupGUI();
	}
	
	private void setupGUI(){
		GUI_Font f = new GUI_Font("Minecraftia32", "0", new Color(1,1,1,1), new Vector2(15,200+20), false, true, 1);
		gui.addFont("player", f);
		f = new GUI_Font("Minecraftia32", "0", new Color(1,1,1,1), new Vector2(15,960-200), false, true, 1);
		gui.addFont("second", f);
	}
	
	public void increaseFirst(){
		GUI_Font f = gui.getFontMap().get("player");
		f.setText(new Integer(new Integer(f.getText()) + 1).toString());
	}
	
	public void increaseSecond(){
		GUI_Font f = gui.getFontMap().get("second");
		f.setText(new Integer(new Integer(f.getText()) + 1).toString());
	}
	
	@Override
	public void hide() {
		

	}

	@Override
	public void pause() {
		

	}

	@Override
	public void resume() {
		

	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}
	
/** InputProcessor Methods **/

	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode == Keys.BACK){
			this.dispose();
			parent.setScreen(new Mode(parent));
			return true;
		}
		
		world.setKeyDown(keycode);
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		world.setKeyUp(keycode);
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (Gdx.app.getType().equals(ApplicationType.Android) == false)
			return false;
		
		/** Player One **/
		if(screenY > Gdx.graphics.getHeight()/2){			
			PaddlePlayer p = world.getPlayer();
			
			if(screenX > p.getPosition().x + p.getSize().x/5 &&
					screenX < p.getPosition().x + 4*p.getSize().x/5){
				world.setAllKeysUp();
				return true;
			}
			
			if(screenX < p.getPosition().x + p.getSize().x/5){
				world.setKeyDown(world.getPlayer().getLeft());
				world.setKeyUp(world.getPlayer().getRight());
			}
			else if(screenX > p.getPosition().x + 4*p.getSize().x/5){
				world.setKeyDown(world.getPlayer().getRight());
				world.setKeyUp(world.getPlayer().getLeft());
			}
		}
		/** Player Two **/
		else if(world.getAI() == false){
			PaddlePlayer p = world.getSecondPlayer();
			
			if(screenX < p.getPosition().x + p.getSize().x/5){
				world.setKeyDown(world.getSecondPlayer().getLeft());
				world.setKeyUp(world.getSecondPlayer().getRight());
			}
			else if(screenX > p.getPosition().x + 4*p.getSize().x/5){
				world.setKeyDown(world.getSecondPlayer().getRight());
				world.setKeyUp(world.getSecondPlayer().getLeft());
			}
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (Gdx.app.getType().equals(ApplicationType.Android) == false)
			return false;
		
		world.setAllKeysUp();
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (Gdx.app.getType().equals(ApplicationType.Android) == false)
			return false;
		
		/** Player One **/
		if(screenY > Gdx.graphics.getHeight()/2){			
			PaddlePlayer p = world.getPlayer();
			
			if(screenX > p.getPosition().x + p.getSize().x/5 &&
					screenX < p.getPosition().x + 4*p.getSize().x/5){
				world.setAllKeysUp();
				return true;
			}
			
			if(screenX < p.getPosition().x + p.getSize().x/5){
				world.setKeyDown(world.getPlayer().getLeft());
				world.setKeyUp(world.getPlayer().getRight());
			}
			else if(screenX > p.getPosition().x + 4*p.getSize().x/5){
				world.setKeyDown(world.getPlayer().getRight());
				world.setKeyUp(world.getPlayer().getLeft());
			}
		}
		/** Player Two **/
		else if(world.getAI() == false){
			PaddlePlayer p = world.getSecondPlayer();
			
			if(screenX < p.getPosition().x + p.getSize().x/5){
				world.setKeyDown(world.getSecondPlayer().getLeft());
				world.setKeyUp(world.getSecondPlayer().getRight());
			}
			else if(screenX > p.getPosition().x + 4*p.getSize().x/5){
				world.setKeyDown(world.getSecondPlayer().getRight());
				world.setKeyUp(world.getSecondPlayer().getLeft());
			}
		}
		
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}

}
