package Entities;

import java.util.HashMap;

/**
 * Class for storing the texture file paths for all items within the game.
 */
public class ItemFilePaths {

    //Hash map for storing file paths using the itemType enum as the key
    private HashMap<ItemType, String> itemFilePaths;

    ItemFilePaths(){
        this.itemFilePaths = new HashMap<ItemType, String>();
        itemFilePaths.put(ItemType.SHOES, "Sprites/Objects/Trainers/Trainers_object_spritesheet_resized.png");
        itemFilePaths.put(ItemType.BACKPACK, "Sprites/Objects/Backpack/Backpack_object_spritesheet_resize.png");
        itemFilePaths.put(ItemType.COFFEE, "Sprites/Objects/Mug/Mug_object_spritesheet_resized.png");
    }

    String getTexture(ItemType itemType){
        return itemFilePaths.get(itemType);
    }


}
