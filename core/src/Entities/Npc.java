package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * The class Npc is used to add random moving non player characters into levels.
 */
public class Npc {

    //Textures / Animations
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> characterAnimation;
    private TextureAtlas characterAtlas;
    private float frameDuration;

    //NPC information
    private HashMap<Integer, String> NPCs = new HashMap<Integer, String>();
    private float[] x;
    private float[] y;
    private String lastDirection[];

    //Game Object
    private AstonAdventure game;

    //Randomise
    private Random random = new Random();

    //NPC timer before changing direction
    private int timeSinceLastDirectionChange = 0;

    //Number of NPCs in the level
    private int numberOfNPCs;

    //NPC speed
    private float speed;

    //Player restrictions
    private int xMinPlayer;
    private int xMaxPlayer;
    private int yMinPlayer;
    private int yMaxPlayer;

    /**
     * The constructor is used to initialise the NPCs and set their co-ordinates and character profile.
     * @param game The game object.
     * @param numberOfNPCs The number of NPCs to display.
     */
    public Npc(AstonAdventure game, int numberOfNPCs, Map map) {

        //Boundaries for the NPCs to walk
        xMinPlayer = 80;
        xMaxPlayer = 3970;
        yMinPlayer = 158;
        yMaxPlayer = 1960;

        //Set number of NPCs in level
        this.numberOfNPCs = numberOfNPCs;

        //Set frame duration
        frameDuration = 1 / 5f;

        //Assign game object
        this.game = game;

        //Set textures / animations
        characterAtlas = new TextureAtlas("Sprites/Characters/Players/characters.atlas");
        characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration);

        //Set player speed
        speed = 50;

        //Set up directions and co-ordinates
        lastDirection = new String[numberOfNPCs];
         x = new float[numberOfNPCs];
         y = new float[numberOfNPCs];

         //Give each NPC a character, co-ordinates, direction
        for (int i = 0; i< numberOfNPCs; i++) {
            NPCs.put(i, randomCharacter());
            x[i] = randomXCoordinate();
            y[i] = randomYCoordinate();
            lastDirection[i] = randomDirection();
        }

        //Give each NPC a character, co-ordinates, direction
        for (int i = 0; i< numberOfNPCs; i++) {
            boolean setupColliding = false;

            while (setupColliding) {
                x[i] = randomXCoordinate();
                y[i] = randomYCoordinate();
                setupColliding = checkSetupCollision(map, i);
            }

        }
    }

    /**
     * Used to draw each of the NPCs in the level.
     * @param elapsedTime Timer.
     */
    public void drawNPCs(float elapsedTime, Camera camera, Map map) {

        int maxStepsBeforeChange = 500;

        //Increase timer since last direction change
        timeSinceLastDirectionChange++;

        //Change direction after max number of cycles
        if (timeSinceLastDirectionChange >= maxStepsBeforeChange) {
            for (int i = 0; i< numberOfNPCs; i++) {
                lastDirection [i] = randomDirection();
            }
            timeSinceLastDirectionChange = 0;
        }

        //Calculate movement and draw each NPC
        for (int i = 0; i< numberOfNPCs; i++) {
            movement(lastDirection[i], i, map);
            if ((camera.getX()-370 < x[i] && x[i] < camera.getX()+370) && ((camera.getY()-350 < y[i] && y[i] < camera.getY()+350))) {
                game.batch.draw(characterAnimation.getKeyFrame(elapsedTime, true), x[i], y[i]);
            }
        }
    }

    /**
     * Method used to determine any movement of the NPCs. Moves the characters accordingly.
     * @param direction The direction each NPC is currently heading in.
     * @param npc The active NPC.
     */
    private void movement(String direction, int npc, Map map) {

        float oldX = getX(npc), oldY = getY(npc);

        //Determine any movement by NPC
        if ((direction.equalsIgnoreCase("Up")) && (y[npc] < yMaxPlayer)) {
            moveUp(npc);
            y[npc] += speed * Gdx.graphics.getDeltaTime();


        } else if (direction.equalsIgnoreCase("Down") && (y[npc] > yMinPlayer))  {
            moveDown(npc);
            y[npc] -= speed * Gdx.graphics.getDeltaTime();


        } else if (direction.equalsIgnoreCase("Left")  && (x[npc] < xMaxPlayer)) {
            moveRight(npc);
            x[npc] += speed * Gdx.graphics.getDeltaTime();


        } else if (direction.equalsIgnoreCase("Right") && (x[npc] > xMinPlayer))  {
            moveLeft(npc);
            x[npc] -= speed * Gdx.graphics.getDeltaTime();


        } else {
            standStill(npc);
        }

        checkCollision(map, oldX, oldY, npc);
    }

    /**
     * Method to check if the player is colliding with an object, and reposition if they are
     *
     * @param map  The current level map
     * @param oldX The player's X coordinate before moving into collision range
     * @param oldY The player's Y coordiante before moving into collision range
     */

    private void checkCollision(Map map, float oldX, float oldY, int npc) {
        boolean colliding = false;

        List<TiledMapTileLayer.Cell> tiles = getCurrentTiles(map, npc);
        for (TiledMapTileLayer.Cell tile : tiles) {
            if (tile != null && tile.getTile().getProperties().containsKey("Collision")) {
                colliding = true;
                break;
            }
        }
        if (colliding) {
            x[npc] = oldX;
            y[npc] = oldY;
            lastDirection[npc] = randomDirection();
            timeSinceLastDirectionChange = 0;
        }
    }


    private boolean checkSetupCollision(Map map, int npc) {


        List<TiledMapTileLayer.Cell> tiles = getCurrentTiles(map, npc);
        for (TiledMapTileLayer.Cell tile : tiles) {
            if (tile != null && tile.getTile().getProperties().containsKey("Collision")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Method to set the animation of the player whilst moving upwards.
     * @param npc The active NPC.
     */
    private void moveUp(int npc) {

        if (NPCs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/up"));
        }
        else {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/up"));
        }
    }

    /**
     * Method to set the animation of the player whilst moving downwards.
     * @param npc The active NPC.
     */
    private void moveDown(int npc) {

        if (NPCs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/down"));
        }
        else {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/down"));
        }
    }

    /**
     * Method to set the animation of the player whilst moving left.
     * @param npc The active NPC.
     */
    private void moveLeft(int npc) {

        if (NPCs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/left"));
        }
        else {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/left"));
        }

    }

    /**
     * Method to set the animation of the player whilst moving right.
     * @param npc The active NPC.
     */
    private void moveRight(int npc) {

        if (NPCs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/right"));
        }
        else {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/right"));
        }
    }

    /**
     * Method to set the animation of the player whilst standing still.
     * @param npc The active NPC.
     */
    private void standStill(int npc){

        if (NPCs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/standing"));
        }
        else {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/standing"));
        }
    }

    /**
     * Method used to give an NPC a random character from a selected list.
     * @return The random character.
     */
    private String randomCharacter() {

        //Number of NPC character skins
        int numberOfCharacters = 2;

        //Possible NPC characters
        String character[] = new String[numberOfCharacters];
        character[0] = "Male";
        character[1] = "Female";

        return character[random.nextInt(character.length)];
    }

    /**
     * Method used to retrieve the tile at the current position of the player
     *
     * @param map The current level map
     * @return The tile the player is currently positioned at
     */
    private List<TiledMapTileLayer.Cell> getCurrentTiles(Map map, int npc) {

        List<TiledMapTileLayer.Cell> tileList = new ArrayList<TiledMapTileLayer.Cell>();
        MapLayers mapLayers = map.getMapLayers();

        int tileX = (int) (x[npc] / map.getTileWidth());
        int tileY = (int) (y[npc] / map.getTileHeight());

        for (MapLayer mapLayer : mapLayers) {
            TiledMapTileLayer tiledMapTileLayer;
            try {
                tiledMapTileLayer = (TiledMapTileLayer) mapLayer;
            } catch (Exception e) {
                continue;
            }
            TiledMapTileLayer.Cell tile = tiledMapTileLayer.getCell(tileX, tileY);
            if (tile != null) {
                tileList.add(tile);
            }
        }

        return tileList;
    }

    /**
     * Method used to give an NPC a random starting X co-ordinate.
     * @return The random co-ordinate.
     */
    private int randomXCoordinate() {


        return random.nextInt(xMaxPlayer-xMinPlayer + 1) + xMinPlayer;
    }

    /**
     * Method used to give an NPC a random starting Y co-ordinate.
     * @return The random co-ordinate.
     */
    private int randomYCoordinate() {

        return random.nextInt(yMaxPlayer-yMinPlayer + 1) + yMinPlayer;
    }

    /**
     * Method to give a random direction for the NPC to move in
     * @return The random direction.
     */
    private String randomDirection() {
        int randomInt = random.nextInt(4);

        if (randomInt == 0) {
            return "Up";
        }
        else if (randomInt == 1) {
            return "Down";
        }
        else if (randomInt == 2) {
            return "Right";
        }
        else if (randomInt == 3) {
            return "Left";
        }
        else {
            return "Stand";
        }
    }

    /**
     * Method used to retrieve the x co-ordinate of the player
     *
     * @return X
     */
    public float getX(int npc) {

        return x[npc];
    }

    /**
     * Method used to retrieve the y co-ordinate of the player
     *
     * @return Y
     */
    public float getY(int npc) {

        return y[npc];
    }
}
