package entities;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class Soundmanager extends ApplicationAdapter {
    public static Music background;
    public static Sound sound;

    public void create(){
        background = Gdx.audio.newMusic(Gdx.files.internal("Town-Square.mp3"));
        background.setLooping(true);
        background.setVolume(0.02f);
        background.play();

    }

    public void soundFx(){
        sound = Gdx.audio.newSound(Gdx.files.internal("picked.wav"));
        sound.play();
    }

    public void dispose(){
        background.dispose();

    }
}
