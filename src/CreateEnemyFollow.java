import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateEnemyFollow {

    private Random random = new Random();

    private List<EnemyFollow> enemyFollows = new ArrayList<>();
    ;
    private int timeIntervalEnemyFollow = 0;

    public void run() {
        if (this.timeIntervalEnemyFollow == 300) {
            EnemyFollow enemyFollow = new EnemyFollow();
            enemyFollow.position.set(this.random.nextInt(1024), this.random.nextInt(600));
            enemyFollow.velocity.set(this.random.nextInt(3) + 1, this.random.nextInt(3) + 1);
            this.enemyFollows.add(enemyFollow);
            this.timeIntervalEnemyFollow = 0;
        } else {
            this.timeIntervalEnemyFollow += 1;
        }
        this.enemyFollows.forEach(enemyFollow -> enemyFollow.run());
    }

    public void updateVelocity(Vector2D vector2D) {
        this.enemyFollows.forEach(enemyFollow -> enemyFollow.update(vector2D));
    }

    public void render(Graphics graphics) {
        this.enemyFollows.forEach(enemyFollow -> enemyFollow.render(graphics));
    }
}

