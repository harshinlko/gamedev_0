package com.box2d.main;

import java.io.IOException;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.box2d.main.TexturePacker;
import com.box2d.main.TexturePacker.Settings;

public class AndroidGame extends AndroidApplication {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true;
		config.useGL20 = true;
//		Settings settings = new Settings();
//        settings.padding = 2;
//        settings.maxWidth = 3000;
//        settings.maxHeight = 3000;
//        settings.incremental = true;
//		try
//		{
//			TexturePacker.process(settings, "./images", "./outimage");
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
  		getWindow().clearFlags(
  				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
  		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		initialize(new Game(), config);
    }
}