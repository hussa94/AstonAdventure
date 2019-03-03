package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

/**
 * The player class is used to display the character in each level. It displays a standing character and can move around
 * the map in all directions. Speed is determined by items picked up by the user.
 */
public class Player {

    // Textures / Animations
    private Animation<TextureRegion> characterAnimation;
    private TextureAtlas characterAtlas;
    private float frameDuration;
    private Rectangle playerRectangle;

    //Player co-ordinates
    float x, y;

    //Character information
    private String selectedCharacter;

    //Game object
    private AstonAdventure game;

    //Player restrictions
    private float xMinPlayer;
    private float xMaxPlayer;
    private float yMinPlayer;
    private float yMaxPlayer;

    //Player speed.
    private float speed;

    /**
     * The constructor is used to set up the player in its starting co-ordinates. It also decides which character
     * has been selected by the user.
     * @param selectedCharacter Character selected by the user.
     * @param game The game object.
     */
    public Player(String selectedCharacter, AstonAdventure game, float startingX, float startingY) {


        // rectangle for collisions
        playerRectangle = new Rectangle();
        playerRectangle.x = this.x;
        playerRectangle.y = this.y;
        playerRectangle.width = 16;
        playerRectangle.height = 32;

        //Boundaries for the player to walk
        xMinPlayer = 80;
        xMaxPlayer = 3970;
        yMinPlayer = 158;
        yMaxPlayer = 1960;

        //Animation frame rate
        frameDuration = 1 / 5f;

        //Set game object
        this.game = game;

        //Starting co-ordinates for the player
        x = startingX;
        y = startingY;

        //Sprite sheet for character
        characterAtlas = new TextureAtlas("Sprites/Characters/Players/characters.atlas");

        //Set character selection
        this.selectedCharacter = selectedCharacter;

        //Set animation
        characterAnimation = new Animation<TextureRegion>(frameDuration);

        //Set starting animation
        standStill();

        //Set player speed
        speed = 150;
    }

    public void setPlayerBoundaries(float xMinPlayer, float xMaxPlayer, float yMinPlayer, float yMaxPlayer) {

        this.xMinPlayer = xMinPlayer;
        this.xMaxPlayer = xMaxPlayer;
        this.yMinPlayer = yMinPlayer;
        this.yMaxPlayer = yMaxPlayer;
    }

    /**
     * Method used to draw the character on the game screen.
     * @param elapsedTime Timer.
     */
    public void drawCharacter(float elapsedTime) {

        game.batch.draw(characterAnimation.getKeyFrame(elapsedTime, true), x, y);
    }

    /**
     * Method used to determine any movement of the character by the player. Moves the character accordingly.
     */
    public void movement(Map map) {

        float gameDelta = 0.016f;

        //Set frame rate based on player speed
        if (speed == 200) {
            frameDuration = 1 / 10f;
        } else {
            frameDuration = 1 / 5f;
        }

        float oldX = getX(), oldY = getY();

        //Determine any movement indicated by user
        if (((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) && (y < yMaxPlayer))) {
            moveUp();
            y += speed * gameDelta;

        } else if (((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) && (y > yMinPlayer)))  {
            moveDown();
            y -= speed * gameDelta;

        } else if (((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) && (x < xMaxPlayer))) {
            moveRight();
            x += speed * gameDelta;

        } else if (((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) && (x > xMinPlayer))) {
            moveLeft();
            x -= speed * gameDelta;

        } else {
            standStill();
        }

        checkCollision(map, oldX, oldY);
    }

    private void checkCollision(Map map, float oldX, float oldY) {
        TiledMapTileLayer.Cell tile;
        tile = getCurrentTile(map);

        if (tile != null) {
            if (tile.getTile().getProperties().containsKey("Collision")) {
                x = oldX;
                y = oldY;
            }
        }
    }

    /**
     * Method to set the animation of the player whilst moving upwards.
     */
    private void moveUp() {

        if (selectedCharacter.equalsIgnoreCase("female")) {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/up"));
        }
        else {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/up"));
        }
    }

    /**
     * Method to set the animation of the player whilst moving downwards.
     */
    private void moveDown() {

        if (selectedCharacter.equalsIgnoreCase("female")) {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/down"));
        }
        else {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/down"));
        }
    }

    /**
     * Method to set the animation of the player whilst moving left.
     */
    private void moveLeft() {

        if (selectedCharacter.equalsIgnoreCase("female")) {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/left"));
        }
        else {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/left"));
        }

    }

    /**
     * Method to set the animation of the player whilst moving right.
     */
    private void moveRight() {

            if (selectedCharacter.equalsIgnoreCase("female")) {
                characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/right"));
            }
            else {
                characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/right"));
            }
    }

    /**
     * Method to set the animation of the player whilst standing still.
     */
    public void standStill(){

            if (selectedCharacter.equalsIgnoreCase("female")) {
                characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/standing"));
            }
            else {
                characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/standing"));
            }
    }

    /**
     * Method used to increase the speed of the character
     */
    void increaseSpeed() {

        speed = 200;
    }

    /**
     * Method used to retrieve the x co-ordinate of the player
     * @return X
     */
    public float getX() {

        return x;
    }

    /**
     * Method used to retrieve the y co-ordinate of the player
     * @return Y
     */
    public float getY() {

        return y;
    }

    /**
     * Method used to retrieve the tile at the current position of the player
     * @param map The current map being
     * @return The tile the player is currently on
     */
    private TiledMapTileLayer.Cell getCurrentTile(Map map){
        TiledMapTileLayer collisionLayer = map.getCollisionLayer();

        int tileX = (int) (x / map.getTileWidth());
        int tileY = (int) (y / map.getTileHeight());

        return collisionLayer.getCell(tileX, tileY);
    }
}
