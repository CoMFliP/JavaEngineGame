package com.comflip.game;

import com.comflip.engine.AbstractGame;
import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.audio.SoundClip;
import com.comflip.engine.gfc.Image;
import com.comflip.engine.gfc.ImageTile;
import com.comflip.game.abstracts.GUI;
import com.comflip.game.abstracts.Objects;

public class LoaderManager implements AbstractGame {
	public Image image;
	public ImageTile imageTile;
	public SoundClip soundClip;

	protected String tag;
	protected float frame;
	protected int indexTileX, indexTileY;

	protected int posX, posY;

	public void update(GameContainer gc, float dt) {
		for (Objects object : Objects.ArrayList()) {
			try {
				object.update(gc, dt);
			} catch (Exception e) {
				System.err.println("Object " + object.tag + " is missed");
			}
		}

		for (GUI elementGui : GUI.ArrayList()) {
			try {
				elementGui.update(gc, dt);
			} catch (Exception e) {
				System.err.println("Object " + elementGui.tag + " is missed");
			}
		}
	}

	public void render(Renderer r) {
		for (Objects object : Objects.ArrayList()) {
			if (object.image != null) {
				r.drawImage(object.image, object.posX, object.posY);
			}
			if (object.imageTile != null) {
				r.drawImageTile(object.imageTile, object.posX, object.posY, object.indexTileX, object.indexTileY);
			}
		}

		for (GUI elementGui : GUI.ArrayList()) {
			if (elementGui.image != null) {
				r.drawImage(elementGui.image, elementGui.posX, elementGui.posY);
			}
			if (elementGui.imageTile != null) {
				r.drawImageTile(elementGui.imageTile, elementGui.posX, elementGui.posY, elementGui.indexTileX,
						elementGui.indexTileY);
			}
		}
	}

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

	public float getFrame() {
		return this.frame;
	}

	public int getIndexTileX() {
		return this.indexTileX;
	}

	public int getIndexTileY() {
		return this.indexTileY;
	}
}
