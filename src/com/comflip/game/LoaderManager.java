package com.comflip.game;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.IO;
import com.comflip.engine.Renderer;
import com.comflip.engine.audio.SoundClip;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.engine.net.ClientSession;
import com.comflip.engine.net.ClientSocketTCP;
import com.comflip.engine.net.ClientSocketUDP;
import com.comflip.engine.net.MatchSession;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;

import java.io.IOException;
import java.util.ArrayList;

public class LoaderManager implements IO {
    protected Sprite sprite;
    protected SoundClip soundClip;
    protected ClientSocketTCP clientSocketTCP = new ClientSocketTCP();
    protected ClientSocketUDP clientSocketUDP = new ClientSocketUDP();
    protected String tag = "null";

    protected int posX = 0, posY = 0;
    protected int width = 0, height = 0;
    protected int widthWindow = 0, heightWindow = 0;

    private String serverStatus = "offline";

    private int FPS;

    ArrayList<Layer> listLayers = new ArrayList<>();
    protected boolean isActive = false;
    private float timer;


    public LoaderManager() {
        listLayers.add(Layer.LOGIN);
        listLayers.add(Layer.SIGN_IN);
        listLayers.add(Layer.MENU);
        listLayers.add(Layer.GAME);
        listLayers.add(Layer.LOBBY);

        try {
            Layer.LOGIN.setActive(true);
        } catch (Exception ignored) {
        }

    }

    public void update(GameContainer gc, float dt) {
        this.widthWindow = gc.getWidth();
        this.heightWindow = gc.getHeigth();

        for (Layer layer : listLayers) {
            if (layer.isActive()) {
                layer.update(gc, dt);
            }

            if (serverStatus.equals("offline") && !layer.equals(Layer.LOGIN) && !layer.equals(Layer.SIGN_IN)) {
                layer.setActive(false);
                Layer.LOGIN.setActive(true);
            }
        }

        if (timer < 30) {
            timer += dt * 30;
        } else if (timer == 30) {
            try {
                clientSocketUDP.startConnection("127.0.0.1", 5556);
                String rep = clientSocketUDP.sendMessage("isOnline=" + ClientSession.getUsername());
                clientSocketUDP.stopConnection();

                serverStatus = "online";
            } catch (IOException ignored) {
                serverStatus = "offline";
                ClientSession.setSession("null:null");
            }
            timer = 0;
        }

        GUI.CURSOR.update(gc, dt);
    }

    public void render(Renderer r) {
        for (Layer layer : listLayers) {
            if (layer.isActive()) {
                layer.render(r);
            }
        }

        r.drawText("FPS: " + FPS, 0, 0, 0xff00ffff);

        GameObject textStatus = r.drawText("Server is ", 5, heightWindow - 20, Color.WHITE);

        if (serverStatus.equals("offline")) {
            r.drawText(serverStatus, textStatus.getWidth() + 5, heightWindow - 20, Color.FIREBRICK);
        } else if (serverStatus.equals("online")) {
            r.drawText(serverStatus, textStatus.getWidth() + 5, heightWindow - 20, Color.LIMEGREEN);
        }

        GUI.CURSOR.render(r);
    }

    public void setFPS(int fps) {
        this.FPS = fps;
    }
}
