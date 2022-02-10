package com.example.game;

import com.example.game.enums.LevelOutcome;
import com.example.game.listeners.Keyboard;
import com.example.game.listeners.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel{
    private JLabel[] gameLabels;
    private JLabel scoreLabel;
    private JLabel lifeLabel;
    private LevelPanel levelPanel;
    ArrayList<LevelButton> levelButtons;

    public GamePanel(){
        this.setFocusable(true);
        this.setBackground(Color.black);
    }

    public void loadMainMenu() {
        System.out.println("Main menu loaded");
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        loadLevelSelectionPanel();
    }
    private void loadLevelSelectionPanel() {
        levelPanel = new LevelPanel();
        levelButtons = levelPanel.getLevelButtons();
        this.add(levelPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }


    public void loadLevel(String chosenLevel, Map map, Paddle paddle, Ball ball, int lives) {
        removeMainMenuItems();
        addInputListeners(map, paddle, ball);

        gameLabels = convertMapToLabels(map);
        setUpBrickCountText(map);
        setUpLivesCountText(lives);
    }
    private void removeMainMenuItems() {
        levelPanel.ResetAllButtons();
        this.remove(levelPanel);
        this.revalidate();
        this.repaint();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
    private void addInputListeners(Map map, Paddle paddle, Ball ball) {
        this.addMouseListener(new Mouse(paddle, ball, map));
        this.addMouseWheelListener(new Mouse(paddle, ball, map));
        this.addKeyListener(new Keyboard(paddle, ball, map));
    }
    private JLabel[] convertMapToLabels(Map map) {
        JLabel[] gameLabels = new JLabel[map.getHeight()];
        for(int i = 0; i< map.getHeight(); i++)
        {
            gameLabels[i] = new JLabel("", SwingConstants.RIGHT);
            gameLabels[i].setFont(new Font("Consolas",Font.PLAIN, 15));
            gameLabels[i].setForeground(Color.white);
            this.add(gameLabels[i]);
        }
        return gameLabels;
    }
    private void setUpLivesCountText(int lives) {
        lifeLabel = new JLabel();
        lifeLabel.setText("Lives left: "+ lives);
        lifeLabel.setForeground(Color.white);
        this.add(lifeLabel);
    }
    private void setUpBrickCountText(Map map) {
        scoreLabel = new JLabel();
        scoreLabel.setText("Bricks left: "+ map.getAmountOfBricks());
        scoreLabel.setForeground(Color.white);
        this.add(scoreLabel);
    }

    public void endGame() {
        for(JLabel label:gameLabels)
        {
            try {
                this.remove(label);
                this.revalidate();
                this.repaint();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.remove(scoreLabel);
        this.remove(lifeLabel);
        this.revalidate();
        this.repaint();
        this.removeMouseListener(this.getMouseListeners()[0]);
        this.removeMouseWheelListener(this.getMouseWheelListeners()[0]);
    }
    public String getChosenLevel() {
        for(LevelButton button: levelButtons)
        {
            if(button.isWasClicked())
            {
                return button.getLevelName();
            }
        }
        return "";
    }
    public void updateLifeLabel(int lifePool) {
        lifeLabel.setText("Lives left: "+ lifePool);
        System.out.println("Lives left: "+ lifePool);
    }
    public void updateBrickLabel(int bricksLeft){
        scoreLabel.setText("Bricks left: " + bricksLeft);
    }

    public void renderMap(Map map) {
        int height = map.getHeight();
        for(int i = 0;i<height;i++){
            gameLabels[i].setText(String.copyValueOf(map.getMapDrawingLine(i)));
        }
    }

}
