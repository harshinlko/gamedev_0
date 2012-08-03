package com.box2d.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
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
	public Rectangle ground, box;
	public World myworld;
	public Vector2 gravity;
	public Box2DDebugRenderer debugger;
	public OrthographicCamera cam;
	public InputHandler input;
	float camx, camy, camz;
	double accumulator = 0.0;
	double currTime;
	public SpriteBatch batch;
	BitmapFont font;

	@Override
	public void create()
	{
		if (!Gdx.app.getGraphics().isGL20Available())
		{
			throw new GdxRuntimeException("GLES2 Not Available!");
		}
		gravity = new Vector2(0, -10);
		myworld = new World(gravity, true);
		objmanager = new ObjectManager(myworld);

		create_objects();
	}

	public void create_objects()
	{
		cam=new OrthographicCamera(GlobalSettings.SCREEN_WIDTH,GlobalSettings.SCREEN_HEIGHT);
		cam.position.set(cam.viewportWidth * .5f, cam.viewportHeight * .5f, 0f);  
        cam.update();
		myball = new Circle(myworld, 0, BodyType.DynamicBody, 1, 1.0f, 0.7f,
				GlobalSettings.SCREEN_WIDTH * 0.33f, 200, 0, 0, 0, "",
				"images/ball.png", "", 32);
		objmanager.objects.add(myball);
		ground = new Rectangle(myworld, 0, BodyType.StaticBody, 1, 0.3f, 0.7f,
				GlobalSettings.SCREEN_WIDTH / 2,
				GlobalSettings.GROUND_HEIGHT / 2, 0, 0, 0, "",
				"images/ground.png", "", GlobalSettings.SCREEN_WIDTH,
				GlobalSettings.GROUND_HEIGHT);
		objmanager.objects.add(ground);
		box = new Rectangle(myworld, 0, BodyType.DynamicBody, 1, 0.3f, 0.8f,
				GlobalSettings.SCREEN_WIDTH * 0.65f, 500, 0, 0, 40, "",
				"images/crate.png", "", 64, 64);
		objmanager.objects.add(box);
		// car = new Car(myworld, 0, BodyType.DynamicBody, 10, 0.3f, 0,
		// GlobalSettings.SCREEN_WIDTH * 0.66f, 500, 0, 0, 0, "",
		// "images/carbody.png", "", "carbody.png", "images/carbody", 307,
		// 145);
		// objmanager.objects.add(car);
		input = new InputHandler();
		Gdx.input.setInputProcessor(input);
		debugger = new Box2DDebugRenderer(true, true, true, true);
		currTime = System.currentTimeMillis() / 1000.0;
		batch = new SpriteBatch();
		font = new BitmapFont();
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render()
	{
		double pvsTime = currTime;
		currTime = System.currentTimeMillis() / 1000.0;
		double frameTime = currTime - pvsTime;
		double alpha;
		accumulator += frameTime;
		int nof=0;
		while (accumulator >= GlobalSettings.BOX_STEP)
		{
			resetSmoothStates();
			if (input.RIGHT == 1)
			{
				myball.body.applyForceToCenter(3, 0);
			}
			if (input.LEFT == 1)
			{
				myball.body.applyForceToCenter(-3, 0);
			}
			if (input.UP == 1)
			{
				myball.body.applyForceToCenter(0, 5.0f);
			}  
			myworld.step(GlobalSettings.BOX_STEP,
					GlobalSettings.VELOCITY_ITERATIONS,
					GlobalSettings.POSITION_ITERATIONS);
			accumulator -= GlobalSettings.BOX_STEP;
			nof++;
		}
		alpha=accumulator/GlobalSettings.BOX_STEP;
		myworld.clearForces();
		smoothState(alpha);
		objmanager.update();
		double testTime = System.currentTimeMillis() / 1000.0;
		Gdx.gl20.glViewport(0, 0, GlobalSettings.SCREEN_WIDTH, GlobalSettings.SCREEN_HEIGHT);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		debugger.render(myworld,cam.combined);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		objmanager.load_texture(batch);
		font.draw(batch, "Accumulator Time : " + Double.toString(accumulator),50, 100);
		font.draw(batch,"NOF : " + Integer.toString(nof), 50, 40);
		font.draw(batch,"Render Time : "+ Double.toString(System.currentTimeMillis() / 1000.0- testTime), 50, 80);
		font.draw(batch,"FPS : " + Integer.toString(Gdx.graphics.getFramesPerSecond()),50, 60);
		font.draw(batch, "Frame Time : " + Double.toString(frameTime), 50, 20);
		batch.end();
	}

	public void resetSmoothStates()
	{
		for (BaseObject obj : objmanager.objects)
		{
			obj.smoothPos = obj.body.getPosition();
			obj.smoothAngle = obj.body.getAngle();
		}
	}
	
	public void smoothState(double alpha)
	{
		for (BaseObject obj : objmanager.objects)
		{
			float x = (float) (alpha*obj.body.getPosition().x + (1.0-alpha)*obj.smoothPos.x);
			float y = (float) (alpha*obj.body.getPosition().y + (1.0-alpha)*obj.smoothPos.y);
			obj.smoothPos = new Vector2(x,y);
			obj.smoothAngle=(float) (alpha*obj.body.getAngle());
		}
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
