package entities;

import com.badlogic.gdx.graphics.Texture;

public class Inventory {

    public Texture HUD;
    public float xHUD, yHUD;

    public Inventory() {
        HUD = new Texture("Sprites/Objects/Inventory/ItemBar_Empty.png");
    }

    public void setInventoryPositiion(float x, float y){
        xHUD = x;
        yHUD = y;
    }

    public void checkHUDStatus(Items items) {

        boolean backpackPick = items.backpackPick;
        boolean bookPick = items.bookPick;
        boolean coffeePick = items.coffeePick;
        boolean shoesPick = items.shoesPick;

        if(!backpackPick && !bookPick && !coffeePick && !shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_Empty.png");
        }
        else if (backpackPick && !bookPick && !coffeePick && !shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BackOnly.png");
        }
        else if (!backpackPick && bookPick && !coffeePick && !shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BookOnly.png");
        }
        else if (backpackPick && bookPick && !coffeePick && !shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BackBook.png");
        }
        else if (!backpackPick && !bookPick && coffeePick && !shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_CoffeeOnly.png");
        }
        else if (backpackPick && !bookPick && coffeePick && !shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BackCoffee.png");
        }
        else if (!backpackPick && bookPick && coffeePick && !shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BookCoffee.png");
        }
        else if (backpackPick && bookPick && coffeePick && !shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BackBookCoffee.png");
        }
        else if (!backpackPick && !bookPick && !coffeePick && shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_ShoesOnly.png");
        }
        else if (backpackPick && !bookPick && !coffeePick && shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BackShoes.png");
        }
        else if (!backpackPick && bookPick && !coffeePick && shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BookShoes.png");
        }
        else if (backpackPick && bookPick && !coffeePick && shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BackBookShoes.png");
        }
        else if (!backpackPick && !bookPick && coffeePick && shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_CoffeeShoes.png");
        }
        else if (backpackPick && !bookPick && coffeePick && shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BackCoffeeShoes.png");
        }
        else if (!backpackPick && bookPick && coffeePick && shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_BookCoffeeShoes.png");
        }
        else if (backpackPick && bookPick && coffeePick && shoesPick) {
            HUD = new Texture("Sprites/Objects/Inventory/ItemBar_All.png");
        }
    }

}
