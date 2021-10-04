package com.comflip.engine;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	private GameContainer gc;

	private final int NUM_KEYS = 256;
	private boolean[] keys = new boolean[NUM_KEYS];
	private boolean[] keysLast = new boolean[NUM_KEYS];
	private boolean isKeyActive;
	private boolean isKeyUpActive;
	private boolean isKeyDownActive;

	private final int NUM_BUTTONS = 256;
	private boolean[] buttons = new boolean[NUM_BUTTONS];
	private boolean[] buttonsLast = new boolean[NUM_BUTTONS];
	private boolean isButtonsActive;
	private boolean isButtonsUpActive;
	private boolean isButtonsDownActive;

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

		for (int i = 0; i < NUM_KEYS; i++) {
			keysLast[i] = keys[i];
		}

		for (int i = 0; i < NUM_BUTTONS; i++) {
			buttonsLast[i] = buttons[i];
		}
	}

	public boolean isKey(int keyCode) {
		if (keys[keyCode]) {
			isKeyActive = true;
		} else {
			isKeyActive = false;
		}
		return keys[keyCode];
	}

	public boolean isKeyUp(int keyCode) {
		if (!keys[keyCode] && keysLast[keyCode]) {
			isKeyUpActive = true;
		} else {
			isKeyUpActive = false;
		}
		return !keys[keyCode] && keysLast[keyCode];
	}

	public boolean isKeyDown(int keyCode) {
		if (keys[keyCode] && !keysLast[keyCode]) {
			isKeyDownActive = true;
		} else {
			isKeyDownActive = false;
		}
		return keys[keyCode] && !keysLast[keyCode];
	}

	public boolean isButton(int button) {
		if(buttons[button]) {
			isButtonsActive = true;
		} else {
			isButtonsActive = false;
		}
		return buttons[button];
	}

	public boolean isButtonUp(int button) {
		if(!buttons[button] && buttonsLast[button]) {
			isButtonsUpActive = true;
		} else {
			isButtonsUpActive = false;
		}
		return !buttons[button] && buttonsLast[button];
	}

	public boolean isButtonDown(int button) {
		if(buttons[button] && !buttonsLast[button]) {
			isButtonsDownActive = true;
		} else {
			isButtonsDownActive = false;
		}
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
		System.out.println(e);
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

	public boolean isKeyActive() {
		return isKeyActive;
	}

	public boolean isKeyUpActive() {
		return isKeyUpActive;
	}

	public boolean isKeyDownActive() {
		return isKeyDownActive;
	}

	public boolean isButtonsActive() {
		return this.isButtonsActive;
	}

	public boolean isButtonsUpActive() {
		return this.isButtonsUpActive;
	}

	public boolean isButtonsDownActive() {
		return this.isButtonsDownActive;
	}
}
