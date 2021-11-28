package com.comflip.game.lists.gui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.comflip.engine.Collisions;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.Sprites;

public class MapBoard extends GUI {
	HashMap<Integer, int[]> mapBoard = new HashMap<Integer, int[]>();

	ArrayList<Sprites> listCheckers = new ArrayList<Sprites>();
	private int currentIdTileBoard;
	private String currentCheckerTag;

	HashMap<String, HashMap<Integer, String>> mapTravel = new HashMap<String, HashMap<Integer, String>>();

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

					String colorChecker = currentCheckerTag.split("_")[0];
					String modeChecker = currentCheckerTag.split("_")[1];

					modeSelection(colorChecker, modeChecker, hashMapLines(currentIdTileBoard));
				}

				if (input.isButtonUp(MouseEvent.BUTTON1)) {
					if (checker.tag.equals(currentCheckerTag)) {
						boolean isSetIdTileBoard = false;

						for (String line : mapTravel.keySet()) {
							HashMap<Integer, String> mapLine = mapTravel.get(line);

							for (int idTileBoard : mapLine.keySet()) {

								String currentColor = currentCheckerTag.split("_")[0];
								String checkerColor = mapLine.get(idTileBoard);

								if (checkerColor.equals("empty")) {
									int emptyX = this.mapBoard.get(idTileBoard)[0] - 3;
									int emptyY = this.mapBoard.get(idTileBoard)[1] - 3;

									boolean axisEmptyX = Collisions.axisX(checker.posX + (checker.width / 2), 0, emptyX,
											29);
									boolean axisEmptyY = Collisions.axisY(checker.posY + (checker.height / 2), 0,
											emptyY, 29);

									if (axisEmptyX && axisEmptyY) {
										checker.posX = emptyX + 3;
										checker.posY = emptyY + 3;
//										System.out.println(currentColor + " went from " + checker.getIdTileBoard() + " cell to " + idTileBoard + " cells."); 
										checker.setIdTileBoard(idTileBoard);
										killChecker(currentColor, checker.getIdTileBoard(), line, mapLine);
										Layer.GAME.changeTurn(currentColor);
										isSetIdTileBoard = true;
									}

									if (currentColor.equals("white") && checker.getIdTileBoard() <= 4) {
										checker.tag = currentCheckerTag.replaceAll("normal", "super");
									} else if (currentColor.equals("black") && checker.getIdTileBoard() >= 45) {
										checker.tag = currentCheckerTag.replaceAll("normal", "super");
									}
								}
							}
						}

						if (!isSetIdTileBoard) {
							checker.posX = this.mapBoard.get(currentIdTileBoard)[0];
							checker.posY = this.mapBoard.get(currentIdTileBoard)[1];
						}
					}
				}
			}
		}
	}

	private void killChecker(String currentColor, int currentIdTileBoard, String line,
			HashMap<Integer, String> mapLine) {
		for (int enemyIdTileBoard : mapLine.keySet()) {
			String checkerColor = null;

			if (line.equals("UL") || line.equals("UR")) {
				if (currentIdTileBoard < enemyIdTileBoard) {
					checkerColor = mapLine.get(enemyIdTileBoard);
				}
			}

			if (line.equals("DL") || line.equals("DR")) {
				if (currentIdTileBoard > enemyIdTileBoard) {
					checkerColor = mapLine.get(enemyIdTileBoard);
				}
			}

			if (checkerColor != null) {
				if (!currentColor.equals(checkerColor) && !checkerColor.equals("empty")) {
					for (int i = 0; i < listCheckers.size(); i++) {
						Sprites enemyChecker = listCheckers.get(i);
						if (enemyChecker.getIdTileBoard() == enemyIdTileBoard) {
							listCheckers.remove(enemyChecker);
							break;
						}
					}
				}
			}
		}
	}

	public void render(Renderer r) {
		if (!listCheckers.isEmpty()) {
			for (int i = 0; i < listCheckers.size(); i++) {
				Sprites checker = listCheckers.get(i);
				if (checker.isPickUp()) {
					currentIdTileBoard = checker.getIdTileBoard();

					int x = this.mapBoard.get(currentIdTileBoard)[0] - 3;
					int y = this.mapBoard.get(currentIdTileBoard)[1] - 3;
					r.drawFillRect(x + 1, y + 1, 27, 27, 0x55FFFF66);
					r.drawRect(x, y, 29, 29, 0xFFCCCC00);

					for (String line : mapTravel.keySet()) {
						HashMap<Integer, String> mapLine = mapTravel.get(line);

						for (int idTileBoard : mapLine.keySet()) {

							String currentColor = currentCheckerTag.split("_")[0];
							String checkerColor = mapLine.get(idTileBoard);

							if (checkerColor.equals("empty")) {
								int emptyX = this.mapBoard.get(idTileBoard)[0] - 3;
								int emptyY = this.mapBoard.get(idTileBoard)[1] - 3;

								r.drawFillRect(emptyX + 1, emptyY + 1, 27, 27, 0x5599DDFF);
								r.drawRect(emptyX, emptyY, 29, 29, 0xFF66CCFF);
							}

							if (!checkerColor.equals(currentColor) && !checkerColor.equals("empty")) {
								int enemyX = this.mapBoard.get(idTileBoard)[0] - 3;
								int enemyY = this.mapBoard.get(idTileBoard)[1] - 3;

								r.drawFillRect(enemyX + 1, enemyY + 1, 27, 27, 0x55FF6666);
								r.drawRect(enemyX, enemyY, 29, 29, 0xFFCC0000);
							}
						}
					}
				}
			}
		}
	}

	private void modeSelection(String colorChecker, String modeChecker,
			HashMap<String, HashMap<Integer, String>> hashMapLines) {

		boolean isEnemy = false;
		ArrayList<String> listKeysEnemy = new ArrayList<String>();

		// Search for the enemy in line and return line where enemy
		for (String line : hashMapLines.keySet()) {
			HashMap<Integer, String> mapLine = hashMapLines.get(line);
			ArrayList<Integer> listKeys = new ArrayList<Integer>(mapLine.keySet());

			int firstKey = 0;
			int secondKey = 0;

			if (line.equals("DL") || line.equals("DR")) {
				Collections.sort(listKeys);
			}

			if (line.equals("UL") || line.equals("UR")) {
				Collections.sort(listKeys, Collections.reverseOrder());
			}

			for (int i = 0; i < listKeys.size() - 1; i++) {
				if (modeChecker.equals("normal")) {
					firstKey = listKeys.get(0);
					secondKey = listKeys.get(1);
				}

				if (modeChecker.equals("super")) {
					firstKey = listKeys.get(i);
					secondKey = listKeys.get(i + 1);
				}

				if (!mapLine.get(firstKey).equals(colorChecker) && !mapLine.get(firstKey).equals("empty")
						&& mapLine.get(secondKey).equals("empty")) {
					isEnemy = true;
					listKeysEnemy.add(line);
				}
				
				if (!mapLine.get(firstKey).equals("empty") && !mapLine.get(secondKey).equals("empty")) {
					break;
				}
			}
		}

		
		for (String line : hashMapLines.keySet()) {
			HashMap<Integer, String> newMapLine = new HashMap<Integer, String>();

			HashMap<Integer, String> mapLine = hashMapLines.get(line);
			ArrayList<Integer> listKeys = new ArrayList<Integer>(mapLine.keySet());

			if (line.equals("DL") || line.equals("DR")) {
				Collections.sort(listKeys);
			}

			if (line.equals("UL") || line.equals("UR")) {
				Collections.sort(listKeys, Collections.reverseOrder());
			}

			for (int i = 0; i < listKeys.size() - 1; i++) {
				if (modeChecker.equals("normal")) {

					int firstKey = listKeys.get(0);
					int secondKey = listKeys.get(1);

					if (colorChecker.equals("white")) {
						if (mapLine.get(firstKey).equals("empty") && !isEnemy) {
							if (line.equals("UL") || line.equals("UR")) {
								newMapLine.put(firstKey, mapLine.get(firstKey));
							}
						}

					}

					if (colorChecker.equals("black")) {
						if (mapLine.get(firstKey).equals("empty") && !isEnemy) {
							if (line.equals("DL") || line.equals("DR")) {
								newMapLine.put(firstKey, mapLine.get(firstKey));
							}
						}
					}

					if (isEnemy) {
						for (String lineEnemy : listKeysEnemy) {
							if (line.equals(lineEnemy)) {
								newMapLine.put(firstKey, mapLine.get(firstKey));
								newMapLine.put(secondKey, mapLine.get(secondKey));
							}
						}
					}
					
					if (mapLine.get(firstKey).equals(colorChecker) && mapLine.get(secondKey).equals("empty")) {
						newMapLine.remove(firstKey);
						newMapLine.remove(secondKey);
						break;
					}

					if (!mapLine.get(firstKey).equals("empty") && !mapLine.get(secondKey).equals("empty")) {
						newMapLine.remove(firstKey);
						newMapLine.remove(secondKey);
						break;
					}
				}

				if (modeChecker.equals("super")) {
					int firstKey = listKeys.get(i);
					int secondKey = listKeys.get(i + 1);

					if (mapLine.get(firstKey).equals("empty") && mapLine.get(secondKey).equals("empty") && !isEnemy) {
						newMapLine.put(firstKey, mapLine.get(firstKey));
						newMapLine.put(secondKey, mapLine.get(secondKey));
					}
					if (mapLine.get(firstKey).equals("empty") && !mapLine.get(secondKey).equals("empty") && !isEnemy) {
						newMapLine.put(firstKey, mapLine.get(firstKey));
					}

					if (isEnemy) {

						for (String lineEnemy : listKeysEnemy) {

							if (line.equals(lineEnemy)) {
								if (!mapLine.get(firstKey).equals("empty")) {
									
									for (int j = i; j < listKeys.size(); j++) {
										int nextKey = listKeys.get(j);
									
										if (j == listKeys.size() - 1) {
											if (!mapLine.get(nextKey).equals("empty")) {
												break;
											}
										}
										
										newMapLine.put(nextKey, mapLine.get(nextKey));
									}
								}
							}
						}
					}

					if (mapLine.get(firstKey).equals(colorChecker) && mapLine.get(secondKey).equals("empty")) {
						newMapLine.remove(firstKey);
						newMapLine.remove(secondKey);
						break;
					}

					if (!mapLine.get(firstKey).equals("empty") && !mapLine.get(secondKey).equals("empty")) {
						newMapLine.remove(firstKey);
						newMapLine.remove(secondKey);
						break;
					}
				}
			}

			if (listKeys.size() == 1) {
				int key = listKeys.get(0);
				if (modeChecker.equals("normal")) {
					if (colorChecker.equals("white") && !isEnemy) {
						if (line.equals("UL") || line.equals("UR")) {
							if (mapLine.get(key).equals("empty")) {
								newMapLine.put(key, mapLine.get(key));
							}
						}
					}

					if (colorChecker.equals("black") && !isEnemy) {
						if (line.equals("DL") || line.equals("DR")) {
							if (mapLine.get(key).equals("empty")) {
								newMapLine.put(key, mapLine.get(key));
							}
						}
					}
				}

				if (modeChecker.equals("super") && !isEnemy) {
					if (mapLine.get(key).equals("empty")) {
						newMapLine.put(key, mapLine.get(key));
					}
				}
			}

			mapTravel.put(line, newMapLine);
		}
	}

	private HashMap<String, HashMap<Integer, String>> hashMapLines(int positionChecker) {
		HashMap<String, HashMap<Integer, String>> hashMapLines = new HashMap<String, HashMap<Integer, String>>();

		// Calculating cells along the diagonal
		int upperLeft = positionChecker;
		HashMap<Integer, String> mapUpperLeft = new HashMap<Integer, String>();
		for (int i = 0; i < 10; i++) {
			if (upperLeft % 10 <= 4) {
				upperLeft -= 5;
			} else if (upperLeft % 10 > 5) {
				upperLeft -= 6;
			}

			if (upperLeft < 0) {
				break;
			}

			mapUpperLeft.put(upperLeft, "empty");

			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				String checkerColor = checker.tag.split("_")[0];
				if (checker.getIdTileBoard() == upperLeft) {
					if (checkerColor.equals("white")) {
						mapUpperLeft.put(upperLeft, "white");
					}

					if (checkerColor.equals("black")) {
						mapUpperLeft.put(upperLeft, "black");
					}
				}
			}

			if (upperLeft % 10 == 5 || upperLeft <= 4) {
				break;
			}
		}
		hashMapLines.put("UL", mapUpperLeft);

		// Calculating cells along the diagonal
		int upperRight = positionChecker;
		HashMap<Integer, String> mapUpperRight = new HashMap<Integer, String>();
		for (int i = 0; i < 10; i++) {
			if (upperRight % 10 < 4) {
				upperRight -= 4;
			} else if (upperRight % 10 >= 5) {
				upperRight -= 5;
			}

			if (upperRight < 0) {
				break;
			}

			mapUpperRight.put(upperRight, "empty");

			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				String checkerColor = checker.tag.split("_")[0];
				if (checker.getIdTileBoard() == upperRight) {
					if (checkerColor.equals("white")) {
						mapUpperRight.put(upperRight, "white");
					}

					if (checkerColor.equals("black")) {
						mapUpperRight.put(upperRight, "black");
					}
				}
			}

			if (upperRight % 10 == 4 || upperRight <= 4) {
				break;
			}
		}
		hashMapLines.put("UR", mapUpperRight);

		// Calculating cells along the diagonal
		int downLeft = positionChecker;
		HashMap<Integer, String> mapDownLeft = new HashMap<Integer, String>();
		for (int i = 0; i < 10; i++) {
			if (downLeft % 10 <= 4) {
				downLeft += 5;
			} else if (downLeft % 10 > 5) {
				downLeft += 4;
			}

			if (downLeft > 49) {
				break;
			}

			mapDownLeft.put(downLeft, "empty");

			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				String checkerColor = checker.tag.split("_")[0];
				if (checker.getIdTileBoard() == downLeft) {
					if (checkerColor.equals("white")) {
						mapDownLeft.put(downLeft, "white");
					}

					if (checkerColor.equals("black")) {
						mapDownLeft.put(downLeft, "black");
					}
				}
			}

			if (downLeft % 10 == 5 || downLeft >= 45) {
				break;
			}

		}
		hashMapLines.put("DL", mapDownLeft);

		// Calculating cells along the diagonal
		int downRight = positionChecker;
		HashMap<Integer, String> mapDownRight = new HashMap<Integer, String>();
		for (int i = 0; i < 10; i++) {
			if (downRight % 10 < 4) {
				downRight += 6;
			} else if (downRight % 10 >= 5) {
				downRight += 5;
			}

			if (downRight > 49) {
				break;
			}

			mapDownRight.put(downRight, "empty");

			for (int j = 0; j < listCheckers.size(); j++) {
				Sprites checker = listCheckers.get(j);
				String checkerColor = checker.tag.split("_")[0];
				if (checker.getIdTileBoard() == downRight) {
					if (checkerColor.equals("white")) {
						mapDownRight.put(downRight, "white");
					}

					if (checkerColor.equals("black")) {
						mapDownRight.put(downRight, "black");
					}
				}
			}

			if (downRight % 10 == 4 || downRight >= 45) {
				break;
			}

		}
		hashMapLines.put("DR", mapDownRight);

		return hashMapLines;
	}

	public void setCheckerList(ArrayList<Sprites> listCheckers) {
		this.listCheckers = listCheckers;
	}

	public int[] getPossiton(int idTileBoard) {
		return this.mapBoard.get(idTileBoard);
	}
}