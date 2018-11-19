package Screens;

public interface Screen {
    public void show();
    public void render();
    public void resize(int width, int height);
    public void pause();
    public void resume();
    public void hide();
    public void dispose();


}
