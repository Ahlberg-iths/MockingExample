package com.assignment2;

public class Game {
    int score = 0;

    void roll (int pins) {
        score += pins;
    }

    int score() {
        return score;
    }
}
