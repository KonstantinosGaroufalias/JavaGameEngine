package org.ict.JSON;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GameInitializer {
    private long boardSize;
    private long diceNum;
    private long playerNum;
    private String difficulty;
    private String gameType;
    private long remainingGuesses;
    private  long randomNumber;

    public void initialize() throws Exception {
        FileReader reader = new FileReader("C:\\Users\\maste\\Desktop\\JavaGameEngine V7\\src\\main\\java\\org\\ict\\Initialize.json");
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        boardSize = (long) jsonObject.get("boardSize");
        diceNum = (long) jsonObject.get("diceNum");
        playerNum = (long) jsonObject.get("playerNum");
        difficulty = (String) jsonObject.get("difficulty");
        gameType = (String) jsonObject.get("gameType");
        remainingGuesses = (long) jsonObject.get("remainingGuesses");
        randomNumber = (long) jsonObject.get("randomNumber");
    }

    public long getBoardSize() {
        return boardSize;
    }

    public long getDiceNum() {
        return diceNum;
    }

    public long getPlayerNum() {
        return playerNum;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public String getGameType(){
        return gameType;
    }

    public long getRemainingGuesses(){
        return remainingGuesses;
    }

    public long getrandomNumber(){
        return randomNumber;
    }

    public static void main(String[] args) {
        try {
            GameInitializer initializer = new GameInitializer();
            initializer.initialize();

            long boardSize = initializer.getBoardSize();
            long diceNum = initializer.getDiceNum();
            long playerNum = initializer.getPlayerNum();
            String difficulty = initializer.getDifficulty();
            String gameType = initializer.getGameType();

            System.out.println("boardSize: " + boardSize);
            System.out.println("diceNum: " + diceNum);
            System.out.println("playerNum: " + playerNum);
            System.out.println("difficulty: " + difficulty);
            System.out.println("gameType: " + gameType);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}