package org.ict.other;

import org.ict.Game.TimeScoreCalc;
import org.ict.GameBoard.SoundManager;
import org.ict.JSON.GameInitializer;
import org.ict.Game.DefaultTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GuessingGame extends JFrame {
    private final JTextArea gameInfo;
    private final JButton guessButton;

    private long startTime; 
    private long randomNumber;
    private long remainingGuesses;
    private int score;

    SoundManager soundManager;

    public GuessingGame() throws  Exception{
        super("Guessing Game");

        // initialize the game
        GameInitializer initializer = new GameInitializer();
        initializer.initialize();
        remainingGuesses = initializer.getRemainingGuesses();
        randomNumber = initializer.getrandomNumber();

        soundManager = new SoundManager();
        soundManager.playBackgroundAudio2();
        
        DefaultTimer gameTimer = new DefaultTimer();

        // set up the UI
        gameInfo = new JTextArea();
        gameInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(250, 300));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int guess = Integer.parseInt(JOptionPane.showInputDialog(null, "Guess the number (1-10):"));
                if (remainingGuesses != 0) {
                   gameTimer.getTime();
                }
                if (guess == randomNumber) {

                    long endTime = gameTimer.getTime(); // get the current time
                    score = TimeScoreCalc.calculateScore(remainingGuesses,startTime,endTime); // calculate the score based on time and remaining guesses

                    gameInfo.append("You guessed correctly! It took you " + String.format("%.2f", (endTime - startTime) / 1000.0) + " seconds.\n");
                    gameInfo.append("Your score is " + score + ".\n");
                    soundManager.playWinSound();
                    guessButton.setEnabled(false);
                } else {
                    remainingGuesses--;
                    gameInfo.append("Incorrect guess! " + remainingGuesses + " guesses left.\n");
                    if (remainingGuesses == 0) {
                        gameInfo.append("Game over! The correct number was " + randomNumber + ".\n");
                        gameInfo.append("Your score is " + score + ".\n");
                        guessButton.setEnabled(false);
                    }
                }
            }
        });
        getContentPane().add(guessButton, BorderLayout.SOUTH);

        // set up the window
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

