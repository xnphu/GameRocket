package base;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    public static KeyboardInput instance = new KeyboardInput();

    public int angle;
    public float value;

    private KeyboardInput() {
        this.angle = 0;
        this.value = 1;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
             this.value = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            angle -= 5.0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            angle += 5.0;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.value = 1;
        }
    }
}

