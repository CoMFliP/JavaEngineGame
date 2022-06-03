package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.net.ClientSocket;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;

import java.util.ArrayList;
import java.util.HashMap;


public class Lobby extends LoaderManager implements Layer {
    ArrayList<GUI> listGUI = new ArrayList<>();
    HashMap <String, String> fromTable = new HashMap<>();
    private String rep = "";
    

    public Lobby() {
        
    }

    @Override
    public void update(GameContainer gc, float dt) {
        this.widthWindow = gc.getWidth();
        this.heightWindow = gc.getHeigth();

        try {
            clientSocket.startConnection("127.0.0.1", 5555);
            rep = clientSocket.sendMessage("lobby=");
            clientSocket.stopConnection();
            fromTable = ClientSocket.decodeResponse(rep);
        } catch (Exception ignored) {
        }

    }

    @Override
    public void render(Renderer r) {
        int startOffY = 15;
        for (String line : fromTable.keySet()) {
            startOffY += 15;
            r.drawText(fromTable.get(line),15,startOffY,Color.WHITE);
        }

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
