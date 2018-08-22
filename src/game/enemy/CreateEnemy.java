package game.enemy;

import action.*;
import base.GameObject;
import base.GameObjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateEnemy extends GameObject {

    private Random random;

    public CreateEnemy() {
        this.random = new Random();
        this.configAction();
    }

    public void configAction() {
        List<Enemy> enemies = new ArrayList<>();
        Action createAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                Enemy enemy = GameObjectManager.instance.recycle(Enemy.class);
                enemy.position.set(random.nextInt(1024), random.nextInt(600));
                enemy.velocity.set(random.nextInt(2) + 1, random.nextInt(2) + 1);
                enemies.add(enemy);
                return true;
            }
        };

        Action waitAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                enemies.removeIf(enemy -> !enemy.isAlive);
                return true;
            }
        };

        Action stopAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).velocity.set(0, 0);
                }
                return true;
            }
        };

        Action shootAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                EnemyShoot enemyShoot = new EnemyShoot();
                for (int i = 0; i < enemies.size(); i++) {
                    enemyShoot.run(enemies.get(i));
                }
                return true;
            }
        };

        Action continueAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).velocity.set(random.nextInt(2) + 1, random.nextInt(2) + 1);
                }
                return true;
            }
        };

        this.addAction(
                new SequenceAction(
                        new WaitAction(20),
                        new LimitAction(4,
                                new SequenceAction(
                                        createAction, new WaitAction(40),
                                        stopAction, new WaitAction(40),
                                        shootAction, new WaitAction(40),
                                        continueAction, new WaitAction(40),
                                        waitAction
                                )
                        )
                )
        );
    }
}
