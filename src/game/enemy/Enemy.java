package game.enemy;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import physic.BoxCollider;
import renderer.ImageRenderer;

import java.awt.*;

public class Enemy extends GameObject {

    public Vector2D velocity;

    public BoxCollider boxCollider;

    public Enemy() {
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(20, 20);
        this.attributes.add(new EnemyShoot());
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);

        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);

        Player player = GameObjectManager.instance.findPlayer();
        if(GameObjectManager.instance.checkcollision_4(this)) {
            if (player != null) {
                this.isAlive = false;
                player.isAlive = false;
            }
        }
    }
}
