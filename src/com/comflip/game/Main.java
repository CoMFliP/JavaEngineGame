package com.comflip.game;

import com.comflip.engine.GameContainer;

public class Main {
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new LoaderManager());
		gc.setTitle("Java Engine 2D");
		gc.setWidth(640);
		gc.setHeigth(360);
		gc.setScale(2f);
		gc.start();
	}
}
