package com.comflip.game;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.game.gui.Cursor;

public class Gui{
	private static final Cursor cursor = new Cursor();	
	
	public void updateInput(Input input, float dt) {
		cursor.updateInput(input, dt);
	}

	public void render(Renderer r) {
		cursor.render(r);		
	}
}