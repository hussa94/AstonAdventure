package Entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class GameCharacter {

    private Texture texture;
    private int characterId;
    private int level;
    private Texture talkIcon;

    //Coordinates
    private float xCoordinate;
    private float yCoordinate;

    //Text boxes
    private List<Text> textList;
    private int textCounter;

    private boolean talk;
    private float talkX;
    private float talkY;

    public GameCharacter(float xCoordinate, float yCoordinate, String filePath, int characterId, int level){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        texture = new Texture(filePath);
        this.characterId = characterId;
        this.level = level;
        talkIcon = new Texture("Sprites/Icons/talkprompt.png");
        talk = false;

        textCounter = 0;
        textList = getTextList(characterId, level);
    }

    /**
     * A method to check whether or not the player is close enough to interact with the character.
     * @param xPlayer the current x coordinate of the player on the game map.
     * @param yPlayer the current y coordinate of the player on the game map.
     * @return true if the player is close enough, false if the player is not near the character.
     */
    public boolean isTalking(float xPlayer, float yPlayer) {
        if (((xCoordinate - 20) < xPlayer && xPlayer < (xCoordinate + 20)) && ((yCoordinate - 20) < yPlayer && yPlayer < (yCoordinate + 20))) {
//            if (!(Sm.isSoundPlaying())) {
//                Sm.pickup();
//            }
            return true;
        }
        return false;
    }

    public Text getText(){
        return textList.get(textCounter);
    }

    public List<Text> getTextList(int characterId, int level){
        List<String> textFilePaths = TextFilePaths.getAtlasPaths(characterId, level);
        List<Text> textList = new ArrayList<Text>();
        for(String filePath : textFilePaths){
            textList.add(new Text(filePath));
        }
        return textList;
    }

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
        talkX = xCoordinate - 24;
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
}
