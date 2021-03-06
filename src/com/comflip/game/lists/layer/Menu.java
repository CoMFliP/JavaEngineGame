package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.engine.net.ClientSession;
import com.comflip.engine.net.ClientSocketTCP;
import com.comflip.engine.net.MatchSession;
import com.comflip.game.LoaderManager;
import com.comflip.game.lists.GUI;
import com.comflip.game.lists.Layer;
import com.comflip.game.lists.gui.Button;

import java.util.ArrayList;

public class Menu extends LoaderManager implements Layer {
    ArrayList<GUI> listGUI = new ArrayList<>();

    public Menu() {
        this.sprite = new Sprite("/menu.png");
        for (int i = 0; i < 3; i++) {
            Button button = new Button();
            button.setTag("button_" + i);
            listGUI.add(button);
        }
    }

    public void update(GameContainer gc, float dt) {
        this.widthWindow = gc.getWidth();
        this.heightWindow = gc.getHeigth();

        for (GUI elementGui : listGUI) {
            switch (elementGui.getTag()) {
                case "button_0" -> {
                    Button button = (Button) elementGui;
                    button.setWidth(widthWindow / 3);
                    button.setHeight(25);

                    button.setPosX(button.getWidth());
                    button.setPosY(heightWindow / 2 - 30);
                    button.setText("Create match");

                    if (button.isExecute()) {
                        try {
                            clientSocketTCP.startConnection("127.0.0.1", 5555);
                            String rep = clientSocketTCP.sendMessage("create-match=" + ClientSession.getUsername());
                            clientSocketTCP.stopConnection();

                            MatchSession.setIdMatch(ClientSocketTCP.decodeResponse(rep).get("idMatch"));
                            MatchSession.setHost(ClientSession.getUsername());

                            Layer.MENU.setActive(false);
                            Layer.GAME.setActive(true);
                        } catch (Exception ignored) {
                        }
                    }
                }

                case "button_1" -> {
                    Button button = (Button) elementGui;
                    button.setWidth(widthWindow / 3);
                    button.setHeight(25);

                    button.setPosX(button.getWidth());
                    button.setPosY(heightWindow / 2);
                    button.setText("Lobby");

                    if (button.isExecute()){
                        Layer.MENU.setActive(false);
                        Layer.LOBBY.setActive(true);
                    }
                }
                case "button_2" -> {
                    Button button = (Button) elementGui;
                    button.setWidth(widthWindow / 3);
                    button.setHeight(25);

                    button.setPosX(button.getWidth());
                    button.setText("Exit");
                    button.setPosY(heightWindow / 2 + 30);

                    if (button.isExecute()) {
                        try {
                            clientSocketTCP.startConnection("127.0.0.1", 5555);
                            clientSocketTCP.sendMessage("logout=" + ClientSession.getUsername());
                            clientSocketTCP.stopConnection();

                            ClientSession.setSession("null:null");

                            Layer.MENU.setActive(false);
                            Layer.LOGIN.setActive(true);
                        } catch (Exception ignored) {
                        }
                    }
                }
                default -> {
                }
            }
            elementGui.update(gc, dt);
        }
    }

    public void render(Renderer r) {
        r.drawSprite(sprite, 0, 0);

        for (GUI elementGui : listGUI) {
            elementGui.render(r);
        }

        GameObject welcomeText = r.drawText("Welcome, " + ClientSession.getUsername(), 0, 0, 0);
        r.drawText("Welcome, " + ClientSession.getUsername(), (widthWindow - welcomeText.getWidth()) / 2, heightWindow / 6 * 5, Color.WHITE);
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
