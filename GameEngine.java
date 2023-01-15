/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learningtoprogram;

/**
 * Author: Kinjal Class: ICS3U
 *
 * Program: Catch 'Em Apples Game Engine
 *
 */
//Import Statements Listed Alphabetically
import java.io.*;           //used for any type of input or output
import java.util.*;         //useful utilities like Scanner
import hsa.Console;
import hsa.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kinjal
 */

public class GameEngine {
    //list of variables that are going to be used throughout the methods 
    //size of the first board will start being 10
    public static int size = 10;
     //creates board with varibale size x size which can be changed later on per level 
    public static char[][] gameTiles = new char [size][size];
    public static Console c = new Console(); 
    public static int moveRow;
    public static int moveCol;
    public static int row;
    public static int column;
    //start off with 4 apples 
    public static int appleNum = 4;
    //the number of apples collected will start at 0
    public static int applesCollected = 0;
    public static int applesLeft;
    //the numSize will start at 50 meaning that the math questions will be between 0 and 50 
    public static int numSize = 50;
    //the game by default will start at level 1 
    public static int level = 1;
    public static int next;
    //helps create access path to file which can be used to for input and output 
    public static File file = new File ("LevelData.txt");

    //creates method that fills the first game board
    public static void fillFirstGameBoard () {
        //creates game board that fills each index with W and the starting point 
         for (row = 0; row<gameTiles.length; row++) {
            for (column = 0; column < gameTiles.length; column++) {
                gameTiles[row][column] = 'W';
        /*if row and column is equal to the last row and the middlemost column 
        then place F as the frog at the start */
                if (gameTiles[row][column] == gameTiles [gameTiles.length-1][size/2]) {
                    gameTiles[row][column] = 'F';
        //then call the lilypads first method which places lilypads around it
                    lilypadsFirst(row, column);
                }   
            }
        }
    }
    //creates method that fills the gameboard fir the rest of the game 
    public static void fillGameBoard (int row, int column) {
        //for each row, according to the size, that many columns are created
        for (row = 0; row<gameTiles.length; row++) {
            for (column = 0; column < gameTiles.length; column++) {
                //each row and column the index is filled with 'W'
                gameTiles[row][column] = 'W';
                //go to the all lilypads method which puts lilypads around based on the frog position
                allLilypads();
                //if the row and column isn't equal to the one that the player wants to move the frog too
                //and that same row and column also has a frog 
                if (gameTiles[row][column] != gameTiles[moveRow][moveCol] && gameTiles[row][column] == 'F') {
                    //replace the frog that was put there with a lilypad 
                    gameTiles[row][column] = 'L';    
                }    
            }
        }
    }
    //prints the filled gameboard onto console
    public static void gameBoard () {
    //creates a game board using a for loop that continues to until it reaches the length of the array
        for (row = 0; row<gameTiles.length; row++) {
            for (column = 0; column < gameTiles.length; column++) {
                c.print (gameTiles[row][column] + " ");   
            }
            c.println ();
        }
    }
    //uses if statements to assembles all the different lilypad postions based on the spot the frog it moved too 
    public static void allLilypads () {
        //if the row that the player wants to move to is equal to the last row (where the frog starts)
        //and the column is not equal to the first and last column
        if (moveRow == gameTiles.length-1 && moveCol != 0 && moveCol != gameTiles.length-1) { 
            //put lilypads around the frog (to the left, right and 3 spots above the frog)
            lilypadsFirst(moveRow, moveCol);
        }
        //else if the row that player wants to move to is equal to topmost row (where frog finishes)
        //and the column is not equal to the first and last column
        else if (moveRow == 0 && moveCol != gameTiles.length-1 && moveCol != 0) {
            //put lilypads around the frog (to the left, right, and 3 spots below the frog)
             lilypadsLast(moveRow, moveRow);
        }
        //if the column is equal to 0 (first column)
        //and the row is not equal to the first and last row 
        else if (moveCol == 0 && moveRow != gameTiles.length-1 && moveRow != 0) {
            //put lilypads around the frog (up, down, and 3 spots to the right of the frog)
            lilypadsColLeft(moveRow, moveCol);
        }
        //if the column is equal to the last column 
        //and the row is not equal to the first and last row 
        else if (moveCol == gameTiles.length-1 && moveRow != gameTiles.length-1 && moveRow != 0) {
            //put lilypads around the frog (up, down, and 3 spots to the left of the frog)
            lilypadsColRight(moveRow, moveCol);
        }
        //if row is the starting row and the column is 0 (first column)
        else if (moveRow == gameTiles.length-1 && moveCol == 0) {
            //put lilypads around the frog (up, and two spots to the right of the frog)
            lilypadsBottomL(moveRow, moveCol);
        }
        //if the row is the starting row and the last column 
        else if (moveRow == gameTiles.length-1 && moveCol == gameTiles.length-1) {
            //put lilypads around the frog (up, and two spots to the left of the frog 
            lilypadsBottomR(moveRow, moveCol);
        }
        //if it is the last (topmost) row and the first column
        else if (moveRow == 0 && moveCol == 0) {
            //put lilypads around the frog(down, and two spots to the right of the frog)
            lilypadsTopL(moveRow, moveCol);
        }
        //if the row is either the last row and last column or the row is the last row and first column 
        else if (moveRow == 0 && moveCol == gameTiles.length-1) {
            //put lilypads around the frog(down, and two spots to ;eft of the frog)
            lilypadsTopR(moveRow, moveCol);
        }
        //otherwise, for all other middle sections 
        else {
            //put lilypads all around the frog (basically a square around the frog
            lilypads(moveRow, moveCol);
        }  
    }
    //organizes lilypad placement if frog is in starting row
    public static void lilypadsFirst (int row, int column) {
        //if the row the frog it at and column one to the left of the frog is not an apple
        if (gameTiles[row][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column-1] = 'L';
        }
        //if the column the frog it at and one row above the frog is not an apple
        if (gameTiles[row-1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column] = 'L';
        }
        //if one row up and one column left of the frog is not an apple
        if (gameTiles[row-1][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column-1] = 'L';
        }
        //if one row up and one column left of the frog is not an apple 
        if (gameTiles[row-1][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column+1] = 'L';
        }
        //bug: despite writing the line below a lilypad is not generated to the right of the frog
        //if the row the frog is at and one column to the left of the frog is not an apple
        if (gameTiles[row][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column+1] = 'L';
        }
}
    //organizes lilypad placemnet if frog is in the starting right corner
    public static void lilypadsBottomR (int row, int column) {
        //if the row the frog is at and one column to the left of the frog is not an apple 
        if (gameTiles[row][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column-1] = 'L';
        }
        //if the column the frog it at and one row above the frog is not an apple
        if (gameTiles[row-1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column] = 'L';
        }
        //if one row above the frog and one column to the left of the frog is not an apple
        if (gameTiles[row-1][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column-1] = 'L';
        }
    }
    //organizes lilypad placement if the frog is in the starting left corner
    public static void lilypadsBottomL (int row, int column) {
        //if the row the frog is at and one column to the right of the frog is not an apple
        if (gameTiles[row][column+1] != 'A') {
            //put a lilypad in that spot 
            gameTiles[row][column+1] = 'L';
        }
        //if the column the frog is at and one row above of the frog is not an apple
        if (gameTiles[row-1][column] != 'A') {
            //put a lilypad in that spot 
            gameTiles[row-1][column] = 'L';
        }
        //if one row above and one column to the right of the frog is not an apple
        if (gameTiles[row-1][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column+1] = 'L';
        }
    }
    //organizes lilypad placement if the frog is in the ending left corner
    public static void lilypadsTopL (int row, int column) {
        //if the column the frog is at and one row below the frog is not an apple
        if (gameTiles[row+1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column] = 'L';
        }
        //if one row below and one column to the right of the frog is not an apple
        if (gameTiles[row+1][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column+1] = 'L';
        }
        //if the row the frog is at and one column to the right of the frog is not an apple
        if (gameTiles[row][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column+1] = 'L';
        }
    }
    //organizes lilypad placement if the frog is in the ending right corner
    public static void lilypadsTopR (int row, int column) {
        //if the row the frog is at and one column to the left of the frog is not an apple
        if (gameTiles[row][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column-1] = 'L';
        }
        //if the column the frog it at and one row below the frog is not an apple
        if (gameTiles[row+1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column] = 'L';
        }
        //if one row below the frog and one column to the left of the frog is not an apple
        if (gameTiles[row+1][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column-1] = 'L';
        }
    }
    //organizes lilypad placement if the frog is in the last (ending) row
    //Bug: this is showing an array out of bound -1 error
    public static void lilypadsLast (int row, int column) {
        //if the row the frog is at and one column to the left of the frog is not an apple
        if (gameTiles[row][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column-1] = 'L';
        }
        //if the column the frog it at and one row below the frog is not an apple
        if (gameTiles[row+1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column] = 'L';
        }
        //if one row below the frog and one column to the left is not an apple
        if (gameTiles[row+1][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column-1] = 'L';
        } 
        //if one row below the frog and one column to the right of the frog is not an apple
        if (gameTiles[row+1][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column+1] = 'L';
        }  
        //if the row the frog is at and one column to the right of the frog is not an apple
        if (gameTiles[row][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column+1] = 'L';
        }
    }
    //organizes lilypad placement if the frog is in the last (rightmost) column 
    //and not in the corners of that column
    public static void lilypadsColRight(int row, int column) {
        //if the column the frog is at and one row below the frog is not an apple
        if (gameTiles[row+1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column] = 'L';
        }
        //if the column the frog is at and one row above the frog is not an apple
        if (gameTiles[row-1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column] = 'L';
        }
        //if the row the frog is at and one column to the left of the frog is not an apple
        if (gameTiles[row][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column-1] = 'L';
        }
        //if one row below the frog and one column to the left of the frog is not an apple
        if (gameTiles[row+1][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column-1] = 'L';
        }
        //if one row below the frog and one column to the left of the frog is not an apple
        if (gameTiles[row-1][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column-1] = 'L';
        }
    }
    //organizes lilypad placement if the frog is in the first (leftmost) column
    //and not in the corners of that column
    public static void lilypadsColLeft(int row, int column) {
        //if the column the frog is at and one row below the frog is not an apple
        if(gameTiles[row+1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column] = 'L';
        }
        //if the column the frog is at and one row above the frog is not an apple 
        if (gameTiles[row-1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column] = 'L';
        }
        //if the row the frog is at and one column to the right of the frog is not an apple
        if (gameTiles[row][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column+1] = 'L';
        }
        //if one row below the frog and one column to the right of the frog is not an apple 
        if (gameTiles[row+1][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column+1] = 'L';
        }
        //if one row above the frog and one column to the right of the frog is not an apple
        if (gameTiles[row-1][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column+1] = 'L';
        }
     }
    //organizes lilpad placemnet all around the board when the frog can be surrounded by all sides 
    public static void lilypads (int row, int column) {
        //if the row the frog is at and one column to the left of the frog is not an apple
        if (gameTiles[row][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row][column-1] = 'L';
        }
        //if the column the frog is at and one row above the frog is not an apple
        if (gameTiles[row-1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column] = 'L';
        }
        //if one row above the frog and one column to the left of the frog is not an apple
        if (gameTiles[row-1][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column-1] = 'L';   
        } 
        //if one row above the frog and one column to the right of the frog is not an apple
        if (gameTiles[row-1][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row-1][column+1] = 'L';
        }
        //if the row the frog is at and one column to the right of the frog is not an apple
        if (gameTiles[row][column+1]!= 'A') {
            //put a lilypad in that spot
            gameTiles[row][column+1] = 'L';
        }
        //if the column the frog is at and one row below the frog is not an apple
        if (gameTiles[row+1][column] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column] = 'L';    
        }
        //if one row below the frog and one column to the right of the frog is not an apple
        if (gameTiles[row+1][column+1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column+1] = 'L';     
        }
        //if one row below the frog and one column to the left of the frog is not an apple
        if (gameTiles[row+1][column-1] != 'A') {
            //put a lilypad in that spot
            gameTiles[row+1][column-1] = 'L';           
        }
    }    
    public static void apples (int appleNum) {
        //until i is not equal to the number of apples that the level has 
        for (int i = appleNum; i>0; i--) {
            //keep generating a random row and column until the spot at that row and column is not a W
            do {
            //calls the random variable method to generate a random row and column with the size-1 as the parameter
                row = randomVariables(size-1);
                column = randomVariables (size-1);
            } while (gameTiles[row][column] != 'W');
            //when it is a W replace the water with an apple 
                gameTiles[row][column] = 'A'; 
        } 
    }
    public static int randomVariables (int numSize) {
      //generatings a random number between 0 and numSize (can change numSize as levels progess)
        Random r = new Random ();
        return r.nextInt(numSize);
}
    //gives user a math question and returns the player's answer 
    public static int mathQuestions (int term1, int term2) { 
    c.println (term1 + " + " + term2);
    //asks user for answer and prints correct if the answer is correct
    int ans = c.readInt();
    return ans;
}
    //allows player to make move the frog onto a lilypad, answer the math question, and get apples 
    public static void move () {
        //the starting row is the last row and the starting column is the middle column
        int previousRow = gameTiles.length-1, previousCol = size/2;
        //while the number of apples collected isn't equal to number of apples placed...
        do {
            //asks user to print lilypads by giving them the previous row and column to help them
            c.println("Pick which lilypad you want go on in the " + size + " by " + size + " board."
                    + "Enter row and then column (the frog is at row " + previousRow + ", column " + previousCol + ")");
            //stores row and column that player wants to move frog too
            moveRow = c.readInt();
            moveCol = c.readInt();
            
            //if the spot player wants to move to is an L or an A, then...
            if (gameTiles[moveRow][moveCol] == 'L' || gameTiles[moveRow][moveCol] == 'A') {
                //stores the variables in previous row and previous column to print on screen on next iteration 
                previousRow = moveRow; previousCol = moveCol;
                //calls method that asks math questions 
                // prints two terms that create random numbers
                int term1 = randomVariables (numSize);
                int term2 = randomVariables (numSize);
                //if the answer that is returned from the math questions method is equal to the sum of both term
                //then answer is correct and moves the frog to that place and prints frog in that position 
                if (mathQuestions (term1, term2) == (term1 + term2)) {
                    c.println ("Correct!"); 
                    //if the row and column the frog is moved too has an apple
                    if (gameTiles[moveRow][moveCol] == 'A') {
                        //adds 1 to the number of apples collected and prints it
                        applesCollected++;
                        c.println ("You got an apple! You now have " + applesCollected + " apples!");
                        //subtracts the number of apples collected from the total number of apples in the game 
                        applesLeft = appleNum-applesCollected;
                        //prints how many apples are left to be collected 
                        c.println ("You still have to collect " + applesLeft + " left to collect!");
                    }
                    //calls the correctAns method which fills the new frog positions with lilypads
                    correctAns();
                    //the new row and column player chose is replaced with the frog 
                    gameTiles[moveRow][moveCol] = 'F';
                    //prints the gameboard with all the changed features 
                    gameBoard ();
                }   
                //otherwise prints the board again with the move being done but changes position of apples
                else {
                    c.println("Yikes, looks like the apples are gonna have to change places :(");
                    //
                    gameTiles[previousRow][previousCol] = gameTiles[moveRow][moveCol];
                    //fills gameboard with lilypads and water
                    fillGameBoard(moveRow, moveCol);
                    //if there are 0 apples left then that means a new level as started
                    //so the apples should be the amount of apple in that round 
                    if (applesLeft == 0) {
                        apples(appleNum);
                    }
                    //otherwise...
                    else {
                    //randomly switches apple postions only for the amount of apples left  
                    apples(applesLeft);
                    }
                    //switches the positions of the frog
                    gameTiles[moveRow][moveCol] = 'F';
                    //prints the gameboard with new positions 
                    gameBoard();
                }
            }
            //otherwise, if the position player wants to move to a tile that has water then...
            else {
                //prints invalid move and user tries again by hitting continue 
                c.println ("Invalid move. Try again!.");
            }
        } while (applesCollected != appleNum);
    }
    //allows lilypads to move with the frog to the next position while old lilypads turn into water
    public static void correctAns () {
        //if the answer is correct then fills the previous lilypads with water 
        //and calls the allLilypads methods which will put lilypads around the frog based on its placement 
        for (row = gameTiles.length-1; row>=0; row--) {
            for (column = gameTiles.length-1; column>=0; column--) {
                if (gameTiles[row][column] == 'L') {
                    gameTiles[row][column] = 'W';
                }  
                allLilypads();
            }

        }
    }
    //creates method that adds levels to the game 
    public static void nextLevel () {
            //loops and increases levels until the player doesn't type 2 which means exit 
            do {
                //prints congratulations for passing level and then 1 to continue to next level and 2 to exit 
                c.println ("Congratulations! You passed Level " + level + "!");
                c.println ("Type 1 to continue and 2 to exit");
                next = c.readInt();
                //if user types one...
                if (next == 1) {
                    //clear screen
                    c.clear();
                    //increase the size by 10 which means the board size would also increase my 10
                    size+=10;
                    //increase the amount of apples that need to be collected by 5
                    appleNum+=5;
                    //increase the number in between which the addition questions are asked by 20
                    numSize+=20;
                    //create new board with the increased size 
                    gameTiles = new char [size][size];
                    //the level increases by one, meaning the player has made it to the next level 
                    level++;
                    //meant to be where data will be serlialized (to see rough code go to levelData class)
//                    dataSer();
                    //fill the game board with water, lilypads and apples 
                    fillFirstGameBoard();
                    //generates randomly positioned apples 
                    apples(appleNum);
                    //prints the game board
                    gameBoard ();
                    //calls the move method where player can move the frog and get apples
                    move();
                    
                }
                //if the user doesn't type in 1 or 2
                else if (next != 1 && next!= 2) {
                    //then asks them to try again 
                    c.println ("Invalid option. Try again");
                }
            } while (next != 2);
              //meant to be where data will be serlialized (to see rough code go to levelData class)
//                    dataSer();
            c.println ("Goodbye!");
        }


    /**
     * * MAIN METHOD
     *
     **
     * @param args
     */
    public static void main(String[] args) {

        //FIRST WRITE YOUR PSEUDOCODE USING COMMENTS, THEN FILL IN WITH CODE
        System.out.println("Starting...");
        //fills the initial game board with water, frog and lilypads
        fillFirstGameBoard();
        //generates randomly positioned apples 
        apples(appleNum);
        //prints the game board
        gameBoard ();
        //assembles all game pieces into the move method
        //this is where the player plays the actual game 
        move();
        //the move method ends when the player has collected all the apples, thus finished the level
        //calls the next level method which changes certain features and prints the next level of the game 
        nextLevel();
        }
    }


