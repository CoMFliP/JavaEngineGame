package com.comflip.game.objects;

import java.awt.event.KeyEvent;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.SpriteTile;
import com.comflip.game.abstracts.Sprites;

public class Arrow extends Sprites {
	public Arrow(String path, int tileW, int tileH) {
		imageTile = new SpriteTile(path, tileW, tileH);
		tag = getClass().getSimpleName();
	}

	public void update(GameContainer gc, float dt) {
		Input input = gc.getInput();
		
		int wheelPosY = Sprites.wheel.getPosY();
		int wheelPosX = Sprites.wheel.getPosX();

		if (input.isKey(KeyEvent.VK_UP)) {
			posY = wheelPosY - Sprites.arrow.imageTile.getTileHeight();
			posX = wheelPosX;
			indexTileX = 2;
		} else if (input.isKey(KeyEvent.VK_DOWN)) {
			posY = wheelPosY + Sprites.arrow.imageTile.getTileHeight();
			posX = wheelPosX;
			indexTileX = 4;
		} else if (input.isKey(KeyEvent.VK_LEFT)) {
			posY = wheelPosY;
			posX = wheelPosX - Sprites.arrow.imageTile.getTileWidth();
			indexTileX = 3;
		} else if (input.isKey(KeyEvent.VK_RIGHT)) {
			posY = wheelPosY;
			posX = wheelPosX + Sprites.arrow.imageTile.getTileWidth();
			indexTileX = 1;
		} else {
			indexTileX = 0;
		}
	}
	
	public GameObject render(Renderer r) {
		return r.drawSpriteTile(imageTile, posX, posY, indexTileX, indexTileY);
	}
}
