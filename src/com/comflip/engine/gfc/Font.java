package com.comflip.engine.gfc;

import java.util.HashMap;

public class Font extends Sprite {
	public static final Font STANDARD = new Font("/fonts/new-standard.png");

	private Sprite fontImage;

	HashMap<Integer, int[]> mapFont = new HashMap<Integer, int[]>();

	private int unicode;

	private int tileFontWidth, tileFontHeight;

	public Font(String path) {
		super(path);
		fontImage = this;
		this.tileFontWidth = 12;
		this.tileFontHeight = 12;
		this.scale = 1;
		
		for (int tileY = 0; tileY < fontImage.getHeight() / this.tileFontHeight; tileY++) {
			for (int tileX = 0; tileX < fontImage.getWidth() / this.tileFontWidth; tileX++) {
				int[] fontPixel = new int[this.tileFontHeight * this.tileFontWidth];
				unicode++;
				for (int x = 0; x < this.tileFontWidth; x++) {
					for (int y = 0; y < this.tileFontHeight; y++) {
						fontPixel[x + y * this.tileFontWidth] = fontImage.getPixel()[(x + tileX * this.tileFontWidth)
						+ (y + tileY * this.tileFontHeight) * fontImage.getWidth()];
					}
				}
				this.mapFont.put(unicode, fontPixel);
				fontPixel = null;
			}
		}
	}

	public int getTileFontWidth() {
		return Math.round(this.tileFontWidth * scale);
	}

	public int getTileFontHeight() {
		return Math.round(this.tileFontHeight * scale);
	}

	public HashMap<Integer, int[]> getMapFont() {
		return this.mapFont;
	}
}
