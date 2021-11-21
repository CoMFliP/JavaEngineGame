package com.comflip.game.lists.sprite;

import java.awt.event.KeyEvent;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.SpriteTile;
import com.comflip.game.lists.Sprites;

public class Arrow extends Sprites {
	public Arrow(String path, int tileW, int tileH) {
		this.imageTile = new SpriteTile(path, tileW, tileH);
		this.tag = getClass().getSimpleName();
	}

	public void update(GameContainer gc, float dt) {
		Input input = gc.getInput();
		Wheel wheel = Sprites.wheel;
		
		int wheelPosY = wheel.posX;
		int wheelPosX = wheel.posY;

		if (input.isKey(KeyEvent.VK_UP)) {
			posY = wheelPosY - this.imageTile.getTileHeight();
			posX = wheelPosX;
			indexTileX = 2;
		} else if (input.isKey(KeyEvent.VK_DOWN)) {
			posY = wheelPosY + this.imageTile.getTileHeight();
			posX = wheelPosX;
			indexTileX = 4;
		} else if (input.isKey(KeyEvent.VK_LEFT)) {
			posY = wheelPosY;
			posX = wheelPosX - this.imageTile.getTileWidth();
			indexTileX = 3;
		} else if (input.isKey(KeyEvent.VK_RIGHT)) {
			posY = wheelPosY;
			posX = wheelPosX + this.imageTile.getTileWidth();
			indexTileX = 1;
		} else {
			indexTileX = 0;
		}
	}
	
	public void render(Renderer r) {
		r.drawSpriteTile(imageTile, posX, posY, indexTileX, indexTileY);
	}
}
