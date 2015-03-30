package com.example.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.example.game.ui.ScoreLabel;

public class Bullet extends Actor {

	private Sprite bulletSprite = new Sprite(new Texture("textures/blank.png")); 
	private Rectangle bounds;
	private ScoreLabel scoreLabel;
	
	/**
	 * 
	 * Bullet constructor
	 * 
	 * @param player - used to get the player position
	 * @param scoreLabel - used to update the score
	 */
	public Bullet(Player player, ScoreLabel scoreLabel) {
		super();
		this.scoreLabel = scoreLabel;
		
		setUserObject(this.getClass());
		
		// setting bullet position
		setPosition(player.getX() + player.getWidth(),
				player.getY() + player.getHeight() / 2);
		
		// setting bullet bounds
		bounds = new Rectangle(getX(), getY(), bulletSprite.getWidth(), bulletSprite.getWidth());
	}

	@Override
	public void act(float delta) {
		
		super.act(delta);
		moveBy(2, 0);
		bounds.x = getX();
		
		// check if the bullet collides with an enemy
		for (Actor otherActor : getStage().getActors()) {
			
			if (otherActor.getUserObject() != null && otherActor.getUserObject().equals(Enemy.class)) {
				
				Enemy enemy = (Enemy) otherActor;
				
				// if collides, remove it and update game info (score) and enemy to isAlive = false
				if(this.bounds.overlaps(enemy.bounds)){
					
					remove();
					
					if ((--enemy.hp) == 0) {
						enemy.isAlive = false;
						scoreLabel.currentScore += 10;
					}
				}
			}
		}
		
		// if the bullet go outside of the screen, remove it
		if ((getX() < 0 || getX() > Gdx.graphics.getWidth())) {
			remove();
		}
			
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		super.draw(batch, parentAlpha);
		batch.draw(bulletSprite, getX(), getY());
		
	}

}