package com.comflip.engine;

import java.awt.*;
import java.awt.image.*;

import javax.swing.JFrame;

public class Window {

	private JFrame frame;
	private BufferedImage image;
	private Cursor cursor;
	private BufferedImage imageCursor;
	private Canvas canvas;
	private BufferStrategy bs;
	private Graphics g;

	public Window(GameContainer gc) {
		image = new BufferedImage(gc.getWidth(), gc.getHeigth(), BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		Dimension s = new Dimension((int) (gc.getWidth() * gc.getScale()), (int) (gc.getHeigth() * gc.getScale()));
		canvas.setPreferredSize(s);
		canvas.setMaximumSize(s);
		canvas.setMinimumSize(s);

		frame = new JFrame(gc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		imageCursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(imageCursor, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(cursor);

		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
	}

	public void update() {
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bs.show();
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	public JFrame getFrame() {
		return this.frame;
	}
}
