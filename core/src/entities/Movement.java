package entities;

import Screens.GameScreen;

public interface Movement {

    public abstract void moveUp(GameScreen gameScreen);

    public abstract void moveDown(GameScreen gameScreen);

    public abstract void moveLeft(GameScreen gameScreen);

    public abstract void moveRight(GameScreen gameScreen);

}
