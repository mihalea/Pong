package com.me.pong.view;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.me.pong.controller.GUI;
import com.me.pong.model.gui.GUI_Element;
import com.me.pong.model.gui.GUI_Font;

public class GUI_Renderer {
	SpriteBatch batch;
	GUI gui;
	
	public GUI_Renderer(GUI gui){
		this.gui = gui;
		batch = new SpriteBatch();
	}
	
	public void render(){		
		batch.begin();
		
			Rectangle bounds;
		
			for (int i=0 ; i<=gui.getMaxIndex() ; i++){
				for(GUI_Element element : gui.getElements()){
					if(element.getIndex() == i && element.getVisible() == true){
						bounds = element.getBounds();
						batch.draw(element.getTexture(),bounds.x, bounds.y, bounds.width, bounds.height);
					}
				}
				
				for(GUI_Font font : gui.getFonts()){
					if(font.getIndex() == i && font.getVisible() == true){
						font.getFont().draw(batch, font.getText(), font.getPosition().x, font.getPosition().y);
					}
				}
			}
		
		batch.end();
	}
}
