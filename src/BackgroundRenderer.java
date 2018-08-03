import java.awt.*;

public class BackgroundRenderer implements Renderer {

    public Vector2D position;
    public Color color;

    public BackgroundRenderer(Color color) {
        this.position = new Vector2D();
        this.color = color;
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        graphics.setColor(this.color);
        graphics.fillRect((int) this.position.x, (int) this.position.y, 1024, 600);
    }
}
