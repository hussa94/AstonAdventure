package Screens;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Entities.Soundmanager;
import Game.AstonAdventure;

public class ExitScreen implements Screen{

    private  AstonAdventure game;
    private Texture exitButton;
    private Texture moreButton;
    private Texture background;
    private static final int BACKGROUND_WIDTH = 680;
    private static final int BACKGROUND_HEIGHT = 480;
    private static final int EXIT_BUTTON_HEIGHT = 80;
    private static final int EXIT_BUTTON_WIDTH = 180;
    private static final int MORE_BUTTON_HEIGHT = 120;
    private static final int MORE_BUTTON_WIDTH = 300;
    private Soundmanager Sm;
    private String text;
    private SpriteBatch batch;


    public ExitScreen() {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("Screens/Exit/AstonExit.png");
        exitButton = new Texture("Screens/Exit/ExitButton.png");
        moreButton = new Texture("Screens/Exit/FindOutButton.png");

    }

    @Override
    public void show() {


    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        batch.draw(exitButton, 240, 20, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        batch.draw(moreButton, 180, 150,  MORE_BUTTON_WIDTH, MORE_BUTTON_HEIGHT);

        if(Gdx.input.isTouched()){


                if ((Gdx.input.getX() > 240) && (Gdx.input.getX() < 420) && ((Gdx.input.getY() > 380) && (Gdx.input.getY() < 500))) {


                       Gdx.app.exit();

                    //game.setScreen(new ExitScreen());
                }


               else if ((Gdx.input.getX() > 180) && (Gdx.input.getX() < 480) && (Gdx.input.getY() > 210) && ((Gdx.input.getY() < 325))) {


                    Gdx.net.openURI("https://www2.aston.ac.uk/");
                }
            }


    batch.end();
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
