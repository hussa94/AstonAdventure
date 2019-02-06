package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {

    private TiledMap tiledMap;
    private TiledMapRenderer mapRenderer;

    float width;
    float height;

    public Map(int level) {
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
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    private void levelOne() {
        tiledMap = new TmxMapLoader().load("tiles/levelonemap.tmx");
    }

    private void levelTwo() {
        tiledMap = new TmxMapLoader().load("tiles/levelonemap.tmx");
    }

    private void levelThree() {
        tiledMap = new TmxMapLoader().load("tiles/levelonemap.tmx");
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public TiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }
}
