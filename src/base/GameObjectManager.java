package base;

import game.enemy.BulletEnemy;
import game.enemy.Enemy;
import game.enemyfollow.EnemyFollow;
import game.player.BulletPlayer;
import game.player.Player;
import physic.BoxCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {

    static public GameObjectManager instance = new GameObjectManager();

    private List<GameObject> list;
    private List<GameObject> tempList;

    private GameObjectManager() {
        this.list = new ArrayList<>();
        this.tempList = new ArrayList<>();
    }

    public void add(GameObject gameObject) {
        this.tempList.add(gameObject);
    }

    public void runAll() {
        this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.run());
        this.list.addAll(this.tempList);
        this.tempList.clear();
    }

    public void renderAll(Graphics graphics) {
        this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.render(graphics));
    }

    public Player findPlayer() {
        return (Player) this.list
                .stream()
                .filter(gameObject -> gameObject instanceof Player)
                .findFirst()
                .orElse(null);
    }

    public EnemyFollow checkCollision(BulletPlayer bulletPlayer) {
        return (EnemyFollow) this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> gameObject instanceof EnemyFollow)
                .filter(gameObject -> {
                    BoxCollider other = ((EnemyFollow) gameObject).boxCollider;
                    return bulletPlayer.boxCollider.checkCollision(other);
                })
                .findFirst()
                .orElse(null);
    }

    public Enemy checkCollision_2(BulletPlayer bulletPlayer) {
        return (Enemy) this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> gameObject instanceof Enemy)
                .filter(gameObject -> {
                    BoxCollider other = ((Enemy) gameObject).boxCollider;
                    return bulletPlayer.boxCollider.checkCollision(other);
                })
                .findFirst()
                .orElse(null);
    }

    public Player checkcollision_3(BulletEnemy bulletEnemy) {
        return (Player) this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> gameObject instanceof Player)
                .filter(gameObject -> {
                    BoxCollider other = ((Player)gameObject).boxCollider;
                    return bulletEnemy.boxCollider.checkCollision(other);
                })
                .findFirst()
                .orElse(null);
    }

    public Player checkcollision_4(Enemy enemy) {
        return (Player) this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> gameObject instanceof Player)
                .filter(gameObject -> {
                    BoxCollider other = ((Player)gameObject).boxCollider;
                    return enemy.boxCollider.checkCollision(other);
                })
                .findFirst()
                .orElse(null);
    }

    public Player checkcollision_5(EnemyFollow enemyFollow) {
        return (Player) this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .filter(gameObject -> gameObject instanceof Player)
                .filter(gameObject -> {
                    BoxCollider other = ((Player)gameObject).boxCollider;
                    return enemyFollow.boxCollider.checkCollision(other);
                })
                .findFirst()
                .orElse(null);
    }
}