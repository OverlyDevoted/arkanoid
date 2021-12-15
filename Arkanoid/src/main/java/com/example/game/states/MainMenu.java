package com.example.game.states;

import com.example.game.*;

public class MainMenu implements GameState {
    Game game;

    public MainMenu(Game newGame) {
        this.game = newGame;
    }

    @Override
    public void loadLevel() {
        String choosingLevel = "";
        while(choosingLevel.equals(""))
        {
            choosingLevel = game.getGamePanel().getChosenLevel();
        }
        game.setMap(new Map('#','_','u', choosingLevel));
        game.setPaddle(new Paddle(game.getMap().getWidth()/2, game.getMap().getHeight()-3,'T','<','>'));
        game.setBall(new Ball(game.getPaddle().getX(), game.getPaddle().getY()-1,0,0,true,'O'));
        game.getGamePanel().loadLevel(choosingLevel, game.getMap(), game.getPaddle(), game.getBall(), game.getLives());
        game.setGameState(game.getGameRunning());
    }

    @Override
    public void runGame() {

    }

    @Override
    public void endGame() {}

    @Override
    public void loadMainMenu() {
        game.setUpFrames();
        game.getGamePanel().loadMainMenu();
    }
}
