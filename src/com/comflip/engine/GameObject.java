package com.comflip.engine;

public class GameObject {
	protected int width, height;
	
	private GameObject gameObject;

	public GameObject(GameObject gameObject, int width, int height) {
		this.setGameObject(gameObject);
		this.width = width;
		this.height = height;
	}

	protected GameObject(String path) {
	}

	protected GameObject(int[] pixel, int tileWidth, int tileHeight) {
	}

	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int[] getPixel() {
		return getGameObject().getPixel();
	}

	public boolean isAlpha() {
		return getGameObject().isAlpha();
	}

	public void setAlpha(boolean alpha) {
		getGameObject().setAlpha(alpha);
	}

	public int getTileWidth() {
		return getGameObject().getTileWidth();
	}

	public void setTileWidth(int tileWidth) {
		getGameObject().setTileWidth(tileWidth);
	}

	public int getTileHeight() {
		return getGameObject().getTileWidth();
	}

	public void setTileHeight(int tileHeight) {
		getGameObject().getTileHeight();
	}

	public void setScale(float scale) {
		getGameObject().setScale(scale);
	}

	public float getScale() {
		return getGameObject().getScale();
	}

	public void setScaleTile(float scale) {
		getGameObject().setScaleTile(scale);
	}
}
