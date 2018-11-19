package Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.ac.aston.team17.AstonAdventure;
import com.badlogic.gdx.Input.TextInputListener;

import java.awt.*;

public class EnrollmentScreen implements Screen, TextInputListener {

    private  AstonAdventure game;
    private SpriteBatch batch;
    private TextField nameInput;
    private String text;
    private Texture background;
    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 500;

    public EnrollmentScreen () {

        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("menu-flat.png");
      //  nameInput = new TextField();
    }
        @Override
        public void show () {

        }

        @Override
        public void render ( float delta) {
            Gdx.gl.glClearColor(0, 0, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(background, 0 , 0,BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
            if (Gdx.input.justTouched()) {
                Gdx.input.getTextInput(this, "Title", "Insert name", "");
                Gdx.app.log("Text", text);
            }
                //batch.draw(nameInput, "insert name here" , );
                batch.end(); }


        public void input(String text){
        this.text = text;

        }
        public void canceled() {
        text = "canceled";

    }

        @Override
        public void resize ( int width, int height){

        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {

        }
    }

