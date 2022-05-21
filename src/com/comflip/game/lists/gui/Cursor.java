package com.comflip.game.lists.gui;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;

public class Cursor extends LoaderManager implements GUI {
	public Cursor() {
		this.sprite = new Sprite("/cursor.png");
	}

	@Override
	public void update(GameContainer gc, float dt) {
		Input input = gc.getInput();
		this.posX = input.getMouseX();
		this.posY = input.getMouseY();
	}

	@Override
	public void render(Renderer r) {
		this.sprite.setScale(0.6f);
		r.drawSprite(this.sprite, this.posX, this.posY);
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}
}