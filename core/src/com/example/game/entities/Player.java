package com.example.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Player extends Actor {

	private AtlasRegion icon;
	private float stateTime;
	
	public int lives;
	public Vector2 velocity;
	public Rectangle bounds;
	
	Array<TextureRegion> playerTextures;
	Animation playerAnimation;

	public Player(float x, float y) {
		setX(x);
		setY(y);
		setOrigin(getWidth(), getHeight());
		setWidth(16);
		setHeight(15);
		setUserObject(this.getClass());
		
		lives = 3;
		velocity = new Vector2(0.0f, 0.0f);
		
		//bounds for simple collision detection
		bounds = new Rectangle(getX(), getY(), 16, 15);
		
		// add textures for animation
		playerTextures = new Array<TextureRegion>();
		playerTextures.add(new TextureRegion(new Texture("textures/player.png")));
		playerTextures.add(new TextureRegion(new Texture("textures/player2.png")));
		playerAnimation = new Animation(0.30f, playerTextures);
	}
	
	public Player(float x, float y, Animation playerAnimation) {

		this.playerAnimation = playerAnimation;
		
		setX(x);
		setY(y);
		setOrigin(getWidth(), getHeight());
		setWidth(16);
		setHeight(15);
		setUserObject(this.getClass());
		
		lives = 3;
		velocity = new Vector2(0.0f, 0.0f);
		bounds = new Rectangle(getX(), getY(), 16, 15);

	}

	public AtlasRegion getIcon() {
		return this.icon;
	}

	@Override
	public void act(float delta) {
		
		super.act(delta);
		stateTime += delta;
		
		// simple in-bounds player management
		if ((getY() + velocity.y >= 0)
				&& (getY() + velocity.y <= 150 - getHeight())) {
			moveBy(0, velocity.y);
		}

		bounds.x = getX();
		bounds.y = getY();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(playerAnimation.getKeyFrame(stateTime, true), getX(), getY());	
	}
	
	public void disposeTextures() {
		for (TextureRegion tr : playerTextures) {
			tr.getTexture().dispose();
		}
	}
}
