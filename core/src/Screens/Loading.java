package Screens;

import Entities.Sounds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import Game.AstonAdventure;

/**
 * The Loading class is used to display the loading screens for each of the levels of the game.
 * From this, the game moves on to the loaded level.
 */
public class Loading implements Screen {

    //Game
    private AstonAdventure game;

    //Background Texture
    private Texture background;
    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;

    //Sounds
    private Sounds Sm;

    //Timer
    private float elapsedTime;


    /**
     * Constructor for the Loading class. Initialises the background textures and sounds. Stores
     * the game object.
     * @param game The Game object.
     */
    Loading(AstonAdventure game) {

        this.game = game;
        background = new Texture("Screens/Loading/Level1INTRO.png");
        Sm = new Sounds();
    }

    /**
     * Render method to display the loading screen and move on to the loaded level after enough
     * time has elapsed.
     * @param delta Elapsed time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Draw Background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //Sounds
        Sm.dispose();

        //Begins level after elapsed time.
        if (elapsedTime >= 2) {
            game.setScreen(new LevelOne(game));
        }
        elapsedTime += Gdx.graphics.getDeltaTime();

        //End
        game.batch.end();
    }

    //Unused
    @Override
    public void show() {

    }

    //Unused
    @Override
    public void resize(int width, int height) {

    }

    //Unused
    @Override
    public void pause() {

    }

    //Unused
    @Override
    public void resume() {

    }

    //Unused
    @Override
    public void hide() {

    }

    //Unused
    @Override
    public void dispose() {
        background.dispose();
    }
}