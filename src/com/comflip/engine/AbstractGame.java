package com.comflip.engine;

public interface AbstractGame {
	abstract void update(GameContainer gameContainer, float dt);
	abstract GameObject render(Renderer renderer);
}
