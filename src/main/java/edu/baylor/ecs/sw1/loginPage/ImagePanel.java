package edu.baylor.ecs.sw1.loginPage;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * Basic Panel to render and scale background images
 */
public class ImagePanel extends JPanel {
	Image bImage;
	private final static Logger LOG = Logger.getLogger(ImagePanel.class.getName());

	public ImagePanel(String img) {
		try {
			this.bImage = ImageIO.read(new File(img));
		}catch(IOException e) {
			LOG.severe("Image file not Found");
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bImage.getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_SMOOTH), 0, 0, null);
	}

}
