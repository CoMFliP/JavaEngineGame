package com.comflip.engine;

public class GameObject {
	protected int width, height;
	
	private GameObject gameObject;

	public GameObject(GameObject gameObject, int width, int height) {
		this.gameObject = gameObject;
		this.width = width;
		this.height = height;
	}

	public GameObject() {
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int[] getPixel() {
		return this.gameObject.getPixel();
	}

	public boolean isAlpha() {
		return this.gameObject.isAlpha();
	}

	public void setAlpha(boolean alpha) {
		this.gameObject.setAlpha(alpha);
	}

	public int getTileWidth() {
		return this.gameObject.getTileWidth();
	}

	public void setTileWidth(int tileWidth) {
		this.gameObject.setTileWidth(tileWidth);
	}

	public int getTileHeight() {
		return this.gameObject.getTileWidth();
	}

	public void setTileHeight(int tileHeight) {
		this.gameObject.getTileHeight();
	}

	public void setScale(float scale) {
		this.gameObject.setScale(scale);
	}

	public float getScale() {
		return this.gameObject.getScale();
	}
}
