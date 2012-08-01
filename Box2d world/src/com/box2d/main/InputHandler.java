package com.box2d.main;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor
{	
	public int LEFT;
	public int RIGHT;
	public int UP;
	public int DOWN;
	public int key_state[]=new int[256];
	
	@Override
	public boolean keyDown(int keycode) 
	{
		if (keycode == Keys.LEFT)
			LEFT=1;
		else if (keycode == Keys.RIGHT)
			RIGHT=1;
		else if (keycode == Keys.UP)
			UP=1;
		else if (keycode == Keys.DOWN)
			DOWN=1;
		else
			key_state[keycode]=1;
		return true;
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		if (keycode == Keys.LEFT)
			LEFT=0;
		else if (keycode == Keys.RIGHT)
			RIGHT=0;
		else if (keycode == Keys.UP)
			UP=0;
		else if (keycode == Keys.DOWN)
			DOWN=0;
		else
			key_state[keycode]=0;
		return true;
	}

	@Override
	public boolean keyTyped(char character) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) 
	{
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) 
	{
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) 
	{
		// TODO Auto-generated method stub
		return false;
	}	

}
