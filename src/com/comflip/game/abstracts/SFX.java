package com.comflip.game.abstracts;

import java.util.ArrayList;

import com.comflip.game.LoaderManager;
import com.comflip.game.sfx.CollisionSFX;

public abstract class SFX extends LoaderManager{
	public static final CollisionSFX collisionSFX = new CollisionSFX("/audio/oh-shit-iam-sorry.wav");	
	
	public static ArrayList<SFX> ArrayList() {
		ArrayList<SFX> list = new ArrayList<SFX>();
		list.add(collisionSFX);
		return list;
	};
}
