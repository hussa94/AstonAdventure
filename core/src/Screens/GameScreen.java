package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import uk.ac.aston.team17.AstonAdventure;

public class GameScreen implements Screen {
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    AstonAdventure game;
    Animation<TextureRegion> animation;
    TextureAtlas textureAtlas;
    SpriteBatch batch;
    Texture texture;
//    Player femalePlayer;
    float x, y,elapsedTime, frameDuration;

    public static final float SPEED = 100;



    public GameScreen() {
        x=50;
        y=50;
        frameDuration = 1/5f;
        batch = new SpriteBatch();
        backgroundTexture = new Texture("landscape.png");
        backgroundSprite =new Sprite(backgroundTexture);
        //Loads the TextureAtlas .atlas file
        textureAtlas = new TextureAtlas("femaleCh.atlas");
        //Find the regions by name and add all frames for that ot animation object
        animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("standing/standing"));
        //        femalePlayer = new FemalePlayer(0, 0);
        this.game = game;
        texture = new Texture("badlogic.jpg");


    }

    public void renderBackground() {
        backgroundSprite.draw(batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        renderBackground();
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(elapsedTime,true),x ,y);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("up/up"));

            y += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("down/down"));

            y -= SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("right/right"));

            x += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("left/left"));

            x -= SPEED * Gdx.graphics.getDeltaTime();
        } else {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("standing/standing"));

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
        batch.dispose();

    }
}
