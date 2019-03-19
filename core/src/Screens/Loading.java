package Screens;

import Entities.Camera;
import Entities.Sounds;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * The Loading class is used to display the loading screens for each of the levels of the game.
 * From this, the game moves on to the loaded level.
 */
public class Loading implements Screen {

    private static final int BACKGROUND_WIDTH = 650;
    private static final int BACKGROUND_HEIGHT = 480;

    //Loading screen instance
    private static Loading loadingScreenInstance;

    private Camera camera;

    //Game
    private AstonAdventure game;

    //Background Texture
    private Texture background;

    private int level;

    //Sounds
    private Sounds Sm;

    //Timer
    private float elapsedTime;

    /**
     * Constructor for the Loading class. Initialises the background textures and sounds. Stores
     * the game object.
     *
     * @param game The Game object.
     */
    private Loading(AstonAdventure game) {

        camera = new Camera(650, 480, 325, 240, game);
        this.game = game;
        Sm = new Sounds();
    }

    /**
     * This method allows this class to follow the Singleton design pattern to allow only one instance of the Loading
     * class to be produced
     * @param game {@link AstonAdventure} game
     * @return the single version of a Loading screen instance
     */
    public static Loading getLoadingScreenInstance(AstonAdventure game) {
        if (loadingScreenInstance == null) {
            loadingScreenInstance = new Loading(game);
        }
        return loadingScreenInstance;
    }

    /**
     * Render method to display the loading screen and move on to the loaded level after enough
     * time has elapsed.
     *
     * @param delta Elapsed time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        resetCameraOnLoadingScreen();

        camera.updateCameraStationary();

        level = game.getLevel();

        elapsedTime += Gdx.graphics.getDeltaTime();

        //Sounds
        Sm.dispose();

        updateLoadingScreen();

        exitLoadingScreen();

        //Draw Background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //End
        game.batch.end();
    }

    public void resetCameraOnLoadingScreen() {
        if ((camera.getX() != 325) || (camera.getY() != 240)) {
            camera.setCameraToSpecificPoint(325, 240);
        }

    }

    public void updateLoadingScreen() {
        if (level == 1) {
            background = new Texture("Screens/Loading/Level1Load.png");
        }
        else if (level == 2) {
            background = new Texture("Screens/Loading/Level2Load.png");
        }
        else {
            background = new Texture("Screens/Loading/Level3Load.png");
        }
    }

    public void exitLoadingScreen() {
        //Begins level after elapsed time.
        if (elapsedTime >= 2) {
            if (level == 1) {
                elapsedTime = 0;
                game.setScreen(LevelOne.getLevelOneInstance(game));
            }
            else if (level ==2) {
                elapsedTime = 0;
                game.setScreen(LevelTwo.getLevelTwoInstance(game));
            }
            else {
                elapsedTime = 0;
                game.setScreen(LevelThree.getLevelThreeInstance(game));
            }

        }
    }

    //Unused
    @Override
    public void show() {

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

    //Unused
    @Override
    public void dispose() {
        background.dispose();
    }
}