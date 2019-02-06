package Screens;

import Entities.Sounds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import Game.AstonAdventure;

/**
 * The class Startup is a screen shown at the launch of the game. It displays the team information / logo
 * and plays a sound.
 */
public class Startup implements Screen {

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
     * Constructor for the Startup Screen. Initialises the background textures and sounds. Stores the
     * game object.
     * @param game The game object used for save data.
     */
    public Startup(AstonAdventure game) {
        this.game = game;
        background = new Texture("Screens/Startup/INTRO.png");
        Sm = new Sounds();
    }

    /**
     * Render method used to display the background for two seconds, as well as play a startup sound.
     * Switches to the main menu of the game upon completion.
     * @param delta Elapsed time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Draw / Display background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //Play sound / Switch Screen
        if (elapsedTime == 0) {
            Sm.startup();
        }
        else if (elapsedTime >= 2) {
            game.setScreen(new MainMenu(game));
        }

        //Update elapsed time
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



