package com.comflip.game.lists.sprite;

import java.awt.event.KeyEvent;

import com.comflip.engine.Collisions;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.SpriteTile;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.SFX;
import com.comflip.game.lists.Sprites;
import com.comflip.game.lists.gui.Cursor;

public class Wheel extends Sprites  {
	private final int step = 2;

	public Wheel(String path, int tileWidht, int tileHeight) {
		this.imageTile = new SpriteTile(path, tileWidht, tileHeight);
		this.tag = getClass().getSimpleName();
	}

	public void update(GameContainer gc, float dt) {
		Input input = gc.getInput();
		Cursor cursor = GUI.CURSOR;
		
		int wheelPosX = this.posX;
		int wheelPosY = this.posY;

		int wheelWight = this.imageTile.getTileWidth();
		int wheelHeight = this.imageTile.getTileHeight();

		int cursorPosX = cursor.posX;
		int cursorPosY = cursor.posY;

		int cursorWight = cursor.image.getWidth();
		int cursorHeight = cursor.image.getHeight();

		boolean axisX = Collisions.axisX(wheelPosX, wheelWight, cursorPosX, cursorWight);
		boolean axisY = Collisions.axisY(wheelPosY, wheelHeight, cursorPosY, cursorHeight);

		boolean toUpperSide = Collisions.toUpperSide(cursorPosY, cursorHeight, wheelPosY, step);
		boolean toLowerSide = Collisions.toLowerSide(wheelPosY, wheelHeight, cursorPosY, step);
		boolean toRightSide = Collisions.toRightSide(wheelPosX, wheelWight, cursorPosX, step);
		boolean toLeftSide = Collisions.toLeftSide(cursorPosX, cursorWight, wheelPosX, step);

		boolean animationWheel = true;
		boolean activeKey = false;

		if (input.isKey(KeyEvent.VK_UP)) {
			activeKey = true;
			if (toUpperSide && axisX) {
				animationWheel = false;
				posY += 0;
			} else {
				posY -= step;
			}
			indexTileY = 1;
		} else if (input.isKey(KeyEvent.VK_DOWN)) {
			activeKey = true;
			if (toLowerSide && axisX) {
				animationWheel = false;
				posY += 0;
			} else {
				posY += step;
			}
			indexTileY = 0;
		} else if (input.isKey(KeyEvent.VK_LEFT)) {
			activeKey = true;
			if (toLeftSide && axisY) {
				animationWheel = false;
				posX += 0;
			} else {
				posX -= step;
			}
			indexTileY = 1;
		} else if (input.isKey(KeyEvent.VK_RIGHT)) {
			activeKey = true;
			if (toRightSide && axisY) {
				animationWheel = false;
				posX += 0;
			} else {
				posX += step;
			}
			indexTileY = 0;
		}

		if (activeKey && animationWheel) {
			frame += (dt * 10) * step;
			if (frame > 4) {
				frame = 0;
			}
			indexTileX = (int) frame;
		}
		
		if (!animationWheel) {
			SFX.COLLISION_SFX.soundClip.setVolume(-10);
			if(!SFX.COLLISION_SFX.soundClip.isRunning()) {
				SFX.COLLISION_SFX.soundClip.play();				
			}
		}
	}
	
	public void render(Renderer r) {
		r.drawSpriteTile(imageTile, posX, posY, indexTileX, indexTileY);
	}
}
