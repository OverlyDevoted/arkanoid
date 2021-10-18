package com.example.game;

import java.util.ArrayList;

public class Map {
    static final int MIN_MAP_HEIGHT = 5;
    static final int MIN_MAP_WIDTH = 2;
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
        CreateMap(mapFileName);
    }

    public void CreateMap(String mapFileName)
    {
        //refactor so that map class does not depend on levelloader class so much
        LevelLoader loader = new LevelLoader();
        ArrayList<String> lines = loader.ReadLevel(mapFileName);
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
        System.out.println("Map created");
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

}
