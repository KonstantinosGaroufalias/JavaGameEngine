package org.ict.Game;

import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class EasyDiff implements GameDifficultyLevel {
    @Override
    public Map<Integer, Integer> getDifficultyMap(JSONObject jsonObject) {
        Map<Integer, Integer> difficultyMap = new HashMap<>();
        JSONObject easyDiffMap = (JSONObject) jsonObject.get("EasyDiffMap");
        for (Object key : easyDiffMap.keySet()) {
            Integer startingSquare = Integer.parseInt((String) key);
            Integer endingSquare = ((Long) easyDiffMap.get(key)).intValue();
            difficultyMap.put(startingSquare, endingSquare);
        }
        return difficultyMap;
    }
}
