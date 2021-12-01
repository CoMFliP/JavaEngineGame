package com.comflip.game.lists;

import com.comflip.game.LoaderManager;
import com.comflip.game.lists.layer.Game;
import com.comflip.game.lists.layer.Menu;
import com.comflip.game.lists.layer.SelectName;

public abstract class Layer extends LoaderManager {
	public boolean isActive;
	public static final Menu MENU = new Menu();
	public static final Game GAME = new Game("");
	public static final SelectName SELECT_NAME = new SelectName();

	public boolean isActive() {
		return this.isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
