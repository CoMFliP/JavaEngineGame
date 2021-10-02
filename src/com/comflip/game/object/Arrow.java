package com.comflip.game.object;

import java.awt.event.KeyEvent;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.ImageTile;

public class Arrow extends Positions{
	private ImageTile arrow = new ImageTile("/arrow.png", 37, 35);
	
	private Wheel wheel = new Wheel();

	public void update(Input input, float dt) {
		wheel.update(input, dt);

		if (input.isKey(KeyEvent.VK_UP)) {
			y = wheel.getY() - 40;
			x = wheel.getX();
			frame = 2;
		} else if (input.isKey(KeyEvent.VK_DOWN)) {
			y = wheel.getY() + 40;
			x = wheel.getX();
			frame = 4;
		} else if (input.isKey(KeyEvent.VK_LEFT)) {
			y = wheel.getY();
			x = wheel.getX() - 40;
			frame = 3;
		} else if (input.isKey(KeyEvent.VK_RIGHT)) {
			y = wheel.getY();
			x = wheel.getX() + 40;
			frame = 1;
		} else {
			frame = 0;
		}
	}

	public void render(Renderer r) {
		r.drawImageTile(arrow, x, y, (int) frame, indexLine);
	}
}
