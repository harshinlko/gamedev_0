package com.box2d.main;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

public class ObjectManager
{
	static final float BOX_TO_WORLD=100.0f;
	static final float WORLD_TO_BOX=0.01f;
	
	static World world;
	public ArrayList<BaseObject> objects;

	public static float to_box(float x)
	{
		return x*WORLD_TO_BOX;
	}
	
	public static int to_world(float x)
	{
		return (int)(x*BOX_TO_WORLD);
	}
	
	public ObjectManager(World w)
	{
		world=w;
		objects=new ArrayList<BaseObject>();
	}
	
	public void update()
	{
			for(BaseObject obj:objects)
			{
				obj.update();
			}
	}
	
	public void load_texture(SpriteBatch batch)
	{
		//batch = new SpriteBatch();
		//batch.begin();
		for(BaseObject obj:objects)
		{
			obj.load_texture(batch);
		}
	}
}

