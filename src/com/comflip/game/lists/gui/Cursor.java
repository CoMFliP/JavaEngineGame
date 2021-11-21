package com.comflip.game.lists.gui;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.lists.GUI;

public class Cursor extends GUI {
	public Cursor(String path) {
		this.image = new Sprite(path);
	}

	public void update(GameContainer gc, float dt) {
		Input input = gc.getInput();
		this.posX = input.getMouseX();
		this.posY = input.getMouseY();
	}
	
	public void render(Renderer r) {
		r.drawSprite(this.image, this.posX, this.posY);
	}
}