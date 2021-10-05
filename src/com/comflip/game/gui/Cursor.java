package com.comflip.game.gui;

import java.awt.event.MouseEvent;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Image;
import com.comflip.game.Objects;
import com.comflip.game.abstracts.AbstractGui;

public class Cursor extends AbstractGui {
	private Image cursor = new Image("/point.png");

	public static boolean isBetween(int value, int min, int max) {
		return ((value > min) && (value < max));
	}

	boolean pick;
	public void updateInput(Input input, float dt) {		
		x = input.getMouseX();
		y = input.getMouseY();
		if (input.isButtonDown(MouseEvent.BUTTON1)
				&& (isBetween(x, Objects.wheel.getX(), Objects.wheel.getX() + 37) 
				&& isBetween(y, Objects.wheel.getY(), Objects.wheel.getY() + 35))) {
			pick = true;
		} else if(input.isButtonUp(MouseEvent.BUTTON1)) {
			pick = false;			
		}
		
		if(pick) {
			Objects.wheel.setX(x-16);
			Objects.wheel.setY(y-16);
		}

	}

	public void render(Renderer r) {
		r.drawImage(cursor, x, y);
	}
}
