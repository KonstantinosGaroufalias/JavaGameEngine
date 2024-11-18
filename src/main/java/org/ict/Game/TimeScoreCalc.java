package org.ict.Game;

public class TimeScoreCalc {
    public static int calculateScore(long remainingGuesses, long startTime, long endTime) {
        double totalTimeMillis = endTime - startTime; // calculate the time taken to guess correctly
        double totalTimeSeconds = totalTimeMillis / 1000.0; // convert to seconds
        int score = (int) (remainingGuesses * 100 - totalTimeSeconds); // calculate the score based on time and remaining guesses
        if (score < 0) {
            score = 0; // make sure the score is not negative
        }
        return score;
    }
}
