package com.comflip.engine.gfc;

import java.util.ArrayList;
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
				ArrayList<Integer> listX = new ArrayList<Integer>();
				unicode++;

				for (int x = 0; x < this.tileFontWidth; x++) {
					int sumY = 0;
					for (int y = 0; y < this.tileFontHeight; y++) {
						if (fontImage.getPixel()[(x + tileX * this.tileFontWidth)
								+ (y + tileY * this.tileFontHeight) * fontImage.getWidth()] == Color.BLACK) {
							sumY += y;

							if (sumY == ((0 + (this.tileFontHeight - 1)) * this.tileFontHeight) / 2) {
								listX.add(x);
							}
						}
					}
				}

				int offsetLeft = 0;
				int offsetRight = 0;

				for (int i = 0; i < listX.size(); i++) {
					if (i < listX.size() - 1) {
						if (listX.get(i + 1) - listX.get(i) == 1) {
							offsetLeft++;
						} else {
							break;
						}
					}
				}

				offsetRight = (this.tileFontWidth - ((this.tileFontWidth - listX.size()) + offsetLeft)) - 1;

				int widthChar = this.tileFontWidth - (offsetLeft + offsetRight);

				int[] fontPixel = new int[this.tileFontHeight * widthChar];

				for (int newX = offsetLeft; newX < this.tileFontWidth - offsetRight; newX++) {
					for (int newY = 0; newY < this.tileFontHeight; newY++) {
						fontPixel[(newX - offsetLeft) + newY * widthChar] = fontImage
								.getPixel()[(newX + tileX * this.tileFontWidth)
										+ (newY + tileY * this.tileFontHeight) * fontImage.getWidth()];
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
