package Entities;

import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * The class Items is used to set and display any items to display in-game.
 */
public class Items {

    //Game object
    private AstonAdventure game;

    //Textures
    private Texture backpack;
    private Texture coffee;
    private Texture shoes;

    //Co-ordinates
    private float xBackpack, yBackpack;
    private float xCoffee, yCoffee;
    private float xShoes, yShoes;

    //Picking indicators
    boolean backpackPick;
    boolean coffeePick;
    boolean shoesPick;
    boolean recentPick;

    //Picking indicators
    private int displayBackpack;
    private int displayCoffee;
    private int displayShoes;

    //Sounds
    private Sounds Sm = new Sounds();


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
    private boolean hasPlayerPickedBackpack(float xPlayer, float yPlayer) {
        if (((xBackpack - 20) < xPlayer && xPlayer < (xBackpack + 20)) && ((yBackpack - 20) < yPlayer && yPlayer < (yBackpack + 20))) {
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
    private boolean hasPlayerPickedCoffee(float xPlayer, float yPlayer) {
        if (((xCoffee - 20) < xPlayer && xPlayer < (xCoffee + 20)) && ((yCoffee - 20) < yPlayer && yPlayer < (yCoffee + 20))) {
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
    private boolean hasPlayerPickedShoes(float xPlayer, float yPlayer) {
        if (((xShoes - 20) < xPlayer && xPlayer < (xShoes + 20)) && ((yShoes - 20) < yPlayer && yPlayer < (yShoes + 20))) {
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
                player.increaseSpeed();
                displayShoes ++;
            }
            text.resetElapsedTime();
        }
    }

    /**
     * Method that sets the indicatorfor recently picking an item.
     */
    private void setItemRecentlyPicked () {
        recentPick = true;
    }

    /**
     * Method that removes the indicator for recently picking an item.
     */
    void setItemNotRecentlyPicked () {
        recentPick = false;
    }
}
