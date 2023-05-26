package com.assignment2;

public class Game {
    int score = 0;
    int bonusRounds = 0;
    boolean doubleBonus = false;
    int bonusPoints = 0;
    boolean firstRollInFrame = true;
    int firstRoll = 0;
    int frame = 1;
    boolean gameIsComplete = false;
    Integer specialRoll1 = null;
    Integer specialRoll2 = null;

    void roll (int pins) {
        if (gameIsComplete) return;
        if (frame == 10) {
            if (specialRoll1 == null) {
                specialRoll1 = pins;
            } else if (specialRoll2 == null) {
                specialRoll2 = pins;
                if (specialRoll1 == 10) {
                    if (doubleBonus) bonusPoints += pins;
                    bonusPoints += pins;
                    return;
                } else if (specialRoll1 + pins == 10) {
                    if (bonusRounds > 0) bonusPoints += pins;
                    score += pins;
                    return;
                } else {
                    if (bonusRounds > 0) bonusPoints += pins;
                    score += pins;
                    gameIsComplete = true;
                    return;
                }
            } else {
                bonusPoints += pins;
                gameIsComplete = true;
                return;
            }
        }
        if (pins < 0 || pins > 10) handleMachineError();
        if (bonusRounds > 0) bonusPoints+= pins;
        if (doubleBonus) bonusPoints+= pins;
        bonusRounds = Math.max(0, bonusRounds - 1);
        doubleBonus = false;
        if (firstRollInFrame) handleFirstThrow(pins);
        else handleSecondThrow(pins);
        score += pins;
    }

    private void handleMachineError() {
        throw new BowlingMachineErrorException();
    }

    private void handleFirstThrow(int pins) {
        if (pins != 10) {
            firstRoll = pins;
            firstRollInFrame = false;
            return;
        }
        if (bonusRounds > 0) doubleBonus = true;
        bonusRounds = 2;
        if (frame != 10) frame++;
    }

    private void handleSecondThrow(int pins) {
        if (pins + firstRoll > 10) handleMachineError();
        if (pins + firstRoll == 10) bonusRounds = 1;
        firstRoll = 0;
        frame++;
        firstRollInFrame = true;
    }

    int score() {
        return score + bonusPoints;
    }

}
