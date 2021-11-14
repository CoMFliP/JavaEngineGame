package com.comflip.engine.gfc;

public class Font extends Sprite {
	public static final Font STANDARD = new Font("/fonts/new-standard.png");
	public static final Font NONSTANDARD = new Font("/fonts/non-standard.png");

	private Sprite fontImage;

	private int unicode;

	private int tileFontWidth, tileFontHeight;

	public Font(String path) {
		super(path);
		this.tileFontWidth = 12;
		this.tileFontHeight = 12;
		
		fontImage = new Sprite(path);
	}

	public Sprite getFontImage() {
		return this.fontImage;
	}

	public void setFontImage(Sprite fontImage) {
		this.fontImage = fontImage;
	}

	public int getLine(int unicode) {
		int line = 0;
		if (unicode <= 32 && unicode >= 0) {
			line = 0;
		} else if (unicode > 32 && unicode <= 62) {
			line = 1;
		} else if (unicode > 62) {
			line = 2;
		}
		return this.tileFontHeight * line;
	}

	public int setUnicode(int unicode) {
		if (unicode <= 32 && unicode >= 0) {
			this.unicode = unicode;
		} else if (unicode > 32 && unicode <= 62) {
			this.unicode = unicode - 33;
		} else if (unicode > 62) {
			this.unicode = unicode - 65;
		}
		return this.tileFontWidth * this.unicode;
	}

	public int getTileFontWidth() {
		return this.tileFontWidth;
	}

	public int getTileFontHeight() {
		return this.tileFontHeight;
	}

}
