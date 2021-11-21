package com.comflip.game.lists.layer;

import java.util.ArrayList;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.Sprites;
import com.comflip.game.lists.gui.MapBoard;
import com.comflip.game.lists.sprite.BlackChecker;
import com.comflip.game.lists.sprite.WhiteChecker;

public class Game extends Layer {
	private Sprite imageBoard;

	ArrayList<Sprites> listCheckers = new ArrayList<Sprites>();
	ArrayList<GUI> listGUI = new ArrayList<GUI>();

	public Game(String tag) {
		this.tag = tag;

		imageBoard = new Sprite("/board.png");

		for (int i = 0; i < 20; i++) {
			Sprites.blackChecker = new BlackChecker("/checker/black.png", "black_normal_" + i);
			Sprites.whiteChecker = new WhiteChecker("/checker/white.png", "white_normal_" + i);
			listCheckers.add(Sprites.blackChecker);
			listCheckers.add(Sprites.whiteChecker);
		}

		for (int i = 0; i < listCheckers.size(); i++) {
			Sprites checker = listCheckers.get(i);
			MapBoard mapBoard = GUI.MAP_BOARD;

			if (i % 2 == 0) {
				if (checker.tag.equals("black_normal_" + Integer.toString(i / 2))) {
					checker.posX = mapBoard.getPossiton(i / 2)[0];
					checker.posY = mapBoard.getPossiton(i / 2)[1];
				}
			} else {
				if (checker.tag.equals("white_normal_" + Integer.toString(i / 2))) {
					checker.posX = mapBoard.getPossiton(i / 2 + 30)[0];
					checker.posY = mapBoard.getPossiton(i / 2 + 30)[1];
				}
			}
		}

		listGUI.add(GUI.MAP_BOARD);
	}

	public void update(GameContainer gc, float dt) {

		if (!listCheckers.isEmpty()) {
			for (int i = 0; i < listCheckers.size(); i++) {
				Sprites checker = listCheckers.get(i);
				checker.update(gc, dt);
			}
		}

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				if (elementGui.equals(GUI.MAP_BOARD)) {
					MapBoard mapBoard = (MapBoard) listGUI.get(i);
					mapBoard.input(listCheckers);
				}
				elementGui.update(gc, dt);
			}
		}

	}

	public void render(Renderer r) {
		r.drawSprite(imageBoard, 0, 0);

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				elementGui.render(r);
			}
		}

		if (!listCheckers.isEmpty()) {
			for (int i = 0; i < listCheckers.size(); i++) {
				Sprites checker = listCheckers.get(i);
				checker.render(r);
			}
			for (int i = 0; i < listCheckers.size(); i++) {
				Sprites checker = listCheckers.get(i);
				if (checker.isPickUp()) {
					checker.render(r);
				}
			}
		}
	}
}