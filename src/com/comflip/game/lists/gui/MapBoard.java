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
	private String currentCheckerTag;
	private boolean isPickUpChecker;

	HashMap<Integer, HashMap<String, Integer>> mapLines;

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

						for (int idLine = 0; idLine < mapLines.size(); idLine++) {
							HashMap<String, Integer> mapLine = mapLines.get(idLine);
							for (String status : mapLine.keySet()) {
								String currentColor = "";

								if (currentCheckerTag.indexOf("white") != -1) {
									currentColor = "white";
								}

								if (currentCheckerTag.indexOf("black") != -1) {
									currentColor = "black";
								}

								if (status == "empty") {
									if (mapLine.get(status) != null) {
										int emptyIdTileBoard = mapLine.get(status);
										if (this.mapBoard.get(emptyIdTileBoard) != null) {
											int emptyX = this.mapBoard.get(emptyIdTileBoard)[0] - 3;
											int emptyY = this.mapBoard.get(emptyIdTileBoard)[1] - 3;

											boolean axisEmptyX = Collisions.axisX(checker.posX + (checker.width / 2), 0,
													emptyX, 29);
											boolean axisEmptyY = Collisions.axisY(checker.posY + (checker.height / 2),
													0, emptyY, 29);

											if (axisEmptyX && axisEmptyY) {
												checker.posX = emptyX + 3;
												checker.posY = emptyY + 3;
												checker.setIdTileBoard(emptyIdTileBoard);
												isSetIdTileBoard = true;

												for (String checkerColor : mapLine.keySet()) {
													if (currentColor != checkerColor && checkerColor != "empty") {
														for (int j = 0; j < listCheckers.size(); j++) {
															Sprites enemyChecker = listCheckers.get(j);
															if (enemyChecker.getIdTileBoard() == mapLine
																	.get(checkerColor)) {
																listCheckers.remove(enemyChecker);
																break;
															}
														}
													}
												}
											}
										}
									}
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
			r.drawFillRect(x + 1, y + 1, 27, 27, 0x55FFFF66);
			r.drawRect(x, y, 29, 29, 0xFFCCCC00);

			for (int idLine = 0; idLine < mapLines.size(); idLine++) {
				HashMap<String, Integer> mapLine = mapLines.get(idLine);
				for (String status : mapLine.keySet()) {
					String currentColor = "";

					if (currentCheckerTag.indexOf("white") != -1) {
						currentColor = "white";
					}

					if (currentCheckerTag.indexOf("black") != -1) {
						currentColor = "black";
					}

					if (status == "empty") {
						if (mapLine.get(status) != null) {
							int emptyIdTileBoard = mapLine.get(status);
							if (this.mapBoard.get(emptyIdTileBoard) != null) {
								int emptyX = this.mapBoard.get(emptyIdTileBoard)[0] - 3;
								int emptyY = this.mapBoard.get(emptyIdTileBoard)[1] - 3;

								r.drawFillRect(emptyX + 1, emptyY + 1, 27, 27, 0x5599DDFF);
								r.drawRect(emptyX, emptyY, 29, 29, 0xFF66CCFF);
							}
						}
					}
					if (status != currentColor && status != "empty") {
						if (mapLine.get(status) != null) {
							int notEmptyIdTileBoard = mapLine.get(status);
							if (this.mapBoard.get(notEmptyIdTileBoard) != null) {
								int notEmptyX = this.mapBoard.get(notEmptyIdTileBoard)[0] - 3;
								int notEmptyY = this.mapBoard.get(notEmptyIdTileBoard)[1] - 3;

								r.drawFillRect(notEmptyX + 1, notEmptyY + 1, 27, 27, 0x55FF6666);
								r.drawRect(notEmptyX, notEmptyY, 29, 29, 0xFFCC0000);
							}
						}
					}
				}
			}
		}
	}

	private void selectionRules() {
		mapLines = new HashMap<Integer, HashMap<String, Integer>>();

		int countMoveUpperLeft = 0;
		int countMoveUpperRight = 0;
		int countMoveDownLeft = 0;
		int countMoveDownRight = 0;

		if (currentCheckerTag.indexOf("normal") != -1) {
			String currentColor = "";

			if (currentCheckerTag.indexOf("white") != -1) {
				countMoveUpperLeft = 1;
				countMoveUpperRight = 1;
				currentColor = "white";
			}

			if (currentCheckerTag.indexOf("black") != -1) {
				countMoveDownLeft = 1;
				countMoveDownRight = 1;
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
					if (currentIdTileBoard % 10 < 4) {
						if (checker.getIdTileBoard() == currentIdTileBoard - 5) {
							countMoveUpperLeft = 2;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 6) {
							countMoveDownRight = 2;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard - 4) {
							countMoveUpperRight = 2;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveDownLeft = 2;
						}
					} else if (currentIdTileBoard % 10 > 5) {
						if (checker.getIdTileBoard() == currentIdTileBoard - 6) {
							countMoveUpperLeft = 2;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveDownRight = 2;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard - 5) {
							countMoveUpperRight = 2;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 4) {
							countMoveDownLeft = 2;
						}
					} else if (currentIdTileBoard % 10 == 4) {
						if (checker.getIdTileBoard() == currentIdTileBoard - 5) {
							countMoveUpperLeft = 2;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveDownLeft = 2;
						}
					} else if (currentIdTileBoard % 10 == 5) {
						if (checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveDownRight = 2;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard - 5) {
							countMoveUpperRight = 2;
						}
					}
				} else {
					if (currentIdTileBoard % 10 < 4) {
						if (checker.getIdTileBoard() == currentIdTileBoard - 5) {
							countMoveUpperLeft = 0;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 6) {
							countMoveDownRight = 0;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard - 4) {
							countMoveUpperRight = 0;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveDownLeft = 0;
						}
					} else if (currentIdTileBoard % 10 > 5) {
						if (checker.getIdTileBoard() == currentIdTileBoard - 6) {
							countMoveUpperLeft = 0;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveDownRight = 0;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard - 5) {
							countMoveUpperRight = 0;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 4) {
							countMoveDownLeft = 0;
						}
					} else if (currentIdTileBoard % 10 == 4) {
						if (checker.getIdTileBoard() == currentIdTileBoard - 5) {
							countMoveUpperLeft = 0;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveDownLeft = 0;
						}
					} else if (currentIdTileBoard % 10 == 5) {
						if (checker.getIdTileBoard() == currentIdTileBoard + 5) {
							countMoveDownRight = 0;
						}
						if (checker.getIdTileBoard() == currentIdTileBoard - 5) {
							countMoveUpperRight = 0;
						}
					}
				}
			}

		} else if (currentCheckerTag.indexOf("super") != -1) {
			countMoveUpperLeft = 10;
			countMoveUpperRight = 10;
			countMoveDownLeft = 10;
			countMoveDownRight = 10;
		}

		int checkersInLine;

		checkersInLine = 0;
		int upperLeft = currentIdTileBoard;
		HashMap<String, Integer> mapUpperLeft = new HashMap<String, Integer>();
		for (int i = 0; i < countMoveUpperLeft; i++) {
			if (upperLeft % 10 == 5 || upperLeft < 5) {
				mapUpperLeft.clear();
				break;
			}

			if (upperLeft % 10 <= 4) {
				upperLeft -= 5;
			} else if (upperLeft % 10 > 5) {
				upperLeft -= 6;
			}

			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				if (checker.getIdTileBoard() == upperLeft) {
					checkersInLine++;
					if (checkersInLine == 1) {
						if (checker.tag.indexOf("white") != -1) {
							mapUpperLeft.put("white", upperLeft);
						}

						if (checker.tag.indexOf("black") != -1) {
							mapUpperLeft.put("black", upperLeft);
						}
					}
				}
			}

			if (checkersInLine < 2) {
				mapUpperLeft.put("empty", upperLeft);
			}

			if (checkersInLine == 2) {
				mapUpperLeft.clear();
				break;
			}
		}
		mapLines.put(0, mapUpperLeft);

		checkersInLine = 0;
		int upperRight = currentIdTileBoard;
		HashMap<String, Integer> mapUpperRight = new HashMap<String, Integer>();
		for (int i = 0; i < countMoveUpperRight; i++) {
			if (upperRight % 10 == 4 || upperRight < 5) {
				mapUpperRight.clear();
				break;
			}

			if (upperRight % 10 < 4) {
				upperRight -= 4;
			} else if (upperRight % 10 >= 5) {
				upperRight -= 5;
			}

			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				if (checker.getIdTileBoard() == upperRight) {
					checkersInLine++;
					if (checkersInLine == 1) {
						if (checker.tag.indexOf("white") != -1) {
							mapUpperRight.put("white", upperRight);
						}

						if (checker.tag.indexOf("black") != -1) {
							mapUpperRight.put("black", upperRight);
						}
					}
				}
			}

			if (checkersInLine < 2) {
				mapUpperRight.put("empty", upperRight);
			}

			if (checkersInLine == 2) {
				mapUpperRight.clear();
				break;
			}
		}
		mapLines.put(1, mapUpperRight);

		checkersInLine = 0;
		int downLeft = currentIdTileBoard;
		HashMap<String, Integer> mapDownLeft = new HashMap<String, Integer>();
		for (int i = 0; i < countMoveDownLeft; i++) {
			if (downLeft % 10 == 5 || downLeft > 44) {
				mapDownLeft.clear();
				break;
			}
			if (downLeft % 10 <= 4) {
				downLeft += 5;
			} else if (downLeft % 10 > 5) {
				downLeft += 4;
			}

			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				if (checker.getIdTileBoard() == downLeft) {
					checkersInLine++;
					if (checkersInLine == 1) {
						if (checker.tag.indexOf("white") != -1) {
							mapDownLeft.put("white", downLeft);
						}

						if (checker.tag.indexOf("black") != -1) {
							mapDownLeft.put("black", downLeft);
						}
					}
				}
			}

			if (checkersInLine < 2) {
				mapDownLeft.put("empty", downLeft);
			}
			
			if (checkersInLine == 2) {
				mapDownLeft.clear();
				break;
			}
		}
		mapLines.put(2, mapDownLeft);

		checkersInLine = 0;
		int downRight = currentIdTileBoard;
		HashMap<String, Integer> mapDownRight = new HashMap<String, Integer>();
		for (int i = 0; i < countMoveDownRight; i++) {
			if (downRight % 10 == 4 || downRight > 44) {
				mapDownRight.clear();
				break;
			}

			if (downRight % 10 < 4) {
				downRight += 6;
			} else if (downRight % 10 >= 5) {
				downRight += 5;
			}

			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				if (checker.getIdTileBoard() == downRight) {
					checkersInLine++;
					if (checkersInLine == 1) {
						if (checker.tag.indexOf("white") != -1) {
							mapDownRight.put("white", downRight);
						}
						
						if (checker.tag.indexOf("black") != -1) {
							mapDownRight.put("black", downRight);
						}
					}
				}
			}

			if (checkersInLine < 2) {
				mapDownRight.put("empty", downRight);
			}

			if (checkersInLine == 2) {
				mapDownRight.clear();
				break;
			}
		}
		mapLines.put(3, mapDownRight);
	}

	public void setCheckerList(ArrayList<Sprites> listCheckers) {
		this.listCheckers = listCheckers;
	}

	public int[] getPossiton(int idTileBoard) {
		return this.mapBoard.get(idTileBoard);
	}
}