package com.comflip.game.lists;

import com.comflip.game.LoaderManager;
import com.comflip.game.lists.sfx.CollisionSFX;

public abstract class SFX extends LoaderManager{
	public static final CollisionSFX COLLISION_SFX = new CollisionSFX("/audio/oh-shit-iam-sorry.wav");	
	}
