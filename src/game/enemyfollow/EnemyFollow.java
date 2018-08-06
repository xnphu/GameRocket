package game.enemyfollow;

import base.GameObject;
import base.Vector2D;
import renderer.ImageRenderer;

public class EnemyFollow extends GameObject {

    public Vector2D velocity;

    public EnemyFollow() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
    }

    @Override
    public void update() {
        super.update();
        this.velocity.set(
                position
                        .subtract(this.position)
                        .normalized()
        ).multiply(1.5f);

    }
}
