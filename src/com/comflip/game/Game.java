package com.comflip.game;

import java.util.ArrayList;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.game.abstracts.Layers;
import com.comflip.game.abstracts.Sprites;

public class Game extends Layers {
	private GameObject imageText;
	private int widthWindow;
	private int heigthWindow;

	ArrayList<Sprites> listSprites = new ArrayList<Sprites>();

	public Game(String tag) {
		this.tag = tag;

		listSprites.add(Sprites.wheel);
		listSprites.add(Sprites.arrow);
	}

	public void update(GameContainer gc, float dt) {
		widthWindow = gc.getWidth();
		heigthWindow = gc.getHeigth();

		if (!listSprites.isEmpty()) {
			for (int i = 0; i < listSprites.size(); i++) {
				listSprites.get(i).update(gc, dt);
			}
		}
	}

	public GameObject render(Renderer r) {
		imageText = r.drawText("welcome to the game!!!", 0, 0, 0);

		if (!listSprites.isEmpty()) {
			for (int i = 0; i < listSprites.size(); i++) {
				listSprites.get(i).render(r).setScale(0.6f);
			}
		}
		r.drawText("welcome to the game!!!", (widthWindow / 2) - (imageText.getWidth() / 2),
				(heigthWindow / 2) - (imageText.getHeight() / 2), Color.WHITE).setScale(10f);
		return null;
	}
}
