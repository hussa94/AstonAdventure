package Tests;

import Entities.Inventory;
import Entities.Item;
import Entities.ItemType;
import Game.AstonAdventure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void TestDefaultInventoryWithNullItems() {
        AstonAdventure testAA = new AstonAdventure();
        Inventory testInventory = new Inventory(testAA);

        assertFalse(testInventory.contains(ItemType.SHOES));
        assertFalse(testInventory.contains(ItemType.COFFEE));
        assertFalse(testInventory.contains(ItemType.BACKPACK));
    }

    @Test
    void TestInventoryAfterAddingShoesOnly() {
        AstonAdventure testAA = new AstonAdventure();
        Inventory testInventory = new Inventory(testAA);
        Item testShoes = new Item(ItemType.SHOES, 0,0);

        testInventory.addItem(testShoes);

        assertTrue(testInventory.contains(ItemType.SHOES));
        assertFalse(testInventory.contains(ItemType.COFFEE));
        assertFalse(testInventory.contains(ItemType.BACKPACK));
    }

    @AfterEach
    void tearDown() {
    }


}