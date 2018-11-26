package entities;

import Screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FemalePlayer extends Player implements Movement {

    Animation<TextureRegion> animation;

    TextureAtlas textureAtlas;

    public FemalePlayer(float x, float y) {
        super(x, y);

        textureAtlas = new TextureAtlas("characters.atlas");

        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("female/standing"));

        /*walkUp = new Texture("Sprites/Characters/Female/Walk Up/female_character_walk_up_spritesheet.png");
        upAnimation = new Animation(new TextureRegion(walkUp), 3, 0.5f);

        walkLeft = new Texture("Sprites/Characters/Female/Walk Left/female_character_walk_left_spritesheet.png");
        leftAnimation = new Animation(new TextureRegion(walkLeft), 3, 0.5f);

        walkDown = new Texture("Sprites/Characters/Female/Walk Down/female_character_walk_down_spritesheet.png");
        downAnimation = new Animation(new TextureRegion(walkDown), 3, 0.5f);

        walkRight = new Texture("Sprites/Characters/Female/Walk Right/female_character_walk_right_spritesheet.png");
        rightAnimation = new Animation(new TextureRegion(walkRight), 3, 0.5f);

        standStill = new Texture("Sprites/Characters/Female/Walk Down/female_character_walk_down_spritesheet.png");
        stillAnimation = new Animation(new TextureRegion(standStill), 3, 0.5f);
*/
    }

    @Override
    public Animation<TextureRegion> moveUp() {
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("female/up"));
        return animation;
    }

    @Override
    public Animation<TextureRegion> moveDown() {
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("female/down"));
        return animation;
    }

    @Override
    public Animation<TextureRegion> moveLeft() {
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("female/left"));
        return animation;
    }

    @Override
    public Animation<TextureRegion> moveRight() {
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("female/right"));
        return animation;
    }

    @Override
    public Animation<TextureRegion> standStill(){
        animation = new Animation<TextureRegion>(GameScreen.getFrameDuration(), textureAtlas.findRegions("female/standing"));
        return animation;
    }

}
