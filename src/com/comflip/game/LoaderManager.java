package com.comflip.game;

import java.util.ArrayList;

import com.comflip.engine.AbstractGame;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.audio.SoundClip;
import com.comflip.engine.gfc.Sprite;
import com.comflip.engine.gfc.SpriteTile;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;

public class LoaderManager implements AbstractGame {
	public Sprite image;

	public SpriteTile imageTile;
	public int indexTileX, indexTileY;
	public float frame;

	public SoundClip soundClip;

	public String tag;

	public int width, height;
	public int posX, posY;

	private int FPS;

	ArrayList<Layer> listLayers = new ArrayList<Layer>();
	ArrayList<GUI> listGUI = new ArrayList<GUI>();

	public LoaderManager() {
		listLayers.add(Layer.MENU);
		listLayers.add(Layer.GAME);

		listGUI.add(GUI.CURSOR);
		
		if (Layer.MENU != null) {
			Layer.MENU.isActive = true;
		}
	}

	public void update(GameContainer gc, float dt) {
		if (!listLayers.isEmpty()) {
			for (int i = 0; i < listLayers.size(); i++) {
				Layer layer = listLayers.get(i);
				if (layer != null) {
					if (layer.isActive) {
						layer.update(gc, dt);
					}
				}
			}
		}

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				if (elementGui != null) {
					elementGui.update(gc, dt);
				}
			}
		}
	}

	public void render(Renderer r) {

		if (!listLayers.isEmpty()) {
			for (int i = 0; i < listLayers.size(); i++) {
				Layer layer = listLayers.get(i);
				if (layer != null) {
					if (layer.isActive) {
						layer.render(r);
					}
				}
			}
		}

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				if (elementGui != null) {
					elementGui.image.setScale(0.6f);
					elementGui.render(r);
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

		r.drawText("Test Engine", 0, 0, 0xFFFFFFFF);
		r.drawText("FPS: " + FPS, 8, 12, 0xff00ffff);
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

	public int setFPS(int fps) {
		return this.FPS = fps;
	}
}
