package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The Text class is used to display all of the in-game text boxes that pop up in levels.
 */
public class TutorialText {

    //Textures
    private Texture sylvia;
    private float xSylvia, ySylvia;
    private float xTextBox, yTextBox;

    //Game Object
    private AstonAdventure game;

    //Tutor character
    private Tutor tutor;

    //Animation frame duration
    private float frameDuration = 1/2f;

    //Timer
    private float elapsedTime;

    //Text boxes
    private Animation<TextureRegion> textBox;
    private TextureAtlas textureAtlasText;

    //Progression tracker
    public int currentSpeech = 1;;

    //Display interrupt
    private boolean textInterrupt;

    /**
     * The constructor initialises all of the textures and text box animations used in the game.
     */
    public TutorialText(AstonAdventure game) {

        //Set tutor
        tutor = new Tutor(game);

        //Set game object
        this.game = game;

        //Set sylvia texture
        sylvia = new Texture("tiles/sylvia.png");

        setSylviaPosition(310, 230);
        setTextPositiion(110, 230);
    }

    /**
     * Method used to determine and set the current text box to be displayed.
     */
    private void setCurrentTextBox() {

        //Determine which text box to display
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
     * Method used to determine the next text box in the sequence to be displayed.
     */
    public void nextTextBox(Inventory inventory) {

        elapsedTime += Gdx.graphics.getDeltaTime();

        //Change text box if necessary
        if (currentSpeech == 1) {
            setTextInterrupt();
        }
        else if (elapsedTime > 7 && currentSpeech == 2) {
            setTextInterrupt();
        }
        else if (elapsedTime > 7 && currentSpeech == 3 && inventory.contains(ItemType.BACKPACK)) {
            setTextInterrupt();
        }
        else if (elapsedTime > 7 && currentSpeech == 4 && inventory.contains(ItemType.BACKPACK) && inventory.contains(ItemType.SHOES)) {
            setTextInterrupt();
        }
        else if (elapsedTime > 7 && currentSpeech == 5 && inventory.contains(ItemType.BACKPACK) && inventory.contains(ItemType.SHOES) && inventory.contains(ItemType.COFFEE)) {
            setTextInterrupt();
        }
    }

    /**
     * Method to draw the text box on the screen. The tutor character will enter, then stand while the
     * text box is displayed. On dismissal, the tutor character will leave and the player will be able to resume.
     * @param camera The camera in the level.
     * @param player The player.
     * @param elapsedTimeLevel Elapsed time in the level.
     */
    public void drawTextBox(Camera camera, Player player, float elapsedTimeLevel) {

        //Update elapsed time for text box
        elapsedTime += Gdx.graphics.getDeltaTime();

        //Draw tutor character entering / exiting / standing
        if (tutor.getEntering()) {
            tutor.enterTutor(elapsedTimeLevel, player);
        }
        else if (tutor.getExiting()) {
            tutor.exitTutor(elapsedTimeLevel, player);
        }
        else if (tutor.getStanding()) {
            tutor.standTutor(elapsedTimeLevel);
        }

        //Draw the current text box
        if (textInterrupt && (!tutor.getExiting()) && (!tutor.getEntering())) {
            setCurrentTextBox();
            game.batch.draw(sylvia,(camera.getX() - xSylvia), (camera.getY() - ySylvia));
            game.batch.draw(textBox.getKeyFrame(elapsedTime, true), (camera.getX() - xTextBox), (camera.getY() - yTextBox));
            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                removeTextInterrupt();
                currentSpeech ++;
                resetElapsedTime();
            }
        }
    }

    /**
     * Method to return a bool indicating whether or not a text box is on screen
     * @return textInterrupt
     */
    public boolean getTextInterrupt() {
        return textInterrupt;
    }

    void resetElapsedTime() {
        elapsedTime = 0;
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

    public boolean getTutorStatus() {
        return tutor.getFinished();
    }

    /**
     * Method to restric the player from walking if the tutor character is active, or a text box is active.
     * @return Whether the player can walk or not.
     */
    public boolean canPlayerWalk() {
        return !getTextInterrupt() && !tutor.getStanding() && !tutor.getEntering() && !tutor.getExiting();
    }

    /**
     * Sets the position of the text boxes to be displayed.
     * @param x x co-ordinate.
     * @param y y co-ordinate.
     */
    public void setTextPositiion(float x, float y){
        xTextBox = x;
        yTextBox = y;
    }

    /**
     * Sets the position of the lecturer character.
     * @param x x co-ordinate.
     * @param y y co-ordinate.
     */
    public void setSylviaPosition(float x, float y) {
        xSylvia = x;
        ySylvia = y;
    }
}