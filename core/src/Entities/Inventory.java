package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Inventory {

    public float xHUD, yHUD;

    public boolean drawInventory;

    public float frameDuration = 1 / 50f;

    public Animation<TextureRegion> HUDAnimated;
    public TextureAtlas textureAtlasHUD;
    public Texture HUDStill;

    public Inventory() {

    }

    public void setInventoryPositiion(float x, float y) {
        xHUD = x;
        yHUD = y;
    }

    public void beginDrawingInventory() {
        drawInventory = true;
    }

    public  void endDrawingInventory() {
        drawInventory = false;
    }

    public void checkHUDStatus(Items items) {

        boolean coffeePick = items.coffeePick;
        boolean shoesPick = items.shoesPick;


            if (!coffeePick && !shoesPick) {
                textureAtlasHUD = new TextureAtlas("Sprites/Objects/Inventory/Inventory Animated/Empty Inventory/Inventory Empty.atlas");
                HUDAnimated = new Animation<TextureRegion>(frameDuration, textureAtlasHUD.getRegions());
                HUDStill = new Texture("Sprites/Objects/Inventory/Inventory Still/Empty_Inventory.png");
            } else if (coffeePick && !shoesPick) {
                textureAtlasHUD = new TextureAtlas("Sprites/Objects/Inventory/Inventory Animated/Inventory Coffee/Inventory Coffee.atlas");
                HUDAnimated = new Animation<TextureRegion>(frameDuration, textureAtlasHUD.getRegions());
                HUDStill = new Texture("Sprites/Objects/Inventory/Inventory Still/Inventory_Coffee.png");
            } else if (!coffeePick && shoesPick) {
                textureAtlasHUD = new TextureAtlas("Sprites/Objects/Inventory/Inventory Animated/Inventory Shoes/Inventory Shoes.atlas");
                HUDAnimated = new Animation<TextureRegion>(frameDuration, textureAtlasHUD.getRegions());
                HUDStill = new Texture("Sprites/Objects/Inventory/Inventory Still/Inventory_Shoes.png");
            } else if (coffeePick && shoesPick) {
                textureAtlasHUD = new TextureAtlas("Sprites/Objects/Inventory/Inventory Animated/Inventory Full/Inventory Full.atlas");
                HUDAnimated = new Animation<TextureRegion>(frameDuration, textureAtlasHUD.getRegions());
                HUDStill = new Texture("Sprites/Objects/Inventory/Inventory Still/Inventory_Full.png");
            }

    }

}
