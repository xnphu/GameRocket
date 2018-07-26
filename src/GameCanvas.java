import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {

    private List<Star> stars;
    private List<Enermy> enermies;
    public Player player = new Player();

    private BufferedImage backBuffered;
    private BufferedImage playerImage;

//    public int positionXPlayer = 600;
//    public int positionYPlayer = 200;

    private Graphics graphics;

    private Random random = new Random();

    private int timeIntervalStar = 0;
    private int timeIntervalEnermy = 0;


    public GameCanvas() {

        this.setSize(1024, 600);

        this.setupBackBuffered();

        this.setupCharacter();

        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        this.setupStar();
        this.setupEnermy();
        this.setupPlayer();
//        this.playerImage = this.loadImage("resources/images/circle.png");
    }

    private void setupStar() {
        this.stars = new ArrayList<>();
    }

    private void setupEnermy() {
        this.enermies = new ArrayList<>();
    }

    private void setupPlayer() {
        this.player.xPoints[0] = this.random.nextInt(1000);
        this.player.yPoints[0] = this.random.nextInt(580);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.renderBackground();

        this.stars.forEach(star -> star.render(graphics));

        this.enermies.forEach(enermy -> enermy.render(graphics));

//        this.graphics.drawImage(this.playerImage, this.positionXPlayer, this.positionYPlayer, 20, 20, null);

        this.player.render(graphics);

        this.repaint();
    }

    public void runAll() {
        this.createStar();
        this.stars.forEach(star -> star.run());
        this.createEnermy();
        this.enermies.forEach(enermy -> enermy.run());
        this.createPlayer();
        this.playerMove();
    }

    private void createStar() {
        if (this.timeIntervalStar == 30) {
            Star star = new Star();
            star.x = 1024;
            star.y = this.random.nextInt(600);
            star.image = this.loadImage("resources/images/star.png");
            star.width = 15;
            star.height = 15;
            star.velocityX = this.random.nextInt(3) + 1;
            this.stars.add(star);
            this.timeIntervalStar = 0;
        } else {
            this.timeIntervalStar += 1;
        }

    }

    private void createEnermy() {
        if (this.timeIntervalEnermy == 188) {
            Enermy enermy = new Enermy();
            enermy.x = this.random.nextInt(1024);
            enermy.y = this.random.nextInt(600);
            enermy.image = this.loadImage("resources/images/circle.png");
            enermy.width = 8;
            enermy.height = 8;
            enermy.velocityX = this.random.nextInt(4) + 1;
            enermy.velocityY = this.random.nextInt(4) + 1;
            this.enermies.add(enermy);
            this.timeIntervalEnermy = 0;
        } else {
            this.timeIntervalEnermy += 1;
        }

    }

    private void createPlayer() {
//        Player player = new Player();
        this.player.xPoints[1] = this.player.xPoints[0] + 20;
        this.player.yPoints[1] = this.player.yPoints[0];

        this.player.xPoints[2] = this.player.xPoints[0] + 20 / 2;
        this.player.yPoints[2] = this.player.yPoints[0] - 17;

        this.player.velocity = 7;
    }

    public void playerMove() {
//        Player player = new Player();
        if (player.xPoints[0] > 1024 && player.xPoints[1] > 1024 && player.xPoints[2] > 1024) {
            player.xPoints[0] = 0;
            createPlayer();
        }
        if (player.xPoints[0] < 0 && player.xPoints[1] < 0 && player.xPoints[2] < 0) {
            player.xPoints[0] = 1024;
            createPlayer();
        }
        if (player.yPoints[0] > 600 && player.yPoints[1] > 600 && player.yPoints[2] > 600) {
            player.yPoints[0] = 0;
            createPlayer();
        }
        if (player.yPoints[0] < 0 && player.yPoints[1] < 0 && player.yPoints[2] < 0) {
            player.yPoints[0] = 600;
            createPlayer();
        }
    }

    private void renderBackground() {
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}