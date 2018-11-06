package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.*;
import uk.ac.aston.team17.AstonAdventure;

public class GameScreen implements Screen {
    AstonAdventure game;

    SpriteBatch batch;
    Texture playerTexture;
    Texture backpackTexture;
    Texture bookTexture;
    Texture coffeeTexture;
    Texture shoesTexture;
    Texture inventoryTexture;
    Player femalePlayer;

    boolean backpackPick = false;
    boolean bookPick = false;
    boolean coffeePick = false;
    boolean shoesPick = false;

    float xPlayer, yPlayer;
    float xBackpack = 200, yBackpack = 350;
    float xBook = 550, yBook = 150;
    float xCoffee = 300, yCoffee = 0;
    float xShoes= 50, yShoes = 100;
    final float xInventory = 380, yInventory = 410;
    public static float SPEED = 100;

    public GameScreen() {
        //femalePlayer = new FemalePlayer(0, 0);

        this.game = game;
        playerTexture = new Texture("core/assets/badlogic.jpg");
        backpackTexture = new Texture("core/assets/Sprites/Objects/Backpack/Backpack_object_spritesheet_resize.png");
        shoesTexture = new Texture("core/assets/Sprites/Objects/Trainers/Trainers_object_spritesheet_resized.png");
        bookTexture = new Texture("core/assets/Sprites/Objects/Book/Book_object_spritesheet_resized.png");
        coffeeTexture = new Texture("core/assets/Sprites/Objects/Mug/Mug_object_spritesheet_resized.png");
        inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_Empty.png");

        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if(!backpackPick) {
            batch.draw(backpackTexture, xBackpack, yBackpack);
        }
        if(!bookPick) {
            batch.draw(bookTexture, xBook, yBook);
        }
        if(!coffeePick) {
            batch.draw(coffeeTexture, xCoffee, yCoffee);
        }
        if(!shoesPick) {
            batch.draw(shoesTexture, xShoes, yShoes);
        }

        batch.draw(playerTexture, xPlayer, yPlayer);
        batch.draw(inventoryTexture, xInventory, yInventory);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerTexture = new Texture("core/assets/Sprites/Characters/walk_up_f.png");
            yPlayer += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerTexture = new Texture("core/assets/Sprites/Characters/walk_down_f.png");
            yPlayer -= SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerTexture = new Texture("core/assets/Sprites/Characters/walk_right_f.png");
            xPlayer += SPEED * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerTexture = new Texture("core/assets/Sprites/Characters/walk_left_f.png");
            xPlayer -= SPEED * Gdx.graphics.getDeltaTime();
        } else {
            playerTexture = new Texture("core/assets/Sprites/Characters/walk_down_f.png");
        }
        batch.end();

        if (((xBackpack-10)< xPlayer && xPlayer<(xBackpack+10)) || ((yBackpack-10)< yPlayer && yPlayer<(yBackpack+10))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                backpackPick = true;
            }
        }

        if (((xBook-20)< xPlayer && xPlayer<(xBook+20)) || ((yBook-20)< yPlayer && yPlayer<(yBook+20))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                bookPick = true;
            }
        }

        if (((xCoffee-10)< xPlayer && xPlayer<(xCoffee+10)) || ((yCoffee-10)< yPlayer && yPlayer<(yCoffee+10))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                coffeePick = true;
            }
        }

        if (((xShoes-10)< xPlayer && xPlayer<(xShoes+15)) || ((yShoes-10)< yPlayer && yPlayer<(yShoes+15))) {
            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                shoesPick = true;
                SPEED = 200;
            }
        }

        if(!backpackPick && !bookPick && !coffeePick && !shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_Empty.png");
        }
        else if (backpackPick && !bookPick && !coffeePick && !shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackOnly.png");
        }
        else if (!backpackPick && bookPick && !coffeePick && !shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BookOnly.png");
        }
        else if (backpackPick && bookPick && !coffeePick && !shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackBook.png");
        }
        else if (!backpackPick && !bookPick && coffeePick && !shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_CoffeeOnly.png");
        }
        else if (backpackPick && !bookPick && coffeePick && !shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackCoffee.png");
        }
        else if (!backpackPick && bookPick && coffeePick && !shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BookCoffee.png");
        }
        else if (backpackPick && bookPick && coffeePick && !shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackBookCoffee.png");
        }
        else if (!backpackPick && !bookPick && !coffeePick && shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_ShoesOnly.png");
        }
        else if (backpackPick && !bookPick && !coffeePick && shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackShoes.png");
        }
        else if (!backpackPick && bookPick && !coffeePick && shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BookShoes.png");
        }
        else if (backpackPick && bookPick && !coffeePick && shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackBookShoes.png");
        }
        else if (!backpackPick && !bookPick && coffeePick && shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_CoffeeShoes.png");
        }
        else if (backpackPick && !bookPick && coffeePick && shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BackCoffeeShoes.png");
        }
        else if (!backpackPick && bookPick && coffeePick && shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_BookCoffeeShoes.png");
        }
        else if (backpackPick && bookPick && coffeePick && shoesPick) {
            inventoryTexture = new Texture("core/assets/Sprites/Objects/Inventory/ItemBar_All.png");
        }



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
