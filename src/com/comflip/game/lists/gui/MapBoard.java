package com.comflip.game.lists.gui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import com.comflip.engine.Collisions;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Sprites;

public class MapBoard extends GUI {
	HashMap<Integer, int[]> mapBoard = new HashMap<Integer, int[]>();

	ArrayList<Sprites> listCheckers = new ArrayList<Sprites>();

	private int currentIdTileBoard;
	private int nextIdTileBoard;
	private int possibleStep;

	private String currentCheckerTag;

	private boolean isPickUpChecker;

	public MapBoard() {
		int idTileBoard = 0;

		for (int tileY = 0; tileY < 10; tileY++) {
			for (int tileX = 0; tileX < 5; tileX++) {
				int[] coordinate = new int[2];
				if (tileY % 2 == 0) {
					coordinate[0] = (200 + 3) + (60 * tileX);
				} else {
					coordinate[0] = (170 + 3) + (60 * tileX);
				}
				coordinate[1] = ((30 + 3) + (30 * tileY));

				this.mapBoard.put(idTileBoard++, coordinate);
			}
		}

	}

	public void update(GameContainer gc, float dt) {
		Input input = gc.getInput();

		if (!listCheckers.isEmpty()) {
			for (int i = 0; i < listCheckers.size(); i++) {
				Sprites checker = listCheckers.get(i);
				if (checker.isPickUp()) {
					currentIdTileBoard = checker.getIdTileBoard();
					currentCheckerTag = checker.tag;
					isPickUpChecker = true;

				}

				if (input.isButtonUp(MouseEvent.BUTTON1)) {
					if (checker.tag.equals(currentCheckerTag)) {
						for (int j = 0; j < selectionRules().length; j++) {
							int x = this.mapBoard.get(selectionRules()[j])[0] - 3;
							int y = this.mapBoard.get(selectionRules()[j])[1] - 3;

							boolean axisX = Collisions.axisX(checker.posX + (checker.width / 2), 0, x, 29);
							boolean axisY = Collisions.axisY(checker.posY + (checker.height / 2), 0, y, 29);

							if (axisX && axisY) {
								checker.posX = x + 3;
								checker.posY = y + 3;
								checker.setIdTileBoard(selectionRules()[j]);
							}
						}
					}
					isPickUpChecker = false;
				}
			}
		}
	}

	public void render(Renderer r) {
		if (isPickUpChecker) {
			for (int i = 0; i < selectionRules().length; i++) {
				int x = this.mapBoard.get(selectionRules()[i])[0] - 3;
				int y = this.mapBoard.get(selectionRules()[i])[1] - 3;

				if (i == 0) {
					r.drawFillRect(x + 1, y + 1, 27, 27, 0x55FF6666);
					r.drawRect(x, y, 29, 29, 0xFFCC0000);
				} else {
					r.drawFillRect(x + 1, y + 1, 27, 27, 0x5599DDFF);
					r.drawRect(x, y, 29, 29, 0xFF66CCFF);
				}
			}
		}
	}

	private int[] selectionRules() {
		possibleStep = 0;
		nextIdTileBoard = currentIdTileBoard;

		int[] listNextIdTileBoard;
		if (currentIdTileBoard % 10 == 4 || currentIdTileBoard % 10 == 5) {
			listNextIdTileBoard = new int[2];
		} else {
			listNextIdTileBoard = new int[3];
		}

		if (currentCheckerTag.indexOf("normal") != -1) {

			if (currentCheckerTag.indexOf("white") != -1) {
//				for (int i  = 0; i < listNextIdTileBoard.length; i++) {
//					
//				}
				listNextIdTileBoard[0] = currentIdTileBoard;
				if (currentIdTileBoard % 10 < 4) {
					listNextIdTileBoard[1] = nextIdTileBoard - 5;
					listNextIdTileBoard[2] = nextIdTileBoard - 4;
				} else if (currentIdTileBoard % 10 > 5) {
					listNextIdTileBoard[1] = nextIdTileBoard - 6;
					listNextIdTileBoard[2] = nextIdTileBoard - 5;
				} else if (currentIdTileBoard % 10 == 4) {
					listNextIdTileBoard[1] = nextIdTileBoard - 5;
				} else if (currentIdTileBoard % 10 == 5) {
					listNextIdTileBoard[1] = nextIdTileBoard - 5;
				}
			}

			if (currentCheckerTag.indexOf("black") != -1) {
				if (currentIdTileBoard % 10 <= 4) {
					nextIdTileBoard += 5;
				} else if (currentIdTileBoard % 10 >= 5) {
					nextIdTileBoard += 4;
				}
			}

		} else if (currentCheckerTag.indexOf("super") != -1) {

		}
		return listNextIdTileBoard;
	}

	public void setCheckerList(ArrayList<Sprites> listCheckers) {
		this.listCheckers = listCheckers;
	}

	public int[] getPossiton(int idTileBoard) {
		return this.mapBoard.get(idTileBoard);
	}
}
