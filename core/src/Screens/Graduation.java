package Screens;

import Entities.Camera;
import Entities.Sounds;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class Graduation implements Screen {

    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;

    private static Graduation graduationScreenInstance;

    private AstonAdventure game;

    //Background
    private Texture background;

    private Camera camera;

    //Timer
    private float elapsedTimeEsc;

    private Sounds Sm;

    private Graduation(AstonAdventure game){
        this.game = game;
        camera = new Camera(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, 325, 240, game);
        setBackground(LevelThree.getPlayerScore());
        this.Sm = new Sounds();
        Sm.dispose();
    }

    public static Graduation getGraduationScreenInstance(AstonAdventure game){
        if (graduationScreenInstance == null) {
            graduationScreenInstance = new Graduation(game);
        }
        return graduationScreenInstance;
    }

    private void setBackground(int playerScore){
        if(playerScore >= 4){
            this.background = new Texture("Screens/Graduation/Congratulations background WITH A 1ST.png");
        } else if(playerScore<4 && playerScore>=2){
            //TODO: Set background to 2.1
            this.background = new Texture("Screens/Graduation/Congratulations background WITH A 2.1.png");
        } else {
            //TODO: Set background to 2.2
            this.background = new Texture("Screens/Graduation/Congratulations background WITH A 2.2.png");
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Draw / Display background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        camera.updateCameraStationary();

        //Update elapsed time
        elapsedTimeEsc += Gdx.graphics.getDeltaTime();

        //Play menu music upon startup and loop
        if (!Sm.isMusicPlaying() && (!Sm.isSoundPlaying())) {
            Sm.menu();
        }

        if(elapsedTimeEsc > 10) {
            game.setScreen(ExitScreen.getExitScreenInstance(game));
        }

        //End
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

    }
}
