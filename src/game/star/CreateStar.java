package game.star;

import action.ActionAdapter;
import action.RepeatActionForever;
import action.SequenceAction;
import action.WaitAction;
import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;

import java.util.Random;

public class CreateStar extends GameObject {

    private Random random;

    public CreateStar() {
        this.random = new Random();
        this.configAction();
    }

    public void configAction() {
        this.addAction(
                new RepeatActionForever(
                        new SequenceAction(
                                new WaitAction(30),
                                new ActionAdapter() {
                                    @Override
                                    public boolean run(GameObject owner) {
                                        Star star = GameObjectManager.instance.recycle(Star.class);
                                        star.position.set(1024, random.nextInt(600));
                                        star.velocity.set(random.nextInt(3) + 1, 0);
                                        return true;
                                    }
                                }
                        )
                )
        );
    }
}