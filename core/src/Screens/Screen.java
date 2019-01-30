package Screens;

public interface Screen {
     void show();
     void render();
     void resize(int width, int height);
     void pause();
     void resume();
     void hide();
     void dispose();


}
