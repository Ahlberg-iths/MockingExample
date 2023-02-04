package com.assignment2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void scoreShouldReturn0WhenNoRollsHaveBeenMade() {
        var game = new Game();

        assertEquals(0, game.score());
    }

    @Test
    void scoreShouldReturn9WhenOneRollWith9PinsHasBeenMade() {
        var game = new Game();

        game.roll(9);

        assertEquals(9, game.score());
    }
}
