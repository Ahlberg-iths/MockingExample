package com.assignment2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @CsvSource({
            "12",
            "11",
            "323",
            "2000"
    })
    void rollWithMoreThan10PinsShouldThrowBowlingMachineErrorException(int value) {
        assertThrows(BowlingMachineErrorException.class, () -> game.roll(value));
    }
    
    @Test
    void gameStartingWithRollsWithPins9And1And5ShouldReturn20() {
        game.roll(9);
        game.roll(1);
        game.roll(5);

        assertEquals(20, game.score());
    }

    @Test
    void moreThan10PinsInTotalForAFrameShouldThrowBowlingMachineErrorException() {
        game.roll(5);

        assertThrows(BowlingMachineErrorException.class, () -> game.roll(6));
    }
    
    @Test
    void gameStartingWithStrikeAnd4And3ShouldReturnAScoreOf24() {
        game.roll(10);
        game.roll(4);
        game.roll(3);

        assertEquals(24, game.score());
    }
    
    @Test
    void gameStartingWithThreeConsecutiveStrikesShouldReturnAScoreOf60() {
        game.roll(10);
        game.roll(10);
        game.roll(10);

        assertEquals(60, game.score());
    }

    @ParameterizedTest
    @MethodSource("fullGameWithScore")
    void aCompleteGameShouldReturnTheCorrectScore(int expectedScore, List<Integer> allRollsOfCompleteGame) {
        allRollsOfCompleteGame.forEach(roll -> game.roll(roll));

        assertEquals(expectedScore, game.score());
    }

    @ParameterizedTest
    @MethodSource("fullGameWithScore")
    void extraRollsAfterGameIsCompleteDoNotCount(int expectedScore, List<Integer> allRollsOfCompleteGame) {
        allRollsOfCompleteGame.forEach(roll -> game.roll(roll));

        IntStream.range(1,10).forEach(i -> game.roll(i));

        assertEquals(expectedScore, game.score());
    }

    static Stream<Arguments> fullGameWithScore() {
        return Stream.of(
                Arguments.of(0, List.of(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)),
                Arguments.of(190, List.of(8,2,9,1,10,7,3,10,9,1,8,2,10,6,3,10,10,5)),
                Arguments.of(85, List.of(9,0,5,3,8,1,1,7,5,3,4,5,10,8,0,8,0,0,0)),
                Arguments.of(300, List.of(10,10,10,10,10,10,10,10,10,10,10,10)),
                Arguments.of(107, List.of(8,1,7,0,3,6,8,0,8,2,3,4,10,8,2,8,0,8,0)),
                Arguments.of(163, List.of(10,9,1,7,2,8,1,10,8,2,10,7,3,10,9,0)),
                Arguments.of(245, List.of(6,4,10,10,10,10,10,10,7,3,8,2,10,9,1))
        );
    }
}
