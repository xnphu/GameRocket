package game.player;

import base.FrameCounter;
import base.GameObjectManager;
import base.KeyboardInput;

public class PlayerAttack implements PlayerShoot {

    private FrameCounter frameCounter = new FrameCounter(100);

    @Override
    public void run(Player player) {
        if (this.frameCounter.run()) {
            BulletPlayer bulletPlayer = new BulletPlayer();
            bulletPlayer.position.set(player.position);
            bulletPlayer.velocity.set(player.velocity.copy().rotate(KeyboardInput.instance.angle)).multiply(1.5f);

            GameObjectManager.instance.add(bulletPlayer);
            this.frameCounter.reset();
        }
    }
}
