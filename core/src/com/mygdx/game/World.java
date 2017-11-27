package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class World {
    private Flyer flyer;
    private int score;
    private JetpackJoyrideGame jetpackjoyrideGame;
    private GameScreen gameScreen;
    World(JetpackJoyrideGame jetpackjoyrideGame) {
        flyer = new Flyer(100,100,this);
        this.jetpackjoyrideGame = jetpackjoyrideGame;
        score = 0;
    }

    private void updatePacmanDirection() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            flyer.jumpUp();
            //System.out.println("UP");
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            flyer.jumpDown();
            //System.out.println("DOWN");
        }
    }

    public void update(float delta) {
        updatePacmanDirection();
        flyer.update();
    }

    public int getScore() {
        return score;
    }
    public void increaseScore() {
        score += 1;
    }

    Flyer getFlyer() {
        return flyer;
    }
}
