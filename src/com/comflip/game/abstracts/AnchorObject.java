package com.comflip.game.abstracts;

public abstract class AnchorObject {
	public int indexLine;
	public float frame;
	public int lastFrame;

	public int x;
	public int y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int setX(int x) {
		return this.x = x;
	}

	public int setY(int y) {
		return this.y = y;
	}
}
