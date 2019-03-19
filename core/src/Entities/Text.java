package Entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Text {

    private Animation<TextureRegion> animation;

    public Text(String textureAtlasFilePath){
        TextureAtlas textureAtlas = new TextureAtlas(textureAtlasFilePath);
        animation = new Animation<TextureRegion>(1/2f, textureAtlas.getRegions());
    }

    public TextureRegion getKeyFrame(float elapsedTime){
        return animation.getKeyFrame(elapsedTime, true);
    }

}