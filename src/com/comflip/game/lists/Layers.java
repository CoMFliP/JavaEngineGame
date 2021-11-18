package com.comflip.game.lists;

import com.comflip.game.LoaderManager;
import com.comflip.game.layers.Game;
import com.comflip.game.layers.Menu;

public abstract class Layers extends LoaderManager {
	public static final Menu menu = new Menu();
	public static final Game game = new Game("");
}
