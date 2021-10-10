package com.comflip.game.abstracts;

import java.util.ArrayList;

import com.comflip.game.LoaderManager;
import com.comflip.game.objects.Arrow;
import com.comflip.game.objects.Wheel;

public abstract class Objects extends LoaderManager{	
	public static final Wheel wheel = new Wheel("/circle.png", 37, 35);
	public static final Arrow arrow = new Arrow("/arrow.png", 37, 35);
	
	public static ArrayList<Objects> ArrayList() {
		ArrayList<Objects> list = new ArrayList<Objects>();
		list.add(Objects.wheel);
		list.add(Objects.arrow);
		return list;
	};
}
