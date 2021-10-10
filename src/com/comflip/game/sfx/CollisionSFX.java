package com.comflip.game.sfx;

import com.comflip.engine.audio.SoundClip;
import com.comflip.game.abstracts.SFX;

public class CollisionSFX extends SFX {

	public CollisionSFX(String path) {
		soundClip = new SoundClip(path);
	}
}
