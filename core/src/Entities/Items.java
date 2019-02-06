package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * The class Items is used to set and display any items to display in-game.
 */
public class Items {

    AstonAdventure game;

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
    Sounds Sm = new Sounds();


    /**
     * Constructor used to initialise the item textures.
     */
    public Items(AstonAdventure game) {

        this.game = game;
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
                if (!(Sm.isSoundPlaying())) {
                    Sm.pickup();
                }

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
                if (!(Sm.isSoundPlaying())) {
                    Sm.pickup();
                }
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
                shoesPick = true;
                if (!(Sm.isSoundPlaying())) {
                    Sm.pickup();
                }
                return true;
            }
        }
        return false;
    }

    public void drawItems() {

        //Draw items if they have not been picked up
        if (!backpackPick) {
            game.batch.draw(backpack, xBackpack, yBackpack);
        }
        if (!coffeePick) {
            game.batch.draw(coffee, xCoffee, yCoffee);
        }
        if (!shoesPick) {
            game.batch.draw(shoes, xShoes, yShoes);
        }
    }

    public void itemStatus(Text text, Player player) {
        //Check status of items - Display inventory once upon picking up a new object
        if (hasPlayerPickedBackpack(player.getX(), player.getY())) {
            if(displayBackpack < 1) {
                setItemRecentlyPicked();
                displayBackpack ++;
            }
           text.resetElapsedTime();
        }

        if (hasPlayerPickedCoffee(player.getX(), player.getY())) {
            if(displayCoffee < 1) {
                setItemRecentlyPicked();
                displayCoffee++;
            }
            text.resetElapsedTime();
        }

        if (hasPlayerPickedShoes(player.getX(), player.getY())) {
            if(displayShoes < 1) {
                setItemRecentlyPicked();
                player.incrreaseSpeed();
                displayShoes ++;
            }
            text.resetElapsedTime();
        }
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
