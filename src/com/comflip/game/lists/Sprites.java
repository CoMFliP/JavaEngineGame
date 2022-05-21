package com.comflip.game.lists;

import com.comflip.engine.IO;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.sprite.Checker;

public interface Sprites extends IO {
	 Checker whiteChecker = new Checker("/checker/white_normal.png");
	 Checker blackChecker = new Checker("/checker/black_normal.png");
}
