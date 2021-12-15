package com.example.game.tests;

import com.example.game.Ball;
import com.example.game.Game;
import com.example.game.Map;
import com.example.game.Paddle;
import com.example.game.enums.LevelOutcome;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void testMapCreation()
    {
        Map map = new Map('#','-','U', "src/level_1.txt");
        String response = map.createMap("src/level_1.txt");
        assertEquals("Map creation error", response);
    }
    @Test
    public void testChosenLevel()
    {
        Game game = Game.getInstance();
        LevelOutcome outcome = game.checkIfLost(10,10);
        assertEquals(LevelOutcome.LOSE, outcome);
    }
    @Test
    public void testBallLaunch()
    {
        Game game = Game.getInstance();
        game.setMap(new Map('#','_','u', "src/level_3.txt"));
        game.setPaddle(new Paddle(game.getMap().getWidth()/2, game.getMap().getHeight()-3,'T','<','>'));
        game.setBall(new Ball(game.getPaddle().getX(), game.getPaddle().getY()-1,0,0,true,'O'));
        game.getBall().setOnPaddle(false);
        int[] response = game.getNewBallDirections(game.getMap(), game.getMap(), game.getPaddle(), game.getBall());
        //System.out.println(response[0] + " " + response[1]);
        int[] expected = new int[]{-1,0};
        assertArrayEquals(expected,response);
    }


}