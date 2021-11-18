package com.comflip.game.layers;

import java.util.ArrayList;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.gui.Button;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layers;

public class Menu extends Layers {
	private int widthWindow, heigthWindow;

	ArrayList<GUI> listGUI = new ArrayList<GUI>();
	Sprite image = new Sprite("/menu.png");

	public Menu() {
		this.tag = "menu";
		for (int i = 0; i < 3; i++) {
			Button button = new Button("button_" + i);
			listGUI.add(button);
		}
	}

	public void update(GameContainer gc, float dt) {
		widthWindow = gc.getWidth();
		heigthWindow = gc.getHeigth();

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				listGUI.get(i).update(gc, dt);
				if (listGUI.get(i) != null) {
					if (((Button) listGUI.get(i)).isExecute()) {
						switch (listGUI.get(i).tag) {
						case "button_0":
							game.tag = "gameMode_1";
							this.tag = "";
							break;
						case "button_1":
							game.tag = "gameMode_2";
							this.tag = "";
							break;
						case "button_2":
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

	public GameObject render(Renderer r) {
		r.drawSprite(image, 0, 0);

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				if (listGUI.get(i) != null) {
					if (listGUI.get(i).getClass() == GUI.button.getClass()) {
						listGUI.get(i).width = widthWindow / 3;
						listGUI.get(i).height = 15;

						listGUI.get(i).posX = listGUI.get(i).width;
						listGUI.get(i).posY = heigthWindow / 2;

						switch (listGUI.get(i).tag) {
						case "button_0":
							((Button) listGUI.get(i)).setString("one player");
							break;
						case "button_1":
							((Button) listGUI.get(i)).setString("two player");
							listGUI.get(i).posY += 20;
							break;
						case "button_2":
							((Button) listGUI.get(i)).setString("exit");
							listGUI.get(i).posY += 40;
							break;
						default:
							break;
						}
					}
					listGUI.get(i).render(r);
				}
			}
		}
		return null;
	}
}
