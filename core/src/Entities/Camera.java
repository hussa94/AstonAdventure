package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;

public class Camera {

    float startingX = 400;
    float startingY = 400;

    private OrthographicCamera camera;
    private final float xMinCamera;
    private final float xMaxCamera;
    private final float yMinCamera;
    private final float yMaxCamera;

    AstonAdventure game;

    public Camera(float w, float h, AstonAdventure game) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.position.set(startingX, startingY, 0);
        camera.update();
        xMinCamera = 400;
        xMaxCamera = 3700;
        yMinCamera = 300;
        yMaxCamera = 1800;

        this.game = game;
    }

    public void update(Player player) {

        if(!(player.x < xMinCamera || player.x > xMaxCamera)) {
            camera.position.x = player.x;
        }

        if(!(player.y < yMinCamera || player.y > yMaxCamera)) {
            camera.position.y = player.y;
        }

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

    public void viewMap(MapRenderer mapRenderer) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    public float getX() {
        return camera.position.x;
    }

    public float getY() {
        return camera.position.y;
    }

}
