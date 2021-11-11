package com.comflip.game;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.game.abstracts.Layers;

public class Game extends Layers{
	private GameObject imageText;
	private int widthWindow;
	private int heigthWindow;
	
	public Game(String tag) {
		this.tag = tag;
	}
	
	public void update(GameContainer gc, float dt) {
		widthWindow = gc.getWidth();
		heigthWindow = gc.getHeigth();
	}
	
	public void render(Renderer r) {
		imageText = r.drawText("welcome to the game!!!", 0, 0, 0);
		
		r.drawText("welcome to the game!!!", (widthWindow / 2) - (imageText.getW() / 2), (heigthWindow / 2) - (imageText.getH() / 2), Color.WHITE);
	}
}
