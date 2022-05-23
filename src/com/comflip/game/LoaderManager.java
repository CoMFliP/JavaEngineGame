package com.comflip.game;

import com.comflip.engine.GameContainer;
import com.comflip.engine.IO;
import com.comflip.engine.Renderer;
import com.comflip.engine.audio.SoundClip;
import com.comflip.engine.gfc.Sprite;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;

import java.util.ArrayList;

public class LoaderManager implements IO {
    protected Sprite sprite;
    protected SoundClip soundClip;

    protected String tag = "null";

    protected int posX = 0, posY = 0;
    protected int width = 0, height = 0;
    protected int widthWindow = 0, heightWindow = 0;

    private int FPS;

    ArrayList<Layer> listLayers = new ArrayList<>();
    protected boolean isActive = false;

    public LoaderManager() {
        listLayers.add(Layer.MENU);
        listLayers.add(Layer.GAME);
        listLayers.add(Layer.SELECT_NAME);
        listLayers.add(Layer.LOGIN);
    }

    public void update(GameContainer gc, float dt) {
        Layer.LOGIN.setActive(true);
        for (Layer layer : listLayers) {
            if (layer.isActive()) {
                layer.update(gc, dt);
            }
        }

        GUI.CURSOR.update(gc, dt);
    }

    public void render(Renderer r) {

        for (Layer layer : listLayers) {
            if (layer.isActive()) {
                layer.render(r);
            }
        }

        GUI.CURSOR.render(r);

        r.drawText("FPS: " + FPS, 0, 0, 0xff00ffff);
    }

    public void setFPS(int fps) {
        this.FPS = fps;
    }
}
