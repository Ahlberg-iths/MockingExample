package com.assignment2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void scoreShouldReturn0WhenNoRollsHaveBeenMade() {
        assertEquals(0, game.score());
    }

    @Test
    void scoreShouldReturn9WhenOneRollWith9PinsHasBeenMade() {
        game.roll(9);

        assertEquals(9, game.score());
    }
    
    @Test
    void scoreShouldReturn5WhenFirstRollIs2PinsAndSecondIs3() {
        game.roll(2);
        game.roll(3);

        assertEquals(5, game.score());
    }
    
    @Test
    void rollWithNegative1PinsShouldThrowBowlingMachineErrorException() {
        assertThrows(BowlingMachineErrorException.class, () -> game.roll(-1));
    }
}
