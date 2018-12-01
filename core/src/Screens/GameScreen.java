package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import entities.FemalePlayer;
import entities.Inventory;
import entities.Items;
import entities.Player;

import static java.lang.Thread.sleep;

public class GameScreen implements Screen {
    private TiledMap tiledMap;
    private TiledMapRenderer mapRenderer;
    private Animation<TextureRegion> animation;
    private TextureAtlas textureAtlas;
    private SpriteBatch batch;

    private Items items;
    private Inventory inventory;
    private float x, y, elapsedTime;
    private final float x_min = 400;
    private final float x_max = 3700;
    private final float y_min = 300;
    private final float y_max = 1800;
    private static float frameDuration;

    private boolean drawcheck;

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
        //Loads the TextureAtlas .atlas file
        textureAtlas = new TextureAtlas("characters.atlas");
        //Find the regions by name and add all frames for that ot animation object
        animation = new Animation<TextureRegion>(frameDuration, textureAtlas.findRegions("female/standing"));

        player = new FemalePlayer(x, y);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        tiledMap = new TmxMapLoader().load("tiles/levelonemap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.position.set(x, y, 0);
        camera.update();

        //Set items and their co-ordinates
        items = new Items();
        items.setBackpackCoordinates(500, 550);
        items.setBookCoordinates(600, 450);
        items.setCoffeeCoordinates(1800, 1200);
        items.setShoesCoordinates(1000, 1000);

        //Set Inventory and its position
        inventory = new Inventory();
        inventory.setInventoryPositiion(60, 170);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Camera is set to view TiledMap from TMX file
        //TiledMapRender renders the map
        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.begin();

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

        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            batch.draw(inventory.HUD, (camera.position.x + inventory.xHUD), camera.position.y + inventory.yHUD);
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
            && y < y_max){
            animation = player.moveUp();

            y += SPEED * Gdx.graphics.getDeltaTime();
            camera.position.y += SPEED * Gdx.graphics.getDeltaTime();
        } else if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
            && y > y_min) {
            animation = player.moveDown();

            y -= SPEED * Gdx.graphics.getDeltaTime();
            camera.position.y -= SPEED * Gdx.graphics.getDeltaTime();
        } else if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            && x < x_max) {
            animation = player.moveRight();

            x += SPEED * Gdx.graphics.getDeltaTime();
            camera.position.x += SPEED * Gdx.graphics.getDeltaTime();
        } else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            && x > x_min) {
            animation = player.moveLeft();

            x -= SPEED * Gdx.graphics.getDeltaTime();
            camera.position.x -= SPEED * Gdx.graphics.getDeltaTime();
        } else {
            animation = player.standStill();
        }

        camera.update();


        batch.setProjectionMatrix(camera.combined);

        //Check status of items
            if (items.hasPlayerPickedBackpack(x, y)) {
                for (int i = 0; i < 100000; i++) {
                    batch.draw(inventory.HUD, (camera.position.x + inventory.xHUD), camera.position.y + inventory.yHUD);
                }
            }

            if (items.hasPlayerPickedBook(x, y)) {
                for (int i = 0; i < 100000; i++) {
                    batch.draw(inventory.HUD, (camera.position.x + inventory.xHUD), camera.position.y + inventory.yHUD);
                }
            }

            if (items.hasPlayerPickedCoffee(x, y)) {
                for (int i = 0; i < 100000; i++) {
                    batch.draw(inventory.HUD, (camera.position.x + inventory.xHUD), camera.position.y + inventory.yHUD);
                }
            }

            if (items.hasPlayerPickedShoes(x, y)) {
                for (int i = 0; i < 100000; i++) {
                    batch.draw(inventory.HUD, (camera.position.x + inventory.xHUD), camera.position.y + inventory.yHUD);
                }
            }


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
