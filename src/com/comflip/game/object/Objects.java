package com.comflip.game.object;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;

public class Objects {
	public static final Wheel wheel = new Wheel();
	public static final Arrow arrow = new Arrow();	

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
