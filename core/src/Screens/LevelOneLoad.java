package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import entities.Soundmanager;
import uk.ac.aston.team17.AstonAdventure;

public class LevelOneLoad implements Screen {

    private AstonAdventure game;
    private Texture background;
    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;
    private Soundmanager Sm;
    private float elapsedTime;

    public LevelOneLoad(AstonAdventure game) {
        this.game = game;
        background = new Texture("Screens/Loading/Level1INTRO.png");
        Sm = new Soundmanager();
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        Sm.dispose();

        if (elapsedTime >= 2) {
            game.setScreen(new LevelOne(game));
        }
        elapsedTime += Gdx.graphics.getDeltaTime();


        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();

    }
}