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
    private float xMinCamera;
    private float xMaxCamera;
    private float yMinCamera;
    private float yMaxCamera;

    //Game object
    private AstonAdventure game;

    /**
     * The constructor is used to set the camera up and centralise it on the player in the
     * starting co-ordinates.
     * @param w Screen Width.
     * @param h Screen Height.
     * @param startingX X co-ordinate for the starting camera position.
     * @param startingY Y co-ordinate for the starting camera position.
     * @param game Game Object.
     */
    public Camera(float w, float h, float startingX, float startingY, AstonAdventure game) {

        //Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        //Starting camera co-ordinates
        camera.position.set(startingX, startingY, 0);

        //Update Camera
        camera.update();

        //Set game object
        this.game = game;
    }

    /**
     * Method used to set the boundaries for the camera.
     * @param xMinCamera Lower boundary for x-axis.
     * @param xMaxCamera Higher boundary for x-axis.
     * @param yMinCamera Lower boundary for y-axis.
     * @param yMaxCamera Higher boundary for y-axis.
     */
    public void setCameraBoundaries(float xMinCamera, float xMaxCamera, float yMinCamera, float yMaxCamera) {

        this.xMinCamera = xMinCamera;
        this.xMaxCamera = xMaxCamera;
        this.yMinCamera = yMinCamera;
        this.yMaxCamera = yMaxCamera;
    }

    /**
     * Update the cameras position.
     * @param player The player / character.
     */
    public void updateCameraOnPlayer(Player player) {

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


    public void updateCameraStationary() {

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
