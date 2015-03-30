package com.example.game.screen;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.example.game.SimpleGame;
import com.example.game.entities.Enemy;
import com.example.game.entities.Player;
import com.example.game.factory.EnemyFactory;
import com.example.game.inputHandler.GestureHandler;
import com.example.game.inputHandler.InputHandler;
import com.example.game.ui.ScoreLabel;

public class GameScreen implements Screen {

	SimpleGame game;
	Stage gameStage;
	
	// 1 second interval for spawns
	float spawnTime = 1;

	Player player;
	Enemy enemy;
	EnemyFactory enemyFactory;
	ScoreLabel scoreLabel;
	
	public GameScreen(final SimpleGame game, boolean isEndless) {
		this.game = game;

		gameStage = new Stage();
		enemyFactory = EnemyFactory.instantiate();

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// createEnemy
		createEnemy(delta);
		
		// if player has lives left, continue to play
		if (player.lives > 0) {

			gameStage.act(delta);
			gameStage.draw();

		} else {
			//else game over
			game.setScreen(new MainMenuScreen(game));
		}

	}

	@Override
	public void resize(int width, int height) {
		
		// set the camera
		OrthographicCamera camera = (OrthographicCamera) gameStage
				.getViewport().getCamera();
		camera.viewportWidth = 250;
		camera.viewportHeight = 150;
		camera.position.x = 250 / 2;
		camera.position.y = 150 / 2;

	}

	@Override
	public void show() {
		gameStage.clear();
		
		// initialize the player and add it to the gameStage
		player = new Player(10, 150.0f / 2);
		gameStage.addActor(player);
		
		// initialize the score label and add it to the gameStage
		scoreLabel = new ScoreLabel();
		scoreLabel.setName("score");
		gameStage.addActor(scoreLabel);
		
		// add inputHander (keyboard) and gestureDetector (touch)
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(new InputHandler(player, gameStage));
		inputMultiplexer.addProcessor(new GestureDetector(new GestureHandler(
				player, gameStage)));
		
		// set the inputProcessor supported
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	/**
	 * Create an enemy
	 * 
	 * @param delta time
	 */
	public void createEnemy(float delta) {
		// increase spawn time by delta
		spawnTime += delta;
		
		// if 3 seconds has passed, create an enemy
		if ((int) spawnTime % 3 == 0) {
			
			// reset spawnTime to 1 second
			spawnTime = 1;
			
			//TODO: delete hard coded (x,y) position
			int yPos = 0;
			switch (new Random().nextInt(3)) {
			case 0:
				yPos = (150 / 2) - 40;
				break;
			case 1:
				yPos = 150 / 2;
				break;
			case 2:
				yPos = (150 / 2) + 40;
				break;
			}
			
			// add the enemy to the gameStage
			gameStage.addActor(enemyFactory.createEnemy(
					new Random().nextInt(3), 250.0f, yPos, player));
		}
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		
		// manual texture's dispose
		
		player.disposeTextures();
		for (Actor actor : gameStage.getActors()) {
			if (actor instanceof Enemy) {
				((Enemy) actor).disposeTextures();
			}
		}
		
		gameStage.dispose();
	}
}
