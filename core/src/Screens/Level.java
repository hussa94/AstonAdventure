package Screens;

import Entities.*;
import Game.AstonAdventure;

import java.util.ArrayList;

public abstract class Level {

    //Game
    AstonAdventure game;

    //Map
    Map map;

    //Player
    Player player;

    //Sounds
    Sounds Sm;

    //NPCs
    Npc npc;

    //Camera
    Camera camera;

    //Inventory
    Inventory inventory;
    //A counter for the number of frames to display the inventory
    int inventoryFrames;

    //All items used in the level
    ArrayList<Item> levelItems;

    Level(){
    }

    //Method to add an item to the players inventory if the item is close enough to be picked
    void pickUpItem() {
        //Create a copy of the items currently in the level to iterate over
        ArrayList<Item> levelItemsCopy = new ArrayList<Item>(levelItems);
        //Check which items have been picked
        for (Item item : levelItemsCopy) {
            if (item.isBeingPicked(player.getX(), player.getY())) {
                inventory.addItem(item);
                levelItems.remove(item);
                //Updates status of inventory
                inventory.updateInventoryStatus();
                inventory.drawInventory(camera, true);
                inventoryFrames = 20;
                if (inventory.contains(ItemType.SHOES)) {
                    player.increaseSpeed();
                }
                if(inventory.contains(ItemType.COFFEE)){
                    player.increaseSpeed();
                }
            }
        }
    }


}
