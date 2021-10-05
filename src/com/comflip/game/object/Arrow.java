package com.comflip.game.object;

import java.awt.event.KeyEvent;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.ImageTile;
import com.comflip.game.ListObjects;
import com.comflip.game.abstracts.AnchorObject;

public class Arrow extends AnchorObject{
	private ImageTile arrow = new ImageTile("/arrow.png", 37, 35);
	
	public void updateInput(Input input, float dt) {
		if (input.isKey(KeyEvent.VK_UP)) {
			y = ListObjects.wheel.getY() - 40;
			x = ListObjects.wheel.getX();
			frame = 2;
		} else if (input.isKey(KeyEvent.VK_DOWN)) {
			y = ListObjects.wheel.getY() + 40;
			x = ListObjects.wheel.getX();
			frame = 4;
		} else if (input.isKey(KeyEvent.VK_LEFT)) {
			y = ListObjects.wheel.getY();
			x = ListObjects.wheel.getX() - 40;
			frame = 3;
		} else if (input.isKey(KeyEvent.VK_RIGHT)) {
			y = ListObjects.wheel.getY();
			x = ListObjects.wheel.getX() + 40;
			frame = 1;
		} else {
			frame = 0;
		}
	}

	public void render(Renderer r) {
		r.drawImageTile(arrow, x, y, (int) frame, indexLine);
	}
}
