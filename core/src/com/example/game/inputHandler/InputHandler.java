package com.example.game.inputHandler;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.example.game.entities.Bullet;
import com.example.game.entities.Player;
import com.example.game.ui.ScoreLabel;

public class InputHandler implements InputProcessor {

	private Player player;
	private Stage stage;

	public InputHandler(Player player, Stage stage) {
		this.player = player;
		this.stage = stage;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		
		// basic player movements
		case Keys.W:
			player.velocity.y += 1;
			break;
		case Keys.S:
			player.velocity.y -= 1;
			break;
		case Keys.SPACE:
			// create a bullet
			stage.addActor(new Bullet(player, (ScoreLabel) stage.getRoot()
					.findActor("score")));
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		switch (keycode) {

		case Keys.W:
			player.velocity.y = 0;
			break;
		case Keys.S:
			player.velocity.y = 0;
			break;
		case Keys.SPACE:
			break;
		default:
			break;
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
