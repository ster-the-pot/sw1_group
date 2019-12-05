package edu.baylor.ecs.sw1.myday;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import edu.baylor.ecs.sw1.loginPage.ImagePanel;
import edu.baylor.ecs.sw1.utils.ImageSpinner;
import edu.baylor.ecs.sw1.utils.RotateableImage;

public class SpinnerTest {
	static boolean waitingOnCanvas = false;
	
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame testFrame = new JFrame();
				testFrame.setVisible(true);
				testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				testFrame.pack();
				
				Thread cernyThread = new Thread() {
					public void run(){
						JDialog dialog = new JDialog(testFrame, "Please wait - Syncing with canvas.");
						
						BufferedImage before = null;
						try {
							before = ImageIO.read(new File("src/main/resources/cerny.png"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						int w = before.getWidth();
						int h = before.getHeight();
						System.out.println("W/H: " + w + " " + h);
						BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
						AffineTransform at = new AffineTransform();
						at.scale(1.0, 1.0);
						AffineTransformOp scaleOp = 
						   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
						bufferedImage = scaleOp.filter(before, bufferedImage);
						
						RotateableImage rot = new RotateableImage(bufferedImage);
						dialog.add(rot);
						dialog.setPreferredSize(new Dimension(300,300+10));
						dialog.pack();
						dialog.setVisible(true);
						
						GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
						
						Point centerPoint = env.getCenterPoint();
						int dx = centerPoint.x - dialog.getSize().width / 2;
						int dy = centerPoint.y - dialog.getSize().height / 2;
						
						dialog.setLocation(dx,dy);
						
						waitingOnCanvas = true;
					
						ImageSpinner spinner = new ImageSpinner(rot,1,10f);
						spinner.run();
						
						while(waitingOnCanvas) {
							
						}
			
						dialog.dispose();
					}
				};
				
				cernyThread.start();
				
			}
			
		});
		
		
		
	}
}
