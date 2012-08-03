package com.box2d.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BaseObject
{
	public World world;
	public Body body;
	public TexWrapper texwrap;
	public int collisionGroup;
	public FixtureDef fixtureDef;
	public Vector2 smoothPos;
	public float smoothAngle;
	public BaseObject(World myworld,int collisionGp,BodyType bodyType,float density,
			float restitution,float friction,float px,float py,float vx,float vy,float angle,String textag,String texfile,String texdir)
	{
		world=myworld;
		collisionGroup=collisionGp;
		BodyDef BodyDef = new BodyDef();
		BodyDef.type = bodyType;
		BodyDef.position.set(ObjectManager.to_box(px),ObjectManager.to_box(py));
		BodyDef.linearVelocity.set(new Vector2(vx,vy));
		BodyDef.angle=angle;
		body = world.createBody(BodyDef);
		smoothPos=new Vector2(vx,vy);
		smoothAngle=angle;
		fixtureDef=new FixtureDef();
 		fixtureDef.density=density;
 		fixtureDef.restitution=restitution;
 		fixtureDef.friction=friction;
		if(!(texfile==""))
		{
			texwrap=new TexWrapper(textag,texfile,texdir);
		}
	}
	
	public void load_texture(SpriteBatch batch)
	{
	}
	
	public void update()
	{
	}
}
