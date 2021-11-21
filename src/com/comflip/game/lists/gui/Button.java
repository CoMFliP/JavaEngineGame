package com.comflip.game.lists.gui;

import java.awt.event.MouseEvent;

import com.comflip.engine.Collisions;
import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.game.lists.GUI;

public class Button extends GUI {
	private String text = "";

	private boolean isActive = false;
	private boolean isExecute = false;
	
	public Button(String tag) {
		this.tag = tag;
	}

	public void update(GameContainer gameContainer, float dt) {
		Input input = gameContainer.getInput();
		Cursor cursor = GUI.CURSOR;

		boolean axisX = Collisions.axisX(this.posX, this.width, cursor.posX, 0);
		boolean axisY = Collisions.axisY(this.posY, this.height, cursor.posY, 0);

		if (axisX && axisY) {
			if (input.isButtonDown(MouseEvent.BUTTON1)) {
				isActive = true;
				isExecute = false;
			} else if (input.isButtonUp(MouseEvent.BUTTON1)) {
				isActive = false;
				isExecute = true;
			} else {
				isExecute = false;
			}
		} else {
			isActive = false;
		}
	}

	public void render(Renderer r) {
		GameObject imageText = r.drawText(text, 0, 0, 0);

		if (isActive) {
			r.drawFillRect(posX, posY, width, height, Color.DARK_GREY);
			r.drawRect(posX, posY, width, height, Color.BLACK);
		} else {
			r.drawFillRect(posX, posY, width, height, Color.GREY);
			r.drawRect(posX, posY, width, height, Color.DARK_GREY);
		}
		
		r.drawText(text, posX + ((width - imageText.getWidth()) / 2), 
				posY + ((height - imageText.getHeight()) / 2), Color.WHITE);
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	public boolean isExecute() {
		return this.isExecute;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
