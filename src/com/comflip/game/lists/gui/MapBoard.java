package com.comflip.game.lists.gui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PrimitiveIterator.OfDouble;

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
	private String currentCheckerTag;
	private boolean isPickUpChecker;

	int[] listNextIdTileBoard;

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

					selectionRules();
				}

				if (input.isButtonUp(MouseEvent.BUTTON1)) {
					if (checker.tag.equals(currentCheckerTag)) {
						boolean isSetIdTileBoard = false;

						for (int j = 0; j < listNextIdTileBoard.length; j++) {
							if (this.mapBoard.get(listNextIdTileBoard[j]) != null) {
								int newX = this.mapBoard.get(listNextIdTileBoard[j])[0] - 3;
								int newY = this.mapBoard.get(listNextIdTileBoard[j])[1] - 3;

								boolean axisNewX = Collisions.axisX(checker.posX + (checker.width / 2), 0, newX, 29);
								boolean axisNewY = Collisions.axisY(checker.posY + (checker.height / 2), 0, newY, 29);

								if (axisNewX && axisNewY) {
									checker.posX = newX + 3;
									checker.posY = newY + 3;
									checker.setIdTileBoard(listNextIdTileBoard[j]);
									isSetIdTileBoard = true;
								}
							}
						}

						if (!isSetIdTileBoard) {
							checker.posX = this.mapBoard.get(currentIdTileBoard)[0];
							checker.posY = this.mapBoard.get(currentIdTileBoard)[1];
						}
					}
					isPickUpChecker = false;
				}
			}
		}
	}

	public void render(Renderer r) {
		if (isPickUpChecker) {
			int x = this.mapBoard.get(currentIdTileBoard)[0] - 3;
			int y = this.mapBoard.get(currentIdTileBoard)[1] - 3;
			r.drawFillRect(x + 1, y + 1, 27, 27, 0x55FF6666);
			r.drawRect(x, y, 29, 29, 0xFFCC0000);

			for (int i = 0; i < listNextIdTileBoard.length; i++) {
				if (this.mapBoard.get(listNextIdTileBoard[i]) != null) {
					if (this.mapBoard.get(listNextIdTileBoard[i]) != this.mapBoard.get(currentIdTileBoard)) {
						int newX = this.mapBoard.get(listNextIdTileBoard[i])[0] - 3;
						int newY = this.mapBoard.get(listNextIdTileBoard[i])[1] - 3;

						r.drawFillRect(newX + 1, newY + 1, 27, 27, 0x5599DDFF);
						r.drawRect(newX, newY, 29, 29, 0xFF66CCFF);
					}
				}
			}
		}
	}

	private void selectionRules() {
		ArrayList<Integer> newListNextIdTileBoard = new ArrayList<Integer>();

		int countMoveUpperLeft = 0;
		int countMoveUpperRight = 0;
		int countMoveDownLeft = 0;
		int countMoveDownRigth = 0;

		if (currentCheckerTag.indexOf("normal") != -1) {
			String currentColor = "";

			if (currentCheckerTag.indexOf("white") != -1) {
				countMoveUpperLeft = 1;
				countMoveUpperRight = 1;
				currentColor = "white";
			}

			if (currentCheckerTag.indexOf("black") != -1) {
				countMoveDownLeft = 1;
				countMoveDownRigth = 1;
				currentColor = "black";
			}

			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				String checkerColor = "";

				if (checker.tag.indexOf("white") != -1) {
					checkerColor = "white";
				}

				if (checker.tag.indexOf("black") != -1) {
					checkerColor = "black";
				}

				if (currentColor != checkerColor) {
					if (currentIdTileBoard % 10 <= 4) {
						if (checker.getIdTileBoard() == currentIdTileBoard - 5
								|| checker.getIdTileBoard() == currentIdTileBoard + 6) {
							countMoveUpperLeft = 2;
							countMoveDownRigth = 2;
						} else if (checker.getIdTileBoard() == currentIdTileBoard - 4
								|| checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveUpperRight = 2;
							countMoveDownLeft = 2;
						}
					} else if (currentIdTileBoard % 10 >= 5) {
						if (checker.getIdTileBoard() == currentIdTileBoard - 6
								|| checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveUpperLeft = 2;
							countMoveDownRigth = 2;
						} else if (checker.getIdTileBoard() == currentIdTileBoard - 5
								|| checker.getIdTileBoard() == currentIdTileBoard + 4) {
							countMoveUpperRight = 2;
							countMoveDownLeft = 2;
						}
					}
				}
			}

		} else if (currentCheckerTag.indexOf("super") != -1) {

		}

		int upperLeft = currentIdTileBoard;
		for (int i = 0; i < countMoveUpperLeft; i++) {
			if (upperLeft % 10 <= 4) {
				upperLeft -= 5;
			} else if (upperLeft % 10 > 5) {
				upperLeft -= 6;
			}

			newListNextIdTileBoard.add(upperLeft);

			if (upperLeft < 0) {
				newListNextIdTileBoard.remove((Integer) upperLeft);
				break;
			} else if (upperLeft % 10 == 5) {
				break;
			}
		}

		int upperRight = currentIdTileBoard;
		for (int i = 0; i < countMoveUpperRight; i++) {
			if (upperRight % 10 < 4) {
				upperRight -= 4;
			} else if (upperRight % 10 >= 5) {
				upperRight -= 5;
			}

			newListNextIdTileBoard.add(upperRight);

			if (upperRight < 0) {
				newListNextIdTileBoard.remove((Integer) upperRight);
				break;
			} else if (upperRight % 10 == 4) {
				break;
			}
		}

		int downLeft = currentIdTileBoard;
		for (int i = 0; i < countMoveDownLeft; i++) {
			if (downLeft % 10 <= 4) {
				downLeft += 5;
			} else if (downLeft % 10 > 5) {
				downLeft += 4;
			}

			newListNextIdTileBoard.add(downLeft);

			if (downLeft > 50) {
				newListNextIdTileBoard.remove((Integer) downLeft);
				break;
			} else if (downLeft % 10 == 5) {
				break;
			}
		}

		int downRight = currentIdTileBoard;
		for (int i = 0; i < countMoveDownRigth; i++) {
			if (downRight % 10 < 4) {
				downRight += 6;
			} else if (downRight % 10 >= 5) {
				downRight += 5;
			}

			newListNextIdTileBoard.add(downRight);

			if (downRight > 50) {
				newListNextIdTileBoard.remove((Integer) downRight);
				break;
			} else if (downRight % 10 == 4) {
				break;
			}
		}

		Collections.sort(newListNextIdTileBoard);

		listNextIdTileBoard = new int[newListNextIdTileBoard.size()];

		for (int i = 0; i < newListNextIdTileBoard.size(); i++) {
			listNextIdTileBoard[i] = newListNextIdTileBoard.get(i);
			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				if (checker.getIdTileBoard() == newListNextIdTileBoard.get(i)) {
					listNextIdTileBoard[i] = -1;
					break;
				}
			}
		}

		newListNextIdTileBoard.clear();

	}

	public void setCheckerList(ArrayList<Sprites> listCheckers) {
		this.listCheckers = listCheckers;
	}

	public int[] getPossiton(int idTileBoard) {
		return this.mapBoard.get(idTileBoard);
	}
}
