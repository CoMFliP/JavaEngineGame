package com.comflip.engine;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Font;
import com.comflip.engine.gfc.Sprite;
import com.comflip.engine.gfc.SpriteRequest;
import com.comflip.engine.gfc.SpriteTile;

public class Renderer {
	private Font font = Font.STANDARD;

	private ArrayList<SpriteRequest> spriteRequest = new ArrayList<SpriteRequest>();

	private int pixelWidth, pixelHeight;
	private int[] pixel;
	private int[] zbuffer;

	private int zDepth = 0;
	private boolean processing = false;

	private int newX, newY, newWidth, newHeight;

	public Renderer(GameContainer gc) {
		pixelWidth = gc.getWidth();
		pixelHeight = gc.getHeigth();

		pixel = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		zbuffer = new int[pixel.length];
	}

	public void clear() {
		for (int i = 0; i < pixel.length; i++) {
			pixel[i] = 0;
			zbuffer[i] = 0;
		}
	}

	public void process() {
		processing = true;

		Collections.sort(spriteRequest, new Comparator<SpriteRequest>() {
			public int compare(SpriteRequest i0, SpriteRequest i1) {
				if (i0.zDepth < i1.zDepth) {
					return -1;
				} else if (i0.zDepth > i1.zDepth) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		for (int i = 0; i < spriteRequest.size(); i++) {
			SpriteRequest sr = spriteRequest.get(i);
			setzDepth(sr.zDepth);
			sr.sprite.setAlpha(false);
			drawSprite(sr.sprite, sr.offX, sr.offY);
		}
		spriteRequest.clear();
		processing = false;
	}

	private void insideWindowRender(int offX, int offY, int widht, int height) {
		newX = 0;
		newY = 0;
		newWidth = widht;
		newHeight = height;

		// Don't render code
		if (offX < -widht) {
			return;
		}
		if (offY < -height) {
			return;
		}
		if (offX >= pixelWidth) {
			return;
		}
		if (offY >= pixelHeight) {
			return;
		}

		// Clipping code
		if (offX < 0) {
			newX -= offX;
		}
		if (offY < 0) {
			newY -= offY;
		}
		if (newWidth + offX > pixelWidth) {
			newWidth -= newWidth + offX - pixelWidth;
		}
		if (newHeight + offY > pixelHeight) {
			newHeight -= newHeight + offY - pixelHeight;
		}
	}

	public void setPixel(int x, int y, int value) {
		int alpha = (value >> 24) & 0xFF;
		int index = x + y * pixelWidth;

		if ((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || alpha == 0) {
			return;
		}

		if (zbuffer[index] > zDepth) {
			return;
		}

		zbuffer[index] = zDepth;

		if (alpha == 255) {
			pixel[index] = value;
		} else {
			int pixelColor = pixel[index];

			int pixelColorRed = ((pixelColor >> 16) & 0xFF);
			int pixelColorGreen = ((pixelColor >> 8) & 0xFF);
			int pixelColorBlue = (pixelColor & 0xFF);

			int newRed = pixelColorRed - (int) ((pixelColorRed - ((value >> 16) & 0xFF)) * (alpha / 255f));
			int newGreen = pixelColorGreen - (int) ((pixelColorGreen - ((value >> 8) & 0xFF)) * (alpha / 255f));
			int newBlue = pixelColorBlue - (int) ((pixelColorBlue - (value & 0xFF)) * (alpha / 255f));

			pixel[index] = (255 << 24 | newRed << 16 | newGreen << 8 | newBlue);
		}
	}

	public GameObject drawText(String text, int offX, int offY, int color) {
		insideWindowRender(offX, offY, font.getTileFontWidth(), font.getTileFontHeight());
		int offset = 0;
		int[] fontPixel = new int[font.getTileFontWidth() * font.getTileFontHeight()];

		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;

			fontPixel = font.getMapFont().get(unicode);

			for (int y = 0; y < font.getTileFontHeight(); y++) {
				for (int x = 0; x < font.getTileFontWidth(); x++) {
					if (fontPixel != null) {
						if (fontPixel[x + y * font.getTileFontWidth()] == Color.WHITE) {
							int newY = y + offY;
							int newX = x + offX + offset;
							setPixel(newX, newY, color);
						}
					}
				}
			}
			offset += font.getTileFontWidth() - 4;
		}
		return new GameObject(font, offset, font.getTileFontHeight());
	}

	public GameObject drawSprite(Sprite sprite, int offX, int offY) {
		insideWindowRender(offX, offY, sprite.getWidth(), sprite.getHeight());

		if (sprite.isAlpha() && !processing) {
			spriteRequest.add(new SpriteRequest(sprite, zDepth, offX, offY));
			return sprite;
		}

		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				setPixel(x + offX, y + offY, sprite.getPixel()[x + y * sprite.getWidth()]);
			}
		}
		return new GameObject(sprite, sprite.getWidth(), sprite.getWidth());
	}

	public GameObject drawSpriteTile(SpriteTile spriteTile, int offX, int offY, int tileX, int tileY) {
		insideWindowRender(offX, offY, spriteTile.getTileWidth(), spriteTile.getTileHeight());

		if (spriteTile.isAlpha() && !processing) {
			spriteRequest.add(new SpriteRequest(spriteTile.getTileSprite(tileX, tileY), zDepth, offX, offY));
			return spriteTile;
		}

		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				setPixel(x + offX, y + offY, spriteTile.getPixel()[(x + tileX * spriteTile.getTileWidth())
						+ (y + tileY * spriteTile.getTileHeight()) * spriteTile.getWidth()]);
			}
		}
		return new GameObject(spriteTile, spriteTile.getTileWidth(), spriteTile.getTileHeight());
	}

	public GameObject drawRect(int offX, int offY, int width, int height, int color) {
		insideWindowRender(offX, offY, width, height);

		for (int y = newY; y <= newHeight; y++) {
			setPixel(offX, y + offY, color);
			setPixel(offX + width, y + offY, color);
		}
		for (int x = newX; x <= newWidth; x++) {
			setPixel(x + offX, offY, color);
			setPixel(x + offX, offY + height, color);
		}
		return new GameObject(new GameObject(null), newWidth, newHeight);
	}

	public GameObject drawFillRect(int offX, int offY, int width, int height, int color) {
		insideWindowRender(offX, offY, width, height);

		for (int y = newY; y <= newHeight; y++) {
			for (int x = newX; x <= newWidth; x++) {
				setPixel(x + offX, y + offY, color);
			}
		}
		return new GameObject(new GameObject(null), newWidth, newHeight);
	}

	public int getzDepth() {
		return this.zDepth;
	}

	public void setzDepth(int zDepth) {
		this.zDepth = zDepth;
	}
}
