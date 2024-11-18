package org.ict.GameBoard;

import org.ict.Player.Player;

import javax.swing.*;
import java.awt.*;

public class DefaultGameBoard extends JPanel implements GameBoard {
    private int boardSize;
    private JPanel gameBoard;
    private Color color;
    private Player player1;
    private Player player2;
    public DefaultGameBoard(int boardSize, Color color, Player player1, Player player2) {
        this.boardSize = boardSize;
        this.color = color;
        this.player1 = player1;
        this.player2 = player2;
        initializeGameBoard();
    }
    @Override
    public void initializeGameBoard() {
        setLayout(new GridLayout(1, 1));
        gameBoard = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < boardSize; i++) {
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.black));
            panel.setBackground(Color.white);
            JLabel label = new JLabel("" + (boardSize-i), SwingConstants.CENTER);
            panel.add(label);
            gameBoard.add(panel);
        }
        add(gameBoard,BorderLayout.CENTER);
    }

    @Override
    public void paintPlayer(int player, int position) {
        Player currentPlayer;
        if (player == 1) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        int oldPosition = currentPlayer.getPosition();
        currentPlayer.setPosition(position);
       Component newComponent = gameBoard.getComponent(boardSize - position);
        if (player == 1){
            newComponent.setBackground(Color.blue);
        } else{
            newComponent.setBackground(Color.red);
        }
        Component oldComponent = gameBoard.getComponent(boardSize - oldPosition);
        oldComponent.setBackground(Color.WHITE);
    }
}
