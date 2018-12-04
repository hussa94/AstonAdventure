package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Text {

    public float xTextBox, yTextBox;

    public float frameDuration = 1/2f;

    public Animation<TextureRegion> textBox;
    public TextureAtlas textureAtlasText;

    public Text() {
       textureAtlasText = new TextureAtlas("welcometext.atlas");
       textBox = new Animation<TextureRegion>(frameDuration, textureAtlasText.getRegions());
    }

    public void setTextPositiion(float x, float y){
        xTextBox = x;
        yTextBox = y;
    }
}
