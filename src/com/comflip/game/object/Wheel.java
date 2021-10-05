package com.comflip.game.object;

import java.awt.event.KeyEvent;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.ImageTile;
import com.comflip.game.abstracts.AbstractObject;

public class Wheel extends AbstractObject{
	private ImageTile wheel = new ImageTile("/circle.png", 37, 35);

	public void updateInput(Input input, float dt) {
		if (input.isKeyActive()) {
			frame += dt * 15;
			if (frame > 4) {
				frame = 0;
			}
			lastFrame = (int) frame;
		}

		if (input.isKey(KeyEvent.VK_UP)) {
			y -= 3;
			indexLine = 1;
		}
		if (input.isKey(KeyEvent.VK_DOWN)) {
			y += 3;
			indexLine = 0;
		}
		if (input.isKey(KeyEvent.VK_LEFT)) {
			x -= 3;
			indexLine = 1;
		}
		if (input.isKey(KeyEvent.VK_RIGHT)) {
			x += 3;
			indexLine = 0;
		}		
	}

	public void render(Renderer r) {
		r.drawImageTile(wheel, x, y, lastFrame, indexLine);
	}
}
