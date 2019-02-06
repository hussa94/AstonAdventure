package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The class inventory is used to determine which items the player has picked up, and display the
 * content of their backpack.
 */
public class Inventory {

    //Co-ordinates
    public float xHUD, yHUD;

    //Backpack check
    public boolean drawInventory;

    //Animation frame duration
    private float frameDuration = 1 / 50f;

    private float elapsedTime = 0;

    AstonAdventure game;

    //Textures and animations
    public Animation<TextureRegion> HUDAnimated;
    private TextureAtlas textureAtlasHUD;
    public Texture HUDStill;

    public Inventory(AstonAdventure game) {
        setInventoryPositiion(240, -60);
        this.game = game;
    }

    public void drawInventory(Items items, Camera camera) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        if (((Gdx.input.isKeyPressed(Input.Keys.I) && items.backpackPick) || (items.recentPick))) {
            beginDrawingInventory();
        } else {
            endDrawingInventory();
            elapsedTime = 0;
        }

        if (drawInventory) {
            if (!HUDAnimated.isAnimationFinished(elapsedTime)) {

                game.batch.draw(HUDAnimated.getKeyFrame(elapsedTime, true), (camera.getX() + xHUD), (camera.getY() + yHUD));
            } else {
                game.batch.draw(HUDStill, (camera.getX() + xHUD), (camera.getY() + yHUD));
            }
        }

        if(items.recentPick && elapsedTime > 2) {
            items.setItemNotRecentlyPicked();
        }

    }

    /**
     * Method used to set the position of the inventory
     * @param x x co-ordinate
     * @param y y co-ordinate
     */
    public void setInventoryPositiion(float x, float y) {
        xHUD = x;
        yHUD = y;
    }

    /**
     * Method used to begin the animation to display the inventory
     */
    public void beginDrawingInventory() {
        drawInventory = true;
    }

    /**
     * Method used to stop drawing the inventory.
     */
    public  void endDrawingInventory() {
        drawInventory = false;
    }

    /**
     * Used to determine what the player has in their backpack.
     * @param items The items the player has picked up.
     */
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
