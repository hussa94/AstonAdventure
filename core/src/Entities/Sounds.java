package Entities;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * The Sounds class is used to manage all of the background music and sound effects used during
 * the game.
 */
public class Sounds extends ApplicationAdapter {

    //Sounds
    public static Music background;
    public static Music sound;

    /**
     * Main menu background music.
     */
    public void menu(){
        if (isMusicPlaying()) {

            background.stop();
        }
        background = Gdx.audio.newMusic(Gdx.files.internal("Sounds/8bitmenu.wav"));
        background.setLooping(false);
        background.setVolume(0.05f);
        background.play();
    }

    /**
     * Level one background music.
     */
    public void levelOne() {
        if (isMusicPlaying()) {
            background.stop();
        }
        background = Gdx.audio.newMusic(Gdx.files.internal("Sounds/garden-summer-afternoon-birds-and-wind.mp3"));
        background.setLooping(false);
        background.setVolume(0.3f);
        background.play();
    }

    /**
     * Level two background music.
     */
    public void levelTwo() {
        if (isMusicPlaying()) {
            background.stop();
        }
        background = Gdx.audio.newMusic(Gdx.files.internal("Sounds/OneStepAtATime.wav"));
        background.setLooping(false);
        background.setVolume(0.1f);
        background.play();
    }

    public void levelThree(){
        if(isMusicPlaying()) {
            background.stop();
        }
        background = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Red Curtain.mp3"));
        background.setLooping(false);
        background.setVolume(0.3f);
        background.play();
    }


    /**
     * Text sound effect.
     */
    public void text() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/text-sound-4.mp3"));
        sound.setVolume(1f);
        sound.setLooping(false);
        sound.play();
    }

    /**
     * Click sound effect.
     */
    public void click() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/click.wav"));
        sound.setVolume(0.5f);
        sound.setLooping(false);
        sound.play();
    }

    /**
     * Startup sound effect.
     */
    public void startup() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/startup.wav"));
        sound.setVolume(0.5f);
        sound.setLooping(false);
        sound.play();

    }

    /**
     * Menu select sound effect.
     */
    public void menuSelect() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Menu-navigation-sfx.wav"));
        sound.setVolume(0.5f);
        sound.setLooping(false);
        sound.play();
    }

    /**
     * pickup sound effect.
     */
    public void pickup(){
        sound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/coins.wav"));
        sound.setVolume(0.5f);
        sound.setLooping(false);
        sound.play();
    }

    /**
     * Used to determine if background music is currently playing.
     * @return True if background music is playing.
     */
    public boolean isMusicPlaying() {

        try {
            if (background.isPlaying()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Used to determine if a sound effect is currently playing.
     * @return True if a sound effect is playing.
     */
    public boolean isSoundPlaying() {

        try {
            if (sound.isPlaying()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Method to stop all playing sounds.
     */
    public void dispose(){
        background.dispose();
        sound.dispose();
    }
}
