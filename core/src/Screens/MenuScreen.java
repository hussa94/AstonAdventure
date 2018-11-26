package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import entities.Soundmanager;
import uk.ac.aston.team17.AstonAdventure;


public class MenuScreen implements Screen {
    private AstonAdventure game;
    private Texture playButton;
    private Texture background;
    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 500;
    private static final int PLAY_BUTTON_HEIGHT = 100;
    private static final int PLAY_BUTTON_WIDTH = 300;
    Soundmanager Sm = new Soundmanager();

    public MenuScreen(AstonAdventure game) {
        this.game = game;
        background = new Texture("menu-flat.png");
        playButton = new Texture("button.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        game.batch.draw(playButton, 175, 20, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen());
            Sm.create();
        }

        game.batch.end();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
