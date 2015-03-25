package com.example.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ScoreLabel extends Actor{
	
	public int currentScore;
	private BitmapFont font;
	
	public ScoreLabel() {
		currentScore = 0;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.setScale(0.5f);
		setName("score");
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.draw(batch, Integer.toString(currentScore), 250 / 2, 150 );
	}
	
}
