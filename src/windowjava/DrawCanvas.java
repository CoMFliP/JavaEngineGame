package windowjava;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import java.awt.event.*;

public class DrawCanvas extends JPanel {
	int width;
	int height;

	private Timer timer;
	private int drawY = 5;
	private int drawX = 15;
	private int drawDeltaY;
	private int drawDeltaX;
	private int stepY = 5;
	private int stepX = 15;

	private int KeyEvent;

	public DrawCanvas(int w, int h) {
		width = w;
		height = h;

		timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (KeyEvent) {
					case 68:
						drawX += drawDeltaX;
						if (drawX >= stepX && drawDeltaX > 0) {
							stepX = drawX;
							timer.stop();
						} else if (drawX > width - 85) {
							stepX = drawX = width - 85;
							timer.stop();
						} else if (stepX > width - 85) {
							stepX = width - 85;
						}
						break;
					case 65:
						drawX += drawDeltaX;
						if (drawX <= stepX && drawDeltaX < 0) {
							stepX = drawX;
							timer.stop();
						} else if (drawX < 15) {
							stepX = drawX = 15;
							timer.stop();
						} else if (stepX < 15) {
							stepX = 15;
						}
						break;
					case 83:
						drawY += drawDeltaY;
						if (drawY >= stepY && drawDeltaY > 0) {
							stepY = drawY;
							timer.stop();
						} else if (drawY > height - 100) {
							stepY = drawY = height - 95;
							timer.stop();
						} else if (stepY > height - 100) {
							stepY = height - 95;
						}
						break;
					case 87:
						drawY += drawDeltaY;
						if (drawY <= stepY && drawDeltaY < 0) {
							stepY = drawY;
							timer.stop();
						} else if (drawY < 10) {
							stepY = drawY = 5;
							timer.stop();
						} else if (stepY < 10) {
							stepY = 5;
						}
						break;
					default:
						break;
				}

				System.out.println("POS: Y = " + drawY + " | X = " + drawX + " \\ KeyEvent: " + KeyEvent
						+ " \\ StepY = " + stepY + " | StepX = " + stepX);

				repaint();
			}
		});
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		Rectangle2D.Double r = new Rectangle2D.Double(0, 0, width, height);
		g2D.setColor(new Color(100, 149, 237));
		g2D.fill(r);

		Rectangle2D.Double r2 = new Rectangle2D.Double(15, 5, width - 50, height - 50);
		g2D.setColor(new Color(100, 100, 250));
		g2D.fill(r2);

		for (int iy = 0; iy < (height - 10) / 50; iy++) {
			for (int ix = 0; ix < (width - 30) / 50; ix++) {
				g2D.setPaint(Color.BLACK);
				g2D.drawRect(15 + 50 * ix, 5 + 50 * iy, 50, 50);
			}
		}

		Ellipse2D.Double e = new Ellipse2D.Double(drawX, drawY, 50, 50);
		g2D.setColor(new Color(150, 150, 150));
		g2D.fill(e);

		g2D.setPaint(Color.BLACK);
		g2D.drawOval(drawX, drawY, 50, 50);
	}

	public void moveUp(int KeyEvent) {
		stepY -= 50;
		timer.stop();
		this.KeyEvent = KeyEvent;
		drawDeltaY = -5;
		timer.start();
		if (timer.isRunning() == false) {
			System.out.println(drawY);
		}
	}

	public void moveDown(int KeyEvent) {
		stepY += 50;
		timer.stop();
		this.KeyEvent = KeyEvent;
		drawDeltaY = 5;
		timer.start();
		if (timer.isRunning() == false) {
			System.out.println(drawY);
		}
	}

	public void moveLeft(int KeyEvent) {
		stepX -= 50;
		timer.stop();
		this.KeyEvent = KeyEvent;
		drawDeltaX = -5;
		timer.start();
	}

	public void moveRight(int KeyEvent) {
		stepX += 50;
		timer.stop();
		this.KeyEvent = KeyEvent;
		drawDeltaX = 5;
		timer.start();
	}
}
