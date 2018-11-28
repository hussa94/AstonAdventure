package entities;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class Soundmanager extends ApplicationAdapter {
    public static Music background;
    public static Music sound;

    public void create(){
        background = Gdx.audio.newMusic(Gdx.files.internal("Town-Square.mp3"));
        background.setLooping(true);
        background.setVolume(0.01f);
        background.play();

    }

    public void soundFx(){
        sound = Gdx.audio.newMusic(Gdx.files.internal("SoundFx.wav"));
        sound.setVolume(0.03f);
        sound.setLooping(false);
        sound.play();
    }

    public void dispose(){
        background.dispose();
        sound.dispose();
    }
}
