package com.comflip.engine.gfc;

public class SpriteTile extends Sprite {
	private final int tileWidth, tileHeight;

	public SpriteTile(String path, int tileWidth, int tileHeight) {
		super(path);
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		this.scale = 1;
	}

	public Sprite getTileSprite(int tileX, int tileY) {
		int[] pixelTile = new int[tileWidth * tileHeight];
		for (int y = 0; y < tileHeight; y++) {
			if (tileWidth >= 0)
				System.arraycopy(this.pixel, (tileX * tileWidth) + (y + tileY * tileHeight) * this.width, pixelTile, y * tileWidth, tileWidth);
		}
		return new Sprite(pixelTile, tileWidth, tileHeight);
	}
	
	public int getTileWidth() {
		return Math.round(this.tileWidth * scale);
	}

	public int getTileHeight() {
		return Math.round(this.tileHeight * scale);
	}
}
