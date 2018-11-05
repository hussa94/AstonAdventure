package entities;

import Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Texture walkUp;
    private Texture walkLeft;
    private Texture walkRight;
    private Texture walkDown;
    private Texture standStill;
    private Animation leftAnimation;
    private Animation rightAnimation;
    private Animation upAnimation;
    private Animation downAnimation;
    private Animation stillAnimation;

    public Player(int x, int y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        walkUp = new Texture("core/assets/Sprites/Characters/Female/Walk Up/female_character_walk_up_spritesheet.png");
        upAnimation = new Animation(new TextureRegion(walkUp), 3, 0.5f);

        walkLeft = new Texture("core/assets/Sprites/Characters/Female/Walk Left/female_character_walk_left_spritesheet.png");
        leftAnimation = new Animation(new TextureRegion(walkLeft), 3, 0.5f);

        walkDown = new Texture("core/assets/Sprites/Characters/Female/Walk Down/female_character_walk_down_spritesheet.png");
        downAnimation = new Animation(new TextureRegion(walkDown), 3, 0.5f);

        walkRight = new Texture("core/assets/Sprites/Characters/Female/Walk Right/female_character_walk_right_spritesheet.png");
        rightAnimation = new Animation(new TextureRegion(walkRight), 3, 0.5f);

        standStill = new Texture("core/assets/Sprites/Characters/Female/Walk Down/female_character_walk_down_spritesheet.png");
        stillAnimation = new Animation(new TextureRegion(standStill), 3, 0.5f);

    }

    public void movementLeft(float dt){
        leftAnimation.update(dt);
        position.x = GameScreen.SPEED* Gdx.graphics.getDeltaTime();
    }

    public void update(float dt){
        stillAnimation.update(dt);
        velocity.add(0, 0);
        velocity.scl(dt);
    }

    public TextureRegion getTexture(){
        return stillAnimation.getFrame();
    }




}
