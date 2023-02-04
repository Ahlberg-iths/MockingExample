package com.assignment2;

public class Game {
    int score = 0;

    void roll (int pins) {
        if (pins < 0) throw new BowlingMachineErrorException();
        score += pins;
    }

    int score() {
        return score;
    }
}
