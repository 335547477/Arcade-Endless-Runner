/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learningtoprogram;
 import java.awt.Graphics;  
 import java.awt.Image;  
 import java.awt.Toolkit; 
import java.awt.CardLayout;
 import javax.swing.JPanel;
/**
 *
 * @author dharm
 */
public class StartUpPanel extends javax.swing.JPanel {
    JPanel myParent = null;

public void paintComponent(Graphics g) {  
                      Image img = Toolkit.getDefaultToolkit().getImage("ICS3U Game Menu.jpg");  
                      g.drawImage(img, 0, 0, this);  
                } 
    /**
     * Creates new form StartUpPanel
     */
    public StartUpPanel(JPanel p) {
        initComponents();
        myParent= p;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playButton = new javax.swing.JButton();
        howToPlayButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(602, 356));
        setMinimumSize(new java.awt.Dimension(602, 356));
        setPreferredSize(new java.awt.Dimension(602, 356));

        playButton.setBackground(new java.awt.Color(153, 204, 255));
        playButton.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        playButton.setForeground(new java.awt.Color(255, 0, 0));
        playButton.setText("PLAY");
        playButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        playButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playButton.setMaximumSize(null);
        playButton.setMinimumSize(null);
        playButton.setPreferredSize(null);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        howToPlayButton.setBackground(new java.awt.Color(153, 204, 255));
        howToPlayButton.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        howToPlayButton.setForeground(new java.awt.Color(255, 0, 0));
        howToPlayButton.setText("HOW TO PLAY");
        howToPlayButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        howToPlayButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        howToPlayButton.setPreferredSize(null);
        howToPlayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                howToPlayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(208, Short.MAX_VALUE)
                .addComponent(howToPlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(howToPlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        // TODO add your handling code here:
        CardLayout  cl = (CardLayout)myParent.getLayout();
        cl.show(myParent, "game");

    }//GEN-LAST:event_playButtonActionPerformed

    private void howToPlayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howToPlayButtonActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout)myParent.getLayout();
        cl.show(myParent, "rules");
    }//GEN-LAST:event_howToPlayButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton howToPlayButton;
    private javax.swing.JButton playButton;
    // End of variables declaration//GEN-END:variables
}