package Screens.MainMenu;

import Entities.Sounds;
import Game.AstonAdventure;
import Screens.Loading;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Main Menu Class is used to display the games main menu. From the main menu the user can
 * start the game, exit the game, change characters and view controls.
 */
public class MainMenu implements Screen, Input.TextInputListener {

    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;

    //MainMenu instance
    private static MainMenu mainMenuInstance;

    //Game
    private AstonAdventure game;

    //Background Texture
    private Texture background;

    //Sounds
    private Sounds Sm;

    /**
     * Constructor for the MainMenu class. Initialises the background textures and sounds. Stores the
     * game object.
     *
     * @param game The Game object.
     */
    private MainMenu(AstonAdventure game) {
        this.game = game;
        background = new Texture("Screens/MainMenu/MainMenu.png");
        Sm = new Sounds();
    }

    /**
     * This method allows this class to follow the Singleton design pattern to allow only one instance of the MainMenu
     * class to be produced
     * @param game {@link AstonAdventure} game
     * @return the single version of a MainMenu instance
     */
    public static MainMenu getMainMenuInstance(AstonAdventure game) {
        if (mainMenuInstance == null) {
            mainMenuInstance = new MainMenu(game);
        }
        return mainMenuInstance;
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

        mainScreen();

        //End
        game.batch.end();
    }

    /**
     * Method used when the game is on the main screen of the main menu. The method will update the background
     * and if a button is pressed a sound will play and a function will be performed (Based on which button
     * is pressed).
     */
    private void mainScreen() {

        //Play button
        if ((Gdx.input.getX() > 253) && (Gdx.input.getX() < 423) && ((Gdx.input.getY() > 126) && (Gdx.input.getY() < 172))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/MainMenuPlay.png");

            //Begins game upon press
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                }

                    //Start the game at level one
                    game.setLevel(1);
                    game.setScreen(Loading.getLoadingScreenInstance(game));

            }
        }

        //Options button
        else if ((Gdx.input.getX() > 195) && (Gdx.input.getX() < 461) && ((Gdx.input.getY() > 200) && (Gdx.input.getY() < 243))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/MainMenuOptions.png");

            //Switches to options menu screen upon press
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                }
                    game.setScreen(OptionsMenu.getOptionsMenuInstance(game));

            }
        }

        //Exit button
        else if ((Gdx.input.getX() > 253) && (Gdx.input.getX() < 407) && ((Gdx.input.getY() > 270) && (Gdx.input.getY() < 314))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/MainMenuExit.png");

            //Exits application when pressed
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                }
                    Gdx.app.exit();

            }
        }

        //Standard Background
        else {
            background = new Texture("Screens/MainMenu/MainMenu.png");
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
