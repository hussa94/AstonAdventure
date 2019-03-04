package Entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class Item {

    // For storing file paths of all items needed in the game - Note new item file paths must be added here
    private static HashMap<ItemType, String> itemFilePaths;
    static {
        itemFilePaths = new HashMap<ItemType, String>();
        itemFilePaths.put(ItemType.SHOES, "Sprites/Objects/Trainers/Trainers_object_spritesheet_resized.png");
        itemFilePaths.put(ItemType.BACKPACK, "Sprites/Objects/Backpack/Backpack_object_spritesheet_resize.png");
        itemFilePaths.put(ItemType.COFFEE, "Sprites/Objects/Mug/Mug_object_spritesheet_resized.png");
    }

    //enum itemType of the item
    private ItemType itemType;

    private Texture texture;

    //Coordinates
    private int xCoordinate;
    private int yCoordinate;

    private Sounds Sm = new Sounds();

    //TODO JavaDoc Constructor to create a new item
    public Item(ItemType itemName, int xCoordinate, int yCoordinate){
        this.itemType = itemName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        texture = new Texture(itemFilePaths.get(itemName));

    }

    public Texture getTexture(){
        return this.texture;
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate(){
        return this.yCoordinate;
    }

    public ItemType getItemType(){
        return this.itemType;
    }

    //TODO JavaDoc
    public boolean isBeingPicked(float xPlayer, float yPlayer) {
        if (((xCoordinate - 20) < xPlayer && xPlayer < (xCoordinate + 20)) && ((yCoordinate - 20) < yPlayer && yPlayer < (yCoordinate + 20))) {
            if (!(Sm.isSoundPlaying())) {
                Sm.pickup();
            }
            return true;
        }
        return false;
    }

}