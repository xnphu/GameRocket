package game.player;

import base.Attribute;
import base.FrameCounter;
import base.GameObjectManager;

public class PlayerShoot implements Attribute<Player> {

    private FrameCounter frameCounter = new FrameCounter(100);

    @Override
    public void run(Player gameObject) {
        if (this.frameCounter.run()) {
            BulletPlayer bulletPlayer = GameObjectManager.instance.recycle(BulletPlayer.class);
            bulletPlayer.position.set(gameObject.position);
            bulletPlayer.velocity.set(gameObject.velocity.copy()).multiply(1.5f);
            this.frameCounter.reset();
        }
    }
}