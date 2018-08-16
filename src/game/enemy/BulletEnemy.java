package game.enemy;

import base.GameObject;
import base.Vector2D;
import physic.BoxCollider;
import physic.PhysicBody;
import renderer.ImageRenderer;

public class BulletEnemy extends GameObject implements PhysicBody {

    public Vector2D velocity;

    public BoxCollider boxCollider;

    public BulletEnemy() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 5, 5);
        this.boxCollider = new BoxCollider(5, 5);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);

        this.boxCollider.position.set(this.position.x - 2.5f, this.position.y - 2.5f);

    }

    @Override
    public void getHit(GameObject gameObject) {
        this.isAlive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}


