package org.ict;

import org.ict.Dice.DefaultDice;
import org.ict.Dice.Dice;
import org.ict.Game.EasyDiff;
import org.ict.Game.GameDifficultyLevel;
import org.ict.Game.HardDiff;
import org.ict.Game.NormalDiff;
import org.ict.GameBoard.DefaultGameBoard;
import org.ict.GameBoard.SoundManager;
import org.ict.JSON.GameInitializer;
import org.ict.Player.*;
import org.ict.other.GuessingGame;
import org.ict.other.TicTacToe;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Main extends JFrame {
    JTextArea gameInfo;
    JButton rollButton;
    JButton loadButton = new JButton("Load Game");
    JButton saveButton = new JButton("Save Game");
    int player1Position;
    int player2Position;
    long boardSize;
    String gameType;
    int currentPlayer;
    SoundManager soundManager;
    GameDifficultyLevel gameDifficultyLevel;
    WinChecker defaultGameChecker;
    Dice dice;
    Player player1;
    Player player2;

    public Main() throws Exception {
        super("Game Launcher");
        GameInitializer initializer = new GameInitializer();
        initializer.initialize();
        gameType = initializer.getGameType();
        System.out.println(gameType);

        if (gameType.equalsIgnoreCase("Snakes and Ladders")) {
            launchSnakesAndLadders();
        } else if (gameType.equalsIgnoreCase("Tic Tac Toe")) {
            launchTicTacToe();
        } else if (gameType.equalsIgnoreCase("Guessing Game")) {
            launchGuessingGame();
        }
    }

    private void launchSnakesAndLadders() throws Exception {
        GameInitializer initializer = new GameInitializer();
        initializer.initialize();

        boardSize = initializer.getBoardSize();
        long diceNum = initializer.getDiceNum();
        long playerNum = initializer.getPlayerNum();
        String difficulty = initializer.getDifficulty();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("C:\\Users\\maste\\Desktop\\JavaGameEngine V7\\src\\main\\java\\org\\ict\\Initialize.json"));

        switch (difficulty) {
            case "Easy":
                GameDifficultyLevel easyDiff = new EasyDiff();
                Map<Integer, Integer> easyDiffMap = easyDiff.getDifficultyMap(jsonObject);
                System.out.println("Easy difficulty map: " + easyDiffMap);
                gameDifficultyLevel = new EasyDiff();
                break;

            case "Normal":
                GameDifficultyLevel normalDiff = new NormalDiff();
                Map<Integer, Integer> normalDiffMap = normalDiff.getDifficultyMap(jsonObject);
                System.out.println("Normal difficulty map: " + normalDiffMap);
                gameDifficultyLevel = new NormalDiff();
                break;

            case "Hard":
                GameDifficultyLevel hardDiff = new HardDiff();
                Map<Integer, Integer> hardDiffMap = hardDiff.getDifficultyMap(jsonObject);
                System.out.println("Hard difficulty map: " + hardDiffMap);
                gameDifficultyLevel = new HardDiff();
                break;

            default:
                throw new IllegalArgumentException("\n\nInvalid difficulty! Please change it in the Initialize.json file");
        }

        soundManager = new SoundManager();
        soundManager.playBackgroundAudio();
        dice = new DefaultDice((int) diceNum);
        player1 = new DefaultPlayer(0);
        player2 = new DefaultPlayer(0);
        defaultGameChecker = new DefaultWinnerChecker();

        DefaultGameBoard gameBoard = new DefaultGameBoard((int) boardSize, Color.RED, player1, player2);
        getContentPane().add(gameBoard, BorderLayout.CENTER);

        gameInfo = new JTextArea();
        gameInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(250, 300));
        getContentPane().add(scrollPane, BorderLayout.EAST);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });


        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGame();
                Component[] components = gameBoard.getComponents();
                for (Component component : components) {
                    component.setBackground(Color.WHITE);
                }
            }
        });

        rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {


                int roll = dice.roll();
                gameInfo.append("Player " + (currentPlayer + 1) + " rolled a: " + roll + "!\n");
                soundManager.playDiceRollSound();

                Map<Integer, Integer> diffMap = gameDifficultyLevel.getDifficultyMap(jsonObject);
                Integer targetPosition;

                if (currentPlayer == 0) {
                    player1Position += roll;
                    targetPosition = diffMap.get(player1Position);

                    if (targetPosition != null) {
                        player1Position = targetPosition;
                        gameInfo.append("Player 1 TELEPORTS to BOX " + targetPosition + "!!!\n");
                    }

                    gameInfo.append("Player 1 MOVED to BOX: " + player1Position + "\n\n");

                    if (player1Position > boardSize) {
                        player1Position = (int) (boardSize - (player1Position - boardSize));
                    }

                    if (defaultGameChecker.hasPlayerWon(player1Position, (int) boardSize)) {
                        gameInfo.append("Player 1 wins!\n");
                        soundManager.playWinSound();
                        rollButton.setEnabled(false);
                        gameBoard.paintPlayer(1, player1Position);
                        return;
                    }

                    gameBoard.paintPlayer(1, player1Position);
                    currentPlayer = 1;
                } else {
                    player2Position += roll;
                    gameInfo.append("Player 2 MOVED TO: " + player2Position + "\n\n");
                    targetPosition = diffMap.get(player2Position);

                    if (targetPosition != null) {
                        player2Position = targetPosition;
                        gameInfo.append("Player 2 TELEPORTS to BOX " + targetPosition + "!!!\n");
                    }


                    if (player2Position > boardSize) {
                        player2Position = (int) (boardSize - (player2Position - boardSize));
                    }

                    if (defaultGameChecker.hasPlayerWon(player2Position, (int) boardSize)) {
                        gameInfo.append("Player 2 wins!\n");
                        soundManager.playWinSound();
                        rollButton.setEnabled(false);
                        gameBoard.paintPlayer(2, player2Position);
                        return;
                    }

                    gameBoard.paintPlayer(2, player2Position);
                    currentPlayer = 0;
                }
            }
        });

        getContentPane().add(rollButton, BorderLayout.SOUTH);
        getContentPane().add(loadButton, BorderLayout.WEST);
        getContentPane().add(saveButton, BorderLayout.NORTH);

        player1.setPosition(1);
        player2.setPosition(1);
        currentPlayer = 0;

        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



    }

    private void launchTicTacToe() {
        new TicTacToe();
    }

    private void launchGuessingGame() throws Exception {
        new GuessingGame();
    }

    private void loadGame() {
            try {
                DefaultGameBoard gameBoard = new DefaultGameBoard((int) boardSize, Color.RED, player1, player2);
                JSONParser jsonParser = new JSONParser();
                FileReader reader = new FileReader("gameData.json");

                JSONObject gameData = (JSONObject) jsonParser.parse(reader);
                player1Position = (int) (long) gameData.get("player1Position");
                player2Position = (int) (long) gameData.get("player2Position");
                currentPlayer = (int) (long) gameData.get("currentPlayer");

                gameInfo.append("Game loaded successfully.\n");

            } catch (IOException | ParseException exception) {
                exception.printStackTrace();
                gameInfo.append("Failed to load game.\n");
            }
            dispose();
            setSize(1280, 720);
            setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }


    private void saveGame() {
        try {
            JSONObject gameData = new JSONObject();
            // Add the necessary game data to the JSON object
            gameData.put("player1Position", player1Position);
            gameData.put("player2Position", player2Position);
            gameData.put("currentPlayer", currentPlayer);

            // Write the JSON object to the file
            FileWriter writer = new FileWriter("gameData.json");
            writer.write(gameData.toJSONString());
            writer.flush();
            writer.close();

            gameInfo.append("Game saved successfully.\n");

        } catch (IOException e) {
            e.printStackTrace();
            gameInfo.append("Failed to save game.\n");
        }
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}