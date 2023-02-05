package com.assignment2;

public class Game {
    int score = 0;
    int bonusRounds = 0;
    int bonusPoints = 0;
    boolean firstRollInFrame = true;
    int firstRoll = 0;

    void roll (int pins) {
        if (pins < 0 || pins > 10) handleMachineError();
        if (bonusRounds > 0) bonusPoints+= pins;

        if (firstRollInFrame) handleFirstThrow(pins);
        else handleSecondThrow(pins);
        score += pins;
    }

    private void handleMachineError() {
        throw new BowlingMachineErrorException();
    }

    private void handleFirstThrow(int pins) {
        firstRoll = pins;
        firstRollInFrame = false;
    }

    private void handleSecondThrow(int pins) {
        if (pins + firstRoll > 10) handleMachineError();
        if (pins + firstRoll == 10) bonusRounds = 1;
        firstRoll = 0;
    }

    int score() {
        return score + bonusPoints;
    }

}
