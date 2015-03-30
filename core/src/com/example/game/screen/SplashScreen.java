package com.example.game.screen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.example.game.SimpleGame;

public class SplashScreen implements Screen {

	final SimpleGame game;
	private Stage stage;
	private OrthographicCamera camera;
	private Texture img;

	public SplashScreen(final SimpleGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		img = new Texture("badlogic.jpg");
		stage = new Stage();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		stage.clear();
		Image logo = new Image(img);
		
		logo.setWidth(width);
		logo.setHeight(height);
		
		// set a sequence of action to the logo image
		logo.addAction(sequence(delay(0.1f), fadeOut(0.5f), new Action() {
			
			@Override
			public boolean act(float delta) {
				game.setScreen(new MainMenuScreen(game));
				return false;
			}
		}));
		
		stage.addActor(logo);

	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		img.dispose();
		stage.dispose();
	}

}
