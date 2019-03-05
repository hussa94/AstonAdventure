package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashSet;

/**
 * The class inventory is used to determine which items the player has picked up, and display the
 * content of their backpack.
 */
public class Inventory {

    //Co-ordinates
    private float xHUD, yHUD;

    //HashSet of all items that have been added to the inventory
    private HashSet<ItemType> itemSet;

    //Timer
    private float elapsedTime = 0;

    //Game Object
    private AstonAdventure game;

    //Textures and animations
    private Animation<TextureRegion> HUDAnimated;
    private Texture HUDStill;

    /**
     * Constructor used to set up the position of the inventory (Top right corner).
     *
     * @param game The game object.
     */
    public Inventory(AstonAdventure game) {
        setInventoryPosition(240, -60);
        this.game = game;
        itemSet = new HashSet<ItemType>();
    }

    /**
     * Method to draw the animation and still frame of the inventory.
     *
     * @param camera The camera used in the game.
     * @param animated True if you want do draw the inventory animation, false to draw still inventory.
     */
    public void drawInventory(Camera camera, Boolean animated) {
        if(itemSet.contains(ItemType.BACKPACK)) {
            if(!animated) {
                game.batch.draw(HUDStill, (camera.getX() + xHUD), (camera.getY() + yHUD));
            } else {
                elapsedTime += Gdx.graphics.getDeltaTime();
                game.batch.draw(HUDStill, (camera.getX() + xHUD), (camera.getY() + yHUD));
                game.batch.draw(HUDAnimated.getKeyFrame(elapsedTime, true), (camera.getX() + xHUD), (camera.getY() + yHUD));
            }
        }
    }

    /**
     * Method used to set the position of the inventory
     *
     * @param x x co-ordinate
     * @param y y co-ordinate
     */
    private void setInventoryPosition(float x, float y) {
        xHUD = x;
        yHUD = y;
    }

    /**
     * Used to determine what the player has in their backpack.
     */
    public void updateInventoryStatus() {
        TextureAtlas textureAtlasHUD;
        float frameDuration = 1 / 50f;

        if (!itemSet.contains(ItemType.COFFEE) && !itemSet.contains(ItemType.SHOES)) {
            textureAtlasHUD = new TextureAtlas("Sprites/Objects/Inventory/Inventory Animated/Empty Inventory/Inventory Empty.atlas");
            HUDAnimated = new Animation<TextureRegion>(frameDuration, textureAtlasHUD.getRegions());
            HUDStill = new Texture("Sprites/Objects/Inventory/Inventory Still/Empty_Inventory.png");
        } else if (itemSet.contains(ItemType.COFFEE) && !itemSet.contains(ItemType.SHOES)) {
            textureAtlasHUD = new TextureAtlas("Sprites/Objects/Inventory/Inventory Animated/Inventory Coffee/Inventory Coffee.atlas");
            HUDAnimated = new Animation<TextureRegion>(frameDuration, textureAtlasHUD.getRegions());
            HUDStill = new Texture("Sprites/Objects/Inventory/Inventory Still/Inventory_Coffee.png");
        } else if (!itemSet.contains(ItemType.COFFEE) && itemSet.contains(ItemType.SHOES)) {
            textureAtlasHUD = new TextureAtlas("Sprites/Objects/Inventory/Inventory Animated/Inventory Shoes/Inventory Shoes.atlas");
            HUDAnimated = new Animation<TextureRegion>(frameDuration, textureAtlasHUD.getRegions());
            HUDStill = new Texture("Sprites/Objects/Inventory/Inventory Still/Inventory_Shoes.png");
        } else if (itemSet.contains(ItemType.COFFEE) && itemSet.contains(ItemType.SHOES)) {
            textureAtlasHUD = new TextureAtlas("Sprites/Objects/Inventory/Inventory Animated/Inventory Full/Inventory Full.atlas");
            HUDAnimated = new Animation<TextureRegion>(frameDuration, textureAtlasHUD.getRegions());
            HUDStill = new Texture("Sprites/Objects/Inventory/Inventory Still/Inventory_Full.png");
        }
    }

    /**
     * Adds item to the inventory {@link Inventory#itemSet}
     * @param item the item to be added to the inventory.
     */
    public void addItem(Item item) {
        itemSet.add(item.getItemType());
    }

    /**
     * Method to check whether or not the inventory {@link Inventory#itemSet} contains a certain item.
     * @param item the item that you are searching the inventory for.
     * @return true if inventory contains the item, false if it does not.
     */
    public boolean contains(ItemType item) {
        return itemSet.contains(item);
    }

}
