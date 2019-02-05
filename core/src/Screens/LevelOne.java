package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import Entities.*;
import Game.AstonAdventure;

/**
 * The class LevelOne is used to play the first level (Enrolment) of the video game, with
 * the user learning basic controls and finding the university.
 */
public class LevelOne implements Screen {

    //Game
    private AstonAdventure game;

    //Sounds
    private SoundManager Sm;

    //Map
    private TiledMap tiledMap;
    private TiledMapRenderer mapRenderer;

    //Player Textures Animation
    private Animation<TextureRegion> character;
    private TextureAtlas textureAtlasCharacter;
    private static float frameDuration;

    //Classes / Objects
    private Player player;
    private Items items;
    private Inventory inventory;
    private Text text;

    //Character
    private float x, y, elapsedTime, elapsedTimeInventory, elapsedTimeText;
    public static float speed;

    //Camera
    private OrthographicCamera camera;
    private final float xMinCamera;
    private final float xMaxCamera;
    private final float yMinCamera;
    private final float yMaxCamera;

    /**
     * The Constructor for LevelOne initialises the map and player textures, camera,
     * items, inventory and sounds. Stores the game object.
     * @param game The game object.
     */
    public LevelOne(AstonAdventure game) {

        this.game = game;

        x = 400;
        y = 400;

        //Set up Map
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        tiledMap = new TmxMapLoader().load("tiles/levelonemap.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //Set Camera and its position
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.position.set(x, y, 0);
        camera.update();
        xMinCamera = 400;
        xMaxCamera = 3700;
        yMinCamera = 300;
        yMaxCamera = 1800;

        //Set Player and their position, speed and textures
        player = new FemalePlayer(x, y);
        frameDuration = 1 / 5f;
        speed = 100;

        textureAtlasCharacter = new TextureAtlas("Sprites/Characters/characters.atlas");
        character = new Animation<TextureRegion>(frameDuration, textureAtlasCharacter.findRegions("female/standing"));

        //Set items and their co-ordinates
        items = new Items();
        items.setBackpackCoordinates(500, 550);
        items.setCoffeeCoordinates(1100, 1100);
        items.setShoesCoordinates(1000, 1000);

        //Set Inventory and its position
        inventory = new Inventory();
        inventory.setInventoryPositiion(240, -60);

        //Set Text Box and its position
        text = new Text();
        text.setTextPositiion(110, 230);
        text.setSylviaPosition(310, 230);

        //Sounds
        Sm = new SoundManager();
        Sm.dispose();
    }

    /**
     * The render method is used to display the levels background and player, moving the camera such that
     * the player is centralised. It also allows for items to be displayed, picked up and stored.
     * @param delta Elapsed Time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Background sounds
        if (!Sm.background.isPlaying()) {
            Sm.levelOne();
        }

        //Camera is set to view TiledMap from TMX file
        //TiledMapRender renders the map
        mapRenderer.setView(camera);
        mapRenderer.render();

        //Begin sprite batch
        game.batch.begin();

        //Recalculate elapsed time
        elapsedTime += Gdx.graphics.getDeltaTime();
        elapsedTimeInventory += Gdx.graphics.getDeltaTime();
        elapsedTimeText += Gdx.graphics.getDeltaTime();

        //Set frame rate based on player speed
        if (speed == 200) {
            frameDuration = 1 / 10f;
        } else {
            frameDuration = 1 / 5f;
        }

        //Draw items if they have not been picked up
        if (!items.backpackPick) {
            game.batch.draw(items.backpack, items.xBackpack, items.yBackpack);
        }
        if (!items.coffeePick) {
            game.batch.draw(items.coffee, items.xCoffee, items.yCoffee);
        }
        if (!items.shoesPick) {
            game.batch.draw(items.shoes, items.xShoes, items.yShoes);
        }

        //Draw character animation
        game.batch.draw(character.getKeyFrame(elapsedTime, true), x, y);

        //Draw text box relative to player position
        text.nextTextBox(elapsedTimeText, items.backpackPick, items.shoesPick, items.coffeePick);

        if (text.textInterrupt) {
            text.setCurrentTextBox();
            game.batch.draw(text.sylvia,(camera.position.x - text.xSylvia), (camera.position.y - text.ySylvia));
            game.batch.draw(text.textBox.getKeyFrame(elapsedTimeText, true), (camera.position.x - text.xTextBox), (camera.position.y - text.yTextBox));
            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                text.removeTextInterrupt();
                text.currentSpeech ++;
                elapsedTimeText = 0;
            }
        }

        //Draw inventory relative to players position - if the I key is pressed, or an item has been recently picked up
        if (((Gdx.input.isKeyPressed(Input.Keys.I) && items.backpackPick) || (items.recentPick)) && !text.textInterrupt)  {
            inventory.beginDrawingInventory();
        }
        else {
            inventory.endDrawingInventory();
            elapsedTimeInventory = 0;
        }
        if (inventory.drawInventory) {
            if (!inventory.HUDAnimated.isAnimationFinished(elapsedTimeInventory)) {

                game.batch.draw(inventory.HUDAnimated.getKeyFrame(elapsedTimeInventory, true), (camera.position.x + inventory.xHUD), (camera.position.y + inventory.yHUD));
            }
            else {
                game.batch.draw(inventory.HUDStill, (camera.position.x + inventory.xHUD), (camera.position.y + inventory.yHUD) );
            }

            if(items.recentPick && elapsedTimeInventory > 2) {
                items.setItemNotRecentlyPicked();
            }
        }

        //Read inputs to determine movement of character
        if (((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) && y < yMaxCamera) && !text.textInterrupt) {
            character = player.moveUp();
            y += speed * Gdx.graphics.getDeltaTime();
            camera.position.y += speed * Gdx.graphics.getDeltaTime();

        } else if (((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) && y > yMinCamera) && !text.textInterrupt) {
            character = player.moveDown();
            y -= speed * Gdx.graphics.getDeltaTime();
            camera.position.y -= speed * Gdx.graphics.getDeltaTime();

        } else if (((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) && x < xMaxCamera) && !text.textInterrupt) {
            character = player.moveRight();
            x += speed * Gdx.graphics.getDeltaTime();
            camera.position.x += speed * Gdx.graphics.getDeltaTime();

        } else if (((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) && x > xMinCamera) && !text.textInterrupt) {
            character = player.moveLeft();
            x -= speed * Gdx.graphics.getDeltaTime();
            camera.position.x -= speed * Gdx.graphics.getDeltaTime();

        } else {
            character = player.standStill();
        }

        //Update the position of the camera
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        //Check status of items - Display inventory once upon picking up a new object
            if (items.hasPlayerPickedBackpack(x, y)) {
                if(items.displayBackpack < 1) {
                    items.setItemRecentlyPicked();
                    items.displayBackpack ++;
                }
                elapsedTimeText = 0;
            }

            if (items.hasPlayerPickedCoffee(x, y)) {
                if(items.displayCoffee < 1) {
                    items.setItemRecentlyPicked();
                    items.displayCoffee++;
                }
                elapsedTimeText = 0;
            }

            if (items.hasPlayerPickedShoes(x, y)) {
                if(items.displayShoes < 1) {
                    items.setItemRecentlyPicked();
                    items.displayShoes ++;
                }
                elapsedTimeText = 0;
            }

        //Check status of inventory
        inventory.checkHUDStatus(items);

        //Check if level has ended
        checkLevelProgress();

        //End sprite batch
        game.batch.end();
    }

    /**
     * Method used to determine if the level has finished.
     */
    public void checkLevelProgress() {
        if(text.currentSpeech == 6) {
            if (checkPlayerExit()) {
                game.setScreen(new ExitScreen(game));
            }
        }
    }

    /**
     * Method used to check if the player has exited to the main building.
     * @return True if the player has exited.
     */
    public boolean checkPlayerExit() {
        float xEntrance = 1369;
        float yEntrance = 1719;

        if (((xEntrance-20) < x && x < (xEntrance + 20)) && ((yEntrance-20) < y && y < (yEntrance + 20))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                return true;
            }
        }

            return false;
    }

    /**
     * Getter for the frame durations of animations.
     * @return Frame duration.
     */
    public static float getFrameDuration() {

        return LevelOne.frameDuration;
    }

    //Unused
    @Override
    public void show() {

    }

    //Unused
    @Override
    public void resize(int width, int height) {
    }

    //Unused
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    //Unused
    @Override
    public void hide() {

    }

    //Unused
    @Override
    public void dispose() {

        game.batch.dispose();
    }
}
