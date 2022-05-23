package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.Sprites;

import java.util.Arrays;

public class Login extends LoaderManager implements Layer, Sprites {

    public Login() {
        this.sprite = new Sprite("/menu.png");
    }

    @Override
    public void update(GameContainer gc, float dt) {
        this.widthWindow = gc.getWidth();
        this.heightWindow = gc.getHeigth();

        Input input = gc.getInput();

        System.out.println(input.getKeyCode());
    }

    @Override
    public void render(Renderer r) {
        r.drawSprite(this.sprite, 0, 0);
        r.drawText("Hi", this.widthWindow / 2, this.heightWindow / 2, Color.BLACK);
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
