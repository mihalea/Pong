package com.me.pong;

import android.os.Bundle;
import android.os.PowerManager; 
import android.content.Context;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	
	private PowerManager.WakeLock wl;
	
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        cfg.useWakelock = false;
        
        initialize(new Pong(), cfg);
        
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

        wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
    }
    
    @Override
    protected void onPause() {
    	wl.release();
    	super.onPause();
    }
    
    @Override
    protected void onResume() {
    	wl.acquire();
    	super.onResume();
    }
}