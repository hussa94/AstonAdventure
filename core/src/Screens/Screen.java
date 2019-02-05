package Screens;

/**
 * Interface to be used for all screens used in the game.
 */
public interface Screen {
     void show();
     void render();
     void resize(int width, int height);
     void pause();
     void resume();
     void hide();
     void dispose();


}
