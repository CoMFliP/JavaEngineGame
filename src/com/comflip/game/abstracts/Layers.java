package com.comflip.game.abstracts;

import java.util.ArrayList;

import com.comflip.game.LoaderManager;
import com.comflip.game.Menu;

public abstract class Layers extends LoaderManager {
	public static final Menu menu = new Menu();
	
	public static ArrayList<Layers> ArrayList() {
		ArrayList<Layers> list = new ArrayList<Layers>();
		list.add(menu);
		return list;
	}
}
