package com.box2d.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class TexWrapper
{
	public Texture texture;
	public TextureRegion region;
	public TextureAtlas atlas; 
	public Sprite sprite;
	
	int dstX;
	int dstY;
	int destWidth;
	int destHeight;
	int originX;
	int originY;
	float angle;
	int padding;
	Color color;
	
	public TexWrapper(String tag,String texfile,String texdir)
	{
		if(!(tag==""))
		{
			atlas = new TextureAtlas(Gdx.files.internal(texfile),Gdx.files.internal(texdir));
			region = atlas.findRegion(tag);
		}
		else
		{
			texture = new Texture(Gdx.files.internal(texfile));
			region = new TextureRegion(texture);
		}
		sprite=new Sprite(region);
	}
	
	public void load_texture(SpriteBatch batch)
	{
		sprite.setBounds(dstX, dstY, destWidth, destHeight);
		sprite.setOrigin(originX, originY);
		sprite.setRotation(angle/3.14f*180.0f);
		sprite.draw(batch);
	}
}
