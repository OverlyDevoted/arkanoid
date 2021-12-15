package com.example.game.states;

import com.example.game.Game;
import com.example.game.GameState;

public class GameOver implements GameState {
    Game game;

    public GameOver(Game newGame) {
        this.game = newGame;
    }

    @Override
    public void loadLevel() {

    }

    @Override
    public void runGame() {

    }

    @Override
    public void endGame() {
        game.endFrame();
        game.setGameState(game.getMainMenu());
    }

    @Override
    public void loadMainMenu() {

    }
}
