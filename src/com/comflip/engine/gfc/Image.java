package com.comflip.engine.gfc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.comflip.engine.GameObject;

public class Image extends GameObject{
	public Image(String path) {
		super(path);
		
		BufferedImage image = null;

		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		w = image.getWidth();
		h = image.getHeight();
		p = image.getRGB(0, 0, w, h, null, 0, w);

		image.flush();
	}
}
