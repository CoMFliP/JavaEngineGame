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
		listLayers.add(Layer.SELECT_NAME);

		listGUI.add(GUI.CURSOR);

		if (Layer.MENU != null) {
			Layer.MENU.isActive = true;
		}
	}

	public void update(GameContainer gc, float dt) {
		if (!listLayers.isEmpty()) {
			for (int i = 0; i < listLayers.size(); i++) {
				Layer layer = listLayers.get(i);
				if (layer.isActive && layer.equals(Layer.SELECT_NAME)) {
					layer.update(gc, dt);
				} else if (layer.isActive && layer.equals(Layer.MENU)){
					layer.update(gc, dt);
				} else if (layer.isActive && layer.equals(Layer.GAME)){
					layer.update(gc, dt);
				}
			}
		}

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				elementGui.update(gc, dt);
			}
		}
	}

	public void render(Renderer r) {

		if (!listLayers.isEmpty()) {
			for (int i = 0; i < listLayers.size(); i++) {
				Layer layer = listLayers.get(i);
				if (layer.isActive) {
					layer.render(r);
				}
			}
		}

		if (!listGUI.isEmpty()) {
			for (int i = 0; i < listGUI.size(); i++) {
				GUI elementGui = listGUI.get(i);
				elementGui.image.setScale(0.6f);
				elementGui.render(r);
			}
		}

		r.drawText("FPS: " + FPS, 0, 0, 0xff00ffff);
	}

	public int setFPS(int fps) {
		return this.FPS = fps;
	}
}
