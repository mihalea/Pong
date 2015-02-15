package com.me.pong.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.me.pong.model.gui.GUI_Element;
import com.me.pong.model.gui.GUI_Font;

public class GUI {

	Map<String, GUI_Element> elements;
	Map<String, GUI_Font> fonts;
	int maxIndex;
	
	public GUI(){
		init();
	}
	
	private void init(){
		 elements = new HashMap<String, GUI_Element>();
		 fonts = new HashMap<String, GUI_Font>();
		 maxIndex = 0;
	}
	
	public void addElement(String name, GUI_Element element){
		if(elements.containsKey(name) == false){
			elements.put(name, element);
			if(element.getIndex() > maxIndex)
				maxIndex = element.getIndex();
		}
	}
	
	public void addFont(String name, GUI_Font font){
		if(fonts.containsKey(name) == false){
			fonts.put(name, font);
			if(font.getIndex() > maxIndex)
				maxIndex =  font.getIndex();
		}
	}
	
	public Collection<GUI_Element> getElements(){
		return elements.values();
	}

	public int getMaxIndex() {
		return maxIndex;
	}
	
	public Map<String, GUI_Element> getElementMap(){
		return elements;
	}
	
	public Map<String, GUI_Font> getFontMap(){
		return fonts;
	}

	public Collection<GUI_Font> getFonts() {
		return fonts.values();
	}
	
	
}
