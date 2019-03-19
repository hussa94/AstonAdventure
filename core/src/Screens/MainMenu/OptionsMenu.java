package Screens.MainMenu;

import Entities.Sounds;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class OptionsMenu implements Screen, Input.TextInputListener {

    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;

    //MainMenu instance
    private static Screens.MainMenu.OptionsMenu optionsMenuInstance;

    private float elapsedTime;

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
    static Screens.MainMenu.OptionsMenu getOptionsMenuInstance(AstonAdventure game) {
        if (optionsMenuInstance == null) {
            optionsMenuInstance = new Screens.MainMenu.OptionsMenu(game);
        }
        return optionsMenuInstance;
    }

    /**
     * Constructor for the MainMenu class. Initialises the background textures and sounds. Stores the
     * game object.
     *
     * @param game The Game object.
     */
    private OptionsMenu(AstonAdventure game) {
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

        elapsedTime += Gdx.graphics.getDeltaTime();

        //Draw / Display background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //Play menu music upon startup and loop
        if (!Sm.isMusicPlaying() && (!Sm.isSoundPlaying())) {
            Sm.menu();
        }

        optionsScreen();

        //End
        game.batch.end();
    }

    public void optionsScreen() {
        //Characters button
        if ((Gdx.input.getX() > 134) && (Gdx.input.getX() < 536) && ((Gdx.input.getY() > 241) && (Gdx.input.getY() < 285))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/OptionsMenuCharacters.png");

            //Switches to character selection screen
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                }
                    elapsedTime = 0;
                    game.setScreen(CharactersMenu.getCharactersMenuInstance(game));

            }
        }

        //Controls button
        else if ((Gdx.input.getX() > 178) && (Gdx.input.getX() < 500) && ((Gdx.input.getY() > 155) && (Gdx.input.getY() < 202))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/OptionsMenuControls.png");

            //Switches to controls screen when pressed
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();

                }
                    game.setScreen(ControlsMenu.getControlsMenuInstance(game));
                    elapsedTime = 0;

            }
        }

        //Standard Background
        else {
            background = new Texture("Screens/MainMenu/OptionsMenu.png");
        }

        //Return 'up' to main screen
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && (elapsedTime > 0.5)) {
            elapsedTime = 0;
            game.setScreen(MainMenu.getMainMenuInstance(game));
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

