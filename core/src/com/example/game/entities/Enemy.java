package com.example.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Enemy extends Actor {
	
	public Vector2 velocity;
	float stateTime;
	boolean isAlive = true;
	public Rectangle bounds;
	
	public int hp;
	public int ap;
	
	private Texture enemyTexture;
	private Animation explosionAnim;
	private Array<TextureRegion> explosionTexture;
	private Player player;
	
	public Enemy(float x, float y, Player player, int ap, int hp) {
		
		// adding textures for animation
		enemyTexture = new Texture("textures/enemy.png");

		explosionTexture = new Array<TextureRegion>();
		explosionTexture.add(new TextureRegion(new Texture(
				"textures/explosion.png")));
		explosionTexture.add(new TextureRegion(new Texture(
				"textures/explosion2.png")));
		explosionTexture.add(new TextureRegion(new Texture(
				"textures/explosion3.png")));
		explosionAnim = new Animation(.1f, explosionTexture);
		
		this.player = player;
		this.ap = ap;
		this.hp = hp;
		
		setX(x);
		setY(y);
		setOrigin(getWidth(), getHeight());
		setWidth(20);
		setHeight(15);
		setUserObject(this.getClass());
		setOrigin(getWidth(), getHeight());
		
		velocity = new Vector2(0.0f, 0.0f);
		
		// bounds for simple collision
		bounds = new Rectangle(getX(), getY(), enemyTexture.getWidth(), enemyTexture.getHeight());

	}
	
	@Override
	public void act(float delta) {
	    super.act(delta);
	    
	    if (!isAlive) {
	    	stateTime += delta;
	    }
	    
	    if ((getY() < Gdx.graphics.getHeight() - getHeight()) && isAlive) {
	    	moveBy(-0.1f, velocity.y);
	    }
	    
	    if (getY() > 0 && isAlive) {
	    	moveBy(-1, velocity.y);
	    }
	    
	    bounds.x = getX();
	    bounds.y = getY();
	    
	    // if this enemy collide with player or is alive
	    if (this.bounds.overlaps(player.bounds) && isAlive) {
	    	// set enemy to isAlive = false (he's dying!)
	    	isAlive = false;
	    	// player lives will decrease by 1
	    	player.lives -= 1;
	    }
	    
	    // if the object will go outside of the screen or the exposion animation is ended, remove it from the stage
	    if ((getX() < 0 - getWidth() || getX() > Gdx.graphics.getWidth()) || explosionAnim.isAnimationFinished(stateTime)) {
	    	this.remove();
		}
	    
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		Color color = getColor();		
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);		
		if (isAlive) {
			batch.draw(enemyTexture, getX(), getY());
		} else {
			batch.draw(explosionAnim.getKeyFrame(stateTime, false), getX(), getY());
		}
	}
	
	public void disposeTextures() {
		enemyTexture.dispose();
		for (TextureRegion tr : explosionTexture) {
			tr.getTexture().dispose();
		}
	}
}
