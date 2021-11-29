package com.comflip.game.lists.sprite;

import java.awt.event.MouseEvent;

import com.comflip.engine.Collisions;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Sprites;
import com.comflip.game.lists.gui.Cursor;

public class WhiteChecker extends Sprites {
	public WhiteChecker(String path, String tag) {
		this.image = new Sprite(path);
		this.tag = tag;
	}

	public void update(GameContainer gc, float dt) {
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();

		Input input = gc.getInput();
		Cursor cursor = GUI.CURSOR;

		boolean axisX = Collisions.axisX(this.posX, this.width, cursor.posX, 0);
		boolean axisY = Collisions.axisY(this.posY, this.height, cursor.posY, 0);

		if (axisX && axisY) {
			if (input.isButtonDown(MouseEvent.BUTTON1) || input.isKeyDown(MouseEvent.BUTTON1)) {
				this.pickUp = true;
			} else if (input.isButtonUp(MouseEvent.BUTTON1) || input.isKeyUp(MouseEvent.BUTTON1)) {
				this.pickUp = false;
			}
		}

		if (this.pickUp) {
			this.posX = cursor.posX - (this.width / 2);
			this.posY = cursor.posY - (this.height / 2);
		}
	}

	public void render(Renderer r) {
		if (this.pickUp) {
			r.drawSprite(this.image, this.posX, this.posY).setScale(1.2f);
		} else {
			r.drawSprite(this.image, this.posX, this.posY).setScale(1f);
		}
	}
}
