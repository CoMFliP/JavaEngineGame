package com.comflip.game.lists;

import com.comflip.game.LoaderManager;
import com.comflip.game.lists.layer.Game;
import com.comflip.game.lists.layer.Menu;

public abstract class Layer extends LoaderManager {
	public boolean isActive;
	public static final Menu MENU = new Menu();
	public static final Game GAME = new Game("");

	public boolean isActive() {
		return this.isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
