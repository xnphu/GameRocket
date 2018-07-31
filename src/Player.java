import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {

    public Vector2D position;
    public Vector2D velocity;

    private List<Vector2D> vertices;

    private Polygon polygon;

    public double angle = 0.0;

    private List<EnemyFollow> enemyFollows;
    private List<BulletPlayer> bulletPlayers;
    private int timeIntervalFollow;
    private int timeIntervalBullet;

    public Random random = new Random();

    public Player() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();

        this.vertices = Arrays.asList(
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(17, 8)
        );
        this.polygon = new Polygon();

        this.bulletPlayers = new ArrayList<>();
        this.enemyFollows = new ArrayList<>();
    }

    public void run() {
        this.position.addUp(this.velocity);
        this.shoot();
        this.follow();
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.CYAN);

        this.updateTriangle();

        graphics.fillPolygon(this.polygon);

        this.bulletPlayers.forEach(bulletPlayer -> bulletPlayer.render(graphics));
        this.enemyFollows.forEach(enemyFollow -> enemyFollow.render(graphics));
    }

    private void updateTriangle() {
        this.polygon.reset();
        Vector2D center = this.vertices
                .stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))
                .multiply(1.0f / (float) this.vertices.size())
                .rotate(this.angle);

        Vector2D translate = this.position.subtract(center);

        this.vertices
                .stream()
                .map(vector2D -> vector2D.rotate(angle))
                .map(vector2D -> vector2D.add(translate))
                .forEach(vector2D -> polygon.addPoint((int) vector2D.x, (int) vector2D.y));
    }

    public void follow() {
        if (this.timeIntervalFollow == 500) {
            EnemyFollow enemyFollow = new EnemyFollow();
//            try {
//                enemyFollow.image = ImageIO.read(new File("resources/images/circle.png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            enemyFollow.position.set(random.nextInt(1024), random.nextInt(600));
            this.enemyFollows.add(enemyFollow);
            this.timeIntervalFollow = 0;
        } else {
            this.timeIntervalFollow += 1;
        }

        this.enemyFollows.forEach(enemyFollow -> enemyFollow.run());
        this.enemyFollows.forEach(enemyFollow -> enemyFollow.updateVelocity(this.position));
    }

    private void shoot() {
        if (this.timeIntervalBullet == 25) {
            BulletPlayer bulletPlayer = new BulletPlayer();
//            try {
//                bulletPlayer.image = ImageIO.read(new File("resources/images/circle.png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            bulletPlayer.velocity = this.velocity.copy();
            bulletPlayer.position.set(this.position.x, this.position.y);
            bulletPlayer.velocity.multiply(10);
            this.bulletPlayers.add(bulletPlayer);
            this.timeIntervalBullet = 0;
        } else {
            this.timeIntervalBullet += 1;
        }

        this.bulletPlayers.forEach(bulletPlayer -> bulletPlayer.run());
    }
}