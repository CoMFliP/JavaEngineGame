package com.comflip.game.abstracts;

import java.util.ArrayList;

import com.comflip.game.LoaderManager;
import com.comflip.game.gui.Cursor;

public abstract class GUI extends LoaderManager{
	public static final Cursor cursor = new Cursor("/point.png");
	
	public static ArrayList<GUI> ArrayList() {
		ArrayList<GUI> list = new ArrayList<GUI>();
		list.add(cursor);
		return list;
	};
}