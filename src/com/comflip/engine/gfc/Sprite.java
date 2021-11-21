package com.comflip.engine.gfc;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.comflip.engine.GameObject;

public class Sprite extends GameObject {
	protected BufferedImage image = null;
	
	protected int[] pixel;
	protected boolean alpha = false;
	protected float scale;

	public Sprite(String path) {
		super(path);

		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": File (" + path + ") is " + e.fillInStackTrace().getCause()
					+ " or not found");

			StackTraceElement[] elements = e.getStackTrace();

			for (int i = 1; i < elements.length; i++) {
				StackTraceElement s = elements[i];
				System.err.println("\tat " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":"
						+ s.getLineNumber() + ")");
			}
		}
		setScale(1);
	}

	public Sprite(int[] pixelTile, int tileWidht, int tileHeight) {
		super(pixelTile, tileWidht, tileHeight);
	}

	public void setScale(float scale) {
		if (scale == 0) {
			scale = 1;
		}

		this.scale = scale;
		
		int scaleWidth = Math.round((image.getWidth() * scale));
		int scaleHeight = Math.round((image.getHeight() * scale));

		BufferedImage resizedImage = new BufferedImage(scaleWidth, scaleHeight,
				BufferedImage.TYPE_INT_ARGB);

		Graphics graphics = resizedImage.createGraphics();
		graphics.drawImage(image, 0, 0, scaleWidth, scaleHeight, null);
		graphics.dispose();

		width = resizedImage.getWidth();
		height = resizedImage.getHeight();

		pixel = resizedImage.getRGB(0, 0, width, height, null, 0, width);
		resizedImage.flush();
	}

	public float getScale() {
		return this.scale;
	}
	
	public int[] getPixel() {
		return this.pixel;
	}
	
	public boolean isAlpha() {
		return this.alpha;
	}

	public void setAlpha(boolean alpha) {
		this.alpha = alpha;
	}
}
