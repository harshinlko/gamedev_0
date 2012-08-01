package com.box2d.main;

import aurelienribon.bodyeditor.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Car extends BaseObject
{
	public Shape shape;
	public Circle front_tyre;
	public Circle rear_tyre;
	int ms;
	
	public Car(World myworld,int collisionGp,BodyType bodyType,float density,
			float restitution,float friction,float px,float py,float vx,float vy,float angle,String textag,String texfile,String texdir,String shapetag,String shapefile,int texwidth,int texheight)
	{
		super(myworld,collisionGp,bodyType,density,restitution,friction,px,py,vx,vy,angle,textag,texfile,texdir);
		set_shape(shapefile,shapetag);
		front_tyre=new Circle(myworld,0,BodyType.DynamicBody,1,0.6f,0,ObjectManager.to_world(body.getPosition().x)+75,ObjectManager.to_world(body.getPosition().y)+20,0,0,0,"","images/tyre.png","",32);
		rear_tyre=new Circle(myworld,0,BodyType.DynamicBody,1,0.6f,0,ObjectManager.to_world(body.getPosition().x)+240,ObjectManager.to_world(body.getPosition().y)+20,0,0,0,"","images/tyre.png","",32);
		set_joints(myworld);
		texwrap.destWidth=texwidth;
		texwrap.destHeight=texheight;
		update();
	}
	
	public void set_shape(String shapefile,String shapetag)
	{
		 BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal(shapefile));
		 loader.attachFixture(body,shapetag,fixtureDef,ObjectManager.to_box(307));
	}
	
	public void set_joints(World myworld)
	{
		RevoluteJointDef revolutejoint =new RevoluteJointDef();
		revolutejoint.initialize(body,front_tyre.body,front_tyre.body.getWorldCenter());
		revolutejoint.enableMotor=true;
		revolutejoint.motorSpeed=100.0f;
		RevoluteJoint joint_front = (RevoluteJoint)myworld.createJoint(revolutejoint);
		revolutejoint.initialize(body,rear_tyre.body,rear_tyre.body.getWorldCenter());
		RevoluteJoint joint_rear = (RevoluteJoint)myworld.createJoint(revolutejoint);
		joint_rear.enableMotor(true);
		joint_rear.setMotorSpeed(100);
	}
	
	@Override
	public void load_texture(SpriteBatch batch)
	{
		texwrap.load_texture(batch);
		front_tyre.load_texture(batch);
		rear_tyre.load_texture(batch);
	}
	
	@Override
	public void update()
	{
		front_tyre.update();
		rear_tyre.update();
		texwrap.dstX=ObjectManager.to_world(body.getPosition().x);
		texwrap.dstY=ObjectManager.to_world(body.getPosition().y);
		texwrap.angle=body.getAngle();
		texwrap.originX=0;
		texwrap.originY=0;
//		Gdx.app.log("Position  Car: ",Float.toString(ObjectManager.to_world(body.getPosition().x))+"\t"+Float.toString(ObjectManager.to_world(body.getPosition().y)));
//		Gdx.app.log("World Center Car: ",Float.toString(ObjectManager.to_world(body.getLocalCenter().x))+"\t"+Float.toString(ObjectManager.to_world(body.getLocalCenter().y)));
//		Gdx.app.log("Velocity  Ball: ",Float.toString(ObjectManager.to_world(body.getLinearVelocity().x))+"\t"+Float.toString(ObjectManager.to_world(body.getLinearVelocity().y)));
//		Gdx.app.log("Angle Box: ",Float.toString(ObjectManager.to_world(body.getAngle())));
	}
}
