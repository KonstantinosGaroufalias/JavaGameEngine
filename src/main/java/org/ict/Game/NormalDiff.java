package org.ict.Game;

import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NormalDiff implements GameDifficultyLevel {

    @Override
    public Map<Integer, Integer> getDifficultyMap(JSONObject jsonObject) {
        Map<Integer, Integer> difficultyMap = new HashMap<>();
        JSONObject normalDiffMap = (JSONObject) jsonObject.get("NormalDiffMap");
        for (Object key : normalDiffMap.keySet()) {
            Integer startingSquare = Integer.parseInt((String) key);
            Integer endingSquare = ((Long) normalDiffMap.get(key)).intValue();
            difficultyMap.put(startingSquare, endingSquare);
        }
        return difficultyMap;
    }
}
