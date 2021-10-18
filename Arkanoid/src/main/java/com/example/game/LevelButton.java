package com.example.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelButton extends JButton {

    private String levelName;
    private String buttonText;
    private boolean wasClicked;

    public LevelButton(String levelName, String buttonText) {
        this.levelName = levelName;
        this.buttonText = buttonText;
        this.setBackground(Color.black);
        this.setForeground(Color.white);

        this.setText(buttonText);
        this.setVisible(true);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setWasClicked(true);
            }
        });
    }

    public String getLevelName() {
        return levelName;
    }

    public boolean isWasClicked() {
        return wasClicked;
    }

    public void setWasClicked(boolean wasClicked) {
        this.wasClicked = wasClicked;
    }

}
