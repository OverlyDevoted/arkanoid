package com.example.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    static final int MIN_MAP_HEIGHT = 8;
    static final int MIN_MAP_WIDTH = 5;
    private int height;
    private int width;
    private char[][] mapDrawing;
    private int amountOfBricks;

    private char wall;
    private char space;
    private char brick;
    private String mapFileName;

    public Map(char wall, char space, char brick, String mapFileName) {
        this.wall = wall;
        this.space = space;
        this.brick = brick;
        this.mapFileName = mapFileName;
        createMap(mapFileName);
    }

    public Map(final Map map) {
        this.height = map.height;
        this.width = map.width;
        this.wall = map.wall;
        this.space = map.space;
        this.brick = map.brick;
        this.amountOfBricks = map.amountOfBricks;
        this.mapDrawing = map.mapDrawing;

    }
    public Map updateMap(Paddle paddle, Ball ball) {
        Map temp = new Map(this);
        temp.setMapDrawing(this.getMapDrawingCopy());
        for(int i = 0; i< this.getHeight(); i++)
        {
            for(int j = 0; j< this.getWidth(); j++)
            {
                if(ball.getX() ==j && ball.getY() ==i)
                {
                    temp.setElement(i,j,ball.getCharacter());
                    continue;
                }
                if(paddle.getX()==j&& paddle.getY()==i)
                {
                    temp.setElement(i,j, paddle.getMiddleCharacter());
                    continue;
                }
                if(paddle.getX()-1==j&& paddle.getY()==i)
                {
                    temp.setElement(i,j, paddle.getLeftCharacter());
                    continue;
                }
                if(paddle.getX()+1==j&& paddle.getY()==i)
                {
                    temp.setElement(i,j, paddle.getRightCharacter());
                    continue;
                }
            }
        }
        return temp;
    }
    public String createMap(String mapFileName) {
        //refactor so that map class does not depend on levelloader class so much
        height=0;
        width=0;
        LevelLoader loader = new LevelLoader();
        ArrayList<String> lines = loader.getLevel(mapFileName);
        mapDrawing = new char[lines.size()][];
        for(String line:lines) {
            mapDrawing[height] = line.toCharArray();
            for(char a:line.toCharArray())
            {
                if(a == brick)
                    amountOfBricks++;
            }
            height++;
        }
        width = lines.get(0).length();
        if(height <= MIN_MAP_HEIGHT || width <= MIN_MAP_HEIGHT)
        {
            System.out.println("Invalid map");
            return "Map creation error";
        }
        System.out.println("Map created");
        return "Map created";
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public char[][] getMapDrawing() {
        return mapDrawing;
    }
    public char[] getMapDrawingLine(int index){
        return mapDrawing[index];
    }
    public char[][] getMapDrawingCopy() {
        char[][]temp = new char[getHeight()][];
        for(int i=0;i<getHeight();i++)
        {
            temp[i] = Arrays.copyOf(getMapDrawingLine(i),getWidth());
        }
        return temp;
    }


    public void setMapDrawing(char[][] mapDrawing) {
        this.mapDrawing = mapDrawing;
    }

    public int getAmountOfBricks() {
        return amountOfBricks;
    }

    public void setAmountOfBricks(int amountOfBricks) {
        this.amountOfBricks = amountOfBricks;
    }

    public char getWall() {
        return wall;
    }

    public void setWall(char wall) {
        this.wall = wall;
    }

    public char getSpace() {
        return space;
    }

    public void setSpace(char space) {
        this.space = space;
    }

    public char getBrick() {
        return brick;
    }

    public void setBrick(char brick) {
        this.brick = brick;
    }

    public void setElement(int y, int x, char element){
        mapDrawing[y][x] = element;
    }
    public char getElement(int y, int x) { return mapDrawing[y][x];}

    public void deleteBrick(int y, int x) {
        setElement(y,x,getSpace());
        setAmountOfBricks(getAmountOfBricks()-1);
    }
    public void mapToString() {
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                System.out.print(getElement(i,j));
            }
            System.out.println();
        }
    }
    public boolean isNoBrickLeft() {
        if(amountOfBricks==0)
        {
            return true;
        }
        return false;
    }
}
