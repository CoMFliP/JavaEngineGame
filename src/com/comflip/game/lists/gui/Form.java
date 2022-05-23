package com.comflip.game.lists.gui;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;

public class Form extends LoaderManager implements GUI {
    private String content;
    private boolean isFocused;


    public Form(){

    }

    public void update(GameContainer gc, float dt){
        Input input = gc.getInput();

    };

    public void render(Renderer r){

    };
}
