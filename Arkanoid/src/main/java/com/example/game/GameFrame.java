package com.example.game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GamePanel gamePanel;
    public GameFrame() {
        gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER);
        this.setTitle("Console Arkanoid");
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
