package Entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameCharacter class used to create a game character for a level of the game.
 */
public class GameCharacter {

    //Coordinates
    private float xCoordinate;
    private float yCoordinate;

    //Textures
    private Texture texture;
    private Texture talkIcon;

    //Talk icon
    private boolean talk;
    private float talkX;
    private float talkY;

    //Text boxes
    private List<Text> textList;

    //Answer to multiple choice question
    private char answer;

    /**
     * Constructor for a GameCharacter.
     * @param xCoordinate x-coordinate of where to place the GameCharacter on the level map.
     * @param yCoordinate y-coordinate of where to place the GameCharacter on the level map.
     * @param filePath the texture for the game character.
     * @param characterId the number of the character used to retrieve their text. {@link TextFilePaths}
     * @param level the game level that the character belongs to.
     */
    public GameCharacter(float xCoordinate, float yCoordinate, String filePath, int characterId, int level){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        texture = new Texture(filePath);
        talkIcon = new Texture("Sprites/Icons/talkpromptindividual.png");
        talk = false;

        setTextList(characterId, level);
    }

    /**
     * A method to check whether or not the player is close enough to interact with the character.
     * @param xPlayer the current x coordinate of the player on the game map.
     * @param yPlayer the current y coordinate of the player on the game map.
     * @return true if the player is close enough, false if the player is not near the character.
     */
    public boolean isTalking(float xPlayer, float yPlayer) {
        if (((xCoordinate - 20) < xPlayer && xPlayer < (xCoordinate + 20)) && ((yCoordinate - 20) < yPlayer && yPlayer < (yCoordinate + 20))) {
            return true;
        }
        return false;
    }

    /**
     * Method to set text box(es) for a GameCharacter.
     * @param characterId the number of the character used to retrieve their text {@link TextFilePaths}
     * @param level the level that the character belongs to.
     */
    private void setTextList(int characterId, int level){
        List<String> textFilePaths = TextFilePaths.getAtlasPaths(characterId, level);
        List<Text> textList = new ArrayList<Text>();
        for(String filePath : textFilePaths){
            textList.add(new Text(filePath));
        }
        this.textList = textList;
    }

    public List<Text> getText() { return textList; }

    public Texture getTexture(){
        return this.texture;
    }

    public float getX(){
        return this.xCoordinate;
    }

    public float getY(){
        return this.yCoordinate;
    }

    public void setTalk(){
        talk = true;
        talkX = xCoordinate + 8;
        talkY = yCoordinate + 77;
    }

    public boolean getTalk(){
        return this.talk;
    }

    public float getTalkX(){
        return this.talkX;
    }

    public float getTalkY(){
        return this.talkY;
    }

    public Texture getTalkIcon(){
        return this.talkIcon;
    }

    public void setAnswer(char answer){
        this.answer = answer;
    }

    public char getAnswer(){return this.answer;}
}
