import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameCanvas extends JPanel {

    private BufferedImage backBuffered;
    private Background background = new Background();


    public Player player;
    public CreateStar createStar = new CreateStar();
    public CreateEnemy createEnemy = new CreateEnemy();
    public CreateEnemyFollow createEnemyFollow = new CreateEnemyFollow();

    private Graphics graphics;

    private FrameCounter frameCounter;

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
        this.setupPlayer();
    }

    private void setupPlayer() {
        this.player = new Player();
        this.player.position.set(200, 300);
        this.player.velocity.set(3.5f, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {

        this.background.render(this.graphics);

        this.createStar.render(this.graphics);

        this.player.render(this.graphics);

        this.createEnemy.render(this.graphics);

        this.createEnemyFollow.render(this.graphics);

        this.repaint();
    }

    public void runAll() {

        this.createStar.run();

        this.createEnemy.run();

        this.player.run();

        this.createEnemyFollow.run();
        this.createEnemyFollow.updateVelocity(this.player.position);
    }
}