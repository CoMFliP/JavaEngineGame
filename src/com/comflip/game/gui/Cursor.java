package com.comflip.game.gui;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.abstracts.GUI;

public class Cursor extends GUI {
	public Cursor(String path) {
		image = new Sprite(path);
	}

	public void update(GameContainer gameContainer, float dt) {
		Input input = gameContainer.getInput();
		posX = input.getMouseX();
		posY = input.getMouseY();
	}
	
	public GameObject render(Renderer r) {
		return r.drawSprite(image, posX, posY);
	}
}