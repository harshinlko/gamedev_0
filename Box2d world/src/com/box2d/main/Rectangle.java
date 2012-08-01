package com.box2d.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Rectangle extends BaseObject
{
	int width;
	int height;

	public Rectangle(World myworld,int collisionGp,BodyType bodyType,float density,
			float restitution,float friction,float px,float py,float vx,float vy,float angle,String textag,String texfile,String texdir,int width,int height)
	{
		super(myworld,collisionGp,bodyType,density,restitution,friction,px,py,vx,vy,angle,textag,texfile,texdir);
		set_shape(width,height);
	}
	
	public void set_shape(int w,int h)
	{
		width=w;
		height=h;
		PolygonShape box=new PolygonShape();
		box.setAsBox(ObjectManager.to_box(width/2),ObjectManager.to_box(height/2));
		fixtureDef.shape =box; 
		body.createFixture(fixtureDef);
	}
	
	@Override
	public void load_texture(SpriteBatch batch)
	{
		texwrap.load_texture(batch);
	}
	
	@Override
	public void update()
	{
		texwrap.dstX=(int)(ObjectManager.to_world(body.getPosition().x)-width/2);
		texwrap.dstY=(int)(ObjectManager.to_world(body.getPosition().y)-height/2);
		texwrap.destWidth=width;
		texwrap.destHeight=height;
		texwrap.angle=body.getAngle();
		texwrap.originX=width/2;
		texwrap.originY=height/2;
//		Gdx.app.log("Position  Box: ",Float.toString(ObjectManager.to_world(body.getPosition().y))+"\t"+Float.toString(body.getPosition().y));
//		Gdx.app.log("Velocity  Box: ",Float.toString(ObjectManager.to_world(body.getLinearVelocity().x))+"\t"+Float.toString(ObjectManager.to_world(body.getLinearVelocity().y)));
//		Gdx.app.log("angle :",Double.toString(body.getAngle())+"\t"+Double.toString(body.getAngle()/180.0f*Math.PI));
	}
}
