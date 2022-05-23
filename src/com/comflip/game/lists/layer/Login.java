package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Input;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.Sprites;
import com.comflip.game.lists.gui.Form;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;

public class Login extends LoaderManager implements Layer, Sprites, GUI {
    private Form form;

    public Login() {
        this.sprite = new Sprite("/menu.png");
        this.form = new Form();
    }

    @Override
    public void update(GameContainer gc, float dt) {

        this.widthWindow = gc.getWidth();
        this.heightWindow = gc.getHeigth();

        Input input = gc.getInput();

        form.update(gc, dt);
    }

    @Override
    public void render(Renderer r) {
        r.drawSprite(this.sprite, 0, 0);

        form.setWidth(widthWindow / 3);
        form.setHeight(25);

        form.setPosX(form.getWidth());
        form.setPosY(heightWindow / 2);

        form.setText("Username");
        form.setMessageError("* Too Many Charaters! Max 15");

        form.render(r);
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
