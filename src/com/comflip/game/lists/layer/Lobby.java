package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.engine.net.ClientSession;
import com.comflip.engine.net.ClientSocketTCP;
import com.comflip.engine.net.ClientSocketUDP;
import com.comflip.engine.net.MatchSession;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.gui.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Lobby extends LoaderManager implements Layer {
    ArrayList<GUI> listGUI = new ArrayList<>();
    HashMap<String, String> fromTable = new HashMap<>();
    private String rep = "";
    private float timer;

    private final Button buttonBack;
    private String msg = "";


    public Lobby() {
        this.sprite = new Sprite("/menu.png");
        this.buttonBack = new Button();
    }

    @Override
    public void update(GameContainer gc, float dt) {
        this.widthWindow = gc.getWidth();
        this.heightWindow = gc.getHeigth();

        if (timer < 30) {
            timer += dt * 30;
        } else if (timer == 30) {
            try {
                this.loadTable();
            } catch (IOException ignored) {
            }
            timer = 0;
        }

        buttonBack.setWidth(widthWindow / 3);
        buttonBack.setHeight(25);

        buttonBack.setPosX(buttonBack.getWidth());
        buttonBack.setPosY(heightWindow / 10 * 9);

        buttonBack.setTag("button_back");
        buttonBack.setText("Back");

        if (buttonBack.isExecute()) {
            Layer.LOBBY.setActive(false);
            Layer.MENU.setActive(true);
        }

        buttonBack.update(gc, dt);

        for (GUI elementGUI : listGUI) {
            if (elementGUI.getClass().equals(GUI.BUTTON.getClass())) {
                if (((Button) elementGUI).isExecute()) {
                    try {
                        clientSocketTCP.startConnection("127.0.0.1", 5555);
                        String rep = clientSocketTCP.sendMessage("join=" + ClientSession.getUsername() + ":" + elementGUI.getTag());
                        clientSocketTCP.stopConnection();

                        if (ClientSocketTCP.decodeResponse(rep).get("msg").equals("done")) {
                            MatchSession.setIdMatch(elementGUI.getTag());
                            MatchSession.setHost(ClientSocketTCP.decodeResponse(rep).get("userHost"));
                            MatchSession.setGuest(ClientSession.getUsername());
                            Layer.LOBBY.setActive(false);
                            Layer.GAME.setActive(true);
                        }

                    } catch (Exception ignored) {
                    }
                }
            }

            elementGUI.update(gc, dt);
        }
    }

    @Override
    public void render(Renderer r) {
        r.drawSprite(this.sprite, 0, 0);
        r.drawFillRect(0, 0, this.widthWindow, this.heightWindow, 0x88000000);

        buttonBack.render(r);

        GameObject textMsg = r.drawText(msg, 0, 0, 0);
        r.drawText(msg, (this.widthWindow - textMsg.getWidth()) / 2, this.heightWindow / 2, Color.WHITE);

        for (GUI elementGUI : listGUI) {
            elementGUI.render(r);
        }
    }

    private void loadTable() throws IOException {
        clientSocketUDP.startConnection("127.0.0.1", 5556);
        rep = clientSocketUDP.sendMessage("lobby=");
        clientSocketUDP.stopConnection();

        fromTable = ClientSocketUDP.decodeResponse(rep);

        listGUI.clear();
        if (fromTable.get("msg") == null) {
            msg = "";
            int startOffY = 15;
            for (String line : fromTable.keySet()) {
                Button matchButton = new Button();
                startOffY += 30;

                matchButton.setWidth(widthWindow / 3);
                matchButton.setHeight(25);

                matchButton.setPosX(matchButton.getWidth());
                matchButton.setPosY(startOffY);

                matchButton.setTag(fromTable.get(line).split(":")[0]);
                matchButton.setText("Match ID: " + fromTable.get(line).split(":")[0] + " | Host: " + fromTable.get(line).split(":")[1]);

                listGUI.add(matchButton);
            }
        } else {
            msg = fromTable.get("msg");
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
