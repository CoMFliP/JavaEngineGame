package com.comflip.game.lists.layer;

import java.util.*;

import com.comflip.engine.*;
import com.comflip.engine.gfc.*;
import com.comflip.game.lists.*;
import com.comflip.game.lists.sprite.*;
import com.comflip.game.lists.gui.MapBoard;

public class Game extends Layer {
	private Sprite imageBoard;

	ArrayList<Sprites> listCheckers = new ArrayList<Sprites>();
	ArrayList<GUI> listGUI = new ArrayList<GUI>();

	private String canMove = "white";

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
					checker.setIdTileBoard(i / 2);
				}
			} else {
				if (checker.tag.equals("white_normal_" + Integer.toString(i / 2))) {
					checker.posX = mapBoard.getPossiton(i / 2 + 30)[0];
					checker.posY = mapBoard.getPossiton(i / 2 + 30)[1];
					checker.setIdTileBoard(i / 2 + 30);
				}
			}
		}

		listGUI.add(GUI.MAP_BOARD);
	}

	public void update(GameContainer gc, float dt) {
		if (!listCheckers.isEmpty()) {
			for (int i = 0; i < listCheckers.size(); i++) {
				Sprites checker = listCheckers.get(i);
				HashMap<Integer, String> hashMustAttack = GUI.MAP_BOARD.getHashMustAttack();

				if (canMove.equals(checker.tag.split("_")[0])) {

					if (!hashMustAttack.isEmpty()) {
						ArrayList<Integer> listKeys = new ArrayList<Integer>(hashMustAttack.keySet());
						for (int j = 0; j < listKeys.size(); j++) {

							if (listKeys.size() == 1) {
								String checkerTag = hashMustAttack.get(listKeys.get(0));
								if (!canMove.equals(checkerTag.split("_")[0])
										&& canMove.equals(checker.tag.split("_")[0])) {
									checker.update(gc, dt);
								}

								if (checker.tag.equals(checkerTag) && canMove.equals(checkerTag.split("_")[0])) {
									checker.update(gc, dt);
								}
							}

							if (listKeys.size() > 1) {
								String checkerTag = hashMustAttack.get(listKeys.get(j));
								if (checker.tag.equals(checkerTag) && canMove.equals(checkerTag.split("_")[0])) {
									checker.update(gc, dt);
								}
							}
						}

					} else {
						checker.update(gc, dt);
					}
				}
			}
		}

		if (!listGUI.isEmpty() && !listCheckers.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				elementGui.update(gc, dt);
				if (elementGui.equals(GUI.MAP_BOARD)) {
					MapBoard mapBoard = (MapBoard) listGUI.get(i);
					mapBoard.setCheckerList(listCheckers);
				}
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

				try {
					if (canMove.equals(checker.tag.split("_")[0])) {
						r.drawText(Layer.SELECT_NAME.getChoosePlayer().get(canMove) + " (" + canMove + ")", 10, 30,
								Color.WHITE);
					}
				} catch (Exception e) {
				}
			}
			for (int i = 0; i < listCheckers.size(); i++) {
				Sprites checker = listCheckers.get(i);
				if (checker.isPickUp()) {
					checker.render(r);
				}
			}
		}

	}

	public void setCanMove(String canMove) {
		this.canMove = canMove;
	}
}
