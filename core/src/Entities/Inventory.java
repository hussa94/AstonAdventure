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
    private float xHUD, yHUD;

    //Backpack check
    public boolean drawInventory;

    //Timer
    private float elapsedTime = 0;

    //Game Object
    private AstonAdventure game;

    //Textures and animations
    private Animation<TextureRegion> HUDAnimated;
    private TextureAtlas textureAtlasHUD;
    private Texture HUDStill;

    /**
     * Constructor used to set up the position of the inventory (Top right corner).
     * @param game The game object.
     */
    public Inventory(AstonAdventure game) {
        setInventoryPositiion(240, -60);
        this.game = game;
    }

    /**
     * Method to draw the animation and still frame of the inventory, either when 'I' is pressed, or an
     * item is picked.
     * @param items The items that have been picked.
     * @param camera The camera used in the game.
     */
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
    private void setInventoryPositiion(float x, float y) {
        xHUD = x;
        yHUD = y;
    }

    /**
     * Method used to begin the animation to display the inventory
     */
    private void beginDrawingInventory() {
        drawInventory = true;
    }

    /**
     * Method used to stop drawing the inventory.
     */
    private  void endDrawingInventory() {
        drawInventory = false;
    }

    /**
     * Used to determine what the player has in their backpack.
     * @param items The items the player has picked up.
     */
    public void checkHUDStatus(Items items) {

        boolean coffeePick = items.coffeePick;
        boolean shoesPick = items.shoesPick;


        //Animation frame duration
        float frameDuration = 1 / 50f;
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
