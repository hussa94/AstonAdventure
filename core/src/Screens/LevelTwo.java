package Screens;

import Entities.*;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class LevelTwo extends Level implements Screen {

    private boolean canPlayerMove;

    private ArrayList<GameCharacter> levelTwoCharacters;

    //Rendering
    private float elapsedTime;
    //Current text box to display
    private List<Text> currentTextList;
    private int currentTextIndex;

    private boolean isEnterHeld;

    LevelTwo(AstonAdventure game) {

        //Store game object
        this.game = game;

//        //Set up NPCs
//        npc = new Npc(game, 30);

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
        Item backpack = new Item(ItemType.BACKPACK, 250, 340);
        levelItems.add(backpack);
        Item shoes = new Item(ItemType.SHOES, 450, 250);
        levelItems.add(shoes);

        levelTwoCharacters = new ArrayList<GameCharacter>();
        GameCharacter testSprite = new GameCharacter(80, 320, "Sprites/Characters/Down.png", 1, 2);
        testSprite.setTalk();
        levelTwoCharacters.add(testSprite);

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

        if (canPlayerMove) {
            player.movement(map);
        } else {
            player.standStill();
        }

//        //Draw out all NPC characters
//        npc.drawNPCs(elapsedTime);

        //Draw all items in level one
        for (Item item : levelItems) {
            game.batch.draw(item.getTexture(), item.getXCoordinate(), item.getYCoordinate());
        }

        for (GameCharacter character : levelTwoCharacters) {
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
        }

        //Next text box
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            //if this is the first frame enter is pressed / only go to next text if enter is pressed
            if(!isEnterHeld){
                nextText();
                isEnterHeld = true;
            }
        } else isEnterHeld = false;

        //Displaying inventory
        if (inventoryFrames > 0) {
            inventory.drawInventory(camera, true);
            inventoryFrames--;
        }

        //End sprite batch
        game.batch.end();

    }

    private void talkToCharacter() {
        for (GameCharacter character : levelTwoCharacters) {
            if (character.isTalking(player.getX(), player.getY())) {
                canPlayerMove = false;
                currentTextList = character.getText();
            }
        }
    }

    private void renderText() {
        if (currentTextList != null) {
            Text currentText = currentTextList.get(currentTextIndex);
            if (currentText != null) {
                TextureRegion animationFrame = currentText.getKeyFrame(elapsedTime);
                //Where to draw the text box
                game.batch.draw(animationFrame, camera.getX() - 90, camera.getY() - 235);
            }
        }
    }

    private void nextText(){
        if(currentTextList != null){
            currentTextIndex++;
            if(currentTextIndex >= currentTextList.size()){
                //Stop displaying text
                currentTextList = null;
                //Reset
                currentTextIndex = 0;
                canPlayerMove = true;
            }
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

