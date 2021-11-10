package com.comflip.engine.gfc;

import com.comflip.engine.GameObject;

public class ImageTile extends Image {
	
	public ImageTile(String path, int tileW, int tileH) {
		super(path);
		this.tileW = tileW;
		this.tileH = tileH;
	}

	public Image getTileImage(int tileX, int tileY) {
		int[] p = new int[tileW * tileH];
		for (int y = 0; y < tileH; y++) {
			for (int x = 0; x < tileH; x++) {
				p[x + y * tileW] = this.p[(x + tileX * tileW) + (y + tileY * tileH) * w];
			}
		}
		return (Image) new GameObject(p, tileW, tileH);
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
}
