import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    public BufferedImage image;

    public int []xPoints = new int[3];
    public int []yPoints = new int[3];

    public int velocity;

    public Player() {

    }

//    public void run() {
//        this.x -= this.velocityX;
//    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.CYAN);
//        graphics.drawPolygon(this.xPoints, this.yPoints, 3);
        graphics.fillPolygon(this.xPoints, this.yPoints, 3);
    }


}