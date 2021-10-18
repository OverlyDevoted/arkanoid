package com.example.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable{
    private final int DEFAULT_LIVES = 5;
    private Paddle paddle;
    private Ball ball;
    private Map map;
    private JLabel[] gameLabels;
    private JLabel scoreLabel;
    private JLabel lifeLabel;
    private LevelPanel levelPanel;

    Thread gameThread;
    public GamePanel() {

        this.setFocusable(true);
        this.setBackground(Color.black);

        levelPanel = new LevelPanel();

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(true)
        {
            System.out.println("new game");
            this.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
            boolean choosingLevel=true;
            String chosenLevel = "";
            ArrayList<LevelButton> levelButtons = levelPanel.getLevelButtons();
            this.add(levelPanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            while(choosingLevel)
            {
                for(LevelButton button: levelButtons)
                {
                    if(button.isWasClicked())
                    {
                        choosingLevel=false;
                        chosenLevel = button.getLevelName();
                    }
                }
            }
            levelPanel.ResetAllButtons();
            this.remove(levelPanel);
            this.revalidate();
            this.repaint();
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            this.addMouseListener(new ML());
            this.addMouseWheelListener(new ML());



            map = new Map('#','_','u',chosenLevel);
            paddle = new Paddle(map.getWidth()/2, map.getHeight()-3,'T','<','>');
            ball = new Ball(paddle.getX(), paddle.getY()-1,0,0,true,'O');

            gameLabels = new JLabel[map.getHeight()];
            for(int i=0;i< map.getHeight();i++)
            {
                gameLabels[i] = new JLabel("", SwingConstants.RIGHT);
                gameLabels[i].setFont(new Font("Consolas",Font.PLAIN, 15));
                gameLabels[i].setForeground(Color.white);
                this.add(gameLabels[i]);
            }
            scoreLabel = new JLabel();
            scoreLabel.setText("Bricks left: "+ map.getAmountOfBricks());
            scoreLabel.setForeground(Color.white);
            this.add(scoreLabel);

            int lives = DEFAULT_LIVES;
            lifeLabel = new JLabel();
            lifeLabel.setText("Lives left: "+ lives);
            lifeLabel.setForeground(Color.white);
            this.add(lifeLabel);

            while(true)
            {
                char [][]temp = new char[map.getHeight()][];
                for(int i=0;i<map.getHeight();i++)
                {
                    temp[i] = Arrays.copyOf(map.getMapDrawingLine(i),map.getWidth());
                }
                //redraw map
                for(int i=0;i<map.getHeight(); i++)
                {
                    for(int j=0;j<map.getWidth();j++)
                    {
                        if(ball.getX() ==j && ball.getY() ==i)
                        {
                            temp[i][j]=ball.getCharacter();
                            continue;
                        }
                        if(paddle.getX()==j&&paddle.getY()==i)
                        {
                            temp[i][j]=paddle.getMiddleCharacter();
                            continue;
                        }
                        if(paddle.getX()-1==j&&paddle.getY()==i)
                        {
                            temp[i][j]=paddle.getLeftCharacter();
                            continue;
                        }
                        if(paddle.getX()+1==j&& paddle.getY()==i)
                        {
                            temp[i][j]= paddle.getRightCharacter();
                            continue;
                        }

                    }

                    gameLabels[i].setText(new String(temp[i]));
                }

                if(!ball.isOnPaddle())
                {
                    char incomingCharacter = temp[ball.getYVector()][ball.getXVector()];
                    if(incomingCharacter != map.getSpace())
                    {
                        if(incomingCharacter == paddle.getLeftCharacter())
                        {
                            ball.setyDirection(ball.getyDirection()*-1);
                            switch (ball.getxDirection()){
                                case 0:
                                    if(ball.getX()==1)
                                        ball.setxDirection(1);
                                    else
                                        ball.setxDirection(-1);
                                    break;
                                case 1:
                                    ball.setxDirection(0);
                                    break;
                                default:
                                    break;
                            }
                        }
                        else if(incomingCharacter == paddle.getRightCharacter())
                        {
                            ball.setyDirection(ball.getyDirection()*-1);
                            switch (ball.getxDirection()){
                                case 0:
                                    if(ball.getX()==map.getWidth()-2)
                                        ball.setxDirection(-1);
                                    else
                                        ball.setxDirection(1);
                                    break;
                                case 1:
                                    ball.setxDirection(0);
                                    break;
                                default:
                                    break;
                            }
                        }
                        else
                        {
                            char diagonalLetter = temp[ball.getYVector()][ball.getXVector()];
                            char verticalLetter = temp[ball.getYVector()][ball.getX()];
                            char horizontalLetter = temp[ball.getY()][ball.getXVector()];


                            if(verticalLetter != map.getSpace() && horizontalLetter != map.getSpace())
                            {
                                if(verticalLetter == map.getBrick())
                                {
                                    map.setElement(ball.getYVector(),ball.getX(),map.getSpace());
                                    map.setAmountOfBricks(map.getAmountOfBricks()-1);
                                    scoreLabel.setText("Bricks left: " + map.getAmountOfBricks());
                                }
                                if(horizontalLetter == map.getBrick())
                                {
                                    map.setElement(ball.getY(),ball.getXVector(),map.getSpace());
                                    map.setAmountOfBricks(map.getAmountOfBricks()-1);
                                    scoreLabel.setText("Bricks left: " + map.getAmountOfBricks());
                                }
                                ball.setxDirection(ball.getxDirection()*-1);
                                ball.setyDirection(ball.getyDirection()*-1);
                            }
                            else if(verticalLetter != map.getSpace())
                            {
                                if(verticalLetter == map.getBrick())
                                {
                                    map.setElement(ball.getYVector(),ball.getX(),map.getSpace());
                                    map.setAmountOfBricks(map.getAmountOfBricks()-1);
                                    scoreLabel.setText("Bricks left: " + map.getAmountOfBricks());
                                }
                                ball.setyDirection(ball.getyDirection()*-1);
                            }
                            else if(diagonalLetter != map.getSpace()) {
                                if (diagonalLetter == map.getBrick())
                                {
                                    map.setElement(ball.getYVector(), ball.getXVector(), map.getSpace());
                                    map.setAmountOfBricks(map.getAmountOfBricks()-1);
                                    scoreLabel.setText("Bricks left: " + map.getAmountOfBricks());
                                }
                                ball.setxDirection(ball.getxDirection()*-1);
                            }
                        }

                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ball.setY(ball.getYVector());
                ball.setX(ball.getXVector());
                //win condition logic
                if(map.getAmountOfBricks()==0)
                {
                    break;
                }

                if(ball.getYVector() >= map.getHeight()-1)
                {

                    if(lives==0)
                    {
                        System.out.println(lives);
                        break;
                    }
                    else
                    {
                        lives--;
                        lifeLabel.setText("Lives left: "+ lives);
                        System.out.println(lives);
                        ball.setOnPaddle(true);
                        ball.setxDirection(0);
                        ball.setyDirection(0);
                        ball.setX(paddle.getX());
                        ball.setY(paddle.getY()-1);
                    }
                }

            }
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

    }


    public class AL extends KeyAdapter{
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
    public class ML extends MouseAdapter{
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

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
