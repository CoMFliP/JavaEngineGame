package com.comflip.engine;

import com.comflip.game.LoaderManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameContainer implements Runnable {

	private Window window;
	private Renderer renderer;
	private Input input;

	private final LoaderManager loaderManager;

	private int width, heigth;
	private float scale;
	private String title;
	private ImageIcon imageIcon;
	
	public GameContainer(LoaderManager loaderManager) {
		this.loaderManager = loaderManager;
	}

	public void start() {
		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);

		Thread thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		System.exit(0);
	}

	public void run() {
		boolean running = true;

		boolean render;
		double firstTime;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime;
		double unprocessedTime = 0;

		double frameTime = 0;
		int frames = 0;
		int fps = 0;

		while (running) {
			render = false; // Lock to 60 FPS

			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;

			unprocessedTime += passedTime;
			frameTime += passedTime;

			double UPDATE = 1.0 / 60.0;
			while (unprocessedTime >= UPDATE) {
				unprocessedTime -= UPDATE;
				render = true;

				loaderManager.update(this, (float) UPDATE);

				input.update();

				if (frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
				}
			}

			if (render) {
				renderer.clear();
				loaderManager.render(renderer);
				loaderManager.setFPS(fps);
				renderer.process();
				window.update();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		dispose();
	}

	public void dispose() {

	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return this.heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public float getScale() {
		return this.scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Window getWindow() {
		return this.window;
	}

	public Input getInput() {
		return this.input;
	}

	public void setIconImage(String path) {
		 this.imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
	}

	public Image getIconImage() {
		return this.imageIcon.getImage();
	}

}
