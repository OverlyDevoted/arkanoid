package com.example.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelLoader {
    //file reader for levels
    public ArrayList<String> ReadLevel(String fileDirectory)
    {
        File file = new File(fileDirectory);
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> lines = new ArrayList<String>();
        while (fileReader.hasNextLine()) {
            lines.add(fileReader.nextLine());
        }
        fileReader.close();
        System.out.println(fileDirectory + " file loaded");
        return lines;
    }


}
