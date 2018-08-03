import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateStar {

    private Random random = new Random();

    private int timeIntervalStar = 0;
    private List<Star> stars = new ArrayList<>();


    public void run() {
        if (this.timeIntervalStar == 30) {
            Star star = new Star();
            star.position.set(1024, this.random.nextInt(600));
            star.velocity.set(this.random.nextInt(3) + 1, 0);
            this.stars.add(star);
            this.timeIntervalStar = 0;
        } else {
            this.timeIntervalStar += 1;
        }
        this.stars.forEach(star -> star.run());
    }

    public void render(Graphics graphics) {
        this.stars.forEach(star -> star.render(graphics));
    }
}
