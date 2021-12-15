package com.example.game;

//State Design Pattern use
public interface GameState {
    void loadLevel();
    void runGame();
    void endGame();
    void loadMainMenu();
}
