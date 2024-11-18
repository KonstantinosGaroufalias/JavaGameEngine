package org.ict.Player;

public class DefaultWinnerChecker implements WinChecker {

    public boolean hasPlayerWon(int position, int boardSize) {
        return position >= boardSize;
    }
}