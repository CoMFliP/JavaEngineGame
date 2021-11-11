package com.comflip.game.gui;

import java.awt.event.MouseEvent;

import com.comflip.engine.Collisions;
import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.game.abstracts.GUI;

public class Button extends GUI {
	private String string = "";
	private GameObject imageText;
	
	private boolean isActive = false;

	public Button(String tag) {
		this.tag = tag;
	}

	public void update(GameContainer gameContainer, float dt) {
		Input input = gameContainer.getInput();

		int buttonPosX = posX;
		int buttonPosY = posY;

		int buttonWight = width;
		int buttonHeight = height;

		int cursorPosX = GUI.cursor.getPosX();
		int cursorPosY = GUI.cursor.getPosY();

		boolean axisX = Collisions.axisX(buttonPosX, buttonWight, cursorPosX, 0);
		boolean axisY = Collisions.axisY(buttonPosY, buttonHeight, cursorPosY, 0);

		if (axisX && axisY) {
			if (input.isButtonDown(MouseEvent.BUTTON1)) {
				isActive = true;
			} else if (input.isButtonUp(MouseEvent.BUTTON1)) {
				isActive = false;
			}
		} else {
			isActive = false;
		}
	}

	public void render(Renderer r) {
		imageText = r.drawText(string, 0, 0, 0);

		if (isActive) {
			r.drawFillRect(posX, posY, width, height, Color.DARK_GREY);
			r.drawRect(posX, posY, width, height, Color.BLACK);
		} else {
			r.drawFillRect(posX, posY, width, height, Color.GREY);
			r.drawRect(posX, posY, width, height, Color.DARK_GREY);
		}
		
		r.drawText(string, posX + ((width - imageText.getW()) / 2), 
				posY + ((height - imageText.getH()) / 2), Color.WHITE).setScale(0);
	}

	public void setString(String string) {
		this.string = string;
	}

	public boolean isActive() {
		return isActive;
	}
}
