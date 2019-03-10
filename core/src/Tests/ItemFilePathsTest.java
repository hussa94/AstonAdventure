package Tests;


import Entities.ItemFilePaths;
import Entities.ItemType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemFilePathsTest {

    /**
     * Set Up test method before starting the testing
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }
    /**
     * Test Method: JUnit5.3 Testing.
     * This method will test to see if the correct file path is returned for selected Trainers items in Level One of the game.
     */
    @org.junit.jupiter.api.Test
    void getShoeTexture() {

        ItemFilePaths testPath = new ItemFilePaths();
        assertEquals("Sprites/Objects/Trainers/Trainers_object_spritesheet_resized.png", testPath.getTexture(ItemType.SHOES), "Link to shoe texture path");

    }

    /**
     * This method should tests to see if the correct path returned for Backpack
     */
    @org.junit.jupiter.api.Test
    void getBackPackTexture() {

        ItemFilePaths testPath = new ItemFilePaths();
        assertEquals("Sprites/Objects/Backpack/Backpack_object_spritesheet_resize.png", testPath.getTexture(ItemType.BACKPACK), "Link to backpack texture path");

    }

    /**
     * This method tests to see if the correct path returned for Coffee
     */
    @org.junit.jupiter.api.Test
    void getCoffeeTexture() {

        ItemFilePaths testPath = new ItemFilePaths();
        assertEquals("Sprites/Objects/Mug/Mug_object_spritesheet_resized.png", testPath.getTexture(ItemType.COFFEE), "Link to coffee texture path");

    }


    /**
     * Method to run after testing has been completed
     */
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }



}