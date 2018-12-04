package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Text {

    public float xTextBox, yTextBox;
    public float xSylvia, ySylvia;

    public float frameDuration = 1/2f;

    public int currentSpeech = 1;

    public boolean textInterrupt;

    public Texture sylvia;
    public Animation<TextureRegion> textBox;
    public TextureAtlas textureAtlasText;

    public Text() {
       textureAtlasText = new TextureAtlas("Sprites/Objects/Text/Text 1/Text 1.atlas");
       textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
       textInterrupt = true;
       sylvia = new Texture("tiles/sylvia.png");
    }

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

    public void setTextInterrupt() {
        textInterrupt = true;
    }

    public void removeTextInterrupt() {
        textInterrupt = false;
    }

    public void setTextPositiion(float x, float y){
        xTextBox = x;
        yTextBox = y;
    }

    public void setSylviaPosition(float x, float y) {
        xSylvia = x;
        ySylvia = y;
    }
}
