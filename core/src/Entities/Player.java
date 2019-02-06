package Entities;

import Game.AstonAdventure;
import Screens.LevelOne;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {

    Animation<TextureRegion> characterAnimation;

    TextureAtlas characterAtlas;

    float x, y;

    String selectedCharacter;

    //SpriteBatch batch = new SpriteBatch();

    AstonAdventure game;

    private float frameDuration;

    public float speed;

    public Player(String selectedCharacter, AstonAdventure game) {

        frameDuration = 1 / 5f;

        this.game = game;

        x = 400;
        y = 400;

        characterAtlas = new TextureAtlas("Sprites/Characters/characters.atlas");

        this.selectedCharacter = selectedCharacter;

        characterAnimation = new Animation<TextureRegion>(frameDuration);

        speed = 100;
    }

    public void drawCharacter(float elapsedTime) {
        game.batch.draw(characterAnimation.getKeyFrame(elapsedTime, true), x, y);
    }

    public void movement() {

        //Set frame rate based on player speed
        if (speed == 200) {
            frameDuration = 1 / 10f;
        } else {
            frameDuration = 1 / 5f;
        }

        if (((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)))) {
            moveUp();
            y += speed * Gdx.graphics.getDeltaTime();


        } else if (((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))))  {
            moveDown();
            y -= speed * Gdx.graphics.getDeltaTime();


        } else if (((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)))) {
            moveRight();
            x += speed * Gdx.graphics.getDeltaTime();


        } else if (((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))))  {
            moveLeft();
            x -= speed * Gdx.graphics.getDeltaTime();


        } else {
            standStill();
        }
    }

    private void moveUp() {
        if (selectedCharacter.equalsIgnoreCase("female")) {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/up"));
        }
        else {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/up"));
        }
    }


    private void moveDown() {
        if (selectedCharacter.equalsIgnoreCase("female")) {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/down"));
        }
        else {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/down"));
        }
    }


    private void moveLeft() {
        if (selectedCharacter.equalsIgnoreCase("female")) {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/left"));
        }
        else {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/left"));
        }

    }

    private void moveRight() {
            if (selectedCharacter.equalsIgnoreCase("female")) {
                characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/right"));
            }
            else {
                characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/right"));
            }
    }

    private void standStill(){
            if (selectedCharacter.equalsIgnoreCase("female")) {
                characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/standing"));
            }
            else {
                characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/standing"));
            }
    }

    public void incrreaseSpeed() {
        speed = 200;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
