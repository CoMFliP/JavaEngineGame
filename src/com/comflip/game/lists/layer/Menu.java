package com.comflip.game.lists.layer;

import com.comflip.engine.GameContainer;
import com.comflip.engine.GameObject;
import com.comflip.engine.Renderer;
import com.comflip.engine.gfc.Color;
import com.comflip.engine.gfc.Sprite;
import com.comflip.engine.net.ClientSession;
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
            elementGui.update(gc, dt);
            Button button = (Button) elementGui;
            if (button.isExecute()) {
                switch (button.getTag()) {
                    case "button_0" -> {
//                        Layer.GAME.setActive(true);
//                        Layer.SELECT_NAME.setActive(true);
//                        this.isActive = false;
                    }
                    case "button_1" -> {

                    }

                    case "button_2" -> {
//                        System.exit(0);

                    }

                    default -> {
                    }
                }
            }
        }
    }

    public void render(Renderer r) {
        r.drawSprite(sprite, 0, 0);

        for (GUI elementGui : listGUI) {
            Button button = (Button) elementGui;
            button.setWidth(widthWindow / 3);
            button.setHeight(25);

            button.setPosX(button.getWidth());
            button.setPosY(heightWindow / 2);

            switch (button.getTag()) {
                case "button_0" -> button.setText("Create match");
                case "button_1" -> {
                    button.setText("Lobby");
                    button.setPosY(button.getPosY() + 30);
                }
                case "button_2" -> {
                    button.setText("Exit");
                    button.setPosY(button.getPosY() + 60);

                    if (button.isExecute()) {
                        try {
                            clientSoket.startConnection("127.0.0.1", 5555);
                            clientSoket.sendMessage("logout="+ClientSession.getUsername());
                            clientSoket.stopConnection();

                            Layer.MENU.setActive(false);
                            Layer.LOGIN.setActive(true);
                        } catch (Exception ignored) {
                        }
                    }
                }
                default -> {
                }
            }
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
