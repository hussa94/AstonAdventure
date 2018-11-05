package entities;

import Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 position, velocity;
    protected Texture walkUp, walkLeft, walkRight, walkDown, standStill;
    protected Animation leftAnimation, rightAnimation, upAnimation, downAnimation, stillAnimation;

    public Player(int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
    }

    public void movementLeft(float dt) {
        leftAnimation.update(dt);
        position.x = GameScreen.SPEED * Gdx.graphics.getDeltaTime();
    }

    public void update(float dt) {
        stillAnimation.update(dt);
        velocity.add(0, 0);
        velocity.scl(dt);
    }

    public TextureRegion getTexture() {
        return stillAnimation.getFrame();
    }


}
