package com.comflip.game;

import java.util.ArrayList;

import com.comflip.engine.AbstractGame;
import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.audio.SoundClip;
import com.comflip.engine.gfc.Sprite;
import com.comflip.engine.gfc.SpriteTile;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layers;
import com.comflip.game.lists.SFX;
import com.comflip.game.lists.Sprites;

public class LoaderManager implements AbstractGame {
	public Sprite image;
	public SpriteTile imageTile;
	public SoundClip soundClip;
	
	public String tag;
	public float frame;
	public int indexTileX, indexTileY;

	public int width, height;
	public int posX, posY;

	private int FPS;

	ArrayList<Layers> listLayers = new ArrayList<Layers>();
	ArrayList<GUI> listGUI = new ArrayList<GUI>();
	ArrayList<Sprites> listSprites = new ArrayList<Sprites>();
	ArrayList<SFX> listSFXs = new ArrayList<SFX>();

	public LoaderManager() {
		listLayers.add(Layers.menu);
		listLayers.add(Layers.game);

		listGUI.add(GUI.cursor);
	}

	public void update(GameContainer gc, float dt) {
		if (!listLayers.isEmpty()) {
			for (int i = 0; i < listLayers.size(); i++) {
				if (listLayers.get(i) != null) {
					switch (listLayers.get(i).tag) {
					case "menu":
						listLayers.get(i).update(gc, dt);
						break;
					case "gameMode_1":
						listLayers.get(i).update(gc, dt);
						break;
					case "gameMode_2":
						listLayers.get(i).update(gc, dt);
						break;

					default:
						break;
					}
				}
			}
		}

		if(!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				if (listGUI.get(i) != null) {
					try {
						listGUI.get(i).update(gc, dt);
					} catch (Exception e) {
						System.err.println("Object " + listGUI.get(i).tag + " is missed");
					}
				}
			}
		}
	}

	public GameObject render(Renderer r) {
		if (!listLayers.isEmpty()) {
			for (int i = 0; i < listLayers.size(); i++) {
				if (listLayers.get(i) != null) {
					switch (listLayers.get(i).tag) {
					case "menu":
						listLayers.get(i).render(r);
						break;
					case "gameMode_1":
						listLayers.get(i).render(r);
						break;
					case "gameMode_2":
						listLayers.get(i).render(r);
						break;

					default:
						break;
					}
				}
			}
		}

		if(!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				if (listGUI.get(i) != null) {
					listGUI.get(i).render(r).setScale(0.6f);
				}
			}
		}
		
		if (Main.getArgs().length > 0) {
			for (String arg : Main.getArgs()) {
				if (arg.equals("-debugMode")) {
//					debugMode(r);
				}
			}
		}

		if(!listSprites.isEmpty()) {
			for(int i=0; i < listSprites.size(); i++) {
				
			}
		}
		 
		r.drawText("Test Engine", 0, 0, 0xFFFFFFFF);
		r.drawText("FPS: " + FPS, 8, 12, 0xff00ffff);
		
		return null;
	}

//	private void debugMode(Renderer r) {
//		for (Sprites object : listSprites) {
//			if (object.image != null) {
//				r.drawRect(object.posX, object.posY, object.image.getW(), object.image.getH(), 0xFF00FF00);
//			}
//			if (object.imageTile != null) {
//				r.drawRect(object.posX, object.posY, object.imageTile.getTileW(), object.imageTile.getTileH(),
//						0xFF00FF00);
//			}
//		}
//
//		for (GUI elementGui : listGUI) {
//			if (elementGui.image != null) {
//				r.drawRect(elementGui.posX, elementGui.posY, elementGui.image.getW(), elementGui.image.getH(),
//						0xFF00FF00);
//			}
//			if (elementGui.imageTile != null) {
//				r.drawRect(elementGui.posX, elementGui.posY, elementGui.imageTile.getTileW(),
//						elementGui.imageTile.getTileH(), 0xFF00FF00);
//			}
//		}
//	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int setPosX(int posX) {
		return this.posX = posX;
	}

	public int setPosY(int posY) {
		return this.posY = posY;
	}

	public String getTag() {
		return this.tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	public float getFrame() {
		return this.frame;
	}

	public int getIndexTileX() {
		return this.indexTileX;
	}

	public int getIndexTileY() {
		return this.indexTileY;
	}

	public int setFPS(int fps) {
		return this.FPS = fps;
	}
}
