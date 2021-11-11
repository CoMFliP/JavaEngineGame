package com.comflip.game;

import java.util.ArrayList;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Image;
import com.comflip.game.abstracts.GUI;
import com.comflip.game.abstracts.Layers;
import com.comflip.game.abstracts.Sprites;
import com.comflip.game.gui.Button;

public class Menu extends Layers {
	private int widthWindow, heigthWindow;

	ArrayList<GUI> listGUI = new ArrayList<GUI>();
	Image image = new Image("/menu.png");

	public Menu() {
		for (int i = 0; i < 3; i++) {
			Button button = new Button("button_" + i);
			listGUI.add(button);
		}
	}

	public void update(GameContainer gc, float dt) {
		widthWindow = gc.getWidth();
		heigthWindow = gc.getHeigth();
		
		for (Sprites object : Sprites.ArrayList()) {
			try {
				object.update(gc, dt);
			} catch (Exception e) {
				System.err.println("Object " + object.tag + " is missed");
			}
		}
		
		for (GUI elementGui : listGUI) {
			if (((Button) elementGui).isActive()) {
				switch (elementGui.tag) {
				case "button_0":
					System.out.println("One player");
					break;
				case "button_1":
					System.out.println("Two player");
					break;
				case "button_2":
					System.exit(0);
					break;
				default:
					break;
				}				
			}
			elementGui.update(gc, dt);
		}
	}

	public void render(Renderer r) {
		r.drawImage(image, 0, 0);
		
		
		for (Sprites object : Sprites.ArrayList()) {
			if (object.image != null) {
				r.drawImage(object.image, object.posX, object.posY);
			}
			if (object.imageTile != null) {
				r.drawImageTile(object.imageTile, object.posX, object.posY, object.indexTileX, object.indexTileY);
			}
		}

		for (GUI elementGui : listGUI) {
			if(elementGui.getClass() == GUI.button.getClass()) {
				elementGui.width = widthWindow / 3;
				elementGui.height = 15;
				
				elementGui.posX = elementGui.width;
				elementGui.posY = heigthWindow / 2;
				
				switch (elementGui.tag) {
				case "button_0":
					((Button) elementGui).setString("one player");
					break;
				case "button_1":
					((Button) elementGui).setString("two player");
					elementGui.posY += 20;
					break;
				case "button_2":
					((Button) elementGui).setString("exit");
					elementGui.posY += 40;
					break;
				default:
					break;
				}
				
				elementGui.render(r);
			}
		}
	}
}
