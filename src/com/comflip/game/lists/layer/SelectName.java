package com.comflip.game.lists.layer;

import java.util.ArrayList;
import java.util.HashMap;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.gui.Button;

public class SelectName extends Layer {

	private int widthWindow, heigthWindow;
	ArrayList<GUI> listGUI = new ArrayList<GUI>();
	
	String namePlayer1;
	String namePlayer2;

	HashMap<String, String> choosePlayer = new HashMap<String, String>();
	
	public SelectName() {
		for (int i = 0; i < 3; i++) {
			GUI.button = new Button("button_player1_" + i);
			listGUI.add(GUI.button);
		}
		
		for (int i = 0; i < 3; i++) {
			GUI.button = new Button("button_player2_" + i);
			listGUI.add(GUI.button);
		}
		
		GUI.button = new Button("button_next");
		listGUI.add(GUI.button);
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
						case "button_player1_0":
							this.namePlayer1 = "Jorge";
							break;
							
						case "button_player1_1":
							this.namePlayer1 = "Philipp";
							break;
							
						case "button_player1_2":
							this.namePlayer1 = "Vitaly";
							break;
							
						case "button_player2_0":
							this.namePlayer2 = "Jorge";
							break;
							
						case "button_player2_1":
							this.namePlayer2 = "Philipp";
							break;
							
						case "button_player2_2":
							this.namePlayer2 = "Vitaly";
							break;
						
						case "button_next":
							choosePlayer.put("white", namePlayer1);
							choosePlayer.put("black", namePlayer2);
							
							this.isActive = false;
							
						default:
							break;
						}
					}
				}
			}
		}
	}

	public void render(Renderer r) {
		r.drawFillRect(0, 0, widthWindow, heigthWindow, 0x88000000);

		r.drawText("Select your name", widthWindow / 2 - (r.drawText("Select your name", 0, 0, 0).getWidth() / 2), 25,
				Color.WHITE);
		r.drawText("Player1: " + namePlayer1, widthWindow / 2 - (r.drawText("Player1: " + namePlayer1, 0, 0, 0).getWidth() / 2), 45,
				Color.WHITE);
		
		r.drawText("Player2: " + namePlayer2, widthWindow / 2 - (r.drawText("Player2: " + namePlayer2, 0, 0, 0).getWidth() / 2), 175,
				Color.WHITE);
		
		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				if (elementGui.tag.indexOf("button_player1") != -1) {
					Button button = (Button) listGUI.get(i);
					button.width = widthWindow / 3;
					button.height = 25;

					button.posX = button.width;
					button.posY = 65;

					switch (button.tag) {
					case "button_player1_0":
						button.setText("Jorge");
						break;
						
					case "button_player1_1":
						button.setText("Philipp");
						button.posY += 30;
						break;
						
					case "button_player1_2":
						button.setText("Vitaly");
						button.posY += 60;
						break;

					default:
						break;
					}
					elementGui.render(r);
				}
				
				if (elementGui.tag.indexOf("button_player2") != -1) {
					Button button = (Button) listGUI.get(i);
					button.width = widthWindow / 3;
					button.height = 25;
					
					button.posX = button.width;
					button.posY = 195;
					
					switch (button.tag) {
					case "button_player2_0":
						button.setText("Jorge");
						break;
						
					case "button_player2_1":
						button.setText("Philipp");
						button.posY += 30;
						break;
						
					case "button_player2_2":
						button.setText("Vitaly");
						button.posY += 60;
						break;
						
					default:
						break;
					}
					elementGui.render(r);
				}
				
				if ((elementGui.tag.indexOf("button_next") != -1) && namePlayer1 != null && namePlayer2 != null) {
					Button button = (Button) listGUI.get(i);
					button.width = widthWindow / 3;
					button.height = 25;
					
					button.posX = button.width;
					button.posY = 315;
					
					button.setText("Fight!");
					
					elementGui.render(r);
				}
			}
		}
	}

	public HashMap<String, String> getChoosePlayer() {
		return choosePlayer;
	}
}
