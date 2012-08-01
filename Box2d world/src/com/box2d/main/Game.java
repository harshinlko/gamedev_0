package com.box2d.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Game implements ApplicationListener
{

	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_WORLD_TO = 100f;
	public ObjectManager objmanager;
	Car car;
	public Circle myball;
	public Rectangle ground,box;
	public World myworld;
	public Vector2 gravity;
	public Box2DDebugRenderer debugger;
	public OrthographicCamera cam;
	public InputHandler input;
	int camx,camy,camz;
	
	@Override
	public void create()
	{
		if (!Gdx.app.getGraphics().isGL20Available())
		{
			throw new GdxRuntimeException("GLES2 Not Available!");
		}
		gravity=new Vector2(0,-10);
		myworld=new World(gravity, true);
		objmanager=new ObjectManager(myworld);
		
		create_objects();
	}

	public void create_objects()
	{
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
		myball=new Circle(myworld,0,BodyType.DynamicBody,1,0.6f,0.7f,GlobalSettings.SCREEN_WIDTH*0.33f,500,0,0,0,"","images/ball.png","",32);
		objmanager.objects.add(myball);
		ground=new Rectangle(myworld,0,BodyType.StaticBody,1,0.3f,0.7f,GlobalSettings.SCREEN_WIDTH/2,GlobalSettings.GROUND_HEIGHT/2,0,0,0,"","images/ground.png","",GlobalSettings.SCREEN_WIDTH,GlobalSettings.GROUND_HEIGHT);
		objmanager.objects.add(ground);
		box=new Rectangle(myworld,0,BodyType.DynamicBody,1,0.3f,0.8f,GlobalSettings.SCREEN_WIDTH*0.5f,500,0,0,40,"","images/crate.png","",64,64);
		objmanager.objects.add(box);
		car=new Car(myworld,0,BodyType.DynamicBody,10,0.3f,0,GlobalSettings.SCREEN_WIDTH*0.66f,500,0,0,0,"","images/carbody.png","","carbody.png","images/carbody",307,145);
		objmanager.objects.add(car);
		input=new InputHandler();
		Gdx.input.setInputProcessor(input);
		debugger = new Box2DDebugRenderer( true, true, true, true );
	} 
	
	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render()
	{
		// TODO Auto-generated method stub
		
//		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(input.RIGHT==1)
		{
			myball.body.applyForceToCenter(3, 0);
		}
		if(input.LEFT==1)
		{
			myball.body.applyForceToCenter(-3, 0);
		}
		if(input.UP==1)
		{
			myball.body.applyForceToCenter(0, 5.0f);
		}
//		if(input.DOWN==1)
//			camx--;
		cam.position.set(camx,0,0);
		cam.far=20.0f;
		cam.update();
		myworld.step(GlobalSettings.BOX_STEP, GlobalSettings.VELOCITY_ITERATIONS,GlobalSettings.POSITION_ITERATIONS);
		objmanager.update();
		objmanager.load_texture();
//		debugger.render(myworld,cam.combined);
	}
	
	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}

}
