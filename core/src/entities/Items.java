package entities;

import Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Items {

    public Texture backpack;
    public Texture coffee;
    public Texture shoes;

    public float xBackpack, yBackpack;
    public float xCoffee, yCoffee;
    public float xShoes, yShoes;

    public boolean backpackPick;
    public boolean coffeePick;
    public boolean shoesPick;

    public int displayBackpack;
    public int displayCoffee;
    public int displayShoes;

    public boolean recentPick;

    Soundmanager Sm = new Soundmanager();

    public Items() {

        backpack = new Texture("Sprites/Objects/Backpack/Backpack_object_spritesheet_resize.png");
        shoes = new Texture("Sprites/Objects/Trainers/Trainers_object_spritesheet_resized.png");
        coffee = new Texture("Sprites/Objects/Mug/Mug_object_spritesheet_resized.png");

    }

    public void setBackpackCoordinates(float x, float y) {
        xBackpack = x;
        yBackpack = y;
    }

    public void setCoffeeCoordinates(float x, float y) {
        xCoffee = x;
        yCoffee = y;
    }

    public void setShoesCoordinates(float x, float y) {
        xShoes = x;
        yShoes = y;
    }

    public boolean hasPlayerPickedBackpack(float xPlayer, float yPlayer) {
        if (((xBackpack - 5) < xPlayer && xPlayer < (xBackpack + 5)) || ((yBackpack - 5) < yPlayer && yPlayer < (yBackpack + 5))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                backpackPick = true;
                Sm.soundFx();
                return true;
            }
        }
        return false;
    }

    public boolean hasPlayerPickedCoffee(float xPlayer, float yPlayer) {
        if (((xCoffee - 5) < xPlayer && xPlayer < (xCoffee + 5)) || ((yCoffee - 5) < yPlayer && yPlayer < (yCoffee + 5))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                coffeePick = true;
                Sm.soundFx();
                return true;
            }
        }
        return false;
    }

    public boolean hasPlayerPickedShoes(float xPlayer, float yPlayer) {
        if (((xShoes - 5) < xPlayer && xPlayer < (xShoes + 5)) || ((yShoes - 5) < yPlayer && yPlayer < (yShoes + 5))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                GameScreen.speed = 200;
                shoesPick = true;
                Sm.soundFx();
                return true;
            }
        }
        return false;
    }

    public void setItemRecentlyPicked () {
        recentPick = true;
    }

    public void setItemNotRecentlyPicked () {
        recentPick = false;
    }
}
