package com.example.game.states;

import com.example.game.Game;
import com.example.game.GamePanel;
import com.example.game.GameState;
import com.example.game.enums.LevelOutcome;

public class GameRunning implements GameState {
    Game game;

    public GameRunning(Game game) {
        this.game = game;
    }

    @Override
    public void loadLevel() {

    }

    @Override
    public void runGame() {
        LevelOutcome outcome = LevelOutcome.REPEAT;
        while(outcome.equals(LevelOutcome.REPEAT))
        {
            outcome = game.runFrame();
            //create fps
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(outcome.equals(LevelOutcome.WIN))
        {
            System.out.println("W");
        }
        else
        {
            System.out.println("L");
        }
        game.setGameState(game.getGameOver());
    }

    @Override
    public void endGame() {

    }

    @Override
    public void loadMainMenu() {

    }
}
