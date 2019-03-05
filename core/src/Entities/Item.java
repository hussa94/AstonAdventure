package Entities;

import com.badlogic.gdx.graphics.Texture;

public class Item {

    //enum itemType of the item
    private ItemType itemType;

    private Texture texture;

    //Coordinates
    private int xCoordinate;
    private int yCoordinate;

    private Sounds Sm = new Sounds();

    //TODO JavaDoc Constructor to create a new item
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