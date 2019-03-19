package Screens.MainMenu;

import Entities.Sounds;
import Game.AstonAdventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class CharactersMenu implements Screen, Input.TextInputListener {

        private static final int BACKGROUND_WIDTH = 650;
        private static final int BACKGROUND_HEIGHT = 480;
        private static final int CHARACTER_WIDTH = 650;
        private static final int CHARACTER_HEIGHT = 480;

        //Character Information
        private static final int NUMBER_OF_CHARACTERS = 2;

        //MainMenu instance
        private static Screens.MainMenu.CharactersMenu charactersMenuInstance;

        //Game
        private AstonAdventure game;

        //Background Texture
        private Texture background;

        //Character Selection Overlay
        private Texture character;

        //Sounds
        private Sounds Sm;

        private int currentCharacter;

        /**
         * Constructor for the MainMenu class. Initialises the background textures and sounds. Stores the
         * game object.
         *
         * @param game The Game object.
         */
        private CharactersMenu(AstonAdventure game) {
            this.game = game;
            background = new Texture("Screens/MainMenu/MainMenu.png");
            character = new Texture("Screens/MainMenu/FemaleCharacterSelect.png");
            Sm = new Sounds();
        }

        /**
         * This method allows this class to follow the Singleton design pattern to allow only one instance of the MainMenu
         * class to be produced
         * @param game {@link AstonAdventure} game
         * @return the single version of a MainMenu instance
         */
        static Screens.MainMenu.CharactersMenu getCharactersMenuInstance(AstonAdventure game) {
            if (charactersMenuInstance == null) {
                charactersMenuInstance = new Screens.MainMenu.CharactersMenu(game);
            }
            return charactersMenuInstance;
        }

    /**
     * Render method used to display the current menu screen and play menu music.
     *
     * @param delta Elapsed Time.
     */
    @Override
    public void render(float delta) {

        //Setup
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        //Draw / Display background
        game.batch.draw(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        game.batch.draw(character, 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        //Play menu music upon startup and loop
        if (!Sm.isMusicPlaying() && (!Sm.isSoundPlaying())) {
            Sm.menu();
        }

        charactersScreen();

        //End
        game.batch.end();
    }

    /**
     * Method used when the game is on the character selection screen of the main menu. The method will update
     * the background and if a button is pressed a sound will play and a function will be performed (Based on
     * which button is pressed).
     */
    private void charactersScreen() {

        //Right arrow button
        if ((Gdx.input.getX() > 576) && (Gdx.input.getX() < 611) && ((Gdx.input.getY() > 222) && (Gdx.input.getY() < 266))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/CharacterMenuRight.png");

            //Switch to next character preview
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                }
                    if (currentCharacter < NUMBER_OF_CHARACTERS - 1) {
                        currentCharacter++;
                    } else {
                        currentCharacter = 0;
                    }

            }
        } else if ((Gdx.input.getX() > 31) && (Gdx.input.getX() < 66) && ((Gdx.input.getY() > 222) && (Gdx.input.getY() < 266))) {

            //Highlights button when hovered over
            background = new Texture("Screens/MainMenu/CharacterMenuLeft.png");

            //Switch to last character preview
            if (Gdx.input.isTouched()) {
                if (!Sm.isSoundPlaying()) {
                    Sm.menuSelect();
                }
                    if (currentCharacter > 0) {
                        currentCharacter--;
                    } else {
                        currentCharacter = NUMBER_OF_CHARACTERS - 1;
                    }

            }
        }

        //Standard background
        else {
            background = new Texture("Screens/MainMenu/CharacterMenu.png");
        }

        //Updates character overlay for display in the character selection screen
        if (game.characters.get(currentCharacter).equalsIgnoreCase("female")) {
            character = new Texture("Screens/MainMenu/FemaleCharacterSelect.png");
        } else if (game.characters.get(currentCharacter).equalsIgnoreCase("male")) {
            character = new Texture("Screens/MainMenu/MaleCharacterSelect.png");
        }

        //Updates saved character selection in game if character is selected
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.changeCharacter(currentCharacter);
            game.setScreen(OptionsMenu.getOptionsMenuInstance(game));
        }

        //Return 'up' to options menu screen without changing character
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(OptionsMenu.getOptionsMenuInstance(game));
        }
    }

    //Unused
    public void input(String text) {
        //this.text = text;
    }

    //Unused
    public void canceled() {
        // text = "canceled";
    }

    //Unused
    @Override
    public void show() {

    }

    //Unused
    @Override
    public void resize(int width, int height) {

    }

    //Unused
    @Override
    public void pause() {

    }

    //Unused
    @Override
    public void resume() {

    }

    //Unused
    @Override
    public void hide() {

    }

    //Unused
    @Override
    public void dispose() {
        background.dispose();
    }

    }
