package com.box2d.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Circle extends BaseObject
{
	int radius;
	
	public Circle(World myworld,int collisionGp,BodyType bodyType,float density,
			float restitution,float friction,float px,float py,float vx,float vy,float angle,String textag,String texfile,String texdir,int radius)
	{
		super(myworld,collisionGp,bodyType,density,restitution,friction,px,py,vx,vy,angle,textag,texfile,texdir);
		set_shape(radius);
		texwrap.destWidth=radius*2;
		texwrap.destHeight=radius*2;
		update();
	}
	
	public void set_shape(int rad)
	{
		radius=rad;
		fixtureDef.shape=new CircleShape();
		fixtureDef.shape.setRadius(ObjectManager.to_box(radius));
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
//		texwrap.dstX=(int)(ObjectManager.to_world(body.getPosition().x)-texwrap.destWidth/2);
//		texwrap.dstY=(int)(ObjectManager.to_world(body.getPosition().y)-texwrap.destHeight/2);
		texwrap.dstX=(int)(ObjectManager.to_world(smoothPos.x)-texwrap.destWidth/2);
		texwrap.dstY=(int)(ObjectManager.to_world(smoothPos.y)-texwrap.destHeight/2);

		texwrap.angle=body.getAngle();
		texwrap.originX=radius;
		texwrap.originY=radius;
//		Gdx.app.log("Position  Ball: ",Float.toString(ObjectManager.to_world(body.getPosition().x))+"\t"+Float.toString(ObjectManager.to_world(body.getPosition().y)));
//		Gdx.app.log("Velocity  Ball: ",Float.toString(ObjectManager.to_world(body.getLinearVelocity().x))+"\t"+Float.toString(ObjectManager.to_world(body.getLinearVelocity().y)));
	}
}
