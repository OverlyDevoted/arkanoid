package com.example.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelLoader {
    //reads level files
    public ArrayList<String> getLevel(String fileDirectory) {
        Scanner fileData;
        try{
            fileData = readLevel(fileDirectory);

        }
        catch (Exception e)
        {
            System.out.println(e);
            return new ArrayList<String>();
        }

        ArrayList<String> lines = new ArrayList<String>();

        while (fileData.hasNextLine()) {
            lines.add(fileData.nextLine());
        }
        fileData.close();
        System.out.println(fileDirectory + " file loaded");
        return lines;
    }
    private Scanner readLevel(String fileDirectory) {
        File file = new File(fileDirectory);
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileReader;
    }
    public void readFolder() {
        File folder = new File("src");
        File[] files = folder.listFiles();
        for(File thing: files)
        {
            System.out.println(thing.getName());
        }
    }
}
