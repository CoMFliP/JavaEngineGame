package com.comflip.game.layers;

import java.util.ArrayList;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.lists.Layers;
import com.comflip.game.lists.Sprites;
import com.comflip.game.sprites.WhiteChecker;

public class Game extends Layers {
	private GameObject imageText;
	private int widthWindow;
	private int heigthWindow;

	ArrayList<Sprites> listSprites = new ArrayList<Sprites>();
	ArrayList<GameObject> listTextObjects = new ArrayList<GameObject>();

	public Game(String tag) {
		this.tag = tag;

		for (int i = 0; i < 3; i++) {
			WhiteChecker whiteChecker = new WhiteChecker("/checker/white.png", "white_" + i);
			listSprites.add(whiteChecker);
		}

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
		listTextObjects.clear();

		r.drawSprite(new Sprite("/board.png"), 0, 0);

		if (!listSprites.isEmpty()) {
			for (int i = 0; i < listSprites.size(); i++) {
				Sprites sprite = listSprites.get(i);
				
				if(sprite.getClass() == Sprites.WHITE_CHECKER.getClass()) {
					
				}
				
				sprite.render(r);
			}
		}
		r.drawText("welcome to the game!!!", (widthWindow / 2) - (imageText.getWidth() / 2),
				(heigthWindow / 2) - (imageText.getHeight() / 2), Color.WHITE);

		return null;
	}
}
