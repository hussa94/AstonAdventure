package entities;

import Screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MalePlayer extends Player implements Movement {


    public MalePlayer(float x, float y) {
        super(x, y);

        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("male/standing"));
    }

    @Override
    public Animation<TextureRegion> moveUp() {
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("male/up"));
        return animation;
    }

    @Override
    public Animation<TextureRegion> moveDown() {
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("male/down"));
        return animation;
    }

    @Override
    public Animation<TextureRegion> moveLeft() {
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("male/left"));
        return animation;
    }

    @Override
    public Animation<TextureRegion> moveRight() {
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("male/right"));
        return animation;
    }

    @Override
    public Animation<TextureRegion> standStill(){
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("male/standing"));
        return animation;
    }

}
