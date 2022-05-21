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

    private int FPS;

    ArrayList<Layer> listLayers = new ArrayList<>();
    protected boolean isActive = false;

    public LoaderManager() {
        listLayers.add(Layer.MENU);
        listLayers.add(Layer.GAME);
        listLayers.add(Layer.SELECT_NAME);

        try {
            Layer.MENU.setActive(true);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void update(GameContainer gc, float dt) {
        for (Layer layer : listLayers) {
            if (layer.isActive()) {
                layer.update(gc, dt);
            }
        }

        GUI.CURSOR.update(gc, dt);
    }

    @Override
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
