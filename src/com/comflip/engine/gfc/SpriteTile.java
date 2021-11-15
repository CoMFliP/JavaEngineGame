package com.comflip.engine.gfc;

public class SpriteTile extends Sprite {
	private int tileWidht, tileHeight;

	public SpriteTile(String path, int tileWidht, int tileHeight) {
		super(path);
		this.tileHeight = tileHeight;
		this.tileWidht = tileWidht;
		this.scale = 1;
	}

	public Sprite getTileSprite(int tileX, int tileY) {
		int[] pixelTile = new int[tileWidht * tileHeight];
		for (int y = 0; y < tileHeight; y++) {
			for (int x = 0; x < tileWidht; x++) {
				pixelTile[x + y * tileWidht] = this.pixel[(x + tileX * tileWidht)
						+ (y + tileY * tileHeight) * this.width];
			}
		}
		return new Sprite(pixelTile, tileWidht, tileHeight);
	}
	
	public int getTileWidth() {
		return Math.round(this.tileWidht * scale);
	}

	public int getTileHeight() {
		return Math.round(this.tileHeight * scale);
	}
}
