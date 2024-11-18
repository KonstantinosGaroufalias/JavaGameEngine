package org.ict.other;

import org.ict.Game.GameDifficultyLevel;
import org.ict.Player.*;
import org.json.simple.JSONObject;

import java.util.Map;

public class SnakesAndLaddersGame {
    int currentPlayer;
    private Player player1;
    private Player player2;
    private GameDifficultyLevel gameDifficultyLevel;
    private StringBuilder gameInfo;

    public void SnakesAndLadders(Player player1, Player player2, GameDifficultyLevel gameDifficultyLevel) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = 0;
        this.gameDifficultyLevel = gameDifficultyLevel;
        this.gameInfo = new StringBuilder();
    }

    public void play(int rollResult) {
        int currentPosition;
        if (currentPlayer == 0) {
            currentPosition = player1.getPosition();
        } else {
            currentPosition = player2.getPosition();
        }
        int newPosition = currentPosition + rollResult;
        if (newPosition > 100) {
            newPosition = 100 - (newPosition - 100);
        }
        if (newPosition == 100) {
            if (currentPlayer == 0) {
                gameInfo.append("Player 1 wins!\n");
            } else {
                gameInfo.append("Player 2 wins!\n");
            }
        } else {
            checkForSnakesAndLadders(currentPosition);
            if (currentPlayer == 0) {
                player1.setPosition(newPosition);
                gameInfo.append("Player 1 moves to space " + newPosition + "\n");
                currentPlayer = 1;
            } else {
                player2.setPosition(newPosition);
                gameInfo.append("Player 2 moves to space " + newPosition + "\n");
                currentPlayer = 0;
            }
        }
    }

    public void checkForSnakesAndLadders(int position) {
        Map<Integer, Integer> diffMap = gameDifficultyLevel.getDifficultyMap(new JSONObject());
        Integer targetPosition = diffMap.get(position);
        if (targetPosition != null) {
            if (currentPlayer == 0) {
                player1.setPosition(targetPosition);
                if (position < targetPosition) {
                    gameInfo.append("Player 1 climbs a ladder to space " + targetPosition + "\n");
                } else {
                    gameInfo.append("Player 1 slides down a snake to space " + targetPosition + "\n");
                }
            } else {
                player2.setPosition(targetPosition);
                if (position < targetPosition) {
                    gameInfo.append("Player 2 climbs a ladder to space " + targetPosition + "\n");
                } else {
                    gameInfo.append("Player 2 slides down a snake to space " + targetPosition + "\n");
                }
            }
        }
    }

    public String getGameInfo() {
        return gameInfo.toString();
    }



}




