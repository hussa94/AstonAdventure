package uk.ac.aston.team17;

import Screens.MainMenu;


import Screens.Startup;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AstonAdventure extends Game {
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new Startup(this));
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
//		batch.dispose();
		//img.dispose();
	}


    }

