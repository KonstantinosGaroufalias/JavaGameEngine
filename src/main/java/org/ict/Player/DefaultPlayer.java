package org.ict.Player;

public class DefaultPlayer implements PlayerService {
    private int position;


    public DefaultPlayer(int position) {

        this.position = position;

    }

    @Override

    public int getPosition() {

        return position;

    }


    @Override

    public void setPosition(int position) {

        this.position=position;

    }

}

