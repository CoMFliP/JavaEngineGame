package com.comflip.game.lists;

import com.comflip.game.LoaderManager;
import com.comflip.game.lists.sprite.Arrow;
import com.comflip.game.lists.sprite.BlackChecker;
import com.comflip.game.lists.sprite.Wheel;
import com.comflip.game.lists.sprite.WhiteChecker;

public abstract class Sprites extends LoaderManager{
	protected boolean pickUp, pickDown;
	
	public static final Wheel wheel = new Wheel("/circle.png", 37, 35);
	public static final Arrow arrow = new Arrow("/arrow.png", 37, 35);

	public static WhiteChecker whiteChecker;
	public static BlackChecker blackChecker;
	
	public boolean isPickUp() {
		return pickUp;
	}
}
