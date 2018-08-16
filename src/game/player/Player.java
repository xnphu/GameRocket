package game.player;

import base.GameObject;
import base.Vector2D;
import game.enemy.BulletEnemy;
import game.enemy.Enemy;
import game.enemyfollow.EnemyFollow;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.PolygonRenderer;

import java.awt.*;

public class Player extends GameObject implements PhysicBody {

    public Vector2D velocity;

    public double angle = 0.0;

    public BoxCollider boxCollider;

    private RunHitObject runHitObject;

    public Player() {
        this.velocity = new Vector2D();
        this.renderer = new PolygonRenderer(Color.CYAN, new Vector2D(), new Vector2D(0, 16), new Vector2D(20, 8));
        this.boxCollider = new BoxCollider(20, 16);
        this.runHitObject = new RunHitObject(
                Enemy.class, EnemyFollow.class, BulletEnemy.class
        );

        this.attributes.add(new PlayerShoot());
        this.attributes.add(new PlayerMove());

    }

    @Override
    public void run() {
        super.run();
        ((PolygonRenderer) this.renderer).angle = this.angle;

        this.boxCollider.position.set(this.position.x - 10, this.position.y - 8);

        this.runHitObject.run(this);
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