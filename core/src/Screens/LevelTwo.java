package Screens;

import Entities.*;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class LevelTwo implements Screen {

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

    //Items / Pickups
    private Items items;

    //Inventory
    private Inventory inventory;

    //Text Boxes / Mentor
    private Text text;

    //Camera
    private Camera camera;

    //Character
    private float elapsedTime;

    LevelTwo(AstonAdventure game) {

        //Store game object
        this.game = game;

//        //Set up NPCs
//        npc = new Npc(game, 30);

        //Set up map
        map = new Map(2);

        //Set up camera
        camera = new Camera(map.getWidth(), map.getHeight(), 320, 250, game);
        camera.setCameraBoundaries(320, 3700, 250, 1900);

        //Set player / character
        player = new Player(game.getSelectedCharacter(), game, 250, 250);
        player.setPlayerBoundaries(0, 3970, 0, 2060);

//        //Set items and their co-ordinates
//        items = new Items(game);
//        items.setBackpackCoordinates(500, 550);
//        items.setCoffeeCoordinates(1100, 1100);
//        items.setShoesCoordinates(1000, 1000);

//        //Set Inventory and its position
//        inventory = new Inventory(game);

//        //Set Text Box and its position
//        text = new Text(game, 1);
//        text.setTextPositiion(110, 230);
//        text.setSylviaPosition(310, 230);

        //Set up sounds
        Sm = new Sounds();
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

        player.movement();
        player.drawCharacter(elapsedTime);

//        //Draw out all NPC characters
//        npc.drawNPCs(elapsedTime);

//        //Draw all items in level
//        items.drawItems();

        //Draw character animation and calculate movement
//        if (text.canPlayerWalk()) {
//            player.movement();
//            player.drawCharacter(elapsedTime);
//        } else {
//            player.standStill();
//            player.drawCharacter(elapsedTime);
//        }

        //Update the camera position relative to player co-ordinates
        camera.updateCameraOnPlayer(player);

//        //Draw text box relative to player position
//        text.drawTextBox(items, camera, player, elapsedTime);

//        //Draw the inventory in top right corner
//        inventory.drawInventory(items, camera);
//
//        //Check status of inventory
//        inventory.checkHUDStatus(items);
//
//        //Check which items have been picked
//        items.itemStatus(text, player);

        //End sprite batch
        game.batch.end();

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

