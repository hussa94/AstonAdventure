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
public class Text {

    //Textures
    public Texture sylvia;
    public float xSylvia, ySylvia;
    public float xTextBox, yTextBox;

    //Game Object
    AstonAdventure game;

    //Animation frame duration
    private float frameDuration = 1/2f;

    private float elapsedTime;

    //Text boxes
    public Animation<TextureRegion> textBox;
    public TextureAtlas textureAtlasText;

    //Progression tracker
    public int currentSpeech = 1;

    //Display interrupt
    public boolean textInterrupt;

    /**
     * The constructor initialises all of the textures and text box animations used in the game.
     */
    public Text(AstonAdventure game) {
        this.game = game;
       textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Text 1/Text 1.atlas");
       textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
       textInterrupt = true;
       sylvia = new Texture("tiles/sylvia.png");
    }

    /**
     * Method used to determine and set the current text box to be displayed.
     */
    public void setCurrentTextBox() {
        if (currentSpeech == 1) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Text 1/Text 1.atlas");
            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
        if (currentSpeech == 2) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Text 2/Text 2.atlas");
            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
        if (currentSpeech == 3) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Text 3/Text 3.atlas");
            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
        if (currentSpeech == 4) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Text 4/Text 4.atlas");
            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
        if (currentSpeech == 5) {
            textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Text 5/Text 5.atlas");
            textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
        }
    }

    /**
     * Method used to determine the next text box in the sequence to be displayed.
     * @param elapsedTimeText Timer since last text box.
     * @param backpackPick Bool indicating status of the backpack.
     * @param shoesPick Bool indicating status of the book.
     * @param coffeePick Bool indicating status of the coffee.
     */
    public void nextTextBox(float elapsedTimeText, boolean backpackPick, boolean shoesPick, boolean coffeePick) {
        if (elapsedTimeText > 3 && currentSpeech == 2) {
            setTextInterrupt();
        }
        else if (elapsedTimeText > 3 && currentSpeech == 3 && backpackPick) {
            setTextInterrupt();
        }
        else if (elapsedTimeText > 3 && currentSpeech == 4 && backpackPick && shoesPick) {
            setTextInterrupt();
        }
        else if (elapsedTimeText > 3 && currentSpeech == 5 && backpackPick && shoesPick && coffeePick) {
            setTextInterrupt();
        }
    }

    public void drawTextBox(Items items, Camera camera) {

        elapsedTime += Gdx.graphics.getDeltaTime();

        nextTextBox(elapsedTime, items.backpackPick, items.shoesPick, items.coffeePick);

        if (textInterrupt) {
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

    public void resetElapsedTime() {
        elapsedTime = 0;
    }

    /**
     * Sets the interrupt such that the game is interrupted to display a text box.
     */
    public void setTextInterrupt() {
        textInterrupt = true;
    }

    /**
     * Removes the interrupt such that the game stops the interrupt to display a text box.
     */
    public void removeTextInterrupt() {
        textInterrupt = false;
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
