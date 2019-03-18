package Screens;

import Entities.*;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract Level Class is a superclass for all levels.
 * It contains the generic functionality that all levels require.
 */
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
    boolean isEnterHeld;
    GameCharacter lastCharacterSpokenTo;

    //The co-ordinates for the end/exit of the level
    float xExit;
    float yExit;

    //Current text box to display
    private List<Text> currentTextList;
    private int currentTextIndex;

    /**
     * The pickUpItem method checks if the player is close enough to any of them items within the level to pick them up.
     * If the player is close enough to an item, the player picks it up and the item added to the players {@link Level#inventory },
     * this item is then also removed from the level map.
     */
    void pickUpItem() {
        //Create a copy of the items currently in the level to iterate over
        ArrayList<Item> levelItemsCopy = new ArrayList<Item>(levelItems);
        //Check which items have been picked
        for (Item item : levelItemsCopy) {
            //Check if the item being picked up is the backpack or the player already has the backpack
            if(item.getItemType() == ItemType.BACKPACK || inventory.contains(ItemType.BACKPACK)) {
                if (item.isBeingPicked(player.getX(), player.getY())) {
                    inventory.addItem(item);
                    levelItems.remove(item);
                    //Updates status of inventory
                    inventory.updateInventoryStatus();
                    inventory.drawInventory(camera, true);
                    inventoryFrames = 20;
                    if (item.getItemType() == ItemType.SHOES) {
                        player.increaseSpeed();
                    }
                    if (item.getItemType() == ItemType.COFFEE) {
                        player.increaseSpeed();
                    }
                    if(item.getItemType() == ItemType.GOOSE){
                        player.increaseSpeed();
                        player.increaseSpeed();
                    }
                }
            }
        }
    }

    /**
     * The talkToCharacter method checks if the player is close enough to any of the characters within the level to talk to them.
     * If the player is close enough to a character that character's text boxes will be made the {@link Level#currentTextList}.
     */
    void talkToCharacter() {
        for (GameCharacter character : levelCharacters) {
            if (character.isTalking(player.getX(), player.getY())) {
                canPlayerMove = false;
                currentTextList = character.getText();
                lastCharacterSpokenTo = character;
            }
        }
    }

    /**
     * The nextText method checks the {@link Level#currentTextList} for the next text box.
     * For the character currently 'talking' if there are more text boxes to display this method increases the {@link Level#currentTextIndex},
     * if there are no text boxes left to display this method sets the {@link Level#currentTextList} to null and allows the player to move again.
     */
    void nextText() {
        if (currentTextList != null && !isEnterHeld) {
            currentTextIndex++;
            if (currentTextIndex >= currentTextList.size()) {
                //Stop displaying text
                currentTextList = null;
                //Reset
                currentTextIndex = 0;
                canPlayerMove = true;
            }
        }
        isEnterHeld = true;
    }

    /**
     * Renders the text in the {@link Level#currentTextList} at the {@link Level#currentTextIndex}
     */
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
     * The checkPlayer Exit method checks if the player has reached the exit/end of the level.
     * @return True if the player has exited and false if the player is not at the exit.
     */
    boolean checkPlayerExit() {
        if (((xExit - 50) < player.getX() && player.getX() < (xExit + 50)) && ((yExit - 50) < player.getY() && player.getY() < (yExit + 50))) {
            return Gdx.input.isKeyPressed(Input.Keys.E);
        }
        return false;
    }

}
