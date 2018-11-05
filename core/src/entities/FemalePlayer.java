package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FemalePlayer extends Player {

    public FemalePlayer(int x, int y) {
        super(x, y);

        walkUp = new Texture("Sprites/Characters/Female/Walk Up/female_character_walk_up_spritesheet.png");
        upAnimation = new Animation(new TextureRegion(walkUp), 3, 0.5f);

        walkLeft = new Texture("Sprites/Characters/Female/Walk Left/female_character_walk_left_spritesheet.png");
        leftAnimation = new Animation(new TextureRegion(walkLeft), 3, 0.5f);

        walkDown = new Texture("Sprites/Characters/Female/Walk Down/female_character_walk_down_spritesheet.png");
        downAnimation = new Animation(new TextureRegion(walkDown), 3, 0.5f);

        walkRight = new Texture("Sprites/Characters/Female/Walk Right/female_character_walk_right_spritesheet.png");
        rightAnimation = new Animation(new TextureRegion(walkRight), 3, 0.5f);

        standStill = new Texture("Sprites/Characters/Female/Walk Down/female_character_walk_down_spritesheet.png");
        stillAnimation = new Animation(new TextureRegion(standStill), 3, 0.5f);

    }
}
