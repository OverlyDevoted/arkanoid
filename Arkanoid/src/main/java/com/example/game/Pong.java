package com.example.game;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Pong {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Game game = Game.getInstance();
        while(true)
        {
            game.loadMainMenu();
            game.loadLevel();
            game.runGame();
            game.endGame();
        }
    }
}