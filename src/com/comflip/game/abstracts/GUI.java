package com.comflip.game.abstracts;

import com.comflip.game.LoaderManager;
import com.comflip.game.gui.Button;
import com.comflip.game.gui.Cursor;

public abstract class GUI extends LoaderManager{
	public static final Cursor cursor = new Cursor("/point.png");
	public static final Button button = new Button("");
}