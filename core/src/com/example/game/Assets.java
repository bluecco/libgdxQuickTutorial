package com.example.game;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
	
	public static HashMap<String, TextureAtlas> atlasesMap;
	
	// Game Data
	
	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static TextureAtlas loadTextureAtlas(String file) {
		return new TextureAtlas(Gdx.files.internal(file));
	}

	public static void load() {
		atlasesMap = new HashMap<String, TextureAtlas>();
		
		// TODO: load atlas used in game
		//atlasesMap.put("theme_base", loadTextureAtlas("textures/theme_Base.atlas"));
		
	}

}
