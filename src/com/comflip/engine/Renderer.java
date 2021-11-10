package com.comflip.engine;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.comflip.engine.gfc.Font;
import com.comflip.engine.gfc.Image;
import com.comflip.engine.gfc.ImageRequest;
import com.comflip.engine.gfc.ImageTile;

public class Renderer {

	private Font font = Font.STANDARD;
	private ArrayList<ImageRequest> imageRequest = new ArrayList<ImageRequest>();
	
	private int pW, pH;
	private int[] p;
	private int[] zbuffer;

	private int zDepth = 0;
	private boolean processing = false;

	public Renderer(GameContainer gc) {
		pW = gc.getWidth();
		pH = gc.getHeigth();

		p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		zbuffer = new int[p.length];
	}

	public void clear() {
		for (int i = 0; i < p.length; i++) {
			p[i] = 0;
			zbuffer[i] = 0;
		}
	}
	
	public void process() {
		processing = true;
		
		Collections.sort(imageRequest, new Comparator<ImageRequest>() {

			public int compare(ImageRequest i0, ImageRequest i1) {
				if(i0.zDepth < i1.zDepth) {
					return -1;					
				} else if (i0.zDepth > i1.zDepth) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		for(int i = 0; i < imageRequest.size(); i++) {
			ImageRequest ir = imageRequest.get(i);
			setzDepth(ir.zDepth);
			ir.image.setAlpha(false);
			drawImage(ir.image, ir.offX, ir.offY);
		}
		imageRequest.clear();
		processing = false;
	}

	public void setPixel(int x, int y, int value) {
		int alpha = (value >> 24) & 0xFF;

		if ((x < 0 || x >= pW || y < 0 || y >= pH) || alpha == 0) {
			return;
		}

		int index = x + y * pW;
		
		if (zbuffer[index] > zDepth) {
			return;
		}
		
		zbuffer[index] = zDepth;

		if (alpha == 255) {
			p[index] = value;
		} else {
			int pixelColor = p[index];
			
			int pixelColorRed = ((pixelColor >> 16) & 0xFF);
			int pixelColorGreen = ((pixelColor >> 8) & 0xFF);
			int pixelColorBlue = (pixelColor & 0xFF);			
			
			int newRed = pixelColorRed - (int)((pixelColorRed - ((value >> 16) & 0xFF)) * (alpha / 255f));
			int newGreen = pixelColorGreen - (int)((pixelColorGreen - ((value >> 8) & 0xFF)) * (alpha / 255f));
			int newBlue = pixelColorBlue - (int)((pixelColorBlue - (value & 0xFF)) * (alpha / 255f));
			
			p[index] = (255 << 24 | newRed << 16 | newGreen << 8 | newBlue);
		}
	}

	public void drawText(String text, int offX, int offY, int color) {
		int offset = 0;
		text = text.toUpperCase();

		for (int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i) - 32;
			for (int y = 0; y < font.getFontImage().getH(); y++) {
				for (int x = 0; x < font.getWidths()[unicode]; x++) {
					if (font.getFontImage().getP()[(x + font.getOffsets()[unicode])
							+ y * font.getFontImage().getW()] == 0xffffffff) {
						setPixel(x + offX + offset, y + offY, color);
					}
				}
			}
			offset += font.getWidths()[unicode];
		}
	}

	public void drawImage(Image image, int offX, int offY) {
		if(image.isAlpha() && !processing) {
			imageRequest.add(new ImageRequest(image, zDepth, offX, offY));
			return;
		}
		
		// Don't render code
		if (offX < -image.getW())
			return;
		if (offY < -image.getH())
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = image.getW();
		int newHeight = image.getH();

		// Clipping code
		if (offX < 0) {
			newX -= offX;
		}
		if (offY < 0) {
			newY -= offY;
		}
		if (newWidth + offX > pW) {
			newWidth -= newWidth + offX - pW;
		}
		if (newHeight + offY > pH) {
			newHeight -= newHeight + offY - pH;
		}

		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				setPixel(x + offX, y + offY, image.getP()[x + y * image.getW()]);
			}
		}
	}

	public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
		if(image.isAlpha() && !processing) {
			imageRequest.add(new ImageRequest(image.getTileImage(tileX, tileY), zDepth, offX, offY));
			return;
		}
		// Don't render code
		if (offX < -image.getTileW())
			return;
		if (offY < -image.getTileH())
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = image.getTileW();
		int newHeight = image.getTileH();

		// Clipping code
		if (offX < 0) {
			newX -= offX;
		}
		if (offY < 0) {
			newY -= offY;
		}
		if (newWidth + offX > pW) {
			newWidth -= newWidth + offX - pW;
		}
		if (newHeight + offY > pH) {
			newHeight -= newHeight + offY - pH;
		}

		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				setPixel(x + offX, y + offY,
						image.getP()[(x + tileX * image.getTileW()) + (y + tileY * image.getTileH()) * image.getW()]);
			}
		}
	}

	public void drawRect(int offX, int offY, int width, int height, int color) {
		// Don't render code
		if (offX < -width)
			return;
		if (offY < -height)
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = width;
		int newHeight = height;

		// Clipping code
		if (offX < 0) {
			newX -= offX;
		}
		if (offY < 0) {
			newY -= offY;
		}
		if (newWidth + offX > pW) {
			newWidth -= newWidth + offX - pW;
		}
		if (newHeight + offY > pH) {
			newHeight -= newHeight + offY - pH;
		}

		for (int y = newY; y <= newHeight; y++) {
			setPixel(offX, y + offY, color);
			setPixel(offX + width, y + offY, color);
		}
		for (int x = newX; x <= newWidth; x++) {
			setPixel(x + offX, offY, color);
			setPixel(x + offX, offY + height, color);
		}
	}

	public void drawFillRect(int offX, int offY, int width, int height, int color) {
		// Don't render code
		if (offX < -width)
			return;
		if (offY < -height)
			return;
		if (offX >= pW)
			return;
		if (offY >= pH)
			return;

		int newX = 0;
		int newY = 0;
		int newWidth = width;
		int newHeight = height;

		// Clipping code
		if (offX < 0) {
			newX -= offX;
		}
		if (offY < 0) {
			newY -= offY;
		}
		if (newWidth + offX > pW) {
			newWidth -= newWidth + offX - pW;
		}
		if (newHeight + offY > pH) {
			newHeight -= newHeight + offY - pH;
		}

		for (int y = newY; y < newHeight; y++) {
			for (int x = newX; x < newWidth; x++) {
				setPixel(x + offX, y + offY, color);
			}
		}

	}

	public int getzDepth() {
		return this.zDepth;
	}

	public void setzDepth(int zDepth) {
		this.zDepth = zDepth;
	}
}
