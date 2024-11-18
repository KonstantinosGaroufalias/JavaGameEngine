package org.ict.Game;

import org.json.simple.JSONObject;

import java.util.Map;

public interface GameDifficultyLevel {
    Map<Integer, Integer> getDifficultyMap(JSONObject jsonObject);
}
