package com.example.game.listeners;

import com.example.game.Ball;
import com.example.game.Map;
import com.example.game.Paddle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse extends MouseAdapter {
    Paddle paddle;
    Ball ball;
    Map map;
    public Mouse(Paddle paddle, Ball ball, Map map){
        this.paddle = paddle;
        this.ball = ball;
        this.map = map;
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getPreciseWheelRotation() == 1){
            if(paddle.getX()<map.getWidth()-2)
            {
                paddle.setX(paddle.getX()+1);
                if(ball.isOnPaddle())
                    ball.setX(ball.getX()+1);
            }
        }
        if (e.getPreciseWheelRotation() == -1) {
            if (paddle.getX() > 1) {
                paddle.setX(paddle.getX() - 1);
                if (ball.isOnPaddle())
                    ball.setX(ball.getX() - 1);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            if(ball.isOnPaddle())
            {
                ball.setOnPaddle(false);
            }

        }
    }
}
