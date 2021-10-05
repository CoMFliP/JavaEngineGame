package com.comflip.game;

import java.awt.event.KeyEvent;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.audio.SoundClip;
import com.comflip.game.gui.Gui;

public class LoaderManager {
	private ListObjects objects;
	private Gui gui;
	
	private SoundClip clip;

	public LoaderManager() {
		objects = new ListObjects();
		gui = new Gui();
		
		
		clip = new SoundClip("/audio/1.wav");
	}

	public void update(GameContainer gc, float dt) {
		Input input = gc.getInput();

		objects.updateInput(input, dt);
		gui.updateInput(input, dt);


		if (input.isKeyDown(KeyEvent.VK_A)) {
			clip.setVolume(-20);
			clip.play();
		}
	}

	public void render(Renderer r) {
		objects.render(r);
		gui.render(r);
	}
}
