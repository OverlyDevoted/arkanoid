package com.example.game;

import javax.swing.*;
import java.awt.event.KeyListener;

public class Ball {
    private int x;
    private int y;
    private int xDirection;
    private int yDirection;
    private boolean isOnPaddle;
    private char character;


    public Ball(int x, int y, int xDirection, int yDirection, boolean isOnPaddle, char icon) {
        this.x = x;
        this.y = y;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.isOnPaddle = isOnPaddle;
        this.character = icon;
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

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public boolean isOnPaddle() {
        return isOnPaddle;
    }

    public void setOnPaddle(boolean onPaddle) {
        isOnPaddle = onPaddle;
        yDirection=-1;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getXVector(){
        return x + xDirection;
    }

    public int getYVector(){
        int temp = y + yDirection;
        return temp;
    }
}
