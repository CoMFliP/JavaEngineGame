package com.comflip.game.lists;

import com.comflip.game.LoaderManager;
import com.comflip.game.lists.sfx.KillChecker;
import com.comflip.game.lists.sfx.PickUpChecker;

public abstract class SFX extends LoaderManager {
	public static final PickUpChecker PICK_UP_CHECKER = new PickUpChecker("/audio/pick_up.wav");
	public static final KillChecker KILL_CHECKER = new KillChecker("/audio/kill_checker.wav");

	public void play() {
		this.soundClip.play();
	}
	
	public void close() {
		this.soundClip.close();
	}
	
	public void setVolume(float value) {
		this.soundClip.setVolume(value);
	}
	
	public boolean isRunning() {
		return soundClip.isRunning();
	}
}
