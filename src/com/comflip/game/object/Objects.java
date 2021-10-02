package com.comflip.game.object;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;

public class Objects {
	private static final Wheel wheel = new Wheel();
	private static final Arrow arrow = new Arrow();

	public void update(Input input, float dt) {
		wheel.update(input, dt);
		arrow.update(input, dt);
	}

	public void render(Renderer r) {
		wheel.render(r);
		arrow.render(r);
	}
}

abstract class Positions {
	public int indexLine;
	public float frame;
	public int lastFrame;

	public int x, y;

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
