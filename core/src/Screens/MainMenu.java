package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import entities.Soundmanager;
import uk.ac.aston.team17.AstonAdventure;

import java.awt.*;

public class MainMenu implements Screen, Input.TextInputListener {
    private AstonAdventure game;
    private Texture background;
    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;
    private Soundmanager Sm;
    private String screen = "null";

public MainMenu(AstonAdventure game){
    this.game = game;
    background = new Texture("Screens/MainMenu/MainMenu.png");
    Sm = new Soundmanager();


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
//        game.batch.draw(playButton, 175, 20, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);


       if (!Sm.isMusicPlaying() && (!Sm.isSoundPlaying())) {
           Sm.menu();
        }

       if ((Gdx.input.getX() > 250) && (Gdx.input.getX() < 414) && ((Gdx.input.getY() > 156) && (Gdx.input.getY() < 203))) {
           background = new Texture("Screens/MainMenu/MainMenuPlay.png");

            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    screen = "game";
                    }
                }
       }

        if ((Gdx.input.getX() > 190) && (Gdx.input.getX() < 458) && ((Gdx.input.getY() > 229) && (Gdx.input.getY() < 274))) {
            background = new Texture("Screens/MainMenu/MainMenuOpt.png");

            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    screen = "Options";
                }
            }
        }

        if ((Gdx.input.getX() > 258) && (Gdx.input.getX() < 407) && ((Gdx.input.getY() > 302) && (Gdx.input.getY() < 346))) {
            background = new Texture("Screens/MainMenu/MainMenuExit.png");

            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                    screen = "Exit";
                }
            }
        }

       if (screen.equalsIgnoreCase("game")) {

           if (!Sm.isSoundPlaying()) {
               game.setScreen(new LevelOneLoad(game));
           }
        }

        if (screen.equalsIgnoreCase("options")) {
            if (!Sm.isSoundPlaying()) {
                game.setScreen(new LevelOneLoad(game));
            }
        }

        if (screen.equalsIgnoreCase("exit")) {
            if (!Sm.isSoundPlaying()) {
                Gdx.app.exit();
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


    }
}
