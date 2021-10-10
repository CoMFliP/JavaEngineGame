package com.comflip.game.objects;

import java.awt.event.KeyEvent;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.gfc.ImageTile;
import com.comflip.game.abstracts.Objects;

public class Arrow extends Objects {
	public Arrow(String path, int tileW, int tileH) {
		imageTile = new ImageTile(path, tileW, tileH);
		tag = getClass().getSimpleName();
	}

	public void update(GameContainer gc, float dt) {
		Input input = gc.getInput();
		
		int wheelPosY = Objects.wheel.getPosY();
		int wheelPosX = Objects.wheel.getPosX();

		if (input.isKey(KeyEvent.VK_UP)) {
			posY = wheelPosY - Objects.arrow.imageTile.getTileH();
			posX = wheelPosX;
			indexTileX = 2;
		} else if (input.isKey(KeyEvent.VK_DOWN)) {
			posY = wheelPosY + Objects.arrow.imageTile.getTileH();
			posX = wheelPosX;
			indexTileX = 4;
		} else if (input.isKey(KeyEvent.VK_LEFT)) {
			posY = wheelPosY;
			posX = wheelPosX - Objects.arrow.imageTile.getTileW();
			indexTileX = 3;
		} else if (input.isKey(KeyEvent.VK_RIGHT)) {
			posY = wheelPosY;
			posX = wheelPosX + Objects.arrow.imageTile.getTileW();
			indexTileX = 1;
		} else {
			indexTileX = 0;
		}
	}
}
