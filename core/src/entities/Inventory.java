package entities;

import com.badlogic.gdx.graphics.Texture;

public class Inventory {

    public Texture HUD;
    public final float xHUD = 380, yHUD = 410;

    public Inventory() {
        HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_Empty.png");
    }

    public void checkHUDStatus(Items objects) {

        boolean backpackPick = objects.backpackPick;
        boolean bookPick = objects.bookPick;
        boolean coffeePick = objects.coffeePick;
        boolean shoesPick = objects.shoesPick;

        if(!backpackPick && !bookPick && !coffeePick && !shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_Empty.png");
        }
        else if (backpackPick && !bookPick && !coffeePick && !shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackOnly.png");
        }
        else if (!backpackPick && bookPick && !coffeePick && !shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BookOnly.png");
        }
        else if (backpackPick && bookPick && !coffeePick && !shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackBook.png");
        }
        else if (!backpackPick && !bookPick && coffeePick && !shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_CoffeeOnly.png");
        }
        else if (backpackPick && !bookPick && coffeePick && !shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackCoffee.png");
        }
        else if (!backpackPick && bookPick && coffeePick && !shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BookCoffee.png");
        }
        else if (backpackPick && bookPick && coffeePick && !shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackBookCoffee.png");
        }
        else if (!backpackPick && !bookPick && !coffeePick && shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_ShoesOnly.png");
        }
        else if (backpackPick && !bookPick && !coffeePick && shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackShoes.png");
        }
        else if (!backpackPick && bookPick && !coffeePick && shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BookShoes.png");
        }
        else if (backpackPick && bookPick && !coffeePick && shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackBookShoes.png");
        }
        else if (!backpackPick && !bookPick && coffeePick && shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_CoffeeShoes.png");
        }
        else if (backpackPick && !bookPick && coffeePick && shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackCoffeeShoes.png");
        }
        else if (!backpackPick && bookPick && coffeePick && shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BookCoffeeShoes.png");
        }
        else if (backpackPick && bookPick && coffeePick && shoesPick) {
            HUD = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_All.png");
        }
    }

}
