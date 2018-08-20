package game.enemyfollow;

import action.ActionAdapter;
import action.LimitAction;
import action.SequenceAction;
import action.WaitAction;
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
        this.configAction();
    }

    public void configAction() {
        this.addAction(
                new LimitAction(70,
                        new SequenceAction(
                                new WaitAction(400),
                                new ActionAdapter() {
                                    @Override
                                    public boolean run(GameObject owner) {
                                        EnemyFollow enemyFollow = GameObjectManager.instance.recycle(EnemyFollow.class);
                                        enemyFollow.position.set(random.nextInt(1024), random.nextInt(600));
                                        return true;
                                    }
                                }
                        )
                )
        );
    }
}
