package com.comflip.game;

import com.comflip.engine.GameContainer;

public class Main {
	private static String[] argsMain;
	
	public static void main(String[] args) {		
		argsMain = args;
		GameContainer gc = new GameContainer(new LoaderManager());
		gc.setTitle("Java Engine 2D");
		gc.setWidth(640);
		gc.setHeigth(360);
		gc.setScale(2f);
		gc.start();
	}

	public static String[] getArgs() {
		return argsMain;
	}
}
