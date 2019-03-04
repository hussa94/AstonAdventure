package Entities;

import java.util.HashMap;

public class GameItems {

    private HashMap<ItemType, String> itemFilePaths;

    GameItems(){
        this.itemFilePaths = new HashMap<ItemType, String>();
        itemFilePaths.put(ItemType.SHOES, "Sprites/Objects/Trainers/Trainers_object_spritesheet_resized.png");
        itemFilePaths.put(ItemType.BACKPACK, "Sprites/Objects/Backpack/Backpack_object_spritesheet_resize.png");
        itemFilePaths.put(ItemType.COFFEE, "Sprites/Objects/Mug/Mug_object_spritesheet_resized.png");
    }

    String getTexture(ItemType itemName){
        return itemFilePaths.get(itemName);
    }


}
