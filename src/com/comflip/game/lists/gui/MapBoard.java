package com.comflip.game.lists.gui;

import java.util.ArrayList;
import java.util.HashMap;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Sprites;

public class MapBoard extends GUI {
	HashMap<Integer, int[]> mapBoard = new HashMap<Integer, int[]>();

	public MapBoard() {
		int idTIleBoard = 0;

		for (int tileY = 0; tileY < 10; tileY++) {
			for (int tileX = 0; tileX < 5; tileX++) {
				int[] coordinate = new int[2];
				if (tileY % 2 == 0) {
					coordinate[0] = (200 + 3) + (60 * tileX);
				} else {
					coordinate[0] = (170 + 3) + (60 * tileX);
				}
				coordinate[1] = ((30 + 3) + (30 * tileY));
				
				this.mapBoard.put(idTIleBoard++, coordinate);
			}
		}
	}
	
	public void input(ArrayList<Sprites> listCheckers) {
		if (listCheckers != null) {
			for (int i = 0; i < listCheckers.size(); i++) {
				Sprites checker = listCheckers.get(i);
				if (checker.isPickUp()) {
					System.out.println(checker.tag + ": " + checker.isPickUp());
				}
			}
		}
	}

	public void update(GameContainer gc, float dt) {
				
	}
	
	public void render(Renderer r) {
		for (int i = 0; i < this.mapBoard.size(); i++) {
			int x = this.mapBoard.get(i)[0] - 3;
			int y = this.mapBoard.get(i)[1] - 3;
			r.drawFillRect(x + 1, y + 1, 27, 27, 0xAA99DDFF);
			r.drawRect(x, y, 29, 29, 0xFF66CCFF);
		}
	}
	
	
	public int[] getPossiton(int idTileBoard) {
		return this.mapBoard.get(idTileBoard);
	}
}
