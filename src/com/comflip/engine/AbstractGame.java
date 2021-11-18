package com.comflip.engine;

public interface AbstractGame {
	public void update(GameContainer gameContainer, float dt);
	public GameObject render(Renderer renderer);
}
