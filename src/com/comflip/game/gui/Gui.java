package com.comflip.game.gui;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;

public class Gui{
	private static final Cursor cursor = new Cursor();	
	
	public void updateInput(Input input, float dt) {
		cursor.updateInput(input, dt);
	}

	public void render(Renderer r) {
		cursor.render(r);		
	}
}