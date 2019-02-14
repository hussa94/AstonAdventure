package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Random;

public class Npc {

    com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> characterAnimation;

    TextureAtlas characterAtlas;

    private HashMap<Integer, String> npcs = new HashMap<Integer, String>();
    private float[] x;
    private float[] y;
    private String lastDirection[];

    AstonAdventure game;

    Random random = new Random();

    private int numberOfCharacters;

    private int randomTimer = 0;

    private int numberOfNpcs;

    private float frameDuration;

    private float speed;

    //Player restrictions
    private int xMinPlayer;
    private int xMaxPlayer;
    private int yMinPlayer;
    private int yMaxPlayer;

    public Npc(AstonAdventure game, int numberOfNpcs) {

        //Boundaries for the player to walk
        xMinPlayer = 80;
        xMaxPlayer = 3970;
        yMinPlayer = 158;
        yMaxPlayer = 1960;

        numberOfCharacters = 2;

        this.numberOfNpcs = numberOfNpcs;

        lastDirection = new String[numberOfNpcs];

        frameDuration = 1 / 5f;

        this.game = game;

        characterAtlas = new TextureAtlas("Sprites/Characters/characters.atlas");

        characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration);

        speed = 50;

         x = new float[numberOfNpcs];
         y = new float[numberOfNpcs];

        for (int i=0; i<numberOfNpcs; i++) {
            npcs.put(i, randomCharacter());
            x[i] = randomXCoordinate();
            y[i] = randomYCoordinate();
            lastDirection[i] = randomDirection();
        }
    }

    public void drawNpcs(float elapsedTime) {

        randomTimer++;

        if (randomTimer >= 500) {
            for (int i=0; i<numberOfNpcs; i++) {
                lastDirection [i] = randomDirection();
            }
            randomTimer = 0;
        }

        for (int i=0; i<numberOfNpcs; i++) {


            movement(lastDirection[i], i);


            game.batch.draw(characterAnimation.getKeyFrame(elapsedTime, true), x[i], y[i]);
        }
    }

    public void movement(String direction, int npc) {

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
    }

    private void moveUp(int npc) {
        if (npcs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/up"));
        }
        else {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/up"));
        }
    }


    private void moveDown(int npc) {
        if (npcs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/down"));
        }
        else {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/down"));
        }
    }


    private void moveLeft(int npc) {
        if (npcs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/left"));
        }
        else {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/left"));
        }

    }

    private void moveRight(int npc) {
        if (npcs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/right"));
        }
        else {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/right"));
        }
    }

    private void standStill(int npc){
        if (npcs.get(npc).equalsIgnoreCase("female")) {
            characterAnimation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("female/standing"));
        }
        else {
            characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("male/standing"));
        }
    }

    private String randomCharacter() {

        String character[] = new String[numberOfCharacters];

        character[0] = "Male";
        character[1] = "Female";


        return character[random.nextInt(character.length)];
    }

    private int randomXCoordinate() {

        return random.nextInt(xMaxPlayer-xMinPlayer + 1) + xMinPlayer;
    }

    private int randomYCoordinate() {

        return random.nextInt(yMaxPlayer-yMinPlayer + 1) + yMinPlayer;
    }

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
}
