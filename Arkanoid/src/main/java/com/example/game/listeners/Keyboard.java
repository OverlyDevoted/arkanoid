package com.example.game.listeners;

import com.example.game.Ball;
import com.example.game.Map;
import com.example.game.Paddle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {
    Paddle paddle;
    Ball ball;
    Map map;
    public Keyboard(Paddle paddle, Ball ball, Map map){
        this.paddle = paddle;
        this.ball = ball;
        this.map = map;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D)
        {
            if(paddle.getX()<map.getWidth()-2)
            {
                paddle.setX(paddle.getX()+1);
                if(ball.isOnPaddle())
                    ball.setX(ball.getX()+1);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            if(paddle.getX() > 1)
            {
                paddle.setX(paddle.getX()-1);
                if(ball.isOnPaddle())
                    ball.setX(ball.getX()-1);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if(ball.isOnPaddle())
            {
                ball.setOnPaddle(false);
            }

        }
    }

}
