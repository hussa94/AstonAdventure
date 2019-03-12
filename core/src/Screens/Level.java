package Screens;

import Entities.*;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

public abstract class Level {

    //Game
    AstonAdventure game;

    //Map
    Map map;

    //Player
    Player player;
    boolean canPlayerMove;

    //Sounds
    Sounds Sm;

    //NPCs
    Npc npc;

    //Camera
    Camera camera;

    //Rendering
    float elapsedTime;

    //Inventory
    Inventory inventory;
    //A counter for the number of frames to display the inventory
    int inventoryFrames;

    //All items used in the level
    ArrayList<Item> levelItems;

    //Characters
    ArrayList<GameCharacter> levelCharacters;

    //Current text box to display
    private List<Text> currentTextList;
    private int currentTextIndex;
    boolean isEnterHeld;

    float xExit;
    float yExit;

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

    void talkToCharacter() {
        for (GameCharacter character : levelCharacters) {
            if (character.isTalking(player.getX(), player.getY())) {
                canPlayerMove = false;
                currentTextList = character.getText();
            }
        }
    }

    private void nextText(){
        if(currentTextList != null){
            currentTextIndex++;
            if(currentTextIndex >= currentTextList.size()){
                //Stop displaying text
                currentTextList = null;
                //Reset
                currentTextIndex = 0;
                canPlayerMove = true;
            }
        }
    }

    void renderText() {
        if (currentTextList != null) {
            Text currentText = currentTextList.get(currentTextIndex);
            if (currentText != null) {
                TextureRegion animationFrame = currentText.getKeyFrame(elapsedTime);
                //Where to draw the text box
                game.batch.draw(animationFrame, camera.getX() - 90, camera.getY() - 235);
            }
        }
    }

        /**
     * Method used to check if the player has reached the exit of the level.
     *
     * @return True if the player has exited.
     */
    boolean checkPlayerExit() {
        if (((xExit - 40) < player.getX() && player.getX() < (xExit + 40)) && ((yExit - 40) < player.getY() && player.getY() < (yExit + 40))) {
            return Gdx.input.isKeyPressed(Input.Keys.E);
        }
        return false;
    }

    //Next text box
    void checkNextText(){
        //if this is the first frame enter is pressed / only go to next text if enter is pressed
        if(!isEnterHeld){
            nextText();
            isEnterHeld = true;
        }
    }

}
