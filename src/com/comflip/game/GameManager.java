package com.comflip.game;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.comflip.engine.AbstractGame;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Image;

import sun.print.BackgroundLookupListener;

public class GameManager extends AbstractGame {

	private Image cursor;
	private Image background;
	private Image background2;

	public GameManager() {
		cursor = new Image("/point.png");
		background2 = new Image("/lol3.png");
		background = new Image("/lol2.png");
	}

	public void update(GameContainer gc, float dt) {
		if (gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			Color color = new Color(background2.getP()[0]);

			System.out.println(color.getAlpha());
		}
	}

	public void render(GameContainer gc, Renderer r) {
		r.drawImage(background, 0, 0);
		r.drawImage(cursor, 0, 0);
		r.drawImage(cursor, 16, 0);
		r.drawImage(cursor, 32, 0);
		r.drawImage(background2, gc.getInput().getMouseX(), gc.getInput().getMouseY());
	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
