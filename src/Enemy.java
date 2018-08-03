import java.awt.*;

public class Enemy {

    public Vector2D position;
    public Vector2D velocity;

    public Renderer renderer;
    public EnemyShoot enemyShoot;


    public Enemy() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
        this.enemyShoot = new EnemyAttack();
    }

    public void run() {
        this.position.addUp(this.velocity);
        this.enemyShoot.run(this);
    }


    public void render(Graphics graphics) {
        this.renderer.render(graphics, position);
        ((EnemyAttack) this.enemyShoot)
                .bulletEnemies
                .forEach(bulletEnemy -> bulletEnemy.render(graphics));

    }
}