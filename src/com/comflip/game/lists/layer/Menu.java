package com.comflip.game.lists.layer;

import java.util.ArrayList;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.gui.Button;

public class Menu extends Layer {
	private int widthWindow, heigthWindow;

	ArrayList<GUI> listGUI = new ArrayList<GUI>();
	Sprite image = new Sprite("/menu.png");

	public Menu() {
		for (int i = 0; i < 2; i++) {
			GUI.button = new Button("button_" + i);
			listGUI.add(GUI.button);
		}
	}

	public void update(GameContainer gc, float dt) {
		widthWindow = gc.getWidth();
		heigthWindow = gc.getHeigth();

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				elementGui.update(gc, dt);
				if (elementGui.tag.indexOf("button") != -1) {
					Button button = (Button) listGUI.get(i);
					if (button.isExecute()) {
						switch (button.tag) {
						case "button_0":
							Layer.GAME.isActive = true;
							Layer.SELECT_NAME.isActive = true;
							this.isActive = false;
							break;
						
						case "button_1":
							System.exit(0);
							break;

						default:
							break;
						}
					}
				}
			}
		}
	}

	public void render(Renderer r) {
		r.drawSprite(image, 0, 0);

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				if (elementGui.tag.indexOf("button") != -1) {
					Button button = (Button) listGUI.get(i);
					button.width = widthWindow / 3;
					button.height = 25;

					button.posX = button.width;
					button.posY = heigthWindow / 2;

					switch (button.tag) {
					case "button_0":
						button.setText("Play");
						break;
						
					case "button_1":
						button.setText("Exit");
						button.posY += 30;
						break;

					default:
						break;
					}
				}
				elementGui.render(r);
			}
		}
	}
}
