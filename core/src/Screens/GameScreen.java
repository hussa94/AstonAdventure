package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import entities.FemalePlayer;
import entities.Inventory;
import entities.Items;
import entities.Player;

public class GameScreen implements Screen {
    private static Texture backgroundTexture;
    private static Sprite backgroundSprite;
    private Animation<TextureRegion> animation;
    private TextureAtlas textureAtlas;
    private SpriteBatch batch;

    private Items items;
    private Inventory inventory;
    private float x, y, elapsedTime;
    private static float frameDuration;

//    private static final int BACKGROUND_WIDTH = 1920;
//    private static final int BACKGROUND_HEIGHT = 1080;

    private OrthographicCamera camera;

    private Player player;

    public static float SPEED = 100;

    public GameScreen() {
        x = 400;
        y = 400;
        frameDuration = 1 / 5f;

        batch = new SpriteBatch();
        backgroundTexture = new Texture("landscape.png");
        backgroundSprite = new Sprite(backgroundTexture);
        //Loads the TextureAtlas .atlas file
        textureAtlas = new TextureAtlas("characters.atlas");
        //Find the regions by name and add all frames for that ot animation object
        animation = new Animation<TextureRegion>(frameDuration, textureAtlas.findRegions("female/standing"));

        player = new FemalePlayer(x, y);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.position.set(x, y, 0);
        camera.update();

        //Set items and their co-ordinates
        items = new Items();
        items.setBackpackCoordinates(200, 350);
        items.setBookCoordinates(550, 150);
        items.setCoffeeCoordinates(300, 0);
        items.setShoesCoordinates(50, 100);

        //Set Inventory and its position
        inventory = new Inventory();
        inventory.setInventoryPositiion(60, 170);
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
        if (SPEED == 200) {
            frameDuration = 1 / 10f;
        } else {
            frameDuration = 1 / 5f;
        }


        //Draw items if they have not been picked up
        if (!items.backpackPick) {
            batch.draw(items.backpack, items.xBackpack, items.yBackpack);
        }
        if (!items.bookPick) {
            batch.draw(items.book, items.xBook, items.yBook);
        }
        if (!items.coffeePick) {
            batch.draw(items.coffee, items.xCoffee, items.yCoffee);
        }
        if (!items.shoesPick) {
            batch.draw(items.shoes, items.xShoes, items.yShoes);
        }

        //Draw inventory relative to players position
        batch.draw(animation.getKeyFrame(elapsedTime, true), x, y);
        batch.draw(inventory.HUD, (camera.position.x + inventory.xHUD), camera.position.y + inventory.yHUD);

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            animation = player.moveUp();

            y += SPEED * Gdx.graphics.getDeltaTime();
            camera.position.y += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            animation = player.moveDown();

            y -= SPEED * Gdx.graphics.getDeltaTime();
            camera.position.y -= SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            animation = player.moveRight();

            x += SPEED * Gdx.graphics.getDeltaTime();
            camera.position.x += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            animation = player.moveLeft();

            x -= SPEED * Gdx.graphics.getDeltaTime();
            camera.position.x -= SPEED * Gdx.graphics.getDeltaTime();
        } else {
            animation = player.standStill();
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //Check status of items
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

    public static float getFrameDuration() {
        return GameScreen.frameDuration;
    }
}
