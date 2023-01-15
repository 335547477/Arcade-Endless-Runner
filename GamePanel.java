/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package learningtoprogram;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author dharm
 */
public class GamePanel extends javax.swing.JPanel {
        //list of variables that are going to be used throughout the methods 
    //size of the first board will start being 10
    public static int size = 10;
     //creates board with varibale size x size which can be changed later on per level 
    public static char[][] gameTiles = new char [size][size];
    public static int moveRow;
    public static int moveCol;
    public static int row;
    public static int column;
    //the number of apples will always start at 4 
    public static int appleNum=4;
    //the number of apples collected will start at 0
    public static int applesCollected = 0;
    public static int numSize;
    //the game by default will start at level 1 
    public static int level = 1;
    public static int next;
   //connects game panel to frame
    JPanel myParent; 
   //initializes variables to add images into panel 
    Image water;
    Image frog;
    Image lilypad;
    Image apple;
    /** Creates new form GamePanel */
    public GamePanel(JPanel p) {
        initComponents();
        myParent = p;
        //gets image of water from files and scales it to fit the page
        water  = Toolkit.getDefaultToolkit().getImage("onlyWater.jpg").getScaledInstance(620, 350, Image.SCALE_DEFAULT);
        //gets image with frog already on lilypad and sets size 
        frog = Toolkit.getDefaultToolkit().getImage("topViewFrog.png").getScaledInstance(80,80, Image.SCALE_DEFAULT);
        //gets image with apple already on lilypad and sets size
        apple = Toolkit.getDefaultToolkit().getImage("appleOnLilypad.png").getScaledInstance(50,50,Image.SCALE_DEFAULT);
        //gets image of lilypad and sets size
        lilypad = Toolkit.getDefaultToolkit().getImage("lilypad.png"). getScaledInstance(50,50, Image.SCALE_DEFAULT);
    }
    //create a random number between 0 and variable 
    public static int randomVariables (int numSize) {
      //generatings a random number between 0 and numSize (can change numSize as levels progess)
        Random r = new Random ();
        return r.nextInt(numSize)+1;
}
    //method that allows graphics to be printed to the panel
    public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(water, 0, 0, this);
    g.drawImage(frog, 0,0,this);
    //"draws" an apple image at a random width and height based on how many apples are in that round 
    for (int i = 0; i<appleNum; i++) {
        g.drawImage(apple,randomVariables(getWidth()),randomVariables(getHeight()),(this));
    }
    g.drawImage(lilypad,0,0,this);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(620, 350));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
