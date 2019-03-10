package Entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Text {

    private Animation<TextureRegion> animation;

    private float elapsedTime;
    private float frameDuration = 1/2f;

    public Text(String textureAtlasFilePath){
        TextureAtlas textureAtlas = new TextureAtlas(textureAtlasFilePath);
        animation = new Animation<TextureRegion>(frameDuration, textureAtlas.getRegions());
        elapsedTime = 0;

    }

    public TextureRegion getKeyFrame(){
        elapsedTime += frameDuration;
      return animation.getKeyFrame(elapsedTime - frameDuration, true);
    }


}
