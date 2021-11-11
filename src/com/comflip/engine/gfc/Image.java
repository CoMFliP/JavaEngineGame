package com.comflip.engine.gfc;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.comflip.engine.GameObject;

public class Image extends GameObject {
	public Image(String path) {
		super(path);

		BufferedImage image = null;

		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
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

		w = image.getWidth();
		h = image.getHeight();
		p = image.getRGB(0, 0, w, h, null, 0, w);

		image.flush();
	}
}
