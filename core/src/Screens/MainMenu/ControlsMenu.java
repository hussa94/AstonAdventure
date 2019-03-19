package Screens.MainMenu;

import Entities.Sounds;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class ControlsMenu implements Screen, Input.TextInputListener {

    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;

    //MainMenu instance
    private static Screens.MainMenu.ControlsMenu controlsMenuInstance;

    //Game
    private AstonAdventure game;

    //Background Texture
    private Texture background;

    //Sounds
    private Sounds Sm;

    /**
     * This method allows this class to follow the Singleton design pattern to allow only one instance of the MainMenu
     * class to be produced
     *
     * @param game {@link AstonAdventure} game
     * @return the single version of a MainMenu instance
     */
    static Screens.MainMenu.ControlsMenu getControlsMenuInstance(AstonAdventure game) {
        if (controlsMenuInstance == null) {
            controlsMenuInstance = new Screens.MainMenu.ControlsMenu(game);
        }
        return controlsMenuInstance;
    }

    /**
     * Constructor for the MainMenu class. Initialises the background textures and sounds. Stores the
     * game object.
     *
     * @param game The Game object.
     */
    private ControlsMenu(AstonAdventure game) {
        this.game = game;
        background = new Texture("Screens/MainMenu/MainMenu.png");
        Sm = new Sounds();
    }

    /**
     * Render method used to display the current menu screen and play menu music.
     *
     * @param delta Elapsed Time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Draw / Display background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //Play menu music upon startup and loop
        if (!Sm.isMusicPlaying() && (!Sm.isSoundPlaying())) {
            Sm.menu();
        }

        controlsScreen();

        //End
        game.batch.end();
    }

    /**
     * Method used when the game is on the control selection screen of the main menu. The method will update
     * the background and if a button is pressed a sound will play and a function will be performed (Based on
     * which button is pressed).
     */
    private void controlsScreen() {

        //Standard Background
        background = new Texture("Screens/MainMenu/ControlsMenu.png");

        //Returns 'up' to options menu screen
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(OptionsMenu.getOptionsMenuInstance(game));
        }

    }

    //Unused
    public void input(String text) {
        //this.text = text;
    }

    //Unused
    public void canceled() {
        // text = "canceled";
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
