package org.ict.Game;

public class DefaultTimer implements Timer {

    @Override
    public long getTime() {
        return System.currentTimeMillis();
    }
}
