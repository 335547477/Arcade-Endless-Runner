/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learningtoprogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Kinjal
 */
public class levelData implements java.io.Serializable {

    public int level1;
    public int appleNum1;
    public int numSize1;
    public int size1;

    public static File file = new File("LevelData.txt");

//CODE USED TO TRY TO TRY TO USE FILES TO SAVE DATA (MEANT TO BE IN GAME ENGINE)
//due to not being done and many bugs I need to think through I left this here to come back to
//many of the static errors showed are fixed when put back into the game engine 
    public static void saveLevel() {
        try {
            //creates a print writer that helps print on a file without erasing previous data when true
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            //since true one each line prints the level, number of apples, number size for math, and tile size                 
            pw.println(level);
            pw.println(appleNum);
            pw.println(numSize);
            pw.println(size);
            //closes printwrite
            pw.close();
            //when errors are catched nothing is done as for now 
        } catch (FileNotFoundException ex) {
            //do nothing 
        } catch (IOException e) {
            //do nothing
        }
    }

    public static void restartLevel() {
        try {
            //creates a scanner that is able to scan and read the file
            Scanner s = new Scanner(file);
            //There is a  bug but what I want is that the first number is stored in level 
            //then the next integer after that should be in the apple num 
            level = s.nextInt();
            appleNum = s.nextInt();
            numSize = s.nextInt();
            size = s.nextInt();
            //when errors are catched nothing is done for now 
        } catch (FileNotFoundException ex) {
            //do nothing
        } catch (NoSuchElementException e) {
            //do nothing 
        }

    }

    //ANOTHER ATTEMPT TO USE SERIALIZATION AS A METHOD TO SAVE DATA (MEANT TO BE IN THE GAME ENGINE)
    //again put here as rough work that still needs to be worked on and fixed 
    //many of the static errors are fixed when copied back into game engine
    //parts were inspired and taught from https://www.geeksforgeeks.org/serialization-in-java/
    public static levelData d = new levelData();
    public static void dataSer() {
        d.level1 = level;
        d.appleNum1 = appleNum;
        d.numSize1 = numSize;
        d.size1 = size;
        try {
            //saves the object in a file
            FileOutputStream fileO = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileO);
            //serializes object (stores in a sequence of bytes)
            out.writeObject(d);
            //closes object and file 
            out.close();
            fileO.close();
            //catches errors and does nothing for now 
        } catch (IOException e) {
            //do nothing
        }
        //gives the object a value of null
        levelData d = null;
    }

    public static void dataDeser() {
        try {
            //reading the object from a file
            FileInputStream fileI = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileI);
            //deseralizes object (reconstructs from the sequence of bytes)
            d = (levelData) in.readObject();
            //closes object and file
            in.close();
            fileI.close();
            //bug: the integer that is being reconstructed is always 0
            //since the level is serl=ilaized, it should then be reconstructed the level answer
            //then with the level, appleNum, numSize, and size that the player ended off with the game resumes and fills game board 
            level = d.level1;
            appleNum = d.appleNum1;
            numSize = d.numSize1;
            size = d.size1;
        } catch (IOException | ClassNotFoundException e) {
            //do nothing
        }
        //do nothing
    }
}
