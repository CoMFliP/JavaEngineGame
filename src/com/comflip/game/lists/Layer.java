package com.comflip.game.lists;

import com.comflip.engine.GameContainer;
import com.comflip.engine.IO;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.layer.Game;
import com.comflip.game.lists.layer.Login;
import com.comflip.game.lists.layer.Menu;
import com.comflip.game.lists.layer.SelectName;

public interface Layer extends IO {
    Menu MENU = new Menu();
    Game GAME = new Game();
    SelectName SELECT_NAME = new SelectName();
    Login LOGIN = new Login();

    boolean isActive();

    void setActive(boolean isActive);
}
