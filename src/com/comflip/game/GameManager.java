package com.comflip.game;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.comflip.engine.AbstractGame;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Image;
import com.comflip.engine.gfc.ImageTile;

public class GameManager extends AbstractGame {

	private Image cursor;
	private Image background;
	private Image background2;

	private ImageTile anim;

	public GameManager() {
//		cursor = new Image("/anim.png");
		background2 = new Image("/lol3.png");
		background = new Image("/lol2.png");

		anim = new ImageTile("/anim.png", 16, 16);
	}

	public void update(GameContainer gc, float dt) {
		if (gc.getInput().isKeyDown(KeyEvent.VK_A)) {
			Color color = new Color(background2.getP()[0]);

			System.out.println(color.getAlpha());
		}
		
		temp += dt*5;

		if (temp > 3) {
			temp = 0;
		}
	}

	float temp = 0;

	public void render(GameContainer gc, Renderer r) {
		r.drawImage(background, 0, 0);
//		r.drawImage(cursor, 0, 0);
//		r.drawImage(background2, gc.getInput().getMouseX(), gc.getInput().getMouseY());

		r.drawImageTile(anim, gc.getInput().getMouseX(), gc.getInput().getMouseY(), (int)temp, 0);
	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

}
