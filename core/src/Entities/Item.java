package Entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Item class used to create a new items to be placed into the game.
 */
public class Item {

    //enum itemType of the item
    private ItemType itemType;

    private Texture texture;

    //Coordinates
    private int xCoordinate;
    private int yCoordinate;

    private Sounds Sm = new Sounds();

    /**
     * Item constructor is used to create a new item to insert into the game.
     * @param itemType the enum {@link ItemType} of the item being created.
     * @param xCoordinate the x coordinate of where the item will be placed on the game map.
     * @param yCoordinate the y coordinate of where the item will be placed on the game map.
     */
    public Item(ItemType itemType, int xCoordinate, int yCoordinate){
        this.itemType = itemType;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        ItemFilePaths itemFilePaths = new ItemFilePaths();
        texture = new Texture(itemFilePaths.getTexture(itemType));
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

    /**
     * A method to check whether or not the player is close enough to pick up the item.
     * @param xPlayer the current x coordinate of the player on the game map.
     * @param yPlayer the current y coordinate of the player on the game map.
     * @return true if the item has successfully been picked up, false if the player is not near the item.
     */
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