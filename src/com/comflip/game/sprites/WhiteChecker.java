package com.comflip.game.sprites;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.lists.Sprites;

public class WhiteChecker extends Sprites {
		public WhiteChecker(String path, String tag) {
		this.image =  new Sprite(path);
		this.tag = tag;
	}
	
	public void update(GameContainer gameContainer, float dt) {
		
	}
	
	public GameObject render(Renderer r) {
		return r.drawSprite(this.image, posX, posY);
	}
}
