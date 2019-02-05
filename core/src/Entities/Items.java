package Entities;

import Screens.LevelOne;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * The class Items is used to set and display any items to display in-game.
 */
public class Items {

    //Textures
    public Texture backpack;
    public Texture coffee;
    public Texture shoes;

    //Co-ordinates
    public float xBackpack, yBackpack;
    public float xCoffee, yCoffee;
    public float xShoes, yShoes;

    //Picking indicators
    public boolean backpackPick;
    public boolean coffeePick;
    public boolean shoesPick;
    public boolean recentPick;

    //Picking indicators
    public int displayBackpack;
    public int displayCoffee;
    public int displayShoes;

    //Sounds
    SoundManager Sm = new SoundManager();


    /**
     * Constructor used to initialise the item textures.
     */
    public Items() {

        backpack = new Texture("Sprites/Objects/Backpack/Backpack_object_spritesheet_resize.png");
        shoes = new Texture("Sprites/Objects/Trainers/Trainers_object_spritesheet_resized.png");
        coffee = new Texture("Sprites/Objects/Mug/Mug_object_spritesheet_resized.png");
    }

    /**
     * Sets the position of the backpack.
     * @param x x co-ordinates
     * @param y y co-ordinates
     */
    public void setBackpackCoordinates(float x, float y) {
        xBackpack = x;
        yBackpack = y;
    }

    /**
     * Sets the position of the coffee.
     * @param x x co-ordinates
     * @param y y co-ordinates
     */
    public void setCoffeeCoordinates(float x, float y) {
        xCoffee = x;
        yCoffee = y;
    }

    /**
     * Sets the position of the shoes.
     * @param x x co-ordinates
     * @param y y co-ordinates
     */
    public void setShoesCoordinates(float x, float y) {
        xShoes = x;
        yShoes = y;
    }

    /**
     * Method that determines whether or not the player has picked up the backpack item.
     * @param xPlayer x co-ordinate of the player.
     * @param yPlayer y co-ordinate of the player.
     * @return True if item is picked.
     */
    public boolean hasPlayerPickedBackpack(float xPlayer, float yPlayer) {
        if (((xBackpack - 10) < xPlayer && xPlayer < (xBackpack + 10)) && ((yBackpack - 10) < yPlayer && yPlayer < (yBackpack + 10))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                backpackPick = true;
                Sm.pickup();
                return true;
            }
        }
        return false;
    }

    /**
     * Method that determines whether or not the player has picked up the coffee item.
     * @param xPlayer x co-ordinate of the player.
     * @param yPlayer y co-ordinate of the player.
     * @return True if item is picked.
     */
    public boolean hasPlayerPickedCoffee(float xPlayer, float yPlayer) {
        if (((xCoffee - 10) < xPlayer && xPlayer < (xCoffee + 10)) && ((yCoffee - 10) < yPlayer && yPlayer < (yCoffee + 10))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                coffeePick = true;
                Sm.pickup();
                return true;
            }
        }
        return false;
    }

    /**
     * Method that determines whether or not the player has picked up the shoes item.
     * @param xPlayer x co-ordinate of the player.
     * @param yPlayer y co-ordinate of the player.
     * @return True if item is picked.
     */
    public boolean hasPlayerPickedShoes(float xPlayer, float yPlayer) {
        if (((xShoes - 10) < xPlayer && xPlayer < (xShoes + 10)) && ((yShoes - 10) < yPlayer && yPlayer < (yShoes + 10))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                LevelOne.speed = 200;
                shoesPick = true;
                Sm.pickup();
                return true;
            }
        }
        return false;
    }

    /**
     * Method that sets the indicatorfor recently picking an item.
     */
    public void setItemRecentlyPicked () {
        recentPick = true;
    }

    /**
     * Method that removes the indicator for recently picking an item.
     */
    public void setItemNotRecentlyPicked () {
        recentPick = false;
    }
}
