package Screens;

import Entities.*;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * The class LevelOne is used to play the first level (Enrolment) of the video game, with
 * the user learning basic controls and finding the university.
 */
public class LevelOne extends Level implements Screen {

    // LevelOne instance
    private static LevelOne levelOneInstance;

    //Character
    private float elapsedTime;

    private TutorialText tutorialText;

    //Progression tracker
    private int currentSpeech = 1;

    /**
     * The Constructor for LevelOne initialises the map and player textures, camera,
     * items, inventory and sounds. Stores the game object.
     *
     * @param game The game object.
     */
    private LevelOne(AstonAdventure game) {

        //Store game object
        this.game = game;

        //Set up exit co-ordinates
        this.xExit = 1369;
        this.yExit = 1719;

        tutorialText = new TutorialText(game);

        //Set up map including tile width and heights
        map = new Map(1, 16, 16);

        //Set up NPCs
        npc = new Npc(game, 40, map);

        //Set up camera
        camera = new Camera(map.getWidth(), map.getHeight(), 400, 400, game);
        camera.setCameraBoundaries(400, 3700, 400, 1800);

        //Set player / character
        player = new Player(game.getSelectedCharacter(), game, 400, 400);

        //Set Inventory and its position
        inventory = new Inventory(game);
        inventoryFrames = 0;

        //initialise all items and their coordinates
        levelItems = new ArrayList<Item>();
        Item backpack = new Item(ItemType.BACKPACK, 500, 550);
        levelItems.add(backpack);
        Item shoes = new Item(ItemType.SHOES, 900, 1000);
        levelItems.add(shoes);
        Item coffee = new Item(ItemType.COFFEE, 2516, 662);
        levelItems.add(coffee);

        //Set game object
        this.game = game;


        //Set up sounds
        Sm = new Sounds();
        Sm.dispose();
    }

    /**
     * This method allows this class to follow the Singleton design pattern to allow only one instance of the LevelOne
     * class to be produced
     *
     * @param game {@Link AstonAdventure} game
     * @return the single version of a LevelOne instance
     */
    public static LevelOne getLevelOneInstance(AstonAdventure game) {
        if (levelOneInstance == null) {
            levelOneInstance = new LevelOne(game);
        }
        return levelOneInstance;
    }

    /**
     * The render method is used to display the levels background and player, moving the camera such that
     * the player is centralised. It also allows for items to be displayed, picked up and stored.
     *
     * @param delta Elapsed Time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Recalculate elapsed time
        elapsedTime += Gdx.graphics.getDeltaTime();

        //Background sounds
        if (!Sm.isMusicPlaying()) {
            Sm.levelOne();
        }

        //Set view of camera
        camera.viewMap(map.getMapRenderer());

        //Begin sprite batch
        game.batch.begin();

        //Draw out all NPC characters
        npc.drawNPCs(elapsedTime, camera, map);

        //Draw all items in level one
        for (Item item : levelItems) {
            game.batch.draw(item.getTexture(), item.getXCoordinate(), item.getYCoordinate());
        }

        //Draw character animation and calculate movement
        if (tutorialText.canPlayerWalk()) {
            player.movement(map);
            player.drawCharacter(elapsedTime);
        } else {
            player.standStill();
            player.drawCharacter(elapsedTime);
        }

        //Update the camera position relative to player co-ordinates
        camera.updateCameraOnPlayer(player);

        //Get next text box upon completion of last text box
        if (!tutorialText.getTextInterrupt()) {
            tutorialText.nextTextBox(inventory);
        }
        if (!tutorialText.getTutorStatus() || tutorialText.getTextInterrupt()) {
            //Draw text box relative to player position
            tutorialText.drawTextBox(camera, player, elapsedTime);
        }

        //Draw the inventory in top right corner
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            inventory.drawInventory(camera, false);
        }

        //Check if an item is being picked up
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            pickUpItem();
        }

        //Displaying inventory
        if (inventoryFrames > 0) {
            inventory.drawInventory(camera, true);
            inventoryFrames--;
            if (inventoryFrames == 0) {
                inventory.resetElapsedTime();
            }
        }

        if (currentSpeech == 6) {
            //Check if level has ended
            checkLevelProgress();
        }

        //End sprite batch
        game.batch.end();
    }

    /**
     * Method used to determine if the level has finished.
     */
    private void checkLevelProgress() {

        if (checkPlayerExit()) {
            game.setScreen(LevelTwo.getLevelTwoInstance(game));
        }
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
