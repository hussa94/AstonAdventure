package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import entities.Soundmanager;
import uk.ac.aston.team17.AstonAdventure;

import java.awt.*;

public class MenuScreen implements Screen, Input.TextInputListener {
    private  AstonAdventure game;
    private Texture playButton;
    private Texture background;
    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;
    private static final int PLAY_BUTTON_HEIGHT = 100;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private Soundmanager Sm;
    private TextField nameInput;
    private String text;

public MenuScreen(AstonAdventure game){
    this.game = game;
    background = new Texture("newLargeWelcome.png");
   playButton = new Texture("button.png");
    Sm = new Soundmanager();
    nameInput = new TextField();


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

//

       Sm.create();


       if ((Gdx.input.getX() > 175) && (Gdx.input.getX() < 475) && ((Gdx.input.getY() > 360) && (Gdx.input.getY() < 460))) {
        if (Gdx.input.isTouched()) {
                //    Gdx.input.getTextInput(this, "Title", "Insert name", "");
                //    Gdx.app.log("Text", text);

                game.setScreen(new GameScreen());
            }

       }
            game.batch.end();



}


    public void input(String text){
        //this.text = text;

    }
    public void canceled() {
       // text = "canceled";

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
        background.dispose();
        playButton.dispose();


    }
}
