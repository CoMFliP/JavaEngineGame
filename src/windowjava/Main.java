package windowjava;

import javax.swing.JFrame;
import java.awt.event.*;

public class Main extends JFrame implements KeyListener {
    private DrawCanvas drawCanvas;

    static int w = 800;
    static int h = 600;

    public Main() {
        this.drawCanvas = new DrawCanvas(w, h);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            drawCanvas.moveRight(e.getKeyCode());
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            drawCanvas.moveLeft(e.getKeyCode());
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            drawCanvas.moveDown(e.getKeyCode());
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            drawCanvas.moveUp(e.getKeyCode());
        }
}

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    public static void main(String[] args) {
        Main drTest = new Main();

        drTest.setSize(w, h);
        drTest.setTitle("Canvas in Java");
        drTest.add(drTest.drawCanvas);
        drTest.setResizable(false);
        drTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drTest.setVisible(true);
    }
}
