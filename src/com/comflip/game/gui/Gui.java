package com.comflip.game.gui;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;

public class Gui {
	private static final Cursor cursor = new Cursor();
	
	
	public void update(Input input, float dt) {
		cursor.update(input, dt);
	}

	public void render(Renderer r) {
		cursor.render(r);		
	}
}

abstract class GuiPosition {
	public int x;
	public int y;
	
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
