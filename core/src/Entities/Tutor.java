package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The class tutor is used to display a tutor character who walks in prior to a text box being shown.
 */
class Tutor {

    // Textures / Animations
    private Animation<TextureRegion> characterAnimation;
    private TextureAtlas characterAtlas;
    private float frameDuration;

    //Status booleans
    private boolean starting = false;
    private boolean entering;
    private boolean standing;
    private boolean exiting;

    //Max X value of tutor
    private int boundaryX = 400;

    //Game object
    private AstonAdventure game;

    //Player co-ordinates
    private float x, y;

    //Player speed.
    private float speed;

    /**
     * The constructor is used to initialise the tutor and store the game object.
     * @param game The game object.
     */
    Tutor(AstonAdventure game) {

        //Store game object
        this.game = game;

        //Set frame duration
        frameDuration = 1/5f;

        //Set speed
        speed = 150;

        //Atlas for character
        characterAtlas = new TextureAtlas("Sprites/Characters/Tutors/Sylvia.atlas");

        //Set animation
        characterAnimation = new Animation<TextureRegion>(frameDuration);

    }

    /**
     * Method used to draw a moving tutor walking towards the player on the screen.
     * @param elapsedTime Timer.
     */
    void enterTutor(float elapsedTime, Player player) {

        //Set co-ordinates upon start
        if(!starting) {
            starting = true;
            y = player.getY();
            x = player.getX() + boundaryX;
        }

        //Distance between tutor and player
        int distance = 75;

        //Move tutor towards character until correct distance
        if (x > (player.getX()+ distance)) {
            movement("left");
            game.batch.draw(characterAnimation.getKeyFrame(elapsedTime, true), x, y);
        }
        else {
            starting = false;
            setStanding();
            movement("stand");
        }

    }

    /**
     * Method used to draw a moving tutor walking away from the player on the screen.
     * @param elapsedTime Timer.
     */
    void exitTutor(float elapsedTime, Player player) {

        //Move tutor towards character until correct distance
        if (x < (player.getX() + boundaryX)) {
            movement("right");
            game.batch.draw(characterAnimation.getKeyFrame(elapsedTime, true), x, y);
        }
        else {
            setFinished(elapsedTime);
        }

    }

    /**
     * Method used to draw a standing tutor next to the player on the screen.
     * @param elapsedTime Timer.
     */
    void standTutor(float elapsedTime) {
        movement("stand");
        game.batch.draw(characterAnimation.getKeyFrame(elapsedTime, true), x, y);

    }

    /**
     * Method used to determine any movement of the tutor. Moves the character accordingly.
     */
    private void movement(String direction) {

        //Determine any movement indicated by user
        if (direction.equalsIgnoreCase("right")) {
            moveRight();
            x += speed * Gdx.graphics.getDeltaTime();

        } else if (direction.equalsIgnoreCase("left")) {
            moveLeft();
            x -= speed * Gdx.graphics.getDeltaTime();

        } else {
            standStill();
        }
    }

    /**
     * Method to set the animation of the tutor whilst moving left.
     */
    private void moveLeft() {

        characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("Left"));
    }

    /**
     * Method to set the animation of the tutor whilst moving right.
     */
    private void moveRight() {

        characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("Right"));
    }

    /**
     * Method to set the animation of the tutor whilst standing still.
     */
    private void standStill(){

        characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("Stand"));
    }

    /**
     * Method to set the status of the tutor while entering the screen.
     */
    void setEntering() {

        exiting = false;
        standing = false;
        entering = true;
    }

    /**
     * Method to set the status of the tutor while exiting the screen.
     */
    void setExiting() {

        entering = false;
        standing = false;
        exiting = true;
    }

    /**
     * Method to set the status of the tutor while standing in the screen.
     */
    private void setStanding() {

        entering = false;
        standing = true;
        exiting = false;
    }

    /**
     * Method to set the status of the tutor when finished.
     */
    private void setFinished(float elapsedTime) {

        entering = false;
        standing = false;
        exiting = false;

        characterAnimation = new Animation<TextureRegion>(frameDuration, characterAtlas.findRegions("Empty"));
        game.batch.draw(characterAnimation.getKeyFrame(elapsedTime, true), x, y);
    }

    /**
     * Method used to indicate whether or not the tutor is entering the screen.
     * @return entering
     */
    boolean getEntering() {

        return  entering;
    }

    /**
     * Method used to indicate whether or not the tutor is exiting the screen.
     * @return exiting
     */
    boolean getExiting() {

        return  exiting;
    }

    /**
     * Method used to indicate whether or not the tutor is standing in the screen.
     * @return standing
     */
    boolean getStanding() {

        return  standing;
    }
}

