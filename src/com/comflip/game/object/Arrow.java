package com.comflip.game.object;

import java.awt.event.KeyEvent;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.ImageTile;

public class Arrow extends Positions{
	private ImageTile arrow = new ImageTile("/arrow.png", 37, 35);
	
	public void update(Input input, float dt) {
		if (input.isKey(KeyEvent.VK_UP)) {
			y = Objects.wheel.getY() - 40;
			x = Objects.wheel.getX();
			frame = 2;
		} else if (input.isKey(KeyEvent.VK_DOWN)) {
			y = Objects.wheel.getY() + 40;
			x = Objects.wheel.getX();
			frame = 4;
		} else if (input.isKey(KeyEvent.VK_LEFT)) {
			y = Objects.wheel.getY();
			x = Objects.wheel.getX() - 40;
			frame = 3;
		} else if (input.isKey(KeyEvent.VK_RIGHT)) {
			y = Objects.wheel.getY();
			x = Objects.wheel.getX() + 40;
			frame = 1;
		} else {
			frame = 0;
		}
	}

	public void render(Renderer r) {
		r.drawImageTile(arrow, x, y, (int) frame, indexLine);
	}
}
