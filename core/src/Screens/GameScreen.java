package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.FemalePlayer;
import entities.Player;
import uk.ac.aston.team17.AstonAdventure;

public class GameScreen implements Screen {
    AstonAdventure game;

    SpriteBatch batch;
    Texture texture;
    Player femalePlayer;
    float x, y;
    public static final float SPEED = 100;


    public GameScreen() {
        femalePlayer = new FemalePlayer(0, 0);
        this.game = game;
        texture = new Texture("badlogic.jpg");

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
        batch.draw(texture, x, y);
        
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            texture = new Texture("Sprites/Characters/walk_up_f.png");
            y += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            texture = new Texture("Sprites/Characters/walk_down_f.png");
            y -= SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            texture = new Texture("Sprites/Characters/walk_right_f.png");
            x += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            texture = new Texture("Sprites/Characters/walk_left_f.png");
//            femalePlayer.movementLeft(delta);
            x -= SPEED * Gdx.graphics.getDeltaTime();
        } else {
            texture = new Texture("Sprites/Characters/walk_down_f.png");
        }
        batch.end();
//
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
