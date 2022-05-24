package com.comflip.game.lists;

import com.comflip.engine.IO;
import com.comflip.game.lists.gui.Button;
import com.comflip.game.lists.gui.Cursor;
import com.comflip.game.lists.gui.Form;
import com.comflip.game.lists.gui.MapBoard;

public interface GUI extends IO {
    Cursor CURSOR = new Cursor();
    MapBoard MAP_BOARD = new MapBoard();
    Button BUTTON = new Button();

    Form FORM = new Form();

    String getTag();
    void setTag(String tag);
}