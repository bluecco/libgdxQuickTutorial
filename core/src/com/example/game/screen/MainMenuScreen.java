package com.example.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.game.SimpleGame;

public class MainMenuScreen implements Screen {

	SimpleGame game;
	private Stage stage;
	TextButton playButton;
	TextButton aboutButton;

	public MainMenuScreen(final SimpleGame game) {
		
		this.game = game;
		stage = new Stage();

		Skin skin = new Skin();
		skin.add("menu_button", new Texture("menu_button.png"));

		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("menu_button");
		textButtonStyle.font = skin.getFont("default");

		skin.add("default", textButtonStyle);

		Gdx.input.setInputProcessor(stage);

		playButton = new TextButton("PLAY!", skin);
		aboutButton = new TextButton("About", skin);
		
		playButton.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	        	dispose();
	        	game.setScreen(new GameScreen(game, true));
	        }
	    });
		
		aboutButton.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
				dispose();
				//game.setScreen(new GameScreen(game, false));
	        }
	    });
		
		Table table = new Table();
		table.add(playButton);
		table.row();
		table.add(aboutButton).padTop(48);
		table.setFillParent(true);

		stage.addActor(table);

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
