package edu.baylor.ecs.sw1.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class RotateableImage extends JPanel {
    BufferedImage image;
    float currentDegrees;
    float remainingDegrees;

    public RotateableImage(BufferedImage image) {
        this.image = image;
        this.currentDegrees = 0.0f;
        this.remainingDegrees = 0.0f;
    }

    @Override
    public void paintComponent(Graphics g) {
    	AffineTransform at = AffineTransform.getTranslateInstance(14, 10);
    	at.rotate(Math.toRadians(currentDegrees), image.getWidth()/2,image.getHeight()/2);
    	
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.drawImage(image, at, null);
 
    	repaint();
    }	
   
    public void spin(float additionalDegrees) {
    	if(currentDegrees + additionalDegrees > 360) {
    		setSpin(additionalDegrees - (360-currentDegrees));
    	} else if(currentDegrees + additionalDegrees < 0) {
    		setSpin(currentDegrees + 360 - additionalDegrees);
    	} else {
    		setSpin(currentDegrees + additionalDegrees);
    	}
    	
    	
    }

    public void setSpin(float newDegrees) {
    	currentDegrees = newDegrees;
    	repaint();
    }

}