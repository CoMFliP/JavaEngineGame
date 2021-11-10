package com.comflip.game.objects;

import java.awt.event.KeyEvent;

import com.comflip.engine.Collisions;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.gfc.ImageTile;
import com.comflip.game.abstracts.GUI;
import com.comflip.game.abstracts.SFX;
import com.comflip.game.abstracts.Sprites;

public class Wheel extends Sprites  {
	private final int step = 2;

	public Wheel(String path, int tileW, int tileH) {
		imageTile = new ImageTile(path, tileW, tileH);
		tag = getClass().getSimpleName();
	}

	public void update(GameContainer gc, float dt) {
		Input input = gc.getInput();
		
		int wheelPosX = Sprites.wheel.getPosX();
		int wheelPosY = Sprites.wheel.getPosY();

		int wheelWight = Sprites.wheel.imageTile.getTileW();
		int wheelHeight = Sprites.wheel.imageTile.getTileH();

		int cursorPosX = GUI.cursor.getPosX();
		int cursorPosY = GUI.cursor.getPosY();

		int cursorWight = GUI.cursor.image.getW();
		int cursorHeight = GUI.cursor.image.getH();

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
			SFX.collisionSFX.soundClip.setVolume(-10);
			if(!SFX.collisionSFX.soundClip.isRunning()) {
				SFX.collisionSFX.soundClip.play();				
			}
		}
	}
}
