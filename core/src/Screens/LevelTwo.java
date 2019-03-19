package Screens;

import Entities.*;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

/**
 * Level two is the second level of Aston Adventure.
 * It is a maze that the character has to navigate to reach the end.
 */
public class LevelTwo extends Level implements Screen {

    // LevelTwo instance
    private static LevelTwo levelTwoInstance;

    /**
     * Constructor for the LevelTwo class. It creates all character and item instances for level two.
     * @param game The game object.
     */
    private LevelTwo(AstonAdventure game) {

        //Store game object
        this.game = game;

        //Set exit oo-ordinates
        this.yExit = 3966;
        this.xExit = 922;

        //Set up map
        map = new Map(2, 64, 64);

        //Set up camera
        camera = new Camera(map.getWidth(), map.getHeight(), 320, 250, game);
        camera.setCameraBoundaries(315, 7875, 250, 3858);

        //Set player / character
        player = new Player(game.getSelectedCharacter(), game, 250, 250);
        canPlayerMove = true;

        //boolean for input handling
        isEnterHeld = false;

        //Set Inventory and its position
        inventory = new Inventory(game);
        inventoryFrames = 0;

        //Initialise all items and their coordinates
        levelItems = new ArrayList<Item>();
        Item backpack = new Item(ItemType.BACKPACK, 350, 200);
        levelItems.add(backpack);
        Item shoes = new Item(ItemType.SHOES, 950, 250);
        levelItems.add(shoes);
        Item coffee = new Item(ItemType.COFFEE, 2770, 187);
        levelItems.add(coffee);
        Item goose = new Item(ItemType.GOOSE, 1136, 3326);
        levelItems.add(goose);

        //Create characters and add them to the game
        levelCharacters = new ArrayList<GameCharacter>();
        //Character 1
        GameCharacter firstCharacter = new GameCharacter(80, 320, "Sprites/Characters/npcFemaleDown.png", 1, 2);
        firstCharacter.setTalk();
        levelCharacters.add(firstCharacter);
        //Character 2
        GameCharacter character2 = new GameCharacter(1848, 304, "Sprites/Characters/npcFemaleGlassesDown.png", 2, 2);
        character2.setTalk();
        levelCharacters.add(character2);
        //Character 3
        GameCharacter character3 = new GameCharacter(4334, 669, "Sprites/Characters/npcMohawkDown.png", 3, 2);
        character3.setTalk();
        levelCharacters.add(character3);
        //Character 4
        GameCharacter character4 = new GameCharacter(2910, 588, "Sprites/Characters/npcMaleDown.png", 4, 2);
        character4.setTalk();
        levelCharacters.add(character4);
        //Character 5
        GameCharacter character5 = new GameCharacter(2615, 1003, "Sprites/Characters/npcFemaleGlassesDown.png", 5, 2);
        character5.setTalk();
        levelCharacters.add(character5);
        //Character 6
        GameCharacter character6 = new GameCharacter(834, 1790, "Sprites/Characters/npcMohawkDown.png", 6, 2);
        character6.setTalk();
        levelCharacters.add(character6);
        //Character 7
        GameCharacter character7 = new GameCharacter(4285, 2558, "Sprites/Characters/npcMaleGlassesDown.png", 7, 2);
        character7.setTalk();
        levelCharacters.add(character7);
        //Character 8
        GameCharacter character8 = new GameCharacter(2312, 3336, "Sprites/Characters/Down.png", 8, 2);
        character8.setTalk();
        levelCharacters.add(character8);
        //Character 9
        GameCharacter character9 = new GameCharacter(3111, 2588, "Sprites/Characters/npcFemaleDown.png", 9, 2);
        character9.setTalk();
        levelCharacters.add(character9);
        //Character 10
        GameCharacter character10 = new GameCharacter(1200, 3318, "Sprites/Characters/npcMaleDown.png", 10, 2);
        character10.setTalk();
        levelCharacters.add(character10);

        //Set up sounds
        Sm = new Sounds();
        Sm.dispose();
    }

    /**
     * This method allows this class to follow the Singleton design pattern to allow only one instance of the LevelTwo
     * class to be produced
     * @param game {AstonAdventure} game
     * @return the single version of a LevelTwo instance
     */
    static LevelTwo getLevelTwoInstance(AstonAdventure game) {
        if (levelTwoInstance == null) {
            levelTwoInstance = new LevelTwo(game);
        }
        return levelTwoInstance;
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
            Sm.levelTwo();
        }

        //Set view of camera
        camera.viewMap(map.getMapRenderer());

        //Begin sprite batch
        game.batch.begin();

        if (canPlayerMove) {
            player.movement(map);
        } else {
            player.standStill();
        }

        //Draw all items in level two
        for (Item item : levelItems) {
            game.batch.draw(item.getTexture(), item.getXCoordinate(), item.getYCoordinate());
        }

        for (GameCharacter character : levelCharacters) {
            game.batch.draw(character.getTexture(), character.getX(), character.getY());
            if (character.getTalk()) {
                game.batch.draw(character.getTalkIcon(), character.getTalkX(), character.getTalkY());
            }
        }

        player.drawCharacter(elapsedTime);

        //Update the camera position relative to player co-ordinates
        camera.updateCameraOnPlayer(player);

        //Draw text boxes
        renderText();

        //Draw the inventory in top right corner
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            inventory.drawInventory(camera, false);
        }

        //Check if an item is being picked up or a character is being spoken to
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            pickUpItem();
            talkToCharacter();
            checkLevelProgress();
        }

        //Next text box
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            nextText();
        } else isEnterHeld = false;


        //Displaying inventory
        if (inventoryFrames > 0) {
            inventory.drawInventory(camera, true);
            inventoryFrames--;
        }

        //End sprite batch
        game.batch.end();

    }

    /**
     * Method used to determine if the level has finished.
     */
    private void checkLevelProgress() {

        if (checkPlayerExit()) {
            game.setLevel(3);
            game.setScreen(Loading.getLoadingScreenInstance(game));
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

