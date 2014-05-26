package org.group4.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	//画背景图片
    @Override
    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            BufferedImage image1 = ImageIO.read(new File("res/bg.jpg"));
            Dimension size = this.getSize();
            Image image2 = image1.getScaledInstance((int)size.getWidth(), (int)size.getHeight(), Image.SCALE_DEFAULT);
            g.drawImage(image2, 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
