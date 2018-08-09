package game.enemyfollow;

import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;

import java.util.Random;

public class CreateEnemyFollow extends GameObject {

    private Random random;
    private FrameCounter frameCounter;

    public CreateEnemyFollow() {
        this.random = new Random();
        this.frameCounter = new FrameCounter(400);
    }

    @Override
    public void run(){
        super.run();
        if (this.frameCounter.run()) {
            EnemyFollow enemyFollow = new EnemyFollow();
            enemyFollow.position.set(this.random.nextInt(1024), this.random.nextInt(600));
            enemyFollow.velocity.set(this.random.nextInt(3) + 1, this.random.nextInt(3) + 1);

            GameObjectManager.instance.add(enemyFollow);
            this.frameCounter.reset();
        }
    }
}
