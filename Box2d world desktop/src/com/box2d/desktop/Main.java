package com.box2d.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.box2d.main.Game;
import com.box2d.main.GlobalSettings;
import com.box2d.main.TexturePacker;
import com.box2d.main.TexturePacker.Settings;

public class Main
{
        public static void main (String[] args) throws Exception 
        {
        	Settings settings = new Settings();
            settings.padding = 2;
            settings.maxWidth = 3000;
            settings.maxHeight = 3000;
            settings.incremental = true;
            TexturePacker.process(settings, "./images", "./outimage");
            new LwjglApplication(new Game(), "Game",GlobalSettings.SCREEN_WIDTH,GlobalSettings.SCREEN_HEIGHT, true);
        }
} 