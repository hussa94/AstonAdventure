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

    //Textures
    private Texture sylvia;
    private float xSylvia, ySylvia;
    private float xTextBox, yTextBox;

    //Tutor character
    private Tutor tutor;

    //Text boxes
    private Animation<TextureRegion> textBox;

    //Progression tracker
    private int currentSpeech = 1;

    //Display interrupt
    private boolean textInterrupt;

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

        //Set Text Box and its position
        setTextPosition(110, 230);
        setSylviaPosition(310, 230);

        //Set tutor
        tutor = new Tutor(game);

        //Set game object
        this.game = game;

        //Set sylvia texture
        sylvia = new Texture("tiles/sylvia.png");


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
    static LevelOne getLevelOneInstance(AstonAdventure game) {
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
        if (canPlayerWalk()) {
            player.movement(map);
            player.drawCharacter(elapsedTime);
        } else {
            player.standStill();
            player.drawCharacter(elapsedTime);
        }

        //Update the camera position relative to player co-ordinates
        camera.updateCameraOnPlayer(player);

        //Get next text box upon completion of last text box
        if (!getTextInterrupt()) {
            nextTextBoxLevel1(elapsedTime, inventory);
        }
        if (!getTutorStatus() || getTextInterrupt()) {
            //Draw text box relative to player position
            drawTextBox(camera, player, elapsedTime);
        }

        //Draw the inventory in top right corner
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            inventory.drawInventory(camera, false);
        }

        //Print out player co-ordinates
//        if(Gdx.input.isKeyPressed(Input.Keys.C)){
//            System.out.println("player x = " + player.getX() + "player y = " + player.getY());
//        }

        //Check if an item is being picked up
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            pickUpItem();
        }

        //Displaying inventory
        if (inventoryFrames > 0) {
            inventory.drawInventory(camera, true);
            inventoryFrames--;
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

    // Method used to determine the next text box in the sequence to be displayed.
    //@param elapsedTimeText Timer since last text box.
    private void nextTextBoxLevel1(float elapsedTimeText, Inventory inventory) {

        //Change text box if necessary
        if (currentSpeech == 1) {
            setTextInterrupt();
        } else if (elapsedTimeText > 8.5 && currentSpeech == 2) {
            setTextInterrupt();
        } else if (elapsedTimeText > 5 && currentSpeech == 3 && inventory.contains(ItemType.BACKPACK)) {
            setTextInterrupt();
        } else if (elapsedTimeText > 5 && currentSpeech == 4 && inventory.contains(ItemType.BACKPACK) && inventory.contains(ItemType.SHOES)) {
            setTextInterrupt();
        } else if (elapsedTimeText > 5 && currentSpeech == 5 && inventory.contains(ItemType.BACKPACK) && inventory.contains(ItemType.SHOES) && inventory.contains(ItemType.COFFEE)) {
            setTextInterrupt();
        }
    }

    /**
     * Method used to determine and set the current text box to be displayed.
     */
    private void setCurrentTextBox() {

        //Determine which text box to display
        //Animation frame duration
        float frameDuration = 1 / 2f;
        TextureAtlas textureAtlasText;
        if (currentSpeech == 1) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Level One Text/Text 1/Level1Text1.atlas");
            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
        if (currentSpeech == 2) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Level One Text/Text 2/Level1Text2.atlas");

            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
        if (currentSpeech == 3) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Level One Text/Text 3/Level1Text3.atlas");

            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
        if (currentSpeech == 4) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Level One Text/Text 4/Level1Text4.atlas");

            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
        if (currentSpeech == 5) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Level One Text/Text 5/Level1Text5.atlas");
            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
    }


    /**
     * Method to draw the text box on the screen. The tutor character will enter, then stand while the
     * text box is displayed. On dismissal, the tutor character will leave and the player will be able to resume.
     *
     * @param camera           The camera in the level.
     * @param player           The player.
     * @param elapsedTimeLevel Elapsed time in the level.
     */
    private void drawTextBox(Camera camera, Player player, float elapsedTimeLevel) {

        //Update elapsed time for text box
        elapsedTime += Gdx.graphics.getDeltaTime();

        //Draw tutor character entering / exiting / standing
        if (tutor.getEntering()) {
            tutor.enterTutor(elapsedTimeLevel, player);
        } else if (tutor.getExiting()) {
            tutor.exitTutor(elapsedTimeLevel, player);
        } else if (tutor.getStanding()) {
            tutor.standTutor(elapsedTimeLevel);
        }

        //Draw the current text box
        if (textInterrupt && (!tutor.getExiting()) && (!tutor.getEntering())) {
            setCurrentTextBox();
            game.batch.draw(sylvia, (camera.getX() - xSylvia), (camera.getY() - ySylvia));
            game.batch.draw(textBox.getKeyFrame(elapsedTime, true), (camera.getX() - xTextBox), (camera.getY() - yTextBox));
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                removeTextInterrupt();
                currentSpeech++;
            }
        }
    }

    /**
     * Method to return a bool indicating whether or not a text box is on screen
     *
     * @return textInterrupt
     */
    private boolean getTextInterrupt() {
        return textInterrupt;
    }

    /**
     * Sets the interrupt such that the game is interrupted to display a text box.
     */
    private void setTextInterrupt() {
        textInterrupt = true;
        tutor.setEntering();
    }

    /**
     * Removes the interrupt such that the game stops the interrupt to display a text box.
     */
    private void removeTextInterrupt() {
        textInterrupt = false;
        tutor.setExiting();
    }

    private boolean getTutorStatus() {
        return tutor.getFinished();
    }

    /**
     * Method to restric the player from walking if the tutor character is active, or a text box is active.
     *
     * @return Whether the player can walk or not.
     */
    private boolean canPlayerWalk() {
        return !getTextInterrupt() && !tutor.getStanding() && !tutor.getEntering() && !tutor.getExiting();
    }

    /**
     * Sets the position of the text boxes to be displayed.
     *
     * @param x x co-ordinate.
     * @param y y co-ordinate.
     */
    private void setTextPosition(float x, float y) {
        xTextBox = x;
        yTextBox = y;
    }

    /**
     * Sets the position of the lecturer character.
     *
     * @param x x co-ordinate.
     * @param y y co-ordinate.
     */
    private void setSylviaPosition(float x, float y) {
        xSylvia = x;
        ySylvia = y;
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
