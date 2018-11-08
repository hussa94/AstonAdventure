package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import entities.Inventory;
import entities.Items;
import uk.ac.aston.team17.AstonAdventure;

public class GameScreen implements Screen {
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    AstonAdventure game;
    Animation<TextureRegion> animation;
    TextureAtlas textureAtlas;
    SpriteBatch batch;
    Texture texture;
    Items items = new Items();
    Inventory inventory = new Inventory();
    float x, y,elapsedTime, frameDuration;

    OrthographicCamera camera;

    public static float SPEED = 100;

    public GameScreen() {
        x=50;
        y=50;
        frameDuration = 1/5f;
        batch = new SpriteBatch();
        backgroundTexture = new Texture("core/assets/landscape.png");
        backgroundSprite =new Sprite(backgroundTexture);
        //Loads the TextureAtlas .atlas file
        textureAtlas = new TextureAtlas("core/assets/femaleCh.atlas");
        //Find the regions by name and add all frames for that ot animation object
        animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("female/standing"));

        this.game = game;
        texture = new Texture("core/assets/badlogic.jpg");

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.position.set(x,y,0);
        camera.update();
    }

    public void renderBackground() {
        backgroundSprite.draw(batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        renderBackground();
        elapsedTime += Gdx.graphics.getDeltaTime();

        if(!items.backpackPick) {
            batch.draw(items.backpack, items.xBackpack, items.yBackpack);
        }
        if(!items.bookPick) {
            batch.draw(items.book, items.xBook, items.yBook);
        }
        if(!items.coffeePick) {
            batch.draw(items.coffee, items.xCoffee, items.yCoffee);
        }
        if(!items.shoesPick) {
            batch.draw(items.shoes, items.xShoes, items.yShoes);
        }

        batch.draw(inventory.HUD, inventory.xHUD, inventory.yHUD);
        batch.draw(animation.getKeyFrame(elapsedTime,true),x ,y);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("female/up"));

            y += SPEED * Gdx.graphics.getDeltaTime();
            camera.position.y += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("female/down"));

            y -= SPEED * Gdx.graphics.getDeltaTime();
            camera.position.y -= SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("female/right"));

            x += SPEED * Gdx.graphics.getDeltaTime();
            camera.position.x += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("female/left"));

            x -= SPEED * Gdx.graphics.getDeltaTime();
            camera.position.x -= SPEED * Gdx.graphics.getDeltaTime();
        } else {
            animation = new Animation<TextureRegion>(frameDuration,textureAtlas.findRegions("female/standing"));
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        items.hasPlayerPickedBackpack(x, y);
        items.hasPlayerPickedBook(x, y);
        items.hasPlayerPickedCoffee(x, y);
        items.hasPlayerPickedShoes(x, y);
        inventory.checkHUDStatus(items);

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
        batch.dispose();

    }
}
