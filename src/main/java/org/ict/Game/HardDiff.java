package org.ict.Game;

import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class HardDiff implements GameDifficultyLevel {

    @Override
    public Map<Integer, Integer> getDifficultyMap(JSONObject jsonObject) {
        Map<Integer, Integer> difficultyMap = new HashMap<>();
        JSONObject hardDiffMap = (JSONObject) jsonObject.get("HardDiffMap");
        for (Object key : hardDiffMap.keySet()) {
            Integer startingSquare = Integer.parseInt((String) key);
            Integer endingSquare = ((Long) hardDiffMap.get(key)).intValue();
            difficultyMap.put(startingSquare, endingSquare);
        }
        return difficultyMap;
    }
}