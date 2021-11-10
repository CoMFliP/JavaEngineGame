package com.comflip.game;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.game.abstracts.GUI;
import com.comflip.game.abstracts.Layers;
import com.comflip.game.abstracts.Objects;

public class Menu extends Layers {	
	public Menu() {
	}

	public void update(GameContainer gc, float dt) {
		for (Objects object : Objects.ArrayList()) {
			try {
				object.update(gc, dt);
			} catch (Exception e) {
				System.err.println("Object " + object.tag + " is missed");
			}
		}
	}

	public void render(Renderer r) {
		for (Objects object : Objects.ArrayList()) {
			if (object.image != null) {
				r.drawImage(object.image, object.posX, object.posY);
			}
			if (object.imageTile != null) {
				r.drawImageTile(object.imageTile, object.posX, object.posY, object.indexTileX, object.indexTileY);
			}
		}

		for (GUI elementGui : GUI.ArrayList()) {			
			if (elementGui.equals(GUI.button)) {
				elementGui.posX = 100;
				elementGui.posY = 100;
				elementGui.width = 100;
				elementGui.height = 15;
				elementGui.render(r);
//				for (int i = 0; i <= 1; i++) {
//					elementGui.posY += 20;
//				}
			}
		}
	}
}
