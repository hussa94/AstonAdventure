package Screens;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import Entities.Sounds;
import Game.AstonAdventure;

/**
 * The class ExitScreen is used to display the final scoreboard/end game screens when the
 * user has completed the game. Upon completion, the user can either exit the game or move
 * on to Aston's website.
 */
public class ExitScreen implements Screen{

    //Game
    private  AstonAdventure game;

    //Background
    private Texture background;
    private static final int BACKGROUND_WIDTH = 680;
    private static final int BACKGROUND_HEIGHT = 480;

    //Buttons
    private Texture exitButton;
    private Texture moreButton;
    private static final int EXIT_BUTTON_HEIGHT = 80;
    private static final int EXIT_BUTTON_WIDTH = 180;
    private static final int MORE_BUTTON_HEIGHT = 120;
    private static final int MORE_BUTTON_WIDTH = 300;

    /**
     * Constructor for ExitScreen. Initialises the background and button textures and sounds.
     * Stores the game object.
     * @param game The Game object.
     */
    ExitScreen(AstonAdventure game) {

        this.game = game;
        background = new Texture("Screens/Exit/AstonExit.png");
        exitButton = new Texture("Screens/Exit/ExitButton.png");
        moreButton = new Texture("Screens/Exit/FindOutButton.png");
    }


    /**
     * Render method to display the exit screen and buttons. Will exit the app or redirect
     * based on user input.
     * @param delta Elapsed time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Display / Draw Background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //Display / Draw Buttons
        game.batch.draw(exitButton, 240, 20, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        game.batch.draw(moreButton, 180, 150,  MORE_BUTTON_WIDTH, MORE_BUTTON_HEIGHT);

        if(Gdx.input.isTouched()){

                //Exits game if button is clicked
                if ((Gdx.input.getX() > 240) && (Gdx.input.getX() < 420) && ((Gdx.input.getY() > 380) && (Gdx.input.getY() < 500))) {

                       Gdx.app.exit();
                }

                //Directs to website if button is clicked
                else if ((Gdx.input.getX() > 180) && (Gdx.input.getX() < 480) && (Gdx.input.getY() > 210) && ((Gdx.input.getY() < 325))) {

                    Gdx.net.openURI("https://www2.aston.ac.uk/");
                }
            }

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

    }
}