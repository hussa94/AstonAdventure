package Tests;

import Game.AstonAdventure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AstonAdventureTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Default should assert "Male" as there should according to initial create method in the AstonAdventure class
     */
    @Test
    void TestDefeaultCharacerShouldBeMale() {
        AstonAdventure testAA = new AstonAdventure();
        testAA.addCharactersToGame();
        //testAA.changeCharacter();
        assertEquals("Male",testAA.getSelectedCharacter());
    }

    /**
     * Test after changing character to female if the correct value is returned
     */
    @Test
    void TestChangingCharacterToFemale() {
        AstonAdventure testAA = new AstonAdventure();
        testAA.addCharactersToGame();
        testAA.changeCharacter(0);
        assertEquals("Female",testAA.getSelectedCharacter());
    }

    /**
     * Test after changing the character to male if the correct value is returned.
     */
    @Test
    void TestChangingCharacterToMale() {
        AstonAdventure testAA = new AstonAdventure();
        testAA.addCharactersToGame();
        testAA.changeCharacter(1);
        assertEquals("Male",testAA.getSelectedCharacter());
    }
}