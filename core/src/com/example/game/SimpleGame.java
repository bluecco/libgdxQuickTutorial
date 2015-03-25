package com.example.game;

import com.badlogic.gdx.Game;
import com.example.game.screen.SplashScreen;

public class SimpleGame extends Game {
	@Override
	public void create () {
		Assets.load();
		// get the default theme
		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render() {
        super.render(); 
    }
	
	public void dispose() {
    }
}
