package Tests;


import Entities.ItemFilePaths;
import Entities.ItemType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemFilePathsTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getShoeTexture() {

        ItemFilePaths testPath = new ItemFilePaths();
        assertEquals("Sprites/Objects/Trainers/Trainers_object_spritesheet_resized.png", testPath.getTexture(ItemType.SHOES), "Link to shoe texture path");

    }
    @org.junit.jupiter.api.Test
    void getBackPackTexture() {

        ItemFilePaths testPath = new ItemFilePaths();
        assertEquals("Sprites/Objects/Backpack/Backpack_object_spritesheet_resize.png", testPath.getTexture(ItemType.BACKPACK), "Link to backpack texture path");

    }

    @org.junit.jupiter.api.Test
    void getCoffeeTexture() {

        ItemFilePaths testPath = new ItemFilePaths();
        assertEquals("Sprites/Objects/Mug/Mug_object_spritesheet_resized.png", testPath.getTexture(ItemType.COFFEE), "Link to coffee texture path");

    }

}