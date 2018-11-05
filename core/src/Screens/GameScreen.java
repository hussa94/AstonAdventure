package Screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import uk.ac.aston.team17.AstonAdventure;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {
    AstonAdventure game;
    Texture logo;
    SpriteBatch batch;
    float x, y;
    public static final float SPEED = 100;


    public GameScreen() {
        this.game = game;
        logo = new Texture("badlogic.jpg");
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(logo, x, y);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += SPEED*Gdx.graphics.getDeltaTime();
         } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= SPEED*Gdx.graphics.getDeltaTime();
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
