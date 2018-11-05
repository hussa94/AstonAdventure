package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MalePlayer extends Player {


    public MalePlayer(int x, int y) {
        super(x, y);

        //TODO: Add Male Character Walk-Up Sprite png file
        walkUp = new Texture("Sprites/Characters/Male/Walk Up/female_character_walk_up_spritesheet.png");
        upAnimation = new Animation(new TextureRegion(walkUp), 3, 0.5f);

        //TODO: Add Male Character Walk-Left Sprite png file
        walkLeft = new Texture("Sprites/Characters/Female/Walk Left/female_character_walk_left_spritesheet.png");
        leftAnimation = new Animation(new TextureRegion(walkLeft), 3, 0.5f);

        //TODO: Add Male Character Walk-Down Sprite png file
        walkDown = new Texture("Sprites/Characters/Female/Walk Down/female_character_walk_down_spritesheet.png");
        downAnimation = new Animation(new TextureRegion(walkDown), 3, 0.5f);

        //TODO: Add Male Character Walk-Right Sprite png file
        walkRight = new Texture("Sprites/Characters/Female/Walk Right/female_character_walk_right_spritesheet.png");
        rightAnimation = new Animation(new TextureRegion(walkRight), 3, 0.5f);

        //TODO: Add Male Character Walk-Down Sprite png file
        standStill = new Texture("Sprites/Characters/Female/Walk Down/female_character_walk_down_spritesheet.png");
        stillAnimation = new Animation(new TextureRegion(standStill), 3, 0.5f);
    }
}
