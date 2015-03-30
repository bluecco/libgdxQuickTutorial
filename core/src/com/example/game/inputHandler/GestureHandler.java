package com.example.game.inputHandler;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.example.game.entities.Bullet;
import com.example.game.entities.Player;
import com.example.game.ui.ScoreLabel;

public class GestureHandler implements GestureListener {

	private Player player;
	private Stage stage;

	public GestureHandler(Player player, Stage stage) {
		this.player = player;
		this.stage = stage;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		
		//fire a bullet after a tap
		stage.addActor(new Bullet(player, (ScoreLabel) stage.getRoot()
				.findActor("score")));
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		
		// basic player movements
		
		// move down the player
		if (deltaY > 1) {
			player.velocity.y = -1;
		}
		
		// move up the player
		if (deltaY < -1) {
			player.velocity.y = 1;
		}
		
		// stop the player
		if (deltaY > -1f && deltaY < 1f) {
			player.velocity.y = 0;
		}

		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		player.velocity.y = 0;
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

}
