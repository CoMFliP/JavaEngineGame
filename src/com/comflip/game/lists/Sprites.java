package com.comflip.game.lists;

import com.comflip.game.LoaderManager;
import com.comflip.game.sprites.Arrow;
import com.comflip.game.sprites.Wheel;
import com.comflip.game.sprites.WhiteChecker;

public abstract class Sprites extends LoaderManager{	
	public static final Wheel wheel = new Wheel("/circle.png", 37, 35);
	public static final Arrow arrow = new Arrow("/arrow.png", 37, 35);
	
	public static final WhiteChecker WHITE_CHECKER = new WhiteChecker("/checker/white.png", "");
}
