package com.example.game;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LevelPanel extends JPanel{
        private ArrayList<LevelButton> levelButtons;
        public LevelPanel() {
            this.setFocusable(true);
            this.setVisible(true);
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            levelButtons = new ArrayList<LevelButton>();
            CreateLevelButtons();
        }
        private void CreateLevelButtons() {
            levelButtons.add(new LevelButton("src/level_1.txt","Level 1"));
            levelButtons.add(new LevelButton("src/level_2.txt","Level 2"));
            levelButtons.add(new LevelButton("src/level_3.txt","Level 3"));
            levelButtons.add(new LevelButton("src/level_4.txt","Level 4"));
            levelButtons.add(new LevelButton("src/level_5.txt","Level 5"));
            levelButtons.add(new LevelButton("src/level_6.txt","Level 6"));
            CreateLevelPanel();
        }
        private void CreateLevelPanel() {
            for(LevelButton levelButton: levelButtons)
            {
                this.add(levelButton);
            }
        }
        public void ResetAllButtons() {
            for(LevelButton button:levelButtons)
                button.setWasClicked(false);
        }

    public ArrayList<LevelButton> getLevelButtons() {
        return levelButtons;
    }
}
