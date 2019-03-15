package Screens;

import Entities.*;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

public class LevelThree extends Level implements com.badlogic.gdx.Screen {

    // LevelThree instance
    private static LevelThree levelThreeInstance;

    private LevelThree(AstonAdventure game) {
        this.game = game;

        map = new Map(3, 32, 32);

        camera = new Camera(map.getWidth(), map.getHeight(), 1150, 1400, game);
        camera.setCameraBoundaries(315, 2750, 241, 1350);

        player = new Player(game.getSelectedCharacter(), game, 1545, 1350);
        canPlayerMove = true;

        isEnterHeld = false;

        //Set Inventory and its position
        inventory = new Inventory(game);
        inventoryFrames = 0;

        // TODO: Add all required players
        //Create characters and add them to the game
        levelCharacters = new ArrayList<GameCharacter>();
        //Character 1
        GameCharacter character1 = new GameCharacter(1178, 1105, "Sprites/Characters/npcFemaleDown.png", 1, 3);
        character1.setTalk();
        levelCharacters.add(character1);
        //Character 2
        GameCharacter character2 = new GameCharacter(1943, 1112, "Sprites/Characters/npcFemaleGlassesDown.png", 2, 3);
        character2.setTalk();
        levelCharacters.add(character2);
        //Character 3
        GameCharacter character3 = new GameCharacter(1951, 736, "Sprites/Characters/npcMohawkDown.png", 3, 3);
        character3.setTalk();
        levelCharacters.add(character3);
        //Character 4
        GameCharacter character4 = new GameCharacter(1951, 344, "Sprites/Characters/npcMaleDown.png", 4, 3);
        character4.setTalk();
        levelCharacters.add(character4);
        //Character 5
        GameCharacter character5 = new GameCharacter(1178, 335, "Sprites/Characters/npcFemaleGlassesDown.png", 5, 3);
        character5.setTalk();
        levelCharacters.add(character5);
        //Character 6
        GameCharacter character6 = new GameCharacter(1173, 724, "Sprites/Characters/npcMohawkDown.png", 6, 3);
        character6.setTalk();
        levelCharacters.add(character6);

        //TODO: Change Music to more appropriate music
        //Set up sounds
        Sm = new Sounds();
        Sm.dispose();
    }

    /**
     * This method allows this class to follow the Singleton design pattern to allow only one instance of the LevelThree
     * class to be produced
     *
     * @param game {@link AstonAdventure} game
     * @return the single version of a LevelThree instance
     */
    static LevelThree getLevelThreeInstance(AstonAdventure game) {
        if (levelThreeInstance == null) {
            levelThreeInstance = new LevelThree(game);
        }
        return levelThreeInstance;
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
            Sm.levelThree();
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

        //Print out player co-ordinates
        if(Gdx.input.isKeyPressed(Input.Keys.C)){
            System.out.println("player x = " + player.getX() + "player y = " + player.getY());
        }

        //Next text box
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
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
