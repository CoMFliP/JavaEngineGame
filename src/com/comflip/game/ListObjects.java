package com.comflip.game;

import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.game.abstracts.AnchorObject;
import com.comflip.game.object.Arrow;
import com.comflip.game.object.Wheel;

public class ListObjects extends AnchorObject {
	public static final Wheel wheel = new Wheel();
	public static final Arrow arrow = new Arrow();
	
	public void updateInput(Input input, float dt) {
		wheel.updateInput(input, dt);
		arrow.updateInput(input, dt);
	}

	public void render(Renderer r) {
		wheel.render(r);
		arrow.render(r);
	}
}
