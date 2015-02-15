package com.me.pong.screens;

import java.util.Map.Entry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.me.pong.controller.GUI;
import com.me.pong.model.gui.Background;
import com.me.pong.model.gui.Button;
import com.me.pong.model.gui.GUI_Element;
import com.me.pong.view.GUI_Renderer;

public class Mode implements Screen, InputProcessor {

	Game parent;
	BitmapFont font;
	
	GUI_Renderer renderer;
	GUI gui;
	
	public Mode(Game parent) {
		this.parent = parent;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(this);
		
		font = new BitmapFont();
		
		gui = new GUI();
		renderer = new GUI_Renderer(gui);
		addGuiElements();
	}
	
	private void addGuiElements(){
		Background bg = new Background("background", new Vector2(0,0), true, 0);
		gui.addElement("menuBG", bg);
		Button b = new Button("singlePlayer", new Vector2(14,960-128-250), true, 1);
		gui.addElement("singlePlayer", b);
		b = new Button("twoPlayers", new Vector2(14,960-128*2-300), true, 1);
		gui.addElement("twoPlayers",b);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
	}
	

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Keys.NUM_1){
			parent.setScreen(new GamePlay(parent, true));
			this.dispose();
		}
		else if(keycode == Keys.NUM_2){
			parent.setScreen(new GamePlay(parent, false));
			this.dispose();
		}
		
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		for(Entry<String, GUI_Element> set : gui.getElementMap().entrySet()){
			GUI_Element e = set.getValue();
			Rectangle r = e.getBounds();
			if(screenX > r.x && screenX < r.x + r.width &&
					Gdx.graphics.getHeight() - screenY > r.y && 
					Gdx.graphics.getHeight() - screenY < r.y + r.height){
				doAction(set.getKey());
			}
		}
		return false;
	}

	private void doAction(String key) {
		if(key == "singlePlayer"){
			this.dispose();
			parent.setScreen(new GamePlay(parent, true));
		}
		else if(key == "twoPlayers"){
			this.dispose();
			parent.setScreen(new GamePlay(parent, false));
		}
		
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
