package com.comflip.game.gui;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Image;

public class Cursor extends GuiPosition {
	private Image cursor = new Image("/point.png");

	public void update(Input input, float dt) {
		x = input.getMouseX();
		y = input.getMouseY();

	}

	public void render(Renderer r) {
		r.drawImage(cursor, x, y);
	}
}
