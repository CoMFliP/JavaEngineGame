package com.comflip.game.gui;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.gfc.Image;
import com.comflip.game.abstracts.GUI;

public class Cursor extends GUI {
	public Cursor(String path) {
		image = new Image(path);
	}

	public void update(GameContainer gameContainer, float dt) {
		Input input = gameContainer.getInput();
		posX = input.getMouseX();
		posY = input.getMouseY();
	}
}