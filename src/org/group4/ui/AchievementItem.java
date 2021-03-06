/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.ui;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Kam
 */
public class AchievementItem extends javax.swing.JPanel {

    /**
     * Creates new form AchievementItem
     */
    public AchievementItem(String title, String desc) {
        initComponents();
        ImageIcon image = new ImageIcon(new ImageIcon("res/badge.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        badgeImage.setIcon(image);
        badgeName.setText(title);
        badgeDesc.setText(desc);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        badgeImage = new javax.swing.JLabel();
        badgeDesc = new javax.swing.JLabel();
        badgeName = new javax.swing.JLabel();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(630, 80));

        badgeDesc.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N

        badgeName.setFont(new java.awt.Font("微软雅黑", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(badgeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(badgeDesc)
                    .addComponent(badgeName))
                .addContainerGap(530, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(badgeImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(badgeName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(badgeDesc)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel badgeDesc;
    private javax.swing.JLabel badgeImage;
    private javax.swing.JLabel badgeName;
    // End of variables declaration//GEN-END:variables
}
