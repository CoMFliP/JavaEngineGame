package com.comflip.game;

import com.comflip.engine.GameContainer;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new LoaderManager());
		gc.setIconImage("/icon.png");
		gc.setTitle("The Checkers");
		gc.setWidth(640);
		gc.setHeigth(360);
		gc.setScale(2f);
		gc.start();
	}
}
