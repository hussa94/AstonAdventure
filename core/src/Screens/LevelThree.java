package Screens;

import Entities.*;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

public class LevelThree extends Level implements com.badlogic.gdx.Screen {

    LevelThree(AstonAdventure game){
        this.game = game;

        map = new Map(3, 32,32);

        camera = new Camera(map.getWidth(), map.getHeight(), 1150, 1400, game);
        camera.setCameraBoundaries(315, 2750, 241, 1350);

        player = new Player(game.getSelectedCharacter(), game, 1545, 1350);
        canPlayerMove = true;

        isEnterHeld = false;

        //Set Inventory and its position
        inventory = new Inventory(game);
        inventoryFrames = 0;

        // TODO: Add all required players
        levelCharacters = new ArrayList<GameCharacter>();
        GameCharacter testSprite = new GameCharacter(1797, 1350, "Sprites/Characters/Down.png", 1, 3);
        levelCharacters.add(testSprite);

        //TODO: Change Music to more appropriate music
        //Set up sounds
        Sm = new Sounds();
        Sm.dispose();
    }

    @Override
    public void show() {

    }

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

        //Draw all items in level three
        for (GameCharacter character : levelCharacters) {
            game.batch.draw(character.getTexture(), character.getX(), character.getY());
            if (character.getTalk()) {
                game.batch.draw(character.getTalkIcon(), character.getTalkX(), character.getTalkY());
            }
        }

        player.drawCharacter(elapsedTime);

        //Update the camera position relative to player co-ordinates
        camera.updateCameraOnPlayer(player);

        //TODO: Sort out text for level three
        //Draw text boxes
        renderText();

        //Draw the inventory in top right corner
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            inventory.drawInventory(camera, false);
        }

        //Check if an item is being picked up or a character is being spoken to
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            //pickUpItem();
            //TODO: Add talk feature
            talkToCharacter();
        }

        //Next text box
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            checkNextText();
        } else isEnterHeld = false;

        //Displaying inventory
        if (inventoryFrames > 0) {
            inventory.drawInventory(camera, true);
            inventoryFrames--;
        }

        //End sprite batch
        game.batch.end();
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
        game.batch.dispose();
    }
}
