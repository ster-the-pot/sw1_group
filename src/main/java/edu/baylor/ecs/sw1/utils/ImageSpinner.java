package edu.baylor.ecs.sw1.utils;

public class ImageSpinner extends Thread implements Runnable {
	RotateableImage image;
    final float totalDegrees;
    float degrees;
    float speed; // in degrees per second
    public ImageSpinner(RotateableImage image, float degrees, float speed) {
        this.image = image;
        this.degrees = degrees;
        this.totalDegrees = degrees;
        this.speed = speed;
    }
    static boolean running = true;
    public void setRunning() {
    	running = false;
    }
    
    public void run() {
        int fps = 40;
        while(running) { 
            float degreesToRotate = speed / fps;
            image.spin(degreesToRotate);
            image.repaint();
            degrees -= degreesToRotate;
            try { Thread.sleep(1000 / fps); } catch(InterruptedException e) { }
        }
    }
}
