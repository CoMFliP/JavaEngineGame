package com.comflip.engine;

public class GameObject {
	protected int w, h;
	protected int[] p;
	protected boolean alpha = false;
	
	protected int tileW, tileH;
	
	public GameObject(int[] p, int w, int h) {
		this.p = p;
		this.w = w;
		this.h = h;
	}

	public GameObject(String path) {
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
}
