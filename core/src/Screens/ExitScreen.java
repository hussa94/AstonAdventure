package Screens;

import Entities.Camera;
import Entities.Sounds;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The class ExitScreen is used to display the final scoreboard/end game screens when the
 * user has completed the game. Upon completion, the user can either exit the game or move
 * on to Aston's website.
 */
public class ExitScreen implements Screen {

    //ExitScreen Instance
    private static ExitScreen exitScreenInstance;

    //Camera
    Camera camera;

    //Game
    private AstonAdventure game;

    private Sounds sm;

    //Background
    private Texture background;
    private Animation<TextureRegion> credits;
    TextureAtlas textureAtlas;

    private float frameDuration = 3f;

    private boolean animationFinished = false;

    private float elapsedTime;
    private float elapsedTimeClick;


    /**
     * Constructor for ExitScreen. Initialises the background and button textures and sounds.
     * Stores the game object.
     *
     * @param game The Game object.
     */
    private ExitScreen(AstonAdventure game) {

        //Set game object
        this.game = game;

        sm = new Sounds();
        sm.dispose();

        //Set textures
        background = new Texture("Screens/Exit/FinalExit.png");

        textureAtlas = new TextureAtlas("Screens/Exit/Exit.atlas");
        credits = new Animation<TextureRegion>(frameDuration, textureAtlas.findRegions("Exit"));

        //Set camera to look at exit screen
        camera = new Camera(650, 480, 325, 240, game);
    }

    public static ExitScreen getExitScreenInstance(AstonAdventure game) {
        if (exitScreenInstance == null) {
            exitScreenInstance = new ExitScreen(game);
        }
        return exitScreenInstance;
    }


    /**
     * Render method to display the exit screen and buttons. Will exit the app or redirect
     * based on user input.
     *
     * @param delta Elapsed time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        elapsedTime += Gdx.graphics.getDeltaTime();
        elapsedTimeClick += Gdx.graphics.getDeltaTime();

        camera.updateCameraStationary();

        resetCameraOnExitScreen();

        if (!sm.isMusicPlaying() && (!sm.isSoundPlaying())) {
            sm.menu();
        }

        if (!animationFinished) {
            game.batch.draw(credits.getKeyFrame(elapsedTime, true), 0, 0);
        }
        else {
            game.batch.draw(background,0, 0);
        }

        isAnimationFinished();

        buttons();

        //End
        game.batch.end();
    }

    public void buttons() {
        if (animationFinished) {
            if ((Gdx.input.getX() > 142) && (Gdx.input.getX() < 504) && ((Gdx.input.getY() > 89) && (Gdx.input.getY() < 178))) {
                background = new Texture("Screens/Exit/FinalExitAstonAdventure.png");
                if(Gdx.input.isTouched()) {
                    if (!sm.isSoundPlaying()) {
                        sm.menuSelect();
                    }
                    if(elapsedTimeClick > 0.5) {
                        Gdx.net.openURI("https://bit.ly/AstonAdventureGame");
                        elapsedTimeClick = 0;
                    }
                }
            }
            else if ((Gdx.input.getX() > 142) && (Gdx.input.getX() < 504) && ((Gdx.input.getY() > 192) && (Gdx.input.getY() < 281))) {
                background = new Texture("Screens/Exit/FinalExitAstonUniversity.png");
                if(Gdx.input.isTouched()) {
                    if (!sm.isSoundPlaying()) {
                        sm.menuSelect();
                    }
                    if(elapsedTimeClick > 0.5) {
                    Gdx.net.openURI("https://www2.aston.ac.uk/");
                    elapsedTimeClick = 0;
                }
                }
            }
            else if ((Gdx.input.getX() > 142) && (Gdx.input.getX() < 504) && ((Gdx.input.getY() > 295) && (Gdx.input.getY() < 388))) {
                background = new Texture("Screens/Exit/FinalExitExit.png");
                if(Gdx.input.isTouched()) {
                    if (!sm.isSoundPlaying()) {
                        sm.menuSelect();
                    }
                    if(elapsedTimeClick > 0.5) {
                    Gdx.app.exit();
                    elapsedTimeClick = 0;
                }
                }
            }
            else {
                background = new Texture("Screens/Exit/FinalExit.png");
            }
        }
    }

    public void isAnimationFinished() {
        if (credits.isAnimationFinished(elapsedTime)) {
            animationFinished = true;
        }
    }


    public void resetCameraOnExitScreen() {
        if ((camera.getX() != 325) || (camera.getY() != 240)) {
            camera.setCameraToSpecificPoint(325, 240);
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