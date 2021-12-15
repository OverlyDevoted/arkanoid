package com.example.game;

import com.example.game.enums.LevelOutcome;
import com.example.game.states.GameOver;
import com.example.game.states.GameRunning;
import com.example.game.states.MainMenu;

import java.awt.*;

public class Game {
    private final int DEFAULT_LIVES = 5;
    private static Game instance = new Game();
    private GameState mainMenu;
    private GameState gameRunning;
    private GameState gameOver;

    private GameState gameState;

    private GameFrame gameFrame;
    private GamePanel gamePanel;

    Map map;
    Ball ball;
    Paddle paddle;
    int lives;


    private Game(){
        mainMenu = new MainMenu(this);
        gameRunning = new GameRunning(this);
        gameOver = new GameOver(this);
        gameFrame = new GameFrame();
        gameState = mainMenu;

        lives = DEFAULT_LIVES;
    }
    public void setUpFrames() {

        gamePanel = new GamePanel();
        gameFrame.add(gamePanel, BorderLayout.CENTER);
    }


    public void setGameState(GameState newState) {
        this.gameState = newState;
    }
    public GameState getGameState() {
        return gameState;
    }

    public void runGame() {
        gameState.runGame();
    }
    public void endGame() {
        gameState.endGame();
    }
    public void loadMainMenu() {
        gameState.loadMainMenu();
    }
    public void loadLevel() {
        gameState.loadLevel();
    }

    public GamePanel getGamePanel() {return gamePanel;}
    public GameState getMainMenu() {
        return mainMenu;
    }
    public GameState getGameRunning() {
        return gameRunning;
    }
    public GameState getGameOver() {
        return gameOver;
    }

    public void endFrame(){
        gamePanel.endGame();
    }

    public LevelOutcome runFrame(){
            Map updatedMap = map.updateMap(paddle,ball);
            gamePanel.renderMap(updatedMap);
            int directions[] = getNewBallDirections(map, updatedMap, paddle, ball);

            ball.moveBall(directions[1], directions[0]);

            if (map.isNoBrickLeft())
                return LevelOutcome.WIN;

            LevelOutcome lose = checkIfLost(ball.getYVector(),map.getHeight()-1);
            if(lose == LevelOutcome.LOSE)
            {
                lives--;

                if(lives==0)
                    return LevelOutcome.LOSE;

                gamePanel.updateLifeLabel(lives);
                ball.resetBall(paddle.getX(), paddle.getY()-1);
            }
            return LevelOutcome.REPEAT;
    }
    public LevelOutcome checkIfLost(int ballY, int mapBottomY) {
        if(ballY >= mapBottomY) {
            return LevelOutcome.LOSE;
        }
        return null;
    }

    public int[] getNewBallDirections(Map map, Map mapWithPlayer, Paddle paddle, Ball ball) {
        int[] coords = new int[]{ball.getyDirection(),ball.getxDirection()};//0 - y; 1 - x
        if(!ball.isOnPaddle())
        {
            char diagonalLetter = mapWithPlayer.getElement(ball.getYVector(),ball.getXVector());
            //check trajectory symbol
            //if not space
            if(diagonalLetter != map.getSpace())
            {
                int paddleIncline = 0;
                if(diagonalLetter == paddle.getLeftCharacter())//left paddle logic
                {
                    paddleIncline=1;
                }
                else if(diagonalLetter == paddle.getRightCharacter())//right paddle logic
                {
                    paddleIncline=-1;
                }

                if(paddleIncline!=0)
                {
                    coords[0] = ball.getyDirection()*-1;
                    switch (ball.getxDirection()){
                        case 0:
                            if(ball.getX()==1 || ball.getX()==map.getWidth()-2)
                                coords[1] = paddleIncline;
                            else
                                coords[1] = -1*paddleIncline;
                            break;
                        case 1:
                            coords[1] = 0;
                            break;
                        default:
                            break;
                    }

                }
                else
                {
                    // any other element that does not have special directional modifiers

                    char verticalLetter = mapWithPlayer.getElement(ball.getYVector(),ball.getX());
                    char horizontalLetter = mapWithPlayer.getElement(ball.getY(),ball.getXVector());

                    // up/down and left/right is not empty space
                    if(verticalLetter != map.getSpace() && horizontalLetter != map.getSpace())
                    {
                        if(verticalLetter == map.getBrick())
                        {
                            map.deleteBrick(ball.getYVector(), ball.getX());
                            gamePanel.updateBrickLabel(map.getAmountOfBricks());
                        }
                        if(horizontalLetter == map.getBrick())
                        {
                            map.deleteBrick(ball.getY(), ball.getXVector());
                            gamePanel.updateBrickLabel(map.getAmountOfBricks());
                        }
                        // default trajectory for any symbol
                        coords[1] = ball.getxDirection()*-1;
                        coords[0] = ball.getyDirection()*-1;
                    }
                    else if(verticalLetter != map.getSpace())
                    {
                        if(verticalLetter == map.getBrick())
                        {
                            map.deleteBrick(ball.getYVector(), ball.getX());
                            gamePanel.updateBrickLabel(map.getAmountOfBricks());
                        }
                        coords[0] = ball.getyDirection()*-1;
                    }
                    else if(diagonalLetter != map.getSpace()) {
                        if (diagonalLetter == map.getBrick())
                        {
                            map.deleteBrick(ball.getYVector(), ball.getXVector());
                            gamePanel.updateBrickLabel(map.getAmountOfBricks());
                        }
                        coords[1] = ball.getxDirection()*-1;
                    }
                }

            }
        }
        return coords;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    public Map getMap() {
        return map;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
    public Ball getBall() {
        return ball;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }
    public Paddle getPaddle() {
        return paddle;
    }

    public int getLives() {
        return lives;
    }

    public static Game getInstance() {
        return instance;
    }
}