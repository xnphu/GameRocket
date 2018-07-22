import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameCanvas extends JPanel {

    private BufferedImage starImage;
    private BufferedImage backBuffered;
    private BufferedImage playerImage;
    private BufferedImage enermyImage;

    public int positionXStar = 1024;
    public int positionYStar = 200;

    public int positionXPlayer = 600;
    public int positionYPlayer = 200;

    public int positionXEnermy = 500;
    public int positionYEnermy = 300;

    private int moveX = 5;
    private int moveY = 5;

    private Graphics graphics;
    public static Random rd = new Random();


    public GameCanvas() {

        this.setSize(1024, 600);

        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        this.graphics = this.backBuffered.getGraphics();

        // load anh
        try {
            this.starImage = ImageIO.read(new File("resources/images/star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.playerImage = ImageIO.read(new File("resources/images/circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.enermyImage = ImageIO.read(new File("resources/images/circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // draw

        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);

    }

    public void renderAll() {
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);

        this.graphics.drawImage(this.starImage, this.positionXStar, this.positionYStar, 30, 30, null);

        this.graphics.drawImage(this.playerImage, this.positionXPlayer, this.positionYPlayer, 20, 20, null);

        this.graphics.drawImage(this.enermyImage, this.positionXEnermy, this.positionYEnermy, 15, 15, null);

        //di chuyen Player
        if(this.positionYPlayer < 0) {
            this.positionYPlayer = 580;
            this.positionXPlayer = rd.nextInt(1000);
        }
        if(this.positionYPlayer > 580) {
            this.positionYPlayer = 0;
            this.positionXPlayer = rd.nextInt(1000);
        }

        if(this.positionXPlayer < 0) {
            this.positionXPlayer = 1000;
            this.positionYPlayer = rd.nextInt(580);
        }
        if(this.positionXPlayer > 1000) {
            this.positionXPlayer = 0;
            this.positionYPlayer = rd.nextInt(580);
        }

        //di chuyen Star
        if(this.positionXStar < 0) {
            this.positionXStar = 1000;
            this.positionYStar = rd.nextInt(580);
        }

        //di chuyen Enermy
        if(this.positionXEnermy < 0 || this.positionXEnermy > 1000) moveX = -moveX;
        if(this.positionYEnermy < 0 || this.positionYEnermy > 580) moveY = -moveY;
        this.positionXEnermy += moveX;
        this.positionYEnermy += moveY;

        this.repaint();
    }
}