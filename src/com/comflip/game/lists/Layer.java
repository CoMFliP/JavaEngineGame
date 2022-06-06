package com.comflip.game.lists;

import com.comflip.engine.IO;
import com.comflip.game.lists.layer.*;

public interface Layer extends IO {
    Menu MENU = new Menu();
    Game GAME = new Game();
    Login LOGIN = new Login();
    SignUp SIGN_IN = new SignUp();
    Lobby LOBBY = new Lobby();

    boolean isActive();

    void setActive(boolean isActive);
}
