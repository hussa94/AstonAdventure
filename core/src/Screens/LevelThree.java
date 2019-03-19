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

    private char lastKeyPressed;

    private static int correctAnswers;

    /**
     * Constructor for the LevelThree class. It creates all character and item instances for level three.
     * @param game The game object.
     */
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

        //Create characters and add them to the game
        levelCharacters = new ArrayList<GameCharacter>();
        //Character 1
        GameCharacter character1 = new GameCharacter(1456, 1338, "Sprites/Characters/npcFemaleDown.png", 1, 3);
        character1.setTalk();
        levelCharacters.add(character1);
        //Character 2
        GameCharacter character2 = new GameCharacter(1943, 1112, "Sprites/Characters/npcFemaleGlassesDown.png", 2, 3);
        character2.setTalk();
        character2.setAnswer('b');
        levelCharacters.add(character2);
        //Character 3
        GameCharacter character3 = new GameCharacter(1951, 736, "Sprites/Characters/npcMohawkDown.png", 3, 3);
        character3.setTalk();
        character3.setAnswer('c');
        levelCharacters.add(character3);
        //Character 4
        GameCharacter character4 = new GameCharacter(1951, 344, "Sprites/Characters/npcMaleDown.png", 4, 3);
        character4.setTalk();
        character4.setAnswer('b');
        levelCharacters.add(character4);
        //Character 5
        GameCharacter character5 = new GameCharacter(1178, 335, "Sprites/Characters/npcFemaleGlassesDown.png", 5, 3);
        character5.setTalk();
        character5.setAnswer('b');
        levelCharacters.add(character5);
        //Character 6
        GameCharacter character6 = new GameCharacter(1173, 724, "Sprites/Characters/npcMohawkDown.png", 6, 3);
        character6.setTalk();
        character6.setAnswer('c');
        levelCharacters.add(character6);

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

    /**
     * The render method is used to display the levels background and player, moving the camera such that
     * the player is centralised. It also allows for items to be displayed, picked up and stored.
     *
     * @param delta Elapsed time.
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

        //Draw text boxes
        renderText();

        //Draw the inventory in top right corner
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            inventory.drawInventory(camera, false);
        }

        //Check if an item is being picked up or a character is being spoken to
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            //pickUpItem();
            talkToCharacter();
            //reset last key pressed
            lastKeyPressed = 'z';
        }
        lastKeyPressed();

        //Next text box
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            nextText();
            answerQuestion();
        } else isEnterHeld = false;

        //Displaying inventory
        if (inventoryFrames > 0) {
            inventory.drawInventory(camera, true);
            inventoryFrames--;
        }

        //Checks if player has answered all questions
        if(hasQuizFinished()){
            game.setScreen(Graduation.getGraduationScreenInstance(game));
        }

        //End sprite batch
        game.batch.end();
    }

    /**
     * Method lastKeyPressed records the last key pressed in response to a 'question' within level three
     */
    private void lastKeyPressed(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            lastKeyPressed = 'a';
        } if(Gdx.input.isKeyPressed(Input.Keys.B)){
            lastKeyPressed = 'b';
        } if(Gdx.input.isKeyPressed(Input.Keys.C)){
            lastKeyPressed = 'c';
        }
    }

    /**
     * Method to check the players input 'answer' against the true answer to the question asked
     * and to remove the character that just asked the question.
     */
    private void answerQuestion() {
        if(lastCharacterSpokenTo != null){
            if(lastKeyPressed == lastCharacterSpokenTo.getAnswer()){
                correctAnswers++;
                levelCharacters.remove(lastCharacterSpokenTo);
                lastKeyPressed = 'z';
            } else if(lastKeyPressed != lastCharacterSpokenTo.getAnswer()){
                levelCharacters.remove(lastCharacterSpokenTo);
                lastKeyPressed = 'z';
            }
        }
    }

    /**
     * Method to check if all of the questions in the level have been asked.
     * @return true if all of the questions have been asked false if not.
     */
    private boolean hasQuizFinished(){
        return levelCharacters.size() == 0;
    }

    /**
     * Method to return the total number of correct answers the player achieved.
     * @return the number of correct answers {@link LevelThree#correctAnswers}
     */
    static Integer getPlayerScore(){
        return correctAnswers;
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        game.batch.dispose();
    }

}
