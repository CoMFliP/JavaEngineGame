package com.comflip.engine;

import java.awt.event.*;
import java.util.HashMap;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private final GameContainer gc;

    HashMap<Integer, Character> mapKey = new HashMap<>();

    private final int NUM_KEYS = 1024;
    private final boolean[] keys = new boolean[NUM_KEYS];
    private final boolean[] keysLast = new boolean[NUM_KEYS];

    private final int NUM_BUTTONS = 1024;
    private final boolean[] buttons = new boolean[NUM_BUTTONS];
    private final boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private int mouseX, mouseY;
    private int scroll;

    public Input(GameContainer gc) {
        this.gc = gc;
        mouseX = 0;
        mouseY = 0;
        scroll = 0;

        gc.getWindow().getCanvas().addKeyListener(this);
        gc.getWindow().getCanvas().addMouseListener(this);
        gc.getWindow().getCanvas().addMouseMotionListener(this);
        gc.getWindow().getCanvas().addMouseWheelListener(this);
    }

    public void update() {
        scroll = 0;

        System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);
        System.arraycopy(buttons, 0, buttonsLast, 0, NUM_BUTTONS);
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && keysLast[keyCode];
    }

    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !keysLast[keyCode];
    }

    public boolean isButton(int button) {
        return buttons[button];
    }

    public boolean isButtonUp(int button) {
        return !buttons[button] && buttonsLast[button];
    }

    public boolean isButtonDown(int button) {
        return buttons[button] && !buttonsLast[button];
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }

    public void mouseDragged(MouseEvent e) {
        mouseX = (int) (e.getX() / gc.getScale());
        mouseY = (int) (e.getY() / gc.getScale());
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = (int) (e.getX() / gc.getScale());
        mouseY = (int) (e.getY() / gc.getScale());
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        mapKey.clear();
        mapKey.put(e.getKeyCode(),e.getKeyChar());
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public int getMouseX() {
        return this.mouseX;
    }

    public int getMouseY() {
        return this.mouseY;
    }

    public int getScroll() {
        return this.scroll;
    }

    public HashMap<Integer, Character> getMapKey() {
        return this.mapKey;
    }
}
