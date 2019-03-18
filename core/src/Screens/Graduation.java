package Screens;

import Entities.Camera;
import Entities.Sounds;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * The class Graduation is the class for the screen shown at the end of LevelThree following the end of the quiz.
 * This screen shows an image depending on the score the player got in the LevelThree quiz and plays music
 */
public class Graduation implements Screen {

    //Screen measurements
    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;

    //Graduation screen instance
    private static Graduation graduationScreenInstance;

    //Game
    private AstonAdventure game;

    //Background Texture
    private Texture background;

    //Camera
    private Camera camera;

    //Timer
    private float elapsedTimeEsc;

    //Sounds
    private Sounds Sm;

    /**
     * Constructor for the Graduation Screen. Initialises the camera to view the background, stores the game object,
     * sets the background depending on the score the player got in the {@link LevelThree} quiz and initializes the
     * music for the screen
     *
     * @param game The game object used for save data.
     */
    private Graduation(AstonAdventure game){
        this.game = game;
        camera = new Camera(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, 325, 240, game);
        setBackground(LevelThree.getPlayerScore());
        this.Sm = new Sounds();
        Sm.dispose();
    }

    /**
     * This method allows this class to follow the Singleton design pattern to allow only one instance of the Graduation
     * class to be produced
     *
     * @param game The game object used for save data.
     * @return the single version of the Graduation screen instance
     */
    static Graduation getGraduationScreenInstance(AstonAdventure game){
        if (graduationScreenInstance == null) {
            graduationScreenInstance = new Graduation(game);
        }
        return graduationScreenInstance;
    }

    /**
     * This method allows us to set the background of the Graduation screen depending on the score of the player in
     * the {@link LevelThree} quiz
     *
     * @param playerScore The score the player got in the {@link LevelThree} quiz
     */
    private void setBackground(int playerScore){
        if(playerScore >= 4){
            this.background = new Texture("Screens/Graduation/Congratulations background WITH A 1ST.png");
        } else if(playerScore<4 && playerScore>=2){
            this.background = new Texture("Screens/Graduation/Congratulations background WITH A 2.1.png");
        } else {
            this.background = new Texture("Screens/Graduation/Congratulations background WITH A 2.2.png");
        }
    }

    //Unused
    @Override
    public void show() {

    }

    /**
     * Render method used to display the chosen background for the level. This screen runs for 10 seconds before
     * transitioning to the {@link ExitScreen}
     *
     * @param delta Elapsed time
     */
    @Override
    public void render(float delta) {
        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Draw / Display background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //Sets camera position
        camera.updateCameraStationary();

        //Update elapsed time
        elapsedTimeEsc += Gdx.graphics.getDeltaTime();

        //Play menu music upon startup and loop
        if (!Sm.isMusicPlaying() && (!Sm.isSoundPlaying())) {
            Sm.menu();
        }

        //Checks if the time elapsed has been longer than 10 seconds before loading the ExitScreen
        if(elapsedTimeEsc > 10) {
            game.setScreen(ExitScreen.getExitScreenInstance(game));
        }

        //End
        game.batch.end();
    }

    //Unused
    @Override
    public void resize(int width, int height) {
    }

    //Unused
    @Override
    public void pause() {
    }

    //Unused
    @Override
    public void resume() {
    }

    //Unused
    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
