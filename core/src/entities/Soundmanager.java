package entities;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class Soundmanager extends ApplicationAdapter {
    public static Music background;
    public static Music sound;

    public void menu(){
        if (isMusicPlaying()) {

            background.stop();
        }
        background = Gdx.audio.newMusic(Gdx.files.internal("Sounds/8bitmenu.wav"));
        background.setLooping(false);
        background.setVolume(0.05f);
        background.play();



    }

    public void levelOne() {
        if (isMusicPlaying()) {

            background.stop();
        }
        background = Gdx.audio.newMusic(Gdx.files.internal("Sounds/mellow.wav"));
        background.setLooping(false);
        background.setVolume(0.3f);
        background.play();


    }

    public void click() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/click.wav"));
        sound.setVolume(1f);
        sound.setLooping(false);
        sound.play();
    }

    public void startup() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/startup.wav"));
        sound.setVolume(1f);
        sound.setLooping(false);
        sound.play();

    }

    public void menuSelect() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/coins.wav"));
        sound.setVolume(0.5f);
        sound.setLooping(false);
        sound.play();
    }

    public void soundFx(){
        sound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/SoundFx.wav"));
        sound.setVolume(1f);
        sound.setLooping(false);
        sound.play();
    }


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

    public void dispose(){
        background.dispose();
        sound.dispose();
    }
}
