package com.comflip.engine;

import com.comflip.engine.audio.SoundClip;
import com.comflip.engine.gfc.Sprite;

public interface IO {
     void update(GameContainer gc, float dt);
     void render(Renderer renderer);
}
