package com.comflip.engine;

public class GameObject {
	protected int w, h;
	protected int[] p;
	protected boolean alpha = false;

	protected int scale;

	protected int tileW, tileH;

	public GameObject(int w, int h) {
		this.w = w;
		this.h = h;
	}

	public GameObject(String path) {
	}

	public void setScale(int scale) {
		this.scale = scale;
		setH(h * scale);
		setW(w * scale);

		int[] p = new int[w * h];
		for (int y = 0; y < w; y++) {
			for (int x = 0; x < h; x++) {
				p[x + y * w] = this.p[x + y * w];
			}
		}
	}

	public int getW() {
		return this.w;
	}

	public int getH() {
		return this.h;
	}

	public int[] getP() {
		return this.p;
	}

	public boolean isAlpha() {
		return this.alpha;
	}

	public void setAlpha(boolean alpha) {
		this.alpha = alpha;
	}

	public int getScale() {
		return this.scale;
	}

	public int getTileW() {
		return this.tileW;
	}

	public void setTileW(int tileW) {
		this.tileW = tileW;
	}

	public int getTileH() {
		return this.tileH;
	}

	public void setTileH(int tileH) {
		this.tileH = tileH;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setP(int[] p) {
		this.p = p;
	}
}
