package com.comflip.game.lists;

import com.comflip.engine.GameContainer;
import com.comflip.engine.IO;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.layer.*;

public interface Layer extends IO {
    Menu MENU = new Menu();
    Game GAME = new Game();
    SelectName SELECT_NAME = new SelectName();
    Login LOGIN = new Login();
    Register REGISTER = new Register();

    boolean isActive();

    void setActive(boolean isActive);
}
