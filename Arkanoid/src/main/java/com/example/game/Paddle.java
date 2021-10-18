package com.example.game;

import javax.swing.*;
import java.awt.event.*;

public class Paddle {
    private int x;
    private int y;
    private char middleCharacter;
    private char leftCharacter;
    private char rightCharacter;

    public Paddle(int x, int y, char middleCharacter, char leftCharacter, char rightCharacter) {
        this.x = x;
        this.y = y;
        this.middleCharacter = middleCharacter;
        this.leftCharacter = leftCharacter;
        this.rightCharacter = rightCharacter;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getMiddleCharacter() {
        return middleCharacter;
    }

    public void setMiddleCharacter(char middleCharacter) {
        this.middleCharacter = middleCharacter;
    }

    public char getLeftCharacter() {
        return leftCharacter;
    }

    public void setLeftCharacter(char leftCharacter) {
        this.leftCharacter = leftCharacter;
    }

    public char getRightCharacter() {
        return rightCharacter;
    }

    public void setRightCharacter(char rightCharacter) {
        this.rightCharacter = rightCharacter;
    }

}
