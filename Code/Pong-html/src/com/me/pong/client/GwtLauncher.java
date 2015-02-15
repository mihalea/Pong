package com.me.pong.client;

import com.me.pong.Pong;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(540, 960);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new Pong();
	}
}