package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;

/**
 * The Camera class is used to set and update the camera used to view a section of the map. The camera will always
 * be centered on the player, unless at the boundaries of the map.
 */
public class Camera {

    //Camera
    private OrthographicCamera camera;

    //Camera boundaries
    private final float xMinCamera;
    private final float xMaxCamera;
    private final float yMinCamera;
    private final float yMaxCamera;

    //Game object
    private AstonAdventure game;

    /**
     * The constructor is used to set the camera up and centralise it on the player in the
     * starting co-ordinates.
     * @param w Screen Width.
     * @param h Screen Height.
     * @param game Game Object.
     */
    public Camera(float w, float h, AstonAdventure game) {

        //Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        //Starting camera co-ordinates
        float startingX = 400;
        float startingY = 400;
        camera.position.set(startingX, startingY, 0);

        //Update Camera
        camera.update();

        //Set camera boundaries
        xMinCamera = 400;
        xMaxCamera = 3700;
        yMinCamera = 400;
        yMaxCamera = 1800;

        //Set game object
        this.game = game;
    }

    /**
     * Update the cameras position.
     * @param player The player / character.
     */
    public void update(Player player) {

        //Centralise on player if not exceeding limits
        if(!(player.x < xMinCamera || player.x > xMaxCamera)) {
            camera.position.set(player.getX(), camera.position.y, 0);
        }

        //Centralise on player if not exceeding limits
        if(!(player.y < yMinCamera || player.y > yMaxCamera)) {
            camera.position.set(camera.position.x, player.getY(), 0);
        }

        //Update
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Used to set the view of the map using the map renderer.
     * @param mapRenderer Map renderer.
     */
    public void viewMap(MapRenderer mapRenderer) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    /**
     * Method used to retrieve the x position of the camera.
     * @return X.
     */
    float getX() {
        return camera.position.x;
    }

    /**
     * Method used to retrieve the y position of the camera.
     * @return Y.
     */
    float getY() {
        return camera.position.y;
    }

}
