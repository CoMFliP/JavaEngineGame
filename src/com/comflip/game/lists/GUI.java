package com.comflip.game.lists;

import com.comflip.game.LoaderManager;
import com.comflip.game.lists.gui.Button;
import com.comflip.game.lists.gui.Cursor;
import com.comflip.game.lists.gui.MapBoard;

public abstract class GUI extends LoaderManager {
	public static final Cursor CURSOR = new Cursor("/cursor.png");
	public static final MapBoard MAP_BOARD = new MapBoard();
	public static Button button;
}