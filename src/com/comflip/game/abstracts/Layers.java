package com.comflip.game.abstracts;

import com.comflip.game.Game;
import com.comflip.game.LoaderManager;
import com.comflip.game.Menu;

public abstract class Layers extends LoaderManager {
	public static final Menu menu = new Menu();
	public static final Game game = new Game("");
}
