package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import Entities.*;
import Game.AstonAdventure;

import java.util.ArrayList;

/**
 * The class LevelOne is used to play the first level (Enrolment) of the video game, with
 * the user learning basic controls and finding the university.
 */
public class LevelOne implements Screen {

    //Game
    private AstonAdventure game;

    //NPCs
    private Npc npc;

    //Sounds
    private Sounds Sm;

    //Map
    private Map map;

    //Player / Character
    private Player player;

    // TODO Items for the level
    private ArrayList<Item> levelOneItems;

    //Inventory
    private Inventory inventory;
    //A counter for the number of frames to display the inventory
    private int inventoryFrames;

    //Text Boxes / Mentor
    private Text text;

    //Camera
    private Camera camera;

    //Character
    private float elapsedTime;

    /**
     * The Constructor for LevelOne initialises the map and player textures, camera,
     * items, inventory and sounds. Stores the game object.
     *
     * @param game The game object.
     */
    LevelOne(AstonAdventure game) {

        //Store game object
        this.game = game;

        //Set up NPCs
        npc = new Npc(game, 30);

        //Set up map including tile width and heights
        map = new Map(1, 16, 16);

        //Set up camera
        camera = new Camera(map.getWidth(), map.getHeight(), 400, 400, game);
        camera.setCameraBoundaries(400, 3700, 400, 1800);

        //Set player / character
        player = new Player(game.getSelectedCharacter(), game, 400, 400);
        player.setPlayerBoundaries(80, 3970, 158, 1960);

        //Set Inventory and its position
        inventory = new Inventory(game);
        inventoryFrames = 0;

        //TODO initialise items and their coordinates
        levelOneItems = new ArrayList<Item>();
        Item backpack = new Item(ItemType.BACKPACK, 500, 550);
        levelOneItems.add(backpack);
        Item shoes = new Item(ItemType.SHOES, 1000, 1000);
        levelOneItems.add(shoes);
        Item coffee = new Item(ItemType.COFFEE, 1100, 1100);
        levelOneItems.add(coffee);

        //Set Text Box and its position
        text = new Text(game, 1);
        text.setTextPosition(110, 230);
        text.setSylviaPosition(310, 230);

        //Set up sounds
        Sm = new Sounds();
        Sm.dispose();
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
        npc.drawNPCs(elapsedTime, camera);

        //TODO draw all items in level one
        for (Item item : levelOneItems) {
            game.batch.draw(item.getTexture(), item.getXCoordinate(), item.getYCoordinate());
        }

        //Draw character animation and calculate movement
        if (text.canPlayerWalk()) {
            player.movement(map);
            player.drawCharacter(elapsedTime);
        } else {
            player.standStill();
            player.drawCharacter(elapsedTime);
        }

        //Update the camera position relative to player co-ordinates
        camera.updateCameraOnPlayer(player);

        //Get next text box upon completion of last text box
        if(!text.getTextInterrupt()) {
            text.nextTextBoxLevel1(elapsedTime, inventory);
        }
        if (!text.getTutorStatus() || text.getTextInterrupt()){
            //Draw text box relative to player position
            text.drawTextBox(camera, player, elapsedTime);
        }


        //TODO animation is not working
        //Draw the inventory in top right corner
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            inventory.drawInventory(camera, false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            //Create a copy of the items currently in the level to iterate over
            ArrayList<Item> levelOneItemsCopy = new ArrayList<Item>(levelOneItems);
            //Check which items have been picked
            for (Item item : levelOneItemsCopy) {
                if (item.isBeingPicked(player.getX(), player.getY())) {
                    inventory.addItem(item);
                    levelOneItems.remove(item);
                    //Updates status of inventory
                    inventory.updateInventoryStatus();
                    inventory.drawInventory(camera, true);
                    inventoryFrames = 20;
                    if(inventory.contains(ItemType.SHOES)){
                        player.increaseSpeed();
                    }
                }
            }
        }

        //TODO JavaDoc
        //Displaying inventory
        if (inventoryFrames > 0){
            inventory.drawInventory(camera, true);
            inventoryFrames --;
        }

        if (text.currentSpeech == 6) {
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
            game.setScreen(new LevelTwo(game));
        }
    }

    /**
     * Method used to check if the player has exited to the main building.
     *
     * @return True if the player has exited.
     */
    private boolean checkPlayerExit() {
        float xEntrance = 1369;
        float yEntrance = 1719;

        if (((xEntrance - 20) < player.getX() && player.getX() < (xEntrance + 20)) && ((yEntrance - 20) < player.getY() && player.getY() < (yEntrance + 20))) {
            return Gdx.input.isKeyPressed(Input.Keys.E);
        }

        return false;
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
