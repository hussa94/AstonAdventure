package Entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Movement {

    public abstract Animation<TextureRegion> moveUp();

    public abstract Animation<TextureRegion> moveDown();

    public abstract Animation<TextureRegion> moveLeft();

    public abstract Animation<TextureRegion> moveRight();

    public abstract Animation<TextureRegion> standStill();

}
