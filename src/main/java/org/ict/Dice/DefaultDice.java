package org.ict.Dice;

public class DefaultDice implements DiceService {
    private int diceNum;

    public DefaultDice(int diceNum) {
        this.diceNum = diceNum;
    }

    public int roll() {
        return diceNum * ((int) (Math.random() * 6) + 1);
    }

    public void setDiceNum(int diceNum) {
        this.diceNum = diceNum;
    }

    public int getDiceNum() {
        return diceNum;
    }
}