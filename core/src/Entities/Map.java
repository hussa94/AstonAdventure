package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The class map contains all data pertaining to the maps used in each level. It is used
 * to set and render the background map in each level.
 */
public class Map {

    //Map
    private TiledMap tiledMap;
    private TiledMapRenderer mapRenderer;

    //Dimensions
    private float width;
    private float height;
    private float tileWidth;
    private float tileHeight;

    /**
     * Constructor used to set the map for a specific level.
      * @param level The level to be played.
     */
    public Map(int level, float tileWidth, float tileHeight) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        if (level == 1) {
            levelOne();
        }
        else if (level == 2) {
            levelTwo();
        }
        else if (level == 3) {
            levelThree();
        }
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    /**
     * Set map to level one.
     */
    private void levelOne() {
        tiledMap = new TmxMapLoader().load("tiles/levelonemap.tmx");
    }

    /**
     * Set map to level two.
     */
    private void levelTwo() {
        tiledMap = new TmxMapLoader().load("tiles/levelTwoMapResized.tmx");
    }

    /**
     * Set map to level three.
     */
    private void levelThree() {
        tiledMap = new TmxMapLoader().load("tiles/LevelThreeMap.tmx");
    }

    /**
     * Getter method for the width of the map.
     * @return Width.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Getter method for the height of the map.
     * @return Weight.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Getter method for the width of the map.
     * @return Width.
     */
    public float getTileWidth() {
        return tileWidth;
    }

    /**
     * Getter method for the height of the map.
     * @return Weight.
     */
    public float getTileHeight() {
        return tileHeight;
    }

    /**
     * Getter method for the map renderer.
     * @return mapRenderer.
     */
    public TiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    /**
     * Returns tiled map layers
     * @return mapLayers
     */
    public MapLayers getMapLayers() {
        return tiledMap.getLayers();
    }
}
