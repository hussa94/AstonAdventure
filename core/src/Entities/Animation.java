package Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * The Animation class is used to play all of the stored animations in the game.
 */
public class Animation {

    //Frame information
    Array<TextureRegion> frames;
    float maxFrameTime;
    float currentFrameTime;
    int frameCount;
    int frame;

    /**
     * Constructor that sets frame information and initialises textures.
     * @param region Textures.
     * @param frameCount Amount of frames.
     * @param cycleTime Total animation duration.
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime){

        frames = new Array<TextureRegion>();
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    //Unused
    public Animation(float frameDuration, Array<TextureAtlas.AtlasRegion> regions) {

    }

    /**
     * Method used to update the animation and switch frames
     * @param delta Elapsed time.
     */
    public void update(float delta){
        currentFrameTime += delta;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;

    }

    /**
     * Method used to get the current frame of the animation.
     * @return The current frame of the animation.
     */
    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
